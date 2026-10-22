import java.util.UUID;
public class Authenticator{
    private OrderQueue orderQueue;

    public Authenticator(OrderQueue orderQueue)
    {
        if (orderQueue == null)
        {
            throw new IllegalArgumentException("Order queue cannot be null.");
        }
        this.orderQueue = orderQueue;
    }

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