package vitor_ag.rir_app.ui.add_edit_rir

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import vitor_ag.rir_app.data.RirRepository
import vitor_ag.rir_app.util.Routes
import vitor_ag.rir_app.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class AddEditRirViewModel @Inject constructor(
    private val repository: RirRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //Dados gerais
    var fornecedor by mutableStateOf("")
        private set
    val locais = mutableStateListOf<String>()
    var dataDeRecebimento by mutableStateOf("")
        private set
    var categoria by mutableStateOf("")
        private set

    //Documentação
    val documentacao = mutableStateListOf<ValidationListItem>(
        ValidationListItem(
            "Certificado de qualidade", "OK"
        ),
        ValidationListItem(
            "Certificado de procedência (para a madeira)", "OK"
        ),
        ValidationListItem(
            "Manual ou ficha técnica do produto / equipamento", "OK"
        )
    )

    //Notas fiscais
    var notaFiscal by mutableStateOf("")
        private set
    val notas = mutableStateListOf<String>()
    var itensInspecionados by mutableStateOf("")
        private set
    var observacoes by mutableStateOf("")
        private set

    //Conferência técnica
    val conferenciaTecnica = mutableStateListOf<ValidationListItem>(
        ValidationListItem(
            "A carga está devidamente acomodada no transporte?",
            "OK"
        ),
        ValidationListItem(
            "Os itens estão sem ocorrência de avarias?",
            "OK"
        ),
        ValidationListItem(
            "As quantidades dos itens estão de acordo com o pedido de compra?",
            "OK"
        ),
        ValidationListItem(
            "A carga está acompanhada do Certificado de Qualidade / Ensaios? O mesmo atende as normas / ET?",
            "OK"
        ),
        ValidationListItem(
            "As características do material, equipamento e/ou ferramenta atendem ao especificado conforme norma técnica?",
            "OK"
        ),
        ValidationListItem(
            "As características do material, equipamento e/ou ferramenta atendem ao especificado em projeto executivo / ET?",
            "OK"
        ),
        ValidationListItem(
            "O local para armazenamento esta plano, limpo e nivelado? O material foi armazenado com altura segura do solo(sobre calço de madeira / palet) afim de evitar danos?",
            "OK"
        )
    )

    //Aprovação
    var aprovacao1 by mutableStateOf("")
        private set
    var aprovacao2 by mutableStateOf("")
        private set
    var statusRir by mutableStateOf("")
        private set

    //Registro fotográfico
    val photoGallery = mutableStateListOf<Uri>()

    fun onEvent(event: AddEditRirEvent) {
        when (event) {
            is AddEditRirEvent.OnFornecedorChange -> {
                fornecedor = event.fornecedor
            }
            is AddEditRirEvent.OnAddLocal -> {
                locais.add(event.local)
                locais.sort()
            }
            is AddEditRirEvent.OnRemoveLocal -> {
                locais.remove(event.local)
            }
            is AddEditRirEvent.OnDataDeRecebimentoChange -> {
                dataDeRecebimento = event.dataDeRecebimento
            }
            is AddEditRirEvent.OnCategoriaChange -> {
                categoria = event.categoria
            }
            is AddEditRirEvent.OnOpenScanner -> {
                sendUiEvent(UiEvent.Navigate(Routes.SCANNER_PAGE))
            }
            is AddEditRirEvent.OnScanNotaFiscal -> {
                notaFiscal = event.text
                sendUiEvent(UiEvent.PopBackStack)
            }
            is AddEditRirEvent.OnNotaChange -> {
                notaFiscal = event.text
            }
            is AddEditRirEvent.OnAddNota -> {
                if (notaFiscal.isNotBlank()) {
                    notas.add(notaFiscal)
                    notaFiscal = ""
                }
            }
            is AddEditRirEvent.OnRemoveNota -> {
                notas.remove(event.nota)
            }
            is AddEditRirEvent.OnItensInspecionadosChange -> {
                itensInspecionados = event.itensInspecionados
            }
            is AddEditRirEvent.OnObservacoesChange -> {
                observacoes = event.observacoes
            }
            is AddEditRirEvent.OnAprovacao1Change -> {
                aprovacao1 = event.aprovacao
            }
            is AddEditRirEvent.OnAprovacao2Change -> {
                aprovacao2 = event.aprovacao
            }
            is AddEditRirEvent.OnStatusRirChange -> {
                statusRir = event.statusRir
            }
            is AddEditRirEvent.OnPhotoTaken -> {
                photoGallery.add(event.photo)
            }
            is AddEditRirEvent.OnRemovePhoto -> {
                photoGallery.removeAt(event.index)
            }
            is AddEditRirEvent.OnDocumentacaoChange -> {
                documentacao[event.id]
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }
}