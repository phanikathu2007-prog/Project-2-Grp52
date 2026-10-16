import java.util.*;

/**
 * Responsible for the actions the customer can perform, such as placing 
 * an order, viewing the food menu, viewing the number of orders ahead of 
 * their order and the estimated wait time.
 * 
 */
public class Client
{
    private HashMap<String, Food> menu;
    private OrderQueue orderQueue;
    private Scanner scanner;
    private String customerName;
    private UUID customerUUID;
    private int orderNumber;

    private LinkedChain<Food> foodSelections;

    private OrderInfo currentOrder;
    
    /**
     * Create a new Client object.
     * @param menu the menu to view food items
     * @param orderQueue the queue of active orders
     * @param scanner the scanner to process through user input
     * @param customerName a customer name
     * @param customerUUID the UUID associated with an order
     * @param orderNumber the order number associated with an order
     */
    public Client(HashMap<String, Food> menu, OrderQueue orderQueue,
        Scanner scanner, String customerName, 
        UUID customerUUID, int orderNumber)
    {
        this.menu = menu;
        this.orderQueue = orderQueue;
        this.scanner = scanner;
        this.customerName = customerName;
        this.customerUUID = customerUUID;
        this.orderNumber = orderNumber;

        foodSelections = new LinkedChain<Food>();
    }
    
    /**
     * Asks the customer to select food items.
     */
    public void placeOrder()
    {
        boolean finished = false;

        while (!finished)
        {
            viewMenu();

            System.out.println(
                "Enter a food item name, or type 'done' to finish:");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("done"))
            {
                finished = true;
                placeOrder(null, true);
            }
            else
            {
                Food food = menu.get(choice);
                placeOrder(food, false);
            }
        }
    }
    
    /**
     * Allows the customer to select food items and finish their order.
     * @param food the food item selected by the customer
     * @param finished whether the customer has finished selecting food items
     */
    public void placeOrder(Food food, boolean finished)
    {
        if (!finished)
        {
            if (food == null)
            {
                System.out.println(
                    "Unable to add this item. Please select a valid food item");
                return;
            }
            if (!menu.containsValue(food))
            {
                System.out.println(
                    "Invalid food selection, please select an item from the menu");
                return;
    
            }
            
            foodSelections.add(food);
            System.out.println("Food item added to your order.");
            return;
        }
        
        if (foodSelections.isEmpty())
        {
            System.out.println(
                "Your order is empty. Please select at least one food item");
            return;
        }
            
        currentOrder = new OrderInfo(
            customerName,
            foodSelections,
            customerUUID,
            orderNumber
        );
            
        orderQueue.addOrder(currentOrder);
        foodSelections = new LinkedChain<Food>();

        System.out.println("Your order has been placed!");
    }

    /**
     * Returns the customer's current order.
     *
     * @return the current order
     */
    public OrderInfo getCurrentOrder()
    {
        return currentOrder;
    }
    
    /**
     * Displays the available food items and their prices.
     */
    public void viewMenu()
    {
        System.out.println("Food Menu");
        for (String foodName : menu.keySet())
        {
            Food food = menu.get(foodName);

            System.out.println(foodName + ": $" + food.getPrice());
        }
    }
    
    /**
     * Displays the number of orders ahead and the estimated wait time of an 
     * order.
     */
    public void viewOrder()
    {
        OrderInfo order = getCurrentOrder();

        if (order == null)
        {
            System.out.println("You do not have a current order.");
            return;
        }
        
       // double waitTime = totalWaitTimeForOrder(order);
        double waitTime = orderQueue.getTotalWaitTime();

        if (waitTime >= 0) {
            //System.out.println("Orders ahead: " + getOrdersAhead(order));
            System.out.println("Orders ahead: " + orderQueue.getOrdersAhead(order));
            System.out.println("Estimated wait time: " + waitTime + " minutes");
        }
        else {
            System.out.println("Order not found in queue.");
        }
    }
}
