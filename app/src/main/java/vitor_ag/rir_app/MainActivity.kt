package vitor_ag.rir_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirScreen
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirViewModel
import vitor_ag.rir_app.ui.scanner_page.ScannerScreen
import vitor_ag.rir_app.ui.theme.RIRAppTheme
import vitor_ag.rir_app.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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