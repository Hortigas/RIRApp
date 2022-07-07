package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.Dropdown
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.EditableDropdown
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.MultipleSelectionDropdown
import vitor_ag.rir_app.ui.theme.md_theme_light_onPrimary
import vitor_ag.rir_app.ui.theme.md_theme_light_primary
import vitor_ag.rir_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditRirScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditRirViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = { Text("Relatório de Inspeção") },
                navigationIcon = {
                    IconButton(
                        onClick = { /* "Open nav drawer" */ }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = md_theme_light_primary,
                    titleContentColor = md_theme_light_onPrimary,
                    navigationIconContentColor = md_theme_light_onPrimary
                )
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = "Dados Gerais",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                EditableDropdown(
                    objectName = "Fornecedor",
                    objectValue = viewModel.fornecedor,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnFornecedorChange(it)) },
                    valuesList = listOf(
                        "fornecedor1",
                        "fornecedor2",
                        "fornecedor3",
                        "fornecedor4",
                        "fornecedor5"
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                )
                MultipleSelectionDropdown(
                    objectName = "Local",
                    objectValue = viewModel.locais,
                    onAdd = { viewModel.onEvent(AddEditRirEvent.OnAddLocal(it)) },
                    onRemove = { viewModel.onEvent(AddEditRirEvent.OnRemoveLocal(it)) },
                    valuesList = (1..14).map { i -> "Jana %02d".format(i) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                )
                DatePicker(
                    objectName = "Data de recebimento",
                    objectValue = viewModel.dataDeRecebimento,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnDataDeRecebimentoChange(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Dropdown(
                    objectName = "Categoria",
                    objectValue = viewModel.categoria,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnCategoriaChange(it)) },
                    valuesList = listOf("Geral", "Módulos fotovoltaicos"),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    color = Color.LightGray,
                    thickness = 2.dp,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Documentação",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                ValidationList(
                    arrList = listOf(
                        ValidationListProps("Certificado de qualidade", "OK"),
                        ValidationListProps("Certificado de procedência (para a madeira)", "OK"),
                        ValidationListProps(
                            "Manual ou ficha técnica do produto / equipamento", "OK"
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    )
}