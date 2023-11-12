

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class to ensure the appropriate behaviors of any class
 * that implements the ListADT interface.
 *
 * @author  Ada Clevinger
 * @version Sep 29, 2023
 */
public class ListADTTest
{
    
    private ListADT<String> list;
    
    public ListADTTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        list = new SinglyLinkedList<String>();
    }

    
    @Test
    public void testAdd(){
        assertEquals(true, list.isEmpty());
        list.add("One");
        assertEquals(1, list.getSize());
        assertEquals(false, list.isEmpty());
        list.add("Two");
        assertEquals(2, list.getSize());
        assertEquals(false, list.isEmpty());
        list.add("Two");
        assertEquals(3, list.getSize());
        assertEquals(false, list.isEmpty());
    }
    
    @Test
    public void testRemove(){
        assertEquals(true, list.isEmpty());
        list.add("One");
        list.add("Two");
        list.add("Three");
        assertEquals(3, list.getSize());
        assertEquals("Three", list.remove());
        assertEquals(2, list.getSize());
        assertEquals("Two", list.remove());
        assertEquals(1, list.getSize());
        assertEquals("One", list.remove());
        assertEquals(true, list.isEmpty());
        assertEquals(null, list.remove());
        assertEquals(true, list.isEmpty());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
