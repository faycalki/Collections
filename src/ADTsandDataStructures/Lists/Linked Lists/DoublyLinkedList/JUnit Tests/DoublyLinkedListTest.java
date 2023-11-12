import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DoublyLinkedList
 * @author Faycal Kilali
 * @version 1.0
 */
class DoublyLinkedListTest {

    private DoublyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    void add() {
        list.add("A");
        assertEquals("[A]", list.toString());
        list.add("B");
        assertEquals("[B, A]", list.toString());
    }

    @Test
    void addToFront() {
        list.addToFront("A");
        assertEquals("[A]", list.toString());
        list.addToFront("B");
        assertEquals("[B, A]", list.toString());
    }

    @Test
    void addToBack() {
        list.addToBack("A");
        assertEquals("[A]", list.toString());
        list.addToBack("B");
        assertEquals("[A, B]", list.toString());
    }

    @Test
    void addAfter() {
        list.addToBack("A");
        list.addToBack("B");
        list.addAfter("C", "A");
        assertEquals("[A, C, B]", list.toString());
    }

    @Test
    void remove() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("B", list.removeFromBack());
        assertEquals("[A]", list.toString());
    }

    @Test
    void getSize() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals(2, list.getSize());
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.addToBack("A");
        assertFalse(list.isEmpty());
    }

    @Test
    void traverseLinkedList() {
        list.addToBack("A");
        list.addToBack("B");
        Node<String> result = list.traverseLinkedList("A");
        assertEquals("A", result.getData());
    }

    @Test
    void traverseLinkedListToPosition() {
        list.addToBack("A");
        list.addToBack("B");
        Node<String> result = list.traverseLinkedListToPosition(1);
        assertEquals("B", result.getData());
    }

    @Test
    void testRemoveFirstInstance() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.addToBack("C");

        assertEquals("[A, B, A, C]", list.toString());

        list.removeFirstInstance("A");

        assertEquals("[B, A, C]", list.toString());
        assertEquals("B", list.getHeadData().getData());
        assertEquals("C", list.getHeadData().getNext().getNext().getData());

        // Check backward links
        assertNull(list.getHeadData().getPrevReference());
        assertEquals("B", list.getHeadData().getNext().getPrevReference().getData());
        assertEquals("A", list.getHeadData().getNext().getNext().getPrevReference().getData());
    }

    @Test
    void testRemoveAllInstances() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.addToBack("C");

        assertEquals("[A, B, A, C]", list.toString());

        list.removeAllInstances("A");

        assertEquals("[B, C]", list.toString());
        assertEquals("B", list.getHeadData().getData());

        // Check backward links
        assertNull(list.getHeadData().getPrevReference());
        assertEquals("B", list.getHeadData().getNext().getPrevReference().getData());
    }


    @Test
    void testToString() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("[A, B]", list.toString());
    }

    @Test
    void getHeadData() {
        list.addToBack("A");
        assertEquals("A", list.getHeadData().getData());
    }

    @Test
    void setHeadData() {
        Node<String> newNode = new Node<>("C");
        list.setHeadData(newNode);
        assertEquals(newNode, list.getHeadData());
    }

    @Test
    void checkDataAtLocation() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("A", list.checkDataAtLocation(0));
        assertEquals("B", list.checkDataAtLocation(1));
    }
}