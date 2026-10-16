import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Food class.
 */
public class FoodTest
{
    private static final double DELTA = 0.0001;

    @Test
    public void testConstructorValidInput()
    {
        Food food = new Food("Pizza", 12.99, 20.5);

        assertEquals("Pizza", food.getName());
        assertEquals(12.99, food.getPrice(), DELTA);
        assertEquals(20.5, food.getPrepTime(), DELTA);
    }

    @Test
    public void testConstructorZeroPriceAndPrepTime()
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

    @Test
    public void testConstructorNullName()
    {
        try
        {
            new Food(null, 10.00, 10.0);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Invalid food information.", e.getMessage());
        }
    }

    @Test
    public void testConstructorEmptyName()
    {
        try
        {
            new Food("", 10.00, 10.0);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Invalid food information.", e.getMessage());
        }
    }

    @Test
    public void testConstructorNegativePrice()
    {
        try
        {
            new Food("Burger", -0.01, 10.0);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Invalid food information.", e.getMessage());
        }
    }

    @Test
    public void testConstructorNegativePrepTime()
    {
        try
        {
            new Food("Burger", 10.00, -0.01);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Invalid food information.", e.getMessage());
        }
    }

    @Test
    public void testGetName()
    {
        Food food = new Food("Burger", 7.00, 15.0);

        assertEquals("Burger", food.getName());
    }

    @Test
    public void testGetPrice()
    {
        Food food = new Food("Fries", 3.00, 5.0);

        assertEquals(3.00, food.getPrice(), DELTA);
    }

    @Test
    public void testGetPrepTime()
    {
        Food food = new Food("Pizza", 12.99, 20.5);

        assertEquals(20.5, food.getPrepTime(), DELTA);
    }
}
