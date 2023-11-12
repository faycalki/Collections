package ADTsandDataStructures.Trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTADTImplTest {

    @Test
    void getMin() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertNull(bst.getMin());

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertEquals(3, bst.getMin());
    }

    @Test
    void getMax() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertNull(bst.getMax());

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertEquals(7, bst.getMax());
    }

    @Test
    void removeMin() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        bst.removeMin(); // Removing from an empty tree should do nothing

        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.removeMin();
        assertEquals(5, bst.getMin());
    }

    @Test
    void removeMax() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        bst.removeMax(); // Removing from an empty tree should do nothing

        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.removeMax();
        assertEquals(5, bst.getMax());
    }

    @Test
    void balance() {
    }

    @Test
    void getClosestValue() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertNull(bst.getClosestValue(5)); // Tree is empty

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertEquals(5, bst.getClosestValue(5));
        assertEquals(3, bst.getClosestValue(4));
        assertEquals(7, bst.getClosestValue(6));
    }

    @Test
    void getIterator() {
    }

    @Test
    void iterator() {
    }

    @Test
    void add() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertTrue(bst.add(5));
        assertFalse(bst.add(5)); // Duplicate
        assertTrue(bst.add(3));
        assertTrue(bst.add(7));
    }

    @Test
    void get() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertNull(bst.get(5)); // Tree is empty

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertEquals(5, bst.get(5));
        assertEquals(3, bst.get(3));
        assertEquals(7, bst.get(7));
        assertNull(bst.get(4)); // Not in the tree
    }

    @Test
    void contains() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertFalse(bst.contains(5)); // Tree is empty

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));
        assertFalse(bst.contains(4)); // Not in the tree
    }

    @Test
    void remove() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertFalse(bst.remove(5)); // Tree is empty

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5)); // 5 should be removed
        assertFalse(bst.remove(4)); // 4 not in the tree
    }

    @Test
    void isFull() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertFalse(bst.isFull()); // Tree is never full in this implementation
    }

    @Test
    void isEmpty() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertTrue(bst.isEmpty()); // Tree is initially empty

        bst.add(5);
        assertFalse(bst.isEmpty());
    }

    @Test
    void size() {
        BSTADTImpl<Integer> bst = new BSTADTImpl<>();
        assertEquals(0, bst.size()); // Tree is initially empty

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertEquals(3, bst.size());
        bst.remove(5);
        assertEquals(2, bst.size());
    }
}