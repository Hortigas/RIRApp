package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vitor_ag.rir_app.ui.theme.md_theme_light_onPrimary

data class ValidationListProps(
    val title: String,
    val selectedOption: String,
)

@Composable
fun ValidationList(
    arrList: List<ValidationListProps>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        arrList.forEach { item ->
            ValidationItem(
                title = item.title,
                optionsList = listOf("OK", "NC", "NA"),
                initialValue = item.selectedOption,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidationItem(
    title: String,
    optionsList: List<String>,
    initialValue: String = optionsList[0],
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(optionsList.indexOf(initialValue)) }
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = title
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            optionsList.forEachIndexed { i, item ->
                OutlinedButton(
                    contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 8.dp),
                    onClick = { selected = i },
                    colors = if (selected == i) ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors()
                ) {
                    RadioButton(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp),
                        selected = selected == i,
                        onClick = { selected = i },
                        colors = RadioButtonDefaults.colors(selectedColor = md_theme_light_onPrimary)
                    )
                    Text(item)
                }
            }
        }
    }

}