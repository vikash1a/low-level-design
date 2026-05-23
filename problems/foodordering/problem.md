# 🍽️ Food Ordering System

## 📋 Description

This is an online food ordering system with the following capabilities:

- Tied-up restaurants each have a menu of food items with prices.
- Restaurants have limited processing capacity (food preparation and dispatch).
- Capacity is replenished after food fulfillment.
- Orders are accepted **only** if all items can be fulfilled by one or more restaurants.
- Restaurants are selected based on the **lowest price** for each item.

---

## ✨ Features

- ✅ **Add Restaurant**: Onboard new restaurants with menu and capacity.
- 🛠️ **Update Menu**: Modify menu and pricing for any restaurant.
- 🛒 **Place Order**: Customer places order with multiple food items.
- 📊 **Restaurant Selection Strategy**: Choose restaurant offering lowest price for each item.
- 📦 **Track Capacity**: System tracks remaining capacity per restaurant.
- 🔁 **Fulfillment Notification**: Replenishes restaurant capacity when an order is completed.
- 📈 **System Stats**: Display current processing power of all restaurants.

---

## 🧪 Sample Test Cases / Driver Commands

```python
# Add restaurants
add_restaurant("A2B", [Idly for 40Rs, Vada for 30Rs, Paper Plain Dosa for 50Rs], 4)
add_restaurant("Rasaganga", [Idly for 45Rs, Set Dosa for 60Rs, Poori for 25Rs], 6)
add_restaurant("Eat Fit", [Idly for 30Rs, Vada for 40Rs], 2)

# Place orders
order(["Idly", "Poori"])
# Output:
# Order Id#1: Ordered from "Eat Fit" & "Rasaganga"

order(["Idly", "Vada"])
# Output:
# Order Id#2: Ordered from "Eat Fit" & "A2B"

# Print system stats
print_system_stats()
# Output:
# A2B: 3
# Rasaganga: 5
# Eat Fit: 0

# Place another order
order(["Idly"])
# Output:
# Ordered from "A2B" (Eat Fit not selected due to zero capacity)

# Fulfill first order
fulfilled_item_for_restaurant("Order Id#1")

# Print system stats again
print_system_stats()
# Output:
# A2B: 2
# Rasaganga: 6
# Eat Fit: 1

# Fulfill second order
fulfilled_item_for_restaurant("Order Id#2")

# Update Eat Fit's menu
change_menu("Eat Fit", [Idly for 60Rs, Vada for 40Rs], 2)

# Try ordering Idly again
order(["Idly"])
# Output:
# Ordered from "A2B"
