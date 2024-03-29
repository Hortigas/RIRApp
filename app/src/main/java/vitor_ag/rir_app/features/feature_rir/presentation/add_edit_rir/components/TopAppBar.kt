package vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir.AddEditRirEvent
import vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir.AddEditRirViewModel

@Composable
fun TopAppBar(
    viewModel: AddEditRirViewModel
) {
    SmallTopAppBar(
        title = {
            Text(
                text = "", //Relatório de Inspeção
                fontWeight = FontWeight.Normal
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { viewModel.onEvent(AddEditRirEvent.OnOpenNavBar) }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.History,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

    )
}