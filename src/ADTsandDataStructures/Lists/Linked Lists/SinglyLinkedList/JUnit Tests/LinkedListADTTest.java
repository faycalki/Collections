
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class for implementations of the LinkedListADT interface.
 *
 * @author Faycal Kilali
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListADTTest {

    private SinglyLinkedList<String> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testAddToFront() {
        list.addToFront("A");
        assertEquals("[A]", list.toString());
        list.addToFront("B");
        assertEquals("[B, A]", list.toString());
    }

    @Test
    public void testAddToBack() {
        list.addToBack("A");
        assertEquals("[A]", list.toString());
        list.addToBack("B");
        assertEquals("[A, B]", list.toString());
    }

    @Test
    public void testAddAfter() {
        list.addToBack("A");
        list.addToBack("B");
        list.addAfter("C", "A");
        assertEquals("[A, C, B]", list.toString());
    }



    @Test
    public void testRemoveFromFront() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("A", list.removeFromFront());
        assertEquals("[B]", list.toString());
    }

    @Test
    public void testRemoveFromBack() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("B", list.removeFromBack());
        assertEquals("[A]", list.toString());
    }

    @Test
    public void testRemoveFirstInstance() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.removeFirstInstance("A");
        assertEquals("[B, A]", list.toString());
    }

    @Test
    public void testRemoveAllInstances() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.removeAllInstances("A");
        assertEquals("[B]", list.toString());
    }


    @Test
    public void testToString() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("[A, B]", list.toString());
    }

}


