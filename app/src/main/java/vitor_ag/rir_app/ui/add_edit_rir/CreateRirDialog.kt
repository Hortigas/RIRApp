package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun CreateRirDialog(
    viewModel: AddEditRirViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    if (viewModel.shouldShowCreateRirDialog) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                viewModel.shouldShowCreateRirDialog = false
            },
            title = {
                Text(
                    text = "deseja criar a RIR?",
                    fontSize = 20.sp
                )
            },
            dismissButton = {
                TextButton(onClick = {
                    viewModel.shouldShowCreateRirDialog = false
                }) {
                    Text(text = "Cancelar")
                }
            },
            confirmButton = {
                TextButton(onClick = {

                    viewModel.onEvent(AddEditRirEvent.OnCreateRir(context))
                    viewModel.shouldShowCreateRirDialog = false
                }) {
                    Text(text = "Confirmar")
                }
            },
        )
    }
}