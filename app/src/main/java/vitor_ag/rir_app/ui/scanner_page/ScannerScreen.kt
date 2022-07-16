package vitor_ag.rir_app.ui.scanner_page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirEvent
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirViewModel
import vitor_ag.rir_app.ui.add_edit_rir.compose.CameraScanner
import vitor_ag.rir_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditRirViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { PaddingValues ->
        CameraScanner(
            onScan = { viewModel.onEvent(AddEditRirEvent.OnScanNotaFiscal(it)) },
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues)
        )
    }
}
