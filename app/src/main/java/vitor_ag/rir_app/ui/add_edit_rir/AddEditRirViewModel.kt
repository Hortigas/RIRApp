package vitor_ag.rir_app.ui.add_edit_rir

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import vitor_ag.rir_app.data.RirRepository
import vitor_ag.rir_app.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class AddEditRirViewModel @Inject constructor(
    private val repository: RirRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var fornecedor by mutableStateOf("")
        private set
    var categoria by mutableStateOf("")
        private set
    var dataDeRecebimento by mutableStateOf("")
        private set

    private val _locais = mutableStateListOf<String>()
    val locais: List<String> = _locais

    val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditRirEvent) {
        when (event) {
            is AddEditRirEvent.OnFornecedorChange -> {
                fornecedor = event.fornecedor
            }
            is AddEditRirEvent.OnCategoriaChange -> {
                categoria = event.categoria
            }
            is AddEditRirEvent.OnDataDeRecebimentoChange -> {
                dataDeRecebimento = event.dataDeRecebimento
            }
            is AddEditRirEvent.OnAddLocal -> {
                _locais.add(event.local)
                _locais.sort()
            }
            is AddEditRirEvent.OnRemoveLocal -> {
                _locais.remove(event.local)
            }
        }
    }
}