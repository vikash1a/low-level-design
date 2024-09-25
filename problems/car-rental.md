## Requirements
1. The car rental system should allow customers to browse and reserve available cars for specific dates.
2. Each car should have details such as make, model, year, license plate number, and rental price per day.
3. Customers should be able to search for cars based on various criteria, such as car type, price range, and availability.
4. The system should handle reservations, including creating, modifying, and canceling reservations.
5. The system should keep track of the availability of cars and update their status accordingly.
6. The system should handle customer information, including name, contact details, and driver's license information.
7. The system should handle payment processing for reservations.
8. The system should be able to handle concurrent reservations and ensure data consistency.

## Solution Self
```
class CarRentalManager{
    cars
    customers
    reservations
    search(type, priceRange, availability){
        return cars.filter{type,priceRange,  availability}
    }
    reserve(start, end , cartType, customer){
        customers.add(customer)
        card = cars.find{it.type == carType && isAvailable(start,end) }.first()
        reservation = Reservation(customer, card, startDate, endDate, active, pending)
    }
    modlifyReserve(reservationId, start, end, cartType){
        // free up the old car
        // assing a new car based on the updated variable
    }
    cancelReserve(reservationId){
        reservation = reservation.find{id}
        reservation.status = canceled
        reservation.car.isAvailable.remove(start,end);
    }
}

reserve(date, car, customer), modlifyReserve(reservation,), cancelReserve(reservation)
processPayment(reservationId)

car - id, make, model, year, license, rental price, availability<list<start,end>> , isAvailable(start,end)
customer - id,name, contactDetails, license
reservations - id, customer, car, startDate, endDate, status(active, canceled, completed), paymentStatus(Completed, Pending)

concurrent reservations 

Optimisations - create three different class manager for car, resv, and cusotmer
car - add, search, updateAvailbilty, update
reservation(car) - create, modify, reserve
customer - create, modify, delete
for concurrency use synchronized on reserve methods
```