import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedChainTest {
    private LinkedChain<String> chain;

    @Before
    public void setUp(){
        chain = new LinkedChain<String>();
    }

    @Test
    public void testAdd(){
        chain.add("C");
        assertEquals(1, chain.size());
        assertEquals("C", chain.get(0));
    }

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

    @Test
    public void testContains()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.contains("B"));
    }

    @Test
    public void testContainsMissingElement()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertFalse(chain.contains("D"));
    }

    @Test
    public void testGet()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("B", chain.get(1));
    }

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

    // Public int size()
    @Test
    public void testSize()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals(3, chain.size());
    }

    @Test
    public void testSizeEmptyChain()
    {
        assertEquals(0, chain.size());
    }
}
