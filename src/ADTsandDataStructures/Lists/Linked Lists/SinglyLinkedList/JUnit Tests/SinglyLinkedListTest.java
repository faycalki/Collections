import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    private SinglyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
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
    void addToPosition() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToPosition("C", 1);
        assertEquals("[A, C, B]", list.toString());
    }

    @Test
    public void testRemove() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("B", list.removeFromBack());
        assertEquals("[A]", list.toString());
    }

    @Test
    public void testGetSize() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals(2, list.getSize());
    }

    @Test
    public void testIsEmpty() {
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
    void remove() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("A", list.remove());
        assertEquals("[B]", list.toString());
    }

    @Test
    void removeFromFront() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("A", list.removeFromFront());
        assertEquals("[B]", list.toString());
    }

    @Test
    void removeFromBack() {
        list.addToBack("A");
        list.addToBack("B");
        assertEquals("B", list.removeFromBack());
        assertEquals("[A]", list.toString());
    }

    @Test
    void removeFirstInstance() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.removeFirstInstance("A");
        assertEquals("[B, A]", list.toString());
    }

    @Test
    void removeAllInstances() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("A");
        list.removeAllInstances("A");
        assertEquals("[B]", list.toString());
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
}
