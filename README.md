# Project-2-Grp52

VT Dining Order Master is a text-based restaurant ordering system that allows customers to select food items from a menu, place orders, and check their position in the order queue and estimated wait time. The system uses Java classes to manage food items, customer orders, and active orders.

# How to Run the Program

1. Open the project in the Java development environment.
2. Ensure all required Java classes are included in the project.
4. Run VTOrderMaster.java.
5. Follow the menu prompts in the console to interact with the program.

# To run the tests
Run the JUnit 4 test classes using the project's configured JUnit 4 test runner. Ensure the JUnit 4 library is included in the project.

# Features
Place an order: Select one or more food items from the menu and submit an order.
View order status: Check the number of orders ahead and the estimated wait time.
View menu: Display available food items and their prices.
Exit: Exit the program.

# Reflection
When making the application, we organized the application into seperate classes for handling
food items, customer orders, the order queue, and the main program. We also added an OrderQueue class to manage active orders using LinkedChain<OrderInfo>. We also used LinkedChain<Food> to store the food items selected for an order. This allows the same generic data structure to store two different types of objects. So far, we mainly worked on the MVP features (placing orders, viewing the menu, viewing order information, and exiting the program). Cancelling an order is considered a stretch goal but we still tried to implement the UUID.

We used the provided LinkedChain rather than creating a seperate data stucture. The application accesses the linked chain through its public methods, which keeps the order and menu logic in the application classes.

We figured out how to use LinkedChain for both food selections and orders. We still need to make sure the estimated wait time is calculated and displayed correctly, especially when multiple orders are in the queue.

We still need to test the full program to make sure all MVP features work as expected and invalid inputs are handled correctly. Order cancellation is also still a stretch goal, meaning that the cancellation with the UUID is still not working. Along with this, we also want to have it so that it reports the hours and minuts not just minutes.

# Next Steps
Finish and test the MVP features: Make sure placing orders, viewing the menu, viewing order information, and exiting all work correctly. (Phani, Brinda, Romeo, Wyatt). Along with also adding more menu options and clearning up the UI.

Test invalid inputs: Add tests for invalid inputs and edge cases for each class. (Each team member for their classes)

Verify wait time calculations: Make sure the estimated wait times and number of orders ahead are displayed correctly. (Phani, Brinda)

Test the full program: Run the application from start to finish and fix any issues found. (All team members)

Implement order cancellation: Implement the cancellation stretch goal using the Authenticator class and the UUID. 

Test wait time calculations with multiple orders and empty queues. Test finding orders that are in the queue and orders that are not in the queue. Test adding valid orders and handling null orders. Test invalid food selections and attempts to finish an empty order. If cancellation is implemented, test successful cancellation, failed authentication, and attempts to cancel an order that does not exist.

# Atribution
Romeo: Added food class and food test class
Brinda: Added OrderInfo, OrderInfoTest, Client, ClientTest, and OrderQueue classes
Phani: Created the VTOrderMaster and the test for the linkedChain class.

Food.java
Romeo created the Food class, which stores a food item's name, price, and preparation time.

FoodTest.java
Romeo created the test class for Food to check that the class works correctly with valid and invalid inputs.

OrderInfo.java
Brinda created the OrderInfo class, which stores customer and order information and calculates the order's total price and estimated preparation time.

OrderInfoTest.java
Brinda created the test class for OrderInfo to verify its methods and how it handles different inputs.

Client.java
Brinda created the Client class, which handles customer interactions, food selection, and order placement.

ClientTest.java
Brinda created the test class for Client to test customer actions and order placement.

OrderQueue.java
Brinda created the OrderQueue class, which manages active orders and provides methods for checking order counts and estimated wait times.

OrderQueueTest.java
Phani created the test class for the OrderQueue class. This includes the newly added 
removeOrder method, which handes the stretch goals order cancellation.

VTOrderMaster.java
Phani created the main runner class, which displays the main menu and connects the program's options to the appropriate methods.

LinkedChainTest.java
Phani created the test class for LinkedChain to check its methods with normal and invalid inputs.

Authenticator.java
Phani implemented the security handler that validates customers UUIDs with the order queue
to make sure no one cancels other peoples orders

AuthenticatorTest.java
Phani created the test methods covering valid UUID cancellations, null parameter rejections, and attempts to cancel missing orders.

# GenAI Notice

We used generative AI when understanding how we would impement the UUID into our classes without yet making the class. This will save time in the future so we dont have to go back to the prior classes and change alot of code. We also used AI when working on the JUnit tests since because we could not get the package student packages into vscode and the directions said to use junit.