package timurkhabibulin.wineeventsaggregator.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timurkhabibulin.wineeventsaggregator.domain.Event
import timurkhabibulin.wineeventsaggregator.domain.Repository
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _events = mutableStateOf<List<Event>>(listOf())
    val events: State<List<Event>> = _events

    fun makeSearch(text: String) {
        _searchQuery.value = text
        viewModelScope.launch {
            val result = repository.getEvents(text)
            result.onSuccess {
                _events.value = it
            }
        }
    }

    fun signUpToEvent(event: Event){

    }
}
