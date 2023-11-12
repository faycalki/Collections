import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void testEnqueue() {
        try {
            queue.enqueue("A");
            assertFalse(queue.isEmpty());
        } catch (QueueOverflowException e) {
            fail("Unexpected QueueOverflowException");
        }

        try {
            queue.enqueue("B");
            assertFalse(queue.isEmpty());
        } catch (QueueOverflowException e) {
            fail("Unexpected QueueOverflowException");
        }

    }

    @Test
    void testDequeue() {
        // Test dequeue without elements
        assertThrows(QueueUnderflowException.class, () -> queue.dequeue());

        // Enqueue an element
        try {
            queue.enqueue("A");
        } catch (QueueOverflowException e) {
            fail("Unexpected QueueOverflowException");
        }

        // Test dequeue with elements
        assertDoesNotThrow(() -> {
            try {
                String result = queue.dequeue();
                assertTrue(queue.isEmpty());
                assertEquals("A", result);
            } catch (QueueUnderflowException e) {
                fail("Unexpected QueueUnderflowException");
            }
        });

    }

    @Test
    void testIsFull() throws QueueOverflowException {
        assertFalse(queue.isFull());
    }

    @Test
    void testIsEmpty() throws QueueOverflowException, QueueUnderflowException {
        assertTrue(queue.isEmpty());

        queue.enqueue("A");
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testSize() throws QueueOverflowException{
        assertEquals(0, queue.size());
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals(2, queue.size());
    }

    @Test
    void find() throws QueueOverflowException{
        assertEquals(queue.find("A"),-1);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals(queue.find("A"),2);
        assertEquals(queue.find("C"),0);
        assertEquals(queue.find("D"),-1);
        assertEquals(queue.find("B"), 1);
    }


    @Test
    void contains() throws QueueOverflowException{

        assertEquals(queue.contains("A"),false);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals(queue.contains("A"),true);
        assertEquals(queue.contains("C"),true);
        assertEquals(queue.contains("D"),false);

    }


    @Test
    void reverse() throws QueueOverflowException{
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue.toString());
        queue.reverse();
        System.out.println(queue.toString());
        //assert("Queue: [C, B, A]".equals(queue.toString()));
    }


    @Test
    void testQueueUnderflowException() {
        // Trying to dequeue an empty queue should throw QueueUnderflowException
        assertThrows(QueueUnderflowException.class, () -> queue.dequeue());
    }

    @Test
    void testToString() throws QueueOverflowException {
        // Enqueue elements to the queue
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        // Check the string representation
        assertEquals("Queue: [C, B, A]", queue.toString());
    }

}
