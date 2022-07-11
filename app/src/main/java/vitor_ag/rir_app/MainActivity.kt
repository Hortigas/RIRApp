package vitor_ag.rir_app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirScreen
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirViewModel
import vitor_ag.rir_app.ui.scanner_page.ScannerScreen
import vitor_ag.rir_app.ui.theme.RIRAppTheme
import vitor_ag.rir_app.util.Routes

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionsState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(
                key1 = lifecycleOwner,
                effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_RESUME) {
                            permissionsState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
            )
            RIRAppTheme {
                val viewModel: AddEditRirViewModel = hiltViewModel()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.ADD_EDIT_RIR
                ) {
                    composable(Routes.ADD_EDIT_RIR) {
                        AddEditRirScreen(
                            viewModel = viewModel,
                            onNavigate = { navController.navigate(it.route) })
                    }
                    composable(Routes.SCANNER_PAGE) {
                        ScannerScreen(
                            viewModel = viewModel,
                            onPopBackStack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}