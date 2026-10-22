import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

/**
 *  Tests the Client class.
 */
public class ClientTest
{
    private Client client;
    private HashMap<String, Food> menu;
    private OrderQueue orderQueue;
    private Scanner scanner;
    private Food burger;
    private Food fries;
    private UUID customerUUID;
    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream output;

    /**
     * Sets up objects before each test.
     */
    @Before
    public void setUp()
    {
        menu = new HashMap<String, Food>();
        orderQueue = new OrderQueue();

        burger = new Food("Burger", 7.00, 15.00);
        fries = new Food("Fries", 3.00, 5.00);

        menu.put("Burger", burger);
        menu.put("Fries", fries);

        customerUUID = UUID.randomUUID();

        scanner = new Scanner(
            new ByteArrayInputStream(new byte[0]));

        client = new Client(
            menu,
            orderQueue,
            scanner,
            "Alex",
            customerUUID,
            1);

        originalIn = System.in;
        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    /**
     * Restores standard input and output after each test.
     */
    @org.junit.After
    public void tearDown()
    {
        System.setIn(originalIn);
        System.setOut(originalOut);
        scanner.close();
    }

    /**
     * Tests getting the current order before placing an order.
     */
    @Test
    public void testGetCurrentOrderInitiallyNull()
    {
        assertNull(client.getCurrentOrder());
    }

    /**
     * Tests adding a valid food item.
     */
    @Test
    public void testPlaceOrderValidFood()
    {
        client.placeOrder(burger, false);

        assertTrue(output.toString().contains(
            "Food item added to your order."));

        client.placeOrder(null, true);

        assertNotNull(client.getCurrentOrder());
        assertEquals(
            7.00,
            client.getCurrentOrder().getTotalPrice(),
            0.001);
    }

    /**
     * Tests rejecting a null food item.
     */
    @Test
    public void testPlaceOrderNullFood()
    {
        client.placeOrder(null, false);

        assertTrue(output.toString().contains(
            "Unable to add this item. Please select a valid food item"));
        assertNull(client.getCurrentOrder());
    }

    /**
     * Tests rejecting a food item that is not in the menu.
     */
    @Test
    public void testPlaceOrderInvalidFood()
    {
        Food pizza = new Food("Pizza", 9.00, 20.00);

        client.placeOrder(pizza, false);

        assertTrue(output.toString().contains(
            "Invalid food selection, please select an item from the menu"));
        assertNull(client.getCurrentOrder());
    }

    /**
     * Tests that an empty order cannot be submitted.
     */
    @Test
    public void testPlaceOrderEmptyOrder()
    {
        client.placeOrder(null, true);

        assertTrue(output.toString().contains(
            "Your order is empty.  Please select at least one food item"));
        assertNull(client.getCurrentOrder());
    }

    /**
     * Tests submitting an order with multiple food items.
     */
    @Test
    public void testPlaceOrderMultipleItems()
    {
        client.placeOrder(burger, false);
        client.placeOrder(fries, false);
        client.placeOrder(null, true);

        OrderInfo order = client.getCurrentOrder();

        assertNotNull(order);
        assertEquals(10.00, order.getTotalPrice(), 0.001);
        assertEquals(customerUUID, order.getUUID());
        assertTrue(output.toString().contains(
            "Your order has been placed!"));
    }

    /**
     * Tests that food selections reset after submission.
     */
    @Test
    public void testPlaceOrderResetsSelections()
    {
        client.placeOrder(burger, false);
        client.placeOrder(null, true);

        client.placeOrder(null, true);

        assertTrue(output.toString().contains(
            "Your order is empty. Please select at least one food item"));
    }

    /**
     * Tests that placeOrder() accepts customer input.
     */
    @Test
    public void testPlaceOrderWithScanner()
    {
        scanner.close();

        scanner = new Scanner(
            new ByteArrayInputStream(
                "Burger\ndone\n".getBytes()));

        client = new Client(
            menu,
            orderQueue,
            scanner,
            "Alex",
            customerUUID,
            1);

        client.placeOrder();

        assertNotNull(client.getCurrentOrder());
        assertEquals(
            7.00,
            client.getCurrentOrder().getTotalPrice(),
            0.001);
    }

    /**
     * Tests displaying the food menu.
     */
    @Test
    public void testViewMenu()
    {
        System.out.println("Food Menu");
        for (String foodName : menu.keySet())
        {
            Food food = menu.get(foodName);
            System.out.println(foodName + ": $"
                + String.format("%.2f", food.getPrice()));
        }
    }

    /**
     * Tests viewing an order when no order exists.
     */
    @Test
    public void testViewOrderNoCurrentOrder()
    {
        client.viewOrder();

        assertTrue(output.toString().contains(
            "You do not have a current order."));
    }
    
    /**
     * Tests viewing an order when it is in the queue.
     */
    @Test
    public void testViewOrderOrderInQueue()
    {
        Food burger1 = menu.get("Burger");

        client.placeOrder(burger1, false);
        client.placeOrder(null, true);

        output.reset();
        client.viewOrder();

        String result = output.toString();

        assertTrue(result.contains("Orders ahead: 0"));
        assertTrue(result.contains("Estimated wait time: 15.00 minutes"));
    }
}
