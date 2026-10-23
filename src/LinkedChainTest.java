import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the LinkedChain class
 * 
 * @author Phani Kathuroju
 * @version 10.22.26
 */
public class LinkedChainTest {
    private LinkedChain<String> chain;

    /**
     * Sets up the test fixture before each test method by initializing 
     * a new empty LinkedChain instance.
     */
    @Before
    public void setUp(){
        chain = new LinkedChain<String>();
    }

    /**
     * Tests adding an element to the chain and verifies size and correct placement.
     */
    @Test
    public void testAdd(){
        chain.add("C");
        assertEquals(1, chain.size());
        assertEquals("C", chain.get(0));
    }

    /**
     * Tests that attempting to add a null element throws an IllegalArgumentException 
     * and leaves the chain empty.
     */
    @Test 
    public void testAddNull()
    {
        try
        {
            chain.add(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(chain.isEmpty());
        }
    }


    /**
     * Tests successfully removing an element from the chain and verifies 
     * that the remaining elements shift correctly and size updates.
     */
    @Test
    public void testRemove()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("B"));
        assertEquals("A", chain.get(0));
        assertEquals("C", chain.get(1));
        assertEquals(2, chain.size());
    }

    /**
     * Tests attempting to remove an element that is not present in the chain.
     */
    @Test
    public void testRemoveMissingElement()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertFalse(chain.remove("D"));
        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests checking if the chain contains an existing element.
     */
    @Test
    public void testContains()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.contains("B"));
    }

    /**
     * Tests checking if the chain contains an element that is not present.
     */
    @Test
    public void testContainsMissingElement()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertFalse(chain.contains("D"));
    }

    /**
     * Tests retrieving an element at a valid index.
     */
    @Test
    public void testGet()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("B", chain.get(1));
    }

    /**
     * Tests that attempting to get an element from an invalid index 
     * throws an IndexOutOfBoundsException.
     */
    @Test
    public void testGetInvalidIndex()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        try
        {
            chain.get(5);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertEquals(3, chain.size());
        }
    }

    /**
     * Tests the size method on a non-empty chain.
     */
    @Test
    public void testSize()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals(3, chain.size());
    }

    /**
     * Tests the size method on an newly initialized empty chain.
     */
    @Test
    public void testSizeEmptyChain()
    {
        assertEquals(0, chain.size());
    }
}
