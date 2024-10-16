package org.example.uber

import java.util.UUID

/*
```
Rider
    bookRide(location, type): RideId
    cancelRide(riderId)
Driver
    viewRides(): List<Ride>
    acceptRide(riderId)
    cancelRide(riderId)
    startRide(riderId)
    completeRide(riderId)

Rider(id, name)
Driver(id, name, vehicle, location)
Vehicle(id, type, number)
Ride(id, rider, driver,startLocation, endLocation, fare,paymentStatus,  status)
```
(source.lat - it.location.lat)*(source.lat - it.location.lat) +
                    (source.long - it.location.long)*(source.long - it.location.long)
 */
class RideService{
    private val rides: MutableList<Ride> = mutableListOf()
    private val riders: MutableList<Rider> = mutableListOf()
    private val drivers: MutableList<Driver> = mutableListOf()

    fun bookRide(rider:Rider, source: Location, destination: Location, vehicleType: VehicleType): UUID{
        val ride = Ride(
            id = UUID.randomUUID(),
            rider = rider,
            driver = null,
            source = source,
            destination = destination,
            fare = FareCalculator().getFare(source,destination,vehicleType),
            paymentStatus = PaymentStatus.PENDING,
            rideStatus = RideStatus.REQUESTED
        )
        rides.add(ride)
        return ride.id
    }

    fun viewRides(driver: Driver): List<Ride>{
        val nearestRides = rides.filter { it.rideStatus == RideStatus.REQUESTED }.sortedBy {
            (driver.location.lat - it.source.lat)*(driver.location.lat - it.source.lat) +
                    (driver.location.long - it.source.long)*(driver.location.long - it.source.long)
        }.subList(0,10)
        return nearestRides
    }

}

class FareCalculator{
    fun getFare(source: Location, destination: Location, vehicleType: VehicleType): Int{
        TODO()
    }
}

// model
data class Rider(
    val id: UUID,
    val name: String
)
data class Driver(
    val id: UUID,
    val name: String,
    val vehicle: Vehicle,
    val location: Location,
    val isAvailable: Boolean
)
data class Vehicle(
    val id: UUID,
    val type: VehicleType,
    val regNo: String
)
enum class VehicleType {
    SEDAN,
    SUV
}
data class Ride(
    val id: UUID,
    val rider: Rider,
    val driver: Driver?,
    val source: Location,
    val destination: Location,
    val fare: Int,
    val paymentStatus: PaymentStatus,
    val rideStatus: RideStatus
)
data class Location(
    val lat: Int,
    val long: Int
)
enum class PaymentStatus{
    PENDING,
    COMPLETED
}
enum class RideStatus{
    REQUESTED,
    ACCEPTED,
    CANCELED,
    COMPLETED
}