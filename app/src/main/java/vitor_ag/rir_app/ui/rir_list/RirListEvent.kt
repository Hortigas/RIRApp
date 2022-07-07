package vitor_ag.rir_app.ui.rir_list

import vitor_ag.rir_app.data.Rir

sealed class RirListEvent {
    data class DeleteRir(val rir: Rir) : RirListEvent()
    object OnHistoryClick : RirListEvent()
}
