# File-IO-and-Recursion
This discount store carries a variety of furniture products and clothing products. They want us to write an app that stores their inventory of products and displays it to the user, including the unit price of each product, how many units are left of the product, and the discount rate that is applicable to the product. 

The user can then select a product, how many items they want to purchase, insert money (by keying in the amount), and get the selected item dispensed if the amount is right after discount and if there are enough units of the product left to fulfill the order. (The user gets an additional discount of %5 if they buy more than two units of the product.) The app must return the change if the amount entered is above the grand total after all of the discounts.

The app has an option that allows the user to restock the store by setting the units of each product to a positive value of your choosing.

The app must redisplay the updated inventory to allow the user to buy again. The app must update the amount of money that it has collected so far. When the app starts, it must read its inventory from three text files (totalSales.txt, furniture_products.txt and clothing_products.txt). The app must save the inventory, and the amount of money collected over the lifetime of the app, back to the three text files before exiting or when asked to by the user.
