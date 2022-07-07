package vitor_ag.rir_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirScreen
import vitor_ag.rir_app.ui.rir_list.RirListScreen
import vitor_ag.rir_app.ui.theme.RIRAppTheme
import vitor_ag.rir_app.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RIRAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.ADD_EDIT_RIR
                ) {
                    composable(Routes.RIR_LIST) {
                        RirListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(Routes.ADD_EDIT_RIR) {
                        AddEditRirScreen(onPopBackStack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}