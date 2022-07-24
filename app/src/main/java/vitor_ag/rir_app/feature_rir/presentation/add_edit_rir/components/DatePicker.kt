package vitor_ag.rir_app.feature_rir.presentation.add_edit_rir.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    objectName: String,
    objectValue: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onChange("$year-%02d-%02d".format(month + 1, dayOfMonth))
        }, year, month, day
    )

    OutlinedTextField(
        label = { Text(text = objectName.replaceFirstChar { it.uppercaseChar() }) },
        modifier = modifier,
        readOnly = true,
        value = objectValue.split("-").reversed().joinToString("/"),
        onValueChange = { },
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            datePickerDialog.show()
                        }
                    }
                }
            },
        trailingIcon = { Icon(Icons.Rounded.DateRange, "datePicker") }
    )
}
