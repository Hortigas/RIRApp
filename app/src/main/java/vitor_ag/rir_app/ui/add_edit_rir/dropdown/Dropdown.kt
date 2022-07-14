package vitor_ag.rir_app.ui.add_edit_rir.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    objectName: String,
    objectValue: String,
    onChange: (String) -> Unit,
    valuesList: List<String>,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }) {
        OutlinedTextField(
            label = { Text(text = objectName.replaceFirstChar { it.uppercaseChar() }) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = objectValue,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        DropdownMenu(
            modifier = Modifier
                .exposedDropdownSize(matchTextFieldWidth = true)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            valuesList.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    onClick = {
                        onChange(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}