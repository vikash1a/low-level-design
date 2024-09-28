package org.example

interface ITicketBooking{
    fun viewMovies()
    fun showTimings(moviesId, theaterId)
    fun showSeatingArrangements(showId)
    fun confirmBooking(paymentId, showId)
}
interface Base{
    val id: String
}
data class Movies: Base(
    val name: String,
val releaseDate: Date,
val isRunning: Boolean
)

data class Theater: Base(
    val name: String,
val location: Location
)
data class Location: Base(
    // tba
)