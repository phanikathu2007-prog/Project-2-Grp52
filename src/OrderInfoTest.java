import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

/**
 *  Tests the OrderInfo class.
 */
public class OrderInfoTest
{
    private OrderInfo order;
    private LinkedChain<Food> foodSelections;
    private Food burger;
    private Food fries;
    private UUID customerUUID;

    /**
     * Sets up the test objects.
     */
    @Before
    public void setUp()
    {
        foodSelections = new LinkedChain<Food>();

        burger = new Food("Burger", 7.00, 15.00);
        fries = new Food("Fries", 3.00, 5.00);

        foodSelections.add(burger);
        foodSelections.add(fries);

        customerUUID = UUID.randomUUID();

        order = new OrderInfo(
            "Alex",
            foodSelections,
            customerUUID,
            1);
    }

    /**
     * Tests getting the total price of an order.
     */
    @Test
    public void testGetTotalPrice()
    {
        assertEquals(10.00, order.getTotalPrice(), 0.001);
    }

    /**
     * Tests getting the wait time for an order.
     */
    @Test
    public void testGetWaitTime()
    {
        assertEquals(
            burger.getPrepTime() + fries.getPrepTime(),
            order.getWaitTime(),
            0.001);
    }

    /**
     * Tests getting the UUID of an order.
     */
    @Test
    public void testGetUUID()
    {
        assertEquals(customerUUID, order.getUUID());
    }

    /**
     * Tests the total price of an empty order.
     */
    @Test
    public void testGetTotalPriceEmptyOrder()
    {
        LinkedChain<Food> emptySelections =
            new LinkedChain<Food>();

        OrderInfo emptyOrder = new OrderInfo(
            "Alex",
            emptySelections,
            customerUUID,
            2);

        assertEquals(0.0, emptyOrder.getTotalPrice(), 0.001);
    }

    /**
     * Tests the wait time for an empty order.
     */
    @Test
    public void testGetWaitTimeEmptyOrder()
    {
        LinkedChain<Food> emptySelections =
            new LinkedChain<Food>();

        OrderInfo emptyOrder = new OrderInfo(
            "Alex",
            emptySelections,
            customerUUID,
            2);

        assertEquals(0.0, emptyOrder.getWaitTime(), 0.001);
    }

    /**
     * Tests the total price of an order with one item.
     */
    @Test
    public void testGetTotalPriceOneItem()
    {
        LinkedChain<Food> selections =
            new LinkedChain<Food>();

        selections.add(burger);

        OrderInfo singleItemOrder = new OrderInfo(
            "Alex",
            selections,
            customerUUID,
            3);

        assertEquals(7.00, singleItemOrder.getTotalPrice(), 0.001);
    }

    /**
     * Tests the wait time for an order with one item.
     */
    @Test
    public void testGetWaitTimeOneItem()
    {
        LinkedChain<Food> selections =
            new LinkedChain<Food>();

        selections.add(burger);

        OrderInfo singleItemOrder = new OrderInfo(
            "Alex",
            selections,
            customerUUID,
            3);

        assertEquals(
            burger.getPrepTime(),
            singleItemOrder.getWaitTime(),
            0.001);
    }
}
