package org.example.movieticketbooking

import java.time.LocalDateTime
import java.util.*

/*
features,
1. view list of movies
2. show timing of a movie
3. show seating arrangement
4. make payments and confirm booking
5. Support different type of seats and pricing
6. Allow administrator to add, update
7. Concurrent users

Models,
Movies(id, name, releaseDate)
Theaters(id, name, location)
Screen(id, theaterId, seatSetup)
SeatSetup(id, Map((row,column), seat))
Seat(id, typeId) -> NormalSeat, PremiumSeat
Price(id, typeId, price)
Shows(id, theaterId, movieId, screenId, startTime, endTime, seats)
Ticket(id, showId, price, paymentStatus)
*/


class MovieTicketBooking(){
    private val movies: MutableList<Movie> = mutableListOf()
    private val theaters: MutableList<Theater> = mutableListOf()
    private val shows: MutableList<Show> = mutableListOf()
    private val tickets: MutableList<Ticket> = mutableListOf()
    fun viewMovies(): MutableList<Movie> {
        return movies
    }

    fun showTimings(moviesId: String, theaterId: String): List<Pair<LocalDateTime,LocalDateTime>> {
        return shows.filter {
            it.movie.id == moviesId && it.screen.theater.id == theaterId
        }.map { Pair(it.startTime, it.endTime) }
    }

    fun showSeatingArrangements(showId: String): Unit {
        return shows.find { it.id == showId }!!.screen.seatSetup.showSeatingArrangements()
    }

    fun confirmBooking(paymentStatus: Boolean, showId: String, seatNumber: Pair<Int, Int>): String {
        val show =  shows.find { it.id == showId }!!
        show.seatsStatus[seatNumber] = true
        val price = show.screen.seatSetup.seats[seatNumber]!!.priceCategory.price
        val id = UUID.randomUUID().toString()
        tickets.add(
            Ticket(UUID.randomUUID().toString(), show,price, paymentStatus, seatNumber )
        )
        return id
    }

}
// models
data class Movie(
    val id: String,
    val name: String,
    val releaseDate: Date,
    val isRunning: Boolean
)
data class Theater(
    val id: String,
    val name: String,
    val location: String,
    val screens: List<Screen>
)
data class Screen(
    val id: String,
    val theater: Theater,
    val seatSetup: SeatSetup
)
data class SeatSetup(
    val id: String,
    val numberOfRows: Int,
    val numberOfColumns: Int,
    val seats: MutableMap<Pair<Int,Int>,Seat>
){
    fun showSeatingArrangements(){
        TODO()
    }
}
interface Seat {
    val id: String
    val priceCategory: PriceCategory
}
data class NormalSeat(
    override val id: String,
    override val priceCategory: PriceCategory
): Seat
data class PremiumSeat(
    override val id: String,
    override val priceCategory: PriceCategory
): Seat
data class PriceCategory(
    val id: String,
    val price: Int
)

data class Show(
    val id: String,
    val movie: Movie,
    val screen: Screen,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val seatsStatus: MutableMap<Pair<Int,Int>,Boolean>
)
data class Ticket(
    val id: String,
    val show: Show,
    val price: Int,
    val paymentCompleted: Boolean,
    val seatNo: Pair<Int,Int>
)

