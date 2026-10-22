
import java.util.UUID;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Runner class for VT Dining Order Master.
 *
 * Displays the main menu and calls the appropriate methods
 * from Client to handle customer actions.
 *
 * @author Phani Kathuroju
 * @version 10.14.26
 */
public class VTOrderMaster
{
    private static Client client;
    private static HashMap<String, Food> menu;
    private static OrderQueue orderQueue;
    private static Authenticator authenticator;
    private static int nextOrderNumber = 1;

    /**
     * Starts the java program, displays the main menu, and handles the 
     * customers choices.
     * @param args An array of String objects that stores command-line 
     * arguments passed to the program at runtime
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        menu = new HashMap<String, Food>();
        orderQueue = new OrderQueue();
        authenticator = new Authenticator(orderQueue);
        menu.put("Burger", new Food("Burger", 7.00, 15.00));
        menu.put("Fries", new Food("Fries", 3.00, 5.00));
        menu.put("Cookie", new Food("Cookie", 5.00, 10.00));
        menu.put("Donut", new Food("Donut", 4.00, 9.00));
        menu.put("Pizza", new Food("Pizza", 7.00, 11.00));


        
        boolean running = true;

        while (running)
        {
            System.out.println("\nVT Dining Order Master");
            System.out.println("1. Place an order");
            System.out.println("2. View orders ahead and estimated wait");
            System.out.println("3. View food menu");
            System.out.println("4. Cancel an order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1"))
            {
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();
                
                UUID customerUUID = UUID.randomUUID();
                
                client = new Client(
                    menu,
                    orderQueue,
                    scanner,
                    customerName,
                    customerUUID,
                    nextOrderNumber
                );
                int ordersBefore = orderQueue.getNumberOfOrders();

                client.placeOrder();
                if (orderQueue.getNumberOfOrders() > ordersBefore)
                {
                    System.out.println("Your order number is: " + nextOrderNumber);
                    System.out.println("Your cancellation UUID is: " + customerUUID);

                    nextOrderNumber++;
                }
            }
            else if (choice.equals("2"))
            {
                System.out.println("Orders ahead: " + orderQueue.getNumberOfOrders());
                System.out.println("Estimated wait time: "+ orderQueue.getTotalWaitTime() + " minutes");
            }
            else if (choice.equals("3"))
            {
                if (client != null)
                {
                    client.viewMenu();
                }
                else
                {
                    System.out.println("Food Menu");
                    for (String foodName : menu.keySet())
                    {
                        Food food = menu.get(foodName);
            
                        System.out.println(foodName + ": $" + food.getPrice());
                    }
                }
            }
            else if (choice.equals("4"))
            {
                System.out.print("Enter your order UUID to cancel: ");
                String uuidInput = scanner.nextLine();
                try
                {
                    UUID cancelUUID = UUID.fromString(uuidInput);
                    authenticator.cancelOrder(cancelUUID);
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Incorrect password. Your order has not been cancelled");
                }  
            }
            else if (choice.equals("5"))
            {
                running = false;
                System.out.println("Goodbye, come back when you're hungry!");
            }
            else
            {
                System.out.println(
                    "Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }
}
