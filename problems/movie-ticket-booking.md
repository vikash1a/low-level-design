## Requirements
1. The system should allow users to view the list of movies playing in different theaters.
2. Users should be able to select a movie, theater, and show timing to book tickets.
3. The system should display the seating arrangement of the selected show and allow users to choose seats.
4. Users should be able to make payments and confirm their booking.
5. The system should handle concurrent bookings and ensure seat availability is updated in real-time.
6. The system should support different types of seats (e.g., normal, premium) and pricing.
7. The system should allow theater administrators to add, update, and remove movies, shows, and seating arrangements.
8. The system should be scalable to handle a large number of concurrent users and bookings.
   
## Solution Self
```
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
```

Improvements from solution,
Add User model 