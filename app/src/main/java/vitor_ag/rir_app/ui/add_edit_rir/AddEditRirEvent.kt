package vitor_ag.rir_app.ui.add_edit_rir

sealed class AddEditRirEvent {
    data class OnFornecedorChange(val fornecedor: String) : AddEditRirEvent()
    data class OnCategoriaChange(val categoria: String) : AddEditRirEvent()
    data class OnDataDeRecebimentoChange(val dataDeRecebimento: String) : AddEditRirEvent()
    data class OnAddLocal(val local: String) : AddEditRirEvent()
    data class OnRemoveLocal(val local: String) : AddEditRirEvent()
    object OnOpenScanner : AddEditRirEvent()
    data class OnScan(val text: String) : AddEditRirEvent()
}
