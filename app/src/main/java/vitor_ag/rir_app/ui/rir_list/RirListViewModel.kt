package vitor_ag.rir_app.ui.rir_list

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
class RirListViewModel @Inject constructor(
    private val repository: RirRepository
) : ViewModel() {
    val rirs = repository.getRirs()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RirListEvent) {
        when (event) {
            is RirListEvent.DeleteRir -> {
                //TODO
            }
            is RirListEvent.OnHistoryClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.RIR_LIST))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}