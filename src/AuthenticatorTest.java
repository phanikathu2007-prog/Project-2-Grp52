import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
public class AuthenticatorTest {
    private OrderQueue orderQueue;
    private Authenticator authenticator;
    private UUID testUUID;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before
    public void setUp()
    {
        orderQueue = new OrderQueue();
        authenticator = new Authenticator(orderQueue);
        testUUID = UUID.randomUUID();
    }

    /**
     * Tests that passing a null order queue to the Authenticator 
     * throws an IllegalArgumentException.
     */
    @Test
    public void testConstructorNullQueue()
    {
        try
        {
            new Authenticator(null);
            fail("Expected IllegalArgumentException for null order queue.");
        }
        catch (IllegalArgumentException e)
        {
            assertNotNull(e);
        }
    }

    /**
     * Tests successfully canceling an order that exists in the queue.
     */
    @Test
    public void testCancelExistingOrder()
    {
        LinkedChain<Food> foods = new LinkedChain<Food>();
        foods.add(new Food("Burger", 7.00, 15.00));
        
        OrderInfo testOrder = new OrderInfo("Alice", foods, testUUID, 1);
        orderQueue.addOrder(testOrder);
        
        assertEquals(1, orderQueue.getNumberOfOrders());

        boolean result = authenticator.cancelOrder(testUUID);
        
        assertTrue("Cancellation should return true for an existing order", result);
        assertEquals(0, orderQueue.getNumberOfOrders());
    }

    /**
     * Tests attempting to cancel an order UUID that is not in the queue.
     */
    @Test
    public void testCancelNonExistentOrder()
    {
        UUID randomUUID = UUID.randomUUID();
        boolean result = authenticator.cancelOrder(randomUUID);
        
        assertFalse("Cancellation should return false for a non-existent order", result);
    }

    /**
     * Tests passing a null UUID to cancelOrder.
     */
    @Test
    public void testCancelNullUUID()
    {
        boolean result = authenticator.cancelOrder(null);
        
        assertFalse("Cancellation should return false when given a null UUID", result);
    }
}
