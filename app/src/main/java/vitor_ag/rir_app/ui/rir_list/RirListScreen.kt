package vitor_ag.rir_app.ui.rir_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import vitor_ag.rir_app.util.UiEvent

@Composable
fun RirListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: RirListViewModel = hiltViewModel()
) {
    val rirs = viewModel.rirs.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {

                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
}