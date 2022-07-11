package vitor_ag.rir_app.ui.add_edit_rir

import android.net.Uri

sealed class AddEditRirEvent {
    data class OnFornecedorChange(val fornecedor: String) : AddEditRirEvent()
    data class OnAddLocal(val local: String) : AddEditRirEvent()
    data class OnRemoveLocal(val local: String) : AddEditRirEvent()
    data class OnDataDeRecebimentoChange(val dataDeRecebimento: String) : AddEditRirEvent()
    data class OnCategoriaChange(val categoria: String) : AddEditRirEvent()
    data class OnDocumentacaoChange(val id: Int, val selectedOption: String) : AddEditRirEvent()
    object OnOpenScanner : AddEditRirEvent()
    data class OnScanNotaFiscal(val text: String) : AddEditRirEvent()
    data class OnNotaChange(val text: String) : AddEditRirEvent()
    object OnAddNota : AddEditRirEvent()
    data class OnRemoveNota(val nota: String) : AddEditRirEvent()
    data class OnItensInspecionadosChange(val itensInspecionados: String) : AddEditRirEvent()
    data class OnObservacoesChange(val observacoes: String) : AddEditRirEvent()
    data class OnAprovacao1Change(val aprovacao: String) : AddEditRirEvent()
    data class OnAprovacao2Change(val aprovacao: String) : AddEditRirEvent()
    data class OnStatusRirChange(val statusRir: String) : AddEditRirEvent()
    data class OnPhotoTaken(val photo: Uri) : AddEditRirEvent()
    data class OnRemovePhoto(val index: Int) : AddEditRirEvent()
}
