package timurkhabibulin.wineeventsaggregator.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timurkhabibulin.wineeventsaggregator.domain.Event
import timurkhabibulin.wineeventsaggregator.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override suspend fun getEvents(query: String): Result<List<Event>> =
        withContext(Dispatchers.IO) {
            return@withContext Result.success(
                listOf(
                    Event.Default,
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    ),
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    ),
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    ),
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    ),
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    ),
                    Event(
                        name = "Название",
                        address = "г.Екатеринбург, ул Народной Воли 115",
                        time = "12.12.2024, 18:00",
                        duration = "2 часа",
                        description = "Описание",
                        price = "1000 руб"
                    )
                )
            )
        }

}
