package timurkhabibulin.wineeventsaggregator.domain

interface Repository {
    suspend fun getEvents(
        query: String
    ): Result<List<Event>>
}
