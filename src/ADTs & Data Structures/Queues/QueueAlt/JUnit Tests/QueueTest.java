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

        // Add more enqueue tests as needed
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

        // Add more dequeue tests as needed
    }

    @Test
    void testIsFull() throws QueueOverflowException {
        assertFalse(queue.isFull());

        // Fill the queue to its max capacity
        for (int i = 0; i < queue.getMaxCapacity(); i++) {
            queue.enqueue("Element" + i);
        }

        assertTrue(queue.isFull());
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
    void testChangeSize() throws InsufficientSizeException {
        // Initial size is queue.MAX_CAPACITY
        // We'll change size to a larger value
        queue.changeSize(2 * queue.getMaxCapacity());
        //assertEquals(2 * queue.getMaxCapacity(), queue.size());

        // We'll first add elements up to the new MAX_CAPACITY then try to change size to a smaller value, this should in fact throw a InsufficientSizeException
        for (int i = 0; i < queue.getMaxCapacity(); i++){
            try{
                queue.enqueue("Element" + i);
            }
            catch(QueueOverflowException e){
                fail("Unexpected QueueOverflowException");
            }
        }

        assertThrows(InsufficientSizeException.class, () -> queue.changeSize(queue.getMaxCapacity() - 1));
    }


    @Test
    void testQueueOverflowException() {
        // Fill the queue to its max capacity
        for (int i = 0; i < queue.getMaxCapacity(); i++) {
            try {
                queue.enqueue("Element" + i);
            } catch (QueueOverflowException e) {
                fail("Unexpected QueueOverflowException");
            }
        }

        // Trying to enqueue one more should throw QueueOverflowException
        assertThrows(QueueOverflowException.class, () -> queue.enqueue("Overflow"));
    }

    @Test
    void testQueueUnderflowException() {
        // Trying to dequeue an empty queue should throw QueueUnderflowException
        assertThrows(QueueUnderflowException.class, () -> queue.dequeue());
    }

    @Test
    void testInsufficientSizeException() {

        for (int i = 0; i < queue.getMaxCapacity(); i++){
            try{
                queue.enqueue("Element" + i);
            }
            catch(QueueOverflowException e){
                fail("Unexpected QueueOverflowException");
            }
        }

        // Initial size is queue.getMaxCapacity
        assertEquals(queue.getMaxCapacity(), queue.size());

        // Trying to change size to a smaller value should throw InsufficientSizeException
        assertThrows(InsufficientSizeException.class, () -> queue.changeSize(queue.getMaxCapacity() - 1));
    }

    @Test
    void testToString() throws QueueOverflowException {
        // Enqueue elements to the queue
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        // Check the string representation
        assertEquals("Queue: [A, B, C]", queue.toString());
    }

}
