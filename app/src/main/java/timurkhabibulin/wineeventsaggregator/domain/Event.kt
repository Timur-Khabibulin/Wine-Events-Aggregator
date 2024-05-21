package timurkhabibulin.wineeventsaggregator.domain

data class Event(
    val name: String,
    val address: String,
    val time: String,
    val duration: String,
    val description: String,
    val price: String
)
