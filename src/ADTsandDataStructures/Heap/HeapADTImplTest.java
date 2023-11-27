import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeapADTImplTest {

    @Test
    void peekTop() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        heap.add(3);
        heap.add(8);
        heap.add(5);
        assertEquals(8, heap.peekTop());
    }

    @Test
    void swapMaxMin() {
        // Implement your test for swapMaxMin
    }

    @Test
    void isMaxHeap() {
        HeapADT<Integer> maxHeap = new HeapADTImpl<>();
        maxHeap.add(10);
        maxHeap.add(8);
        maxHeap.add(5);
        assertTrue(maxHeap.isMaxHeap());

        HeapADT<Integer> notMaxHeap = new HeapADTImpl<>();
        notMaxHeap.add(5);
        notMaxHeap.add(10);
        notMaxHeap.add(8);
        assertFalse(notMaxHeap.isMaxHeap());
    }

    @Test
    void isMinHeap() {
        // Implement your test for isMinHeap
    }

    @Test
    void add() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        heap.add(3);
        heap.add(8);
        heap.add(5);
        assertEquals(3, heap.peekTop());
        assertEquals(3, heap.size());
    }

    @Test
    void removeTop() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        heap.add(3);
        heap.add(8);
        heap.add(5);
        heap.removeTop();
        assertEquals(5, heap.peekTop());
        assertEquals(2, heap.size());
    }

    @Test
    void replaceTop() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        heap.add(3);
        heap.add(8);
        heap.add(5);
        heap.replaceTop(10);
        assertEquals(10, heap.peekTop());
    }

    @Test
    void heapify() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        Integer[] array = {4, 6, 2, 9, 7};
        heap.heapify(array);
        assertEquals(9, heap.peekTop());
        assertEquals(5, heap.size());
    }

    @Test
    public void testMerge() {
        HeapADT<Integer> heap1 = new HeapADTImpl<>();
        heap1.add(3);
        heap1.add(8);
        heap1.add(5);

        HeapADT<Integer> heap2 = new HeapADTImpl<>();
        heap2.add(10);
        heap2.add(7);
        heap2.add(2);

        HeapADT<Integer> mergedHeap = heap1.merge(heap2);
        assertEquals(10, mergedHeap.peekTop()); // After merging, the max value should be at the root
        assertEquals(6, mergedHeap.size()); // The size should be 6 after merging
    }

    @Test
    void size() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        heap.add(3);
        heap.add(8);
        heap.add(5);
        assertEquals(3, heap.size());
    }

    @Test
    void isEmpty() {
        HeapADT<Integer> heap = new HeapADTImpl<>();
        assertTrue(heap.isEmpty());
        heap.add(3);
        assertFalse(heap.isEmpty());
    }

    @Test
    void isFull() {
        HeapADT<Integer> heap = new HeapADTImpl<>(3);
        assertFalse(heap.isFull());
        heap.add(3);
        heap.add(8);
        heap.add(5);
        assertTrue(heap.isFull());
    }
}
