package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ValidationList(
    arrList: List<ValidationListItem>,
    optionsList: List<String>,
    onChange: (Int, String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(
        modifier = modifier
    ) {
        arrList.forEachIndexed { index, item ->
            ValidationItem(
                id = index,
                title = item.title,
                optionsList = optionsList,
                selectedOption = item.selectedOption,
                onChange = onChange
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ValidationItem(
    id: Int,
    title: String,
    optionsList: List<String>,
    selectedOption: String = optionsList[0],
    onChange: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
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
                    onClick = { onChange(id, option) },
                    border = if (option == selectedOption) null else ButtonDefaults.outlinedButtonBorder,
                    colors =
                    if (option == selectedOption)
                        ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    else
                        ButtonDefaults.outlinedButtonColors()
                ) {
                    RadioButton(
                        enabled = false,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp),
                        selected = option == selectedOption,
                        onClick = { },
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = MaterialTheme.colorScheme.outline,
                            selectedColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Text(option)
                }
            }
        }
    }
}