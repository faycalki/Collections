import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<PriorityDouble> priorityQueue;

    @BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    void enqueue() throws PriQueueOverflowException, PriQueueUnderflowException {
        // Add elements with different priorities
        priorityQueue.enqueue(new PriorityDouble(3.5));
        priorityQueue.enqueue(new PriorityDouble(2.0));
        priorityQueue.enqueue(new PriorityDouble(2.5));
        priorityQueue.enqueue(new PriorityDouble(5.1));
        priorityQueue.enqueue(new PriorityDouble(5.6));

        // Check if the elements are in the correct order based on priorities
        assertEquals(5.6, priorityQueue.dequeue().getValue());
        assertEquals(5.1, priorityQueue.dequeue().getValue());
        assertEquals(3.5, priorityQueue.dequeue().getValue());
        assertEquals(2.5, priorityQueue.dequeue().getValue());
        assertEquals(2.0, priorityQueue.dequeue().getValue());


    }

    @Test
    void dequeue() throws PriQueueOverflowException, PriQueueUnderflowException {
        // Add elements with different priorities
        priorityQueue.enqueue(new PriorityDouble(3.5));
        priorityQueue.enqueue(new PriorityDouble(2.0));
        priorityQueue.enqueue(new PriorityDouble(5.1));

        // Dequeue elements and check the order
        assertEquals(5.1, priorityQueue.dequeue().getValue());
        assertEquals(3.5, priorityQueue.dequeue().getValue());
        assertEquals(2.0, priorityQueue.dequeue().getValue());

        // Dequeue from an empty queue should throw an exception
        assertThrows(PriQueueUnderflowException.class, () -> priorityQueue.dequeue());
    }

    @Test
    void isFull() {
        assertFalse(priorityQueue.isFull()); // Priority queue is never full
    }

    @Test
    void isEmpty() throws PriQueueOverflowException {
        assertTrue(priorityQueue.isEmpty()); // New priority queue is empty

        // Add an element
        priorityQueue.enqueue(new PriorityDouble(3.5));
        assertFalse(priorityQueue.isEmpty()); // Queue is not empty after enqueue
    }

    @Test
    void size() throws PriQueueOverflowException {
        assertEquals(0, priorityQueue.size()); // New priority queue has size 0

        // Enqueue elements
        priorityQueue.enqueue(new PriorityDouble(3.5));
        priorityQueue.enqueue(new PriorityDouble(2.0));
        priorityQueue.enqueue(new PriorityDouble(5.1));

        assertEquals(3, priorityQueue.size()); // Size should be 3 after enqueuing
    }
}
