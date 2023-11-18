package ADTsandDataStructures.Trees;

import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BTADTImplTest {
    @Test
    void add() {
        // You can add test cases for the add method if you implement it.
    }

    @Test
    void insert() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Check if elements are in the tree
        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.contains(3));
        assertTrue(binaryTree.contains(7));
    }

    @Test
    void delete() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Delete an element
        binaryTree.delete(3);

        // Check if the element is removed
        assertFalse(binaryTree.contains(3));
    }

    @Test
    void get() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Get an element
        Integer element = binaryTree.get(3);

        // Check if the correct element is retrieved
        assertEquals(3, element);
    }

    @Test
    void contains() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Check if elements are in the tree
        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.contains(3));
        assertTrue(binaryTree.contains(7));

        // Check if an element that is not in the tree is not contained
        assertFalse(binaryTree.contains(10));
    }

    @Test
    void remove() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Remove an element
        binaryTree.remove(3);

        // Check if the element is removed
        assertFalse(binaryTree.contains(3));
    }

    @Test
    void isFull() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Binary tree is never full in this implementation
        assertFalse(binaryTree.isFull());
    }

    @Test
    void isEmpty() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Check if the tree is empty
        assertTrue(binaryTree.isEmpty());

        // Insert an element
        binaryTree.insert(5);

        // Check if the tree is not empty
        assertFalse(binaryTree.isEmpty());
    }

    @Test
    void size() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Check the size of an empty tree
        assertEquals(0, binaryTree.size());

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Check the size after insertion
        assertEquals(3, binaryTree.size());

        // Remove an element
        binaryTree.remove(3);

        // Check the size after removal
        assertEquals(2, binaryTree.size());
    }

    @Test
    void getIterator() {
        BTADT<Integer> binaryTree = new BTADTImpl<>();

        // Insert elements
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);

        // Test iterator for different traversal orders
        testTraversalOrder(binaryTree, BTADT.Traversal.PREORDER);
        testTraversalOrder(binaryTree, BTADT.Traversal.INORDER);
        testTraversalOrder(binaryTree, BTADT.Traversal.POSTORDER);
        testTraversalOrder(binaryTree, BTADT.Traversal.PREORDER_REVERSE);
        testTraversalOrder(binaryTree, BTADT.Traversal.INORDER_REVERSE);
        testTraversalOrder(binaryTree, BTADT.Traversal.POSTORDER_REVERSE);
    }

    private void testTraversalOrder(BTADT<Integer> binaryTree, BTADT.Traversal traversalOrder) {
        Iterator<Integer> iterator = binaryTree.getIterator(traversalOrder);

        // Check if elements are traversed in the correct order
        switch (traversalOrder) {
            case PREORDER:
                assertEquals(5, iterator.next());
                assertEquals(3, iterator.next());
                assertEquals(7, iterator.next());
                break;
            case INORDER:
                assertEquals(3, iterator.next());
                assertEquals(5, iterator.next());
                assertEquals(7, iterator.next());
                break;
            case POSTORDER:
                assertEquals(3, iterator.next());
                assertEquals(7, iterator.next());
                assertEquals(5, iterator.next());
                break;
            case PREORDER_REVERSE:
                assertEquals(5, iterator.next());
                assertEquals(7, iterator.next());
                assertEquals(3, iterator.next());
                break;
            case INORDER_REVERSE:
                assertEquals(7, iterator.next());
                assertEquals(5, iterator.next());
                assertEquals(3, iterator.next());
                break;
            case POSTORDER_REVERSE:
                assertEquals(7, iterator.next());
                assertEquals(3, iterator.next());
                assertEquals(5, iterator.next());
                break;
            default:
                fail("Unsupported traversal order");
        }
    }

    @Test
    void testToStringInOrder() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("1 2 3 ", tree.toString());
    }

    @Test
    void testToStringPreOrder() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("2 1 3 ", tree.getIterator(BTADT.Traversal.PREORDER).toString());
    }

    @Test
    void testToStringPostOrder() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("1 3 2 ", tree.getIterator(BTADT.Traversal.POSTORDER).toString());
    }

    @Test
    void testToStringInOrderReverse() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("3 2 1 ", tree.getIterator(BTADT.Traversal.INORDER_REVERSE).toString());
    }

    @Test
    void testToStringPreOrderReverse() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("3 1 2 ", tree.getIterator(BTADT.Traversal.PREORDER_REVERSE).toString());
    }

    @Test
    void testToStringPostOrderReverse() {
        BTADT<Integer> tree = new BTADTImpl<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        assertEquals("2 3 1 ", tree.getIterator(BTADT.Traversal.POSTORDER_REVERSE).toString());
    }


}

