package org.example.logger

import java.util.*

/*
To Improve,
vehicle should be linked in ParkingSpot instead of otherwise
parking and unparking vehicle should be moved to ParkingSpot class
 */

data class ParkingSpot(
    val id: UUID,
    val location: Location,
    val type: Type,
    var isOccupied: Boolean = false
)

data class Location(
    val level: Int,
    val spotNo: Int
)
enum class Type{
    motorcycles,
    car,
    trucks
}
data class Vehicle(
    val registrationNumber: String,
    val type: Type,
    var spotId: UUID? = null,
    var fees: Int? = null,
    var feesPaid: Boolean? = null
)

class ParkingManagers{
    private val parkingSpots: MutableList<ParkingSpot> = mutableListOf()
    private val vehicles: MutableList<Vehicle> = mutableListOf()

    fun assign(registrationNumber: String, type: Type){
        synchronized("assign"){
            val vehicle = Vehicle(registrationNumber,type)
            vehicles.add(vehicle)
            val parkingSpot = parkingSpots
                .filter { it.type == vehicle.type && !it.isOccupied }
                .sortedBy { it.location.level }
                .minByOrNull { it.location.spotNo }
            if(parkingSpot == null){
                println("No Parking Spot available")
                return
            }
            vehicle.spotId = parkingSpot.id
            parkingSpot.isOccupied = true
            return
        }
    }

    fun release(registrationNumber: String){
        synchronized("release") {
            val vehicle = vehicles.find { it.registrationNumber == registrationNumber }
            val parkingSpot = parkingSpots.find { it.id == vehicle?.spotId }
            parkingSpot?.isOccupied  = false
        }
    }
}



