package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButton(
    title: String,
    optionsList: List<String>,
    onChange: (String) -> Unit,
    selectedOption: String = optionsList[0],
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            optionsList.forEach { option ->
                OutlinedButton(
                    contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 8.dp),
                    onClick = { onChange(option) },
                    border = if (option == selectedOption) null else ButtonDefaults.outlinedButtonBorder,
                    colors =
                    if (option == selectedOption)
                        ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    else
                        ButtonDefaults.outlinedButtonColors()
                ) {
                    RadioButton(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp),
                        selected = option == selectedOption,
                        onClick = { onChange(option) },
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                    Text(
                        text = option,
                        color =
                        if (option == selectedOption)
                            MaterialTheme.colorScheme.onSecondary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
