package vitor_ag.rir_app.feature_rir.presentation.add_edit_rir.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    drawerState: DrawerState,
    content: @Composable() () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {/*TODO*/ }
    ) {
        content()
    }
}