package vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier,
    modifierDivider: Modifier = Modifier,
    divider: Boolean = true,
) {
    Column() {
        if (divider) {
            Divider(
                color = MaterialTheme.colorScheme.outline,
                modifier = modifierDivider
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                thickness = 1.dp,
            )
        }
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = if (divider) 0.dp else 8.dp),
            text = title,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
    }
}