package vitor_ag.rir_app.ui.add_edit_rir.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirEvent
import vitor_ag.rir_app.ui.add_edit_rir.AddEditRirViewModel
import vitor_ag.rir_app.ui.add_edit_rir.compose.dropdown.Dropdown

@Composable
fun PhotoDialog(
    viewModel: AddEditRirViewModel,
    modifier: Modifier = Modifier
) {
    if (viewModel.shouldShowPhotoDialog)
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                viewModel.onEvent(AddEditRirEvent.OnPhotoCategoryConfirm(viewModel.selectedPhotoCategory))
                viewModel.shouldShowPhotoDialog = false
            },
            title = {
                Text(
                    text = "Categoria da foto:",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp
                )
            },
            text = {
                Dropdown(
                    objectName = "categoria",
                    objectValue = viewModel.selectedPhotoCategory,
                    onChange = {
                        viewModel.onEvent(AddEditRirEvent.OnPhotoCategoryChange(it))
                    },
                    valuesList = viewModel.photoCategoryList
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(AddEditRirEvent.OnPhotoCategoryConfirm(viewModel.selectedPhotoCategory))
                    viewModel.shouldShowPhotoDialog = false
                }) {
                    Text(text = "Confirmar")
                }
            }
        )
}