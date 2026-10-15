import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

/**
 * Tests the OrderQueue class.
 *
 * @author Phani Kathuroju
 * @version 10.15.26
 */
public class OrderQueueTest
{
    private OrderQueue queue;
    private OrderInfo order1;
    private OrderInfo order2;

    /**
     * Sets up the test objects.
     */
    @Before
    public void setUp()
    {
        queue = new OrderQueue();
        LinkedChain<Food> foods1 = new LinkedChain<Food>();
        LinkedChain<Food> foods2 = new LinkedChain<Food>();

        order1 = new OrderInfo("Alice", foods1, UUID.randomUUID(), 1);
        order2 = new OrderInfo("Bob", foods2, UUID.randomUUID(), 2);
    }

    /**
     * Tests adding orders and getting the number of orders.
     */
    @Test
    public void testAddOrder()
    {
        assertEquals(0, queue.getNumberOfOrders());

        queue.addOrder(order1);
        queue.addOrder(order2);

        assertEquals(2, queue.getNumberOfOrders());

        queue.addOrder(null);
        assertEquals(2, queue.getNumberOfOrders());
    }

    /**
     * Tests getting the number of orders ahead.
     */
    @Test
    public void testGetOrdersAhead()
    {
        queue.addOrder(order1);
        queue.addOrder(order2);

        assertEquals(0, queue.getOrdersAhead(order1));
        assertEquals(1, queue.getOrdersAhead(order2));
        assertEquals(-1, queue.getOrdersAhead(null));
    }

    /**
     * Tests getting the total wait time.
     */
    @Test
    public void testGetTotalWaitTime()
    {
        assertEquals(0.0, queue.getTotalWaitTime(), 0.001);

        queue.addOrder(order1);
        queue.addOrder(order2);

        double expected = order1.getWaitTime() + order2.getWaitTime();

        assertEquals(expected, queue.getTotalWaitTime(), 0.001);
    }
}