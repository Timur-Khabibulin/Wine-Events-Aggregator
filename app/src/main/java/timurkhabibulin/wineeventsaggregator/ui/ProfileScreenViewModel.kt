package timurkhabibulin.wineeventsaggregator.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timurkhabibulin.wineeventsaggregator.domain.User
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(

) : ViewModel() {
    val user = User.Default
}
