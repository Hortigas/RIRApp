package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ValidationListItem(
    val title: String,
    val selectedOption: String
)

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
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(20.dp),
                            selected = option == selectedOption,
                            onClick = { onChange(id, option) },
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
}