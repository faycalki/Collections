/**
 * JUnit test based on Dylan's Heap class
 * @author Dylan Kim, Faykal kilali
 * @version 1.0 (11/20/2023)
 */

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapADTImplTest {

    private HeapADTImpl<Integer> heap;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        heap = new HeapADTImpl<Integer>();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void peekTop() {
        try{
            heap.peekTop();
        }
        catch(IllegalArgumentException iae){
            heap.add(1);
            assertEquals(1, heap.peekTop());
            heap.add(2);
            assertEquals(2, heap.peekTop());
            heap.add(3);
            assertEquals(3, heap.peekTop());
            heap.swapMaxMin();
            assertEquals(1, heap.peekTop());
        }

    }

    @org.junit.jupiter.api.Test
    void add() {
        heap.add(2);
        assertEquals(2, heap.peekTop());
        heap.add(3);
        assertEquals(3, heap.peekTop());
        heap.add(4);
        assertEquals(4, heap.peekTop());
        heap.swapMaxMin();
        assertEquals(2, heap.peekTop());
        heap.add(1);
        assertEquals(1, heap.peekTop());
        for(int i = 0; i<heap.size()-4;i++){
            heap.add(i);
        }
        heap.add(1);
        assertEquals(33, heap.size());

    }

    @org.junit.jupiter.api.Test
    void removeTop() {
        try{
            heap.removeTop();
            fail();
        }
        catch(IllegalArgumentException ire){
            heap.add(1);
            heap.add(2);
            heap.add(3);
            heap.add(4);
            heap.removeTop();
            assertEquals(3, heap.peekTop());
            heap.removeTop();
            assertEquals(2, heap.peekTop());
            heap.removeTop();
            assertEquals(1, heap.peekTop());
        }
    }

    @org.junit.jupiter.api.Test
    void replaceTop() {
        try{
            heap.replaceTop(null);
            fail();
        }
        catch(IllegalArgumentException ire){
            heap.add(1);
            heap.add(2);
            heap.add(3);
            heap.add(4);
            heap.replaceTop(5);
            assertEquals(5, heap.peekTop());
            heap.replaceTop(6);
            assertEquals(6, heap.peekTop());
            heap.replaceTop(3);
            assertEquals(4, heap.peekTop());
        }
    }

    @org.junit.jupiter.api.Test
    void heapify() {
        Integer[] input = new Integer[] {1,2,3,4,5,6};
        heap.heapify(input);
        assertEquals(6, heap.peekTop());
        heap.removeTop();
        assertEquals(5, heap.peekTop());
        heap.removeTop();
        assertEquals(4, heap.peekTop());
        heap.removeTop();
        assertEquals(3, heap.peekTop());
        heap.removeTop();
        assertEquals(2, heap.peekTop());
        heap.removeTop();
        assertEquals(1, heap.peekTop());
        try{
            heap.heapify(input);
            fail();
        }
        catch(IllegalArgumentException iae){

        }



    }

    /*
    @org.junit.jupiter.api.Test
    void merge() {

    }

     */

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0,heap.size());
        heap.add(1);
        assertEquals(1,heap.size());
        heap.add(2);
        assertEquals(2,heap.size());
        heap.removeTop();
        assertEquals(1, heap.size());
        heap.removeTop();
        assertEquals(0,heap.size());
    }

    /*
    @org.junit.jupiter.api.Test
    void isEmpty() {

    }

     */

    /*
    @org.junit.jupiter.api.Test
    void isFull() {
    }
    */

}