package vitor_ag.rir_app.ui.add_edit_rir.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleSelectionDropdown(
    objectName: String,
    objectValue: List<String>,
    onAdd: (String) -> Unit,
    onRemove: (String) -> Unit,
    valuesList: List<String>,
    modifier: Modifier = Modifier
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
            value = objectValue.joinToString(),
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        DropdownMenu(
            modifier = Modifier.exposedDropdownSize(matchTextFieldWidth = true),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            valuesList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        if (objectValue.contains(item))
                            onRemove(item)
                        else
                            onAdd(item)
                    },
                    trailingIcon = {
                        if (objectValue.contains(item)) {
                            Icon(
                                Icons.Rounded.CheckCircle,
                                contentDescription = "checked"
                            )
                        }
                    }
                )
            }
        }
    }
}