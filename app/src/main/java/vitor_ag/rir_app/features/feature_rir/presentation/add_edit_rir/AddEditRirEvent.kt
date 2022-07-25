package vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir

import android.content.Context
import android.net.Uri

sealed class AddEditRirEvent {
    object OnOpenNavBar : AddEditRirEvent()
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
    data class OnConferenciaTecnicaChange(val id: Int, val selectedOption: String) :
        AddEditRirEvent()

    data class OnMateriaisRecebidosChange(val aprovacao: String) : AddEditRirEvent()
    data class OnNaoConformidadeChange(val aprovacao: String) : AddEditRirEvent()
    data class OnStatusRirChange(val statusRir: String) : AddEditRirEvent()
    data class OnPhotoTaken(val photo: Uri) : AddEditRirEvent()
    data class OnRemovePhoto(val index: Int) : AddEditRirEvent()
    data class OnPhotoCategoryChange(val category: String) : AddEditRirEvent()
    data class OnPhotoCategoryConfirm(val category: String) : AddEditRirEvent()
    object OnOpenPopupRir : AddEditRirEvent()
    data class OnCreateRir(val context: Context) : AddEditRirEvent()
}
