import java.util.UUID;

/**
 * 
 * Authenticator class handles verification and removal logic for customer orders
 */
public class Authenticator{
    private OrderQueue orderQueue;

    /**
     * Initializes a new Authenticator with the specified order queue.
     * 
     * @param orderQueue the OrderQueue instance to manage cancellations against
     * @throws IllegalArgumentException if the provided orderQueue is null
     */
    public Authenticator(OrderQueue orderQueue)
    {
        if (orderQueue == null)
        {
            throw new IllegalArgumentException("Order queue cannot be null.");
        }
        this.orderQueue = orderQueue;
    }

    /**
     * Attempts to cancel an order matching the given customer UUID.
     * Validates the UUID and coordinates with the OrderQueue to remove the order.
     * 
     * @param customerUUID the UUID of the order to cancel
     * @return true if the order was successfully found and cancelled, false otherwise
     */
    public boolean cancelOrder(UUID customerUUID)
    {
        if (customerUUID == null)
        {
            System.out.println("Incorrect password. Your order has not been cancelled");
            return false;
        }
        
        if (orderQueue.removeOrder(customerUUID))
        {
            System.out.println("Your order has been successfully cancelled.");
            return true;
        }

        System.out.println("Order not found. Please check your order number and try again");
        return false;
    }
}