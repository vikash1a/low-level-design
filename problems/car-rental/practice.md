# Car Rental — Practice Notes

## Requirements (from memory)

1. Customers browse and reserve available cars for specific dates
2. Car details: make, model, year, license plate, rental price/day
3. Search by car type, price range, availability
4. Handle reservations: create, modify, cancel
5. Track availability and update status
6. Store customer info: name, contact, driver's license
7. Payment processing
8. Handle concurrent reservations with data consistency

## Solution sketch

```
Operations:
  browse()
  reserve()
  modifyReservation()
  cancelReservation()

Car
  make, model, type, year, licensePlate, pricePerDay, quantity

Reservation
  user, car, status(BOOKED, COMPLETED, CANCELED), startDate, endDate, completedDate
```
