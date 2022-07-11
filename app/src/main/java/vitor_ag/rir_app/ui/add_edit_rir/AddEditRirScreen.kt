package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import vitor_ag.rir_app.R
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.Dropdown
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.EditableDropdown
import vitor_ag.rir_app.ui.add_edit_rir.dropdown.MultipleSelectionDropdown
import vitor_ag.rir_app.util.CameraButton
import vitor_ag.rir_app.util.UiEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditRirScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: AddEditRirViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding()
                    )
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Title(title = "Dados gerais", divider = false)
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
                    )
                )
                MultipleSelectionDropdown(
                    objectName = "Local",
                    objectValue = viewModel.locais,
                    onAdd = { viewModel.onEvent(AddEditRirEvent.OnAddLocal(it)) },
                    onRemove = { viewModel.onEvent(AddEditRirEvent.OnRemoveLocal(it)) },
                    valuesList = (1..14).map { i -> "Jana %02d".format(i) }
                )
                DatePicker(
                    objectName = "Data de recebimento",
                    objectValue = viewModel.dataDeRecebimento,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnDataDeRecebimentoChange(it)) }
                )
                Dropdown(
                    objectName = "Categoria",
                    objectValue = viewModel.categoria,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnCategoriaChange(it)) },
                    valuesList = listOf("Geral", "Módulos fotovoltaicos")
                )
                Title(title = "Documentação")
                ValidationList(
                    arrList = viewModel.documentacao,
                    optionsList = listOf("OK", "NC", "NA"),
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnDocumentacaoChange()) }
                )
                Title(title = "Notas fiscais")
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(AddEditRirEvent.OnOpenScanner) }
                ) {
                    Icon(
                        painterResource(
                            R.drawable.round_qr_code_scanner_24
                        ),
                        contentDescription = "scanner icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "ESCANEAR NOTA FISCAL"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Nota fiscal") },
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(end = 8.dp),
                        value = viewModel.notaFiscal,
                        onValueChange = { viewModel.onEvent(AddEditRirEvent.OnNotaChange(it)) }
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier,
                        onClick = { viewModel.onEvent(AddEditRirEvent.OnAddNota) }
                    ) {
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = "add icon"
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = "ADICIONAR"
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.notas.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = "delete nota",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            Text(text = it)
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
                OutlinedTextField(
                    label = { Text(text = "Itens inspecionados") },
                    value = viewModel.itensInspecionados,
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditRirEvent.OnItensInspecionadosChange(
                                it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = TextFieldDefaults.MinHeight + TextFieldDefaults.MinHeight)
                )
                OutlinedTextField(
                    label = { Text(text = "Observações") },
                    value = viewModel.observacoes,
                    onValueChange = { viewModel.onEvent(AddEditRirEvent.OnObservacoesChange(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = TextFieldDefaults.MinHeight + TextFieldDefaults.MinHeight)
                )
                Title(title = "Conferência técnica")
                ValidationList(
                    arrList = viewModel.conferenciaTecnica,
                    optionsList = listOf("OK", "NC", "NA")
                )
                Title(title = "Aprovação")
                ValidationItem(
                    title = "Os materiais foram recebidos em sua totalidade?",
                    optionsList = listOf("SIM", "NÃO")
                )
                ValidationItem(
                    title = "Houve algum tipo de não-conformidade?",
                    optionsList = listOf("SIM", "NÃO")
                )
                Dropdown(
                    objectName = "Status do RIR",
                    objectValue = viewModel.statusRir,
                    onChange = { viewModel.onEvent(AddEditRirEvent.OnStatusRirChange(it)) },
                    valuesList = listOf("Aprovado", "Desvio de Documento", "Desvio de Produto")
                )
                Title(title = "Registro fotográfico")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement =
                    if (viewModel.photoGallery.size == 0)
                        Arrangement.Center
                    else Arrangement.spacedBy(8.dp)
                ) {
                    if (viewModel.photoGallery.size == 0)
                        Image(
                            alignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.ic_empty_gallery),
                            contentDescription = "empty gallery"
                        )
                    else
                        viewModel.photoGallery.forEachIndexed { index, item ->
                            Box(
                                contentAlignment = Alignment.BottomEnd,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5))
                                    .fillMaxHeight()
                                    .width(160.dp),
                            ) {
                                AsyncImage(
                                    model = item,
                                    modifier = Modifier.fillMaxSize(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                FloatingActionButton(
                                    modifier = Modifier
                                        .padding(bottom = 8.dp, end = 8.dp)
                                        .fillMaxSize(0.3f),
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    onClick = {
                                        viewModel.onEvent(
                                            AddEditRirEvent.OnRemovePhoto(
                                                index
                                            )
                                        )
                                    }
                                )
                                {
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        tint = MaterialTheme.colorScheme.onTertiary,
                                        contentDescription = "remove photo"
                                    )
                                }
                            }

                        }
                }
                CameraButton(onClick = { viewModel.onEvent(AddEditRirEvent.OnPhotoTaken(it)) })
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "GERAR RIR")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}
/*
                            IconButton(
                                modifier = Modifier
                                    .padding(bottom = 4.dp, end = 4.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                                onClick = { viewModel.onEvent(AddEditRirEvent.OnRemovePhoto(i)) }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    tint = Color.White,
                                    contentDescription = "remove photo"
                                )
                            }
 */