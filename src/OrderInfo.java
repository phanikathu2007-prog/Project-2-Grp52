import java.util.UUID;

/**
 *  Represents a customer's order and stores the customer name, order number, 
 *  UUID info, selected food items, total price, and estimated wait time for 
 *  their order.
 *  
 */
public class OrderInfo
{
    private String customerName;
    private double totalPrice;
    private int orderNumber;
    private LinkedChain<Food> foodSelections;
    private UUID customerUUID;
    private double waitTime;
    
    /**
     * Initializes all the fields for an order.
     * @param customerName a customer name
     * @param foodSelections the specific food items a customer ordered
     * @param customerUUID the UUID associated with an order
     * @param orderNumber the order number associated with an order
     */
    public OrderInfo(String customerName, LinkedChain<Food> foodSelections, 
        UUID customerUUID, int orderNumber)
    {
        this.customerName = customerName;
        this.foodSelections = foodSelections;
        this.customerUUID = customerUUID;
        this.orderNumber = orderNumber;
    }
    
    /**
     * Returns the total price of an order.
     * @return the toal price of an order
     */
    public double getTotalPrice()
    {
        totalPrice = 0.0;

        for (int i = 0; i < foodSelections.size(); i++)
        {
            totalPrice += foodSelections.get(i).getPrice();
        }

        return totalPrice;
    }
    
    /**
     * Returns the estimated wait time for an order.
     * @return the estimated wait time for an order
     */
    public double getWaitTime()
    {
        waitTime = 0.00;

        for (int i = 0; i < foodSelections.size(); i++) {
            waitTime += foodSelections.get(i).getPrepTime();
        }

        return waitTime;
    }
    
    /**
     * Returns the UUID associated with an order
     * @return the UUID stored when the order was created
     */
    public UUID getUUID()
    {
        return customerUUID;
    }
}
