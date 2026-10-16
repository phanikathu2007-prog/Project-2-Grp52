public class FoodTest {
    import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Food class.
 */
public class FoodTest
{
    private static final double DELTA = 0.0001;

    // ---------- Valid construction ----------

    @Test
    public void testConstructorValidInput()
    {
        Food food = new Food("Pizza", 12.99, 20.5);

        assertEquals("Pizza", food.getName());
        assertEquals(12.99, food.getPrice(), DELTA);
        assertEquals(20.5, food.getPrepTime(), DELTA);
    }

    @Test
    public void testConstructorZeroPriceAllowed()
    {
        Food food = new Food("Free Sample", 0.00, 5.0);

        assertEquals(0.00, food.getPrice(), DELTA);
    }

    @Test
    public void testConstructorZeroPrepTimeAllowed()
    {
        Food food = new Food("Salad", 6.50, 0.00);

        assertEquals(0.00, food.getPrepTime(), DELTA);
    }

    @Test
    public void testConstructorZeroPriceAndPrepTimeAllowed()
    {
        Food food = new Food("Water", 0.00, 0.00);

        assertEquals("Water", food.getName());
        assertEquals(0.00, food.getPrice(), DELTA);
        assertEquals(0.00, food.getPrepTime(), DELTA);
    }

    @Test
    public void testConstructorSingleCharacterName()
    {
        Food food = new Food("X", 1.00, 1.00);

        assertEquals("X", food.getName());
    }

    // Tests Invalid names

    @Test
    public void testConstructorNullName()
    {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new Food(null, 10.00, 10.0));

        assertEquals("Invalid food information.", e.getMessage());
    }

    @Test
    public void testConstructorEmptyName()
    {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new Food("", 10.00, 10.0));

        assertEquals("Invalid food information.", e.getMessage());
    }

    // Tests invalid prices

    @Test
    public void testConstructorNegativePrice()
    {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new Food("Burger", -0.01, 10.0));

        assertEquals("Invalid food information.", e.getMessage());
    }

    @Test
    public void testConstructorLargeNegativePrice()
    {
        assertThrows(IllegalArgumentException.class,
            () -> new Food("Burger", -100.00, 10.0));
    }

    // ---------- Invalid prep time ----------

    @Test
    public void testConstructorNegativePrepTime()
    {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new Food("Burger", 10.00,
}
