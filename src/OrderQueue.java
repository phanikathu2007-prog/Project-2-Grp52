/**
 *  Owns the LinkedChain queue of active orders.
 *  
 */
public class OrderQueue
{
    private LinkedChain<OrderInfo> orders;

    /**
     * Create a new OrderQueue object.
     */
    public OrderQueue()
    {
        orders = new LinkedChain<OrderInfo>();
    }

    /**
     * Adds an order to the end of the queue.
     * @param order the order to be added
     */
    public void addOrder(OrderInfo order)
    {
        if (order != null)
        {
            orders.addToEnd(order);
        }
    }

    /**
     * Returns the number of orders in the queue.
     * @return the number of orders
     */
    public int getNumberOfOrders()
    {
        return orders.size();
    }
    
    /**
     * Returns the number of orders ahead of a given order.
     * @param order the customer's order
     * @return the number of orders ahead of the customer's order
     */
    public int getOrdersAhead(OrderInfo order)
    {        
        if (order == null)
        {
            return -1;
        }
        
        for (int i = 0; i < orders.size(); i++)
        {
            OrderInfo current = orders.get(i);

            if (current.getUUID().equals(order.getUUID()))
            {
                return i;
            }
        }

        return -1;
    }

    /**
    * Returns the total estimated preparation time of all
    * orders currently in the queue.
    * @return total preparation time in minutes
    */
    public double getTotalWaitTime()
    {
        double totalWaitTime = 0;

        for (int i = 0; i < orders.size(); i++)
        {
            totalWaitTime += orders.get(i).getWaitTime();
        }

        return totalWaitTime;
    }

    /**
     * Returns the estimated wait time until the specified order is ready.
     *
     * @param order the customer's order
     * @return estimated wait time in minutes, or -1 if order not found
     */
    public double totalWaitTimeForOrder(OrderInfo order)
    {
        if (order == null)
        {
            return -1;
        }
    
        double total = 0.00;
    
        for (int i = 0; i < orders.size(); i++)
        {
            OrderInfo current = orders.get(i);
    
            total += current.getWaitTime();
    
            if(current.getUUID().equals(order.getUUID()))
            {
                return total;
            }
        }
    
        return -1;
    }
}
