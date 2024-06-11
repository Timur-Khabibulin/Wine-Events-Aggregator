package timurkhabibulin.wineeventsaggregator.domain

data class User(
    val name: String,
    val surName: String,
    val email: String
) {
    companion object {
        val Default = User(
            name = "Василий",
            surName = "Пупкин",
            email = "vasia777@email.com"
        )
    }
}
