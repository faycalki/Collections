package ADTsandDataStructures.Trees;
import ADTsandDataStructures.CollectionADT;
import java.util.Iterator;

/**
 * Specification for a Binary Search Tree
 * @author Faycal Kilali
 * @version 1.0
 * @param <T> the generic parameter
 */
public interface BSTADT<T extends Comparable<T>> extends CollectionADT<T>, Iterable<T> {

    /**
     * Accessor for the minimum value in the tree.
     * @return minimum value in tree
     */
    T getMin();

    /**
     * Accessor for the maximum value in the tree.
     * @return maximum value in tree
     */
    T getMax();

    /**
     * Removes the minimum value from the tree.
     */
    void removeMin();

    /**
     * Removes the maximum value from the tree.
     */
    void removeMax();

    /**
     * Balances the Binary Search Tree to maintain its efficiency.
     */
    void balance();

    /**
     * Finds the closest value to the given data in the Binary Search Tree.
     * @param data the data to find the closest value to
     * @return the closest value in the tree to the given data
     */
    T getClosestValue(T data);

    /**
     * Specifies traversal order for the Binary Search Tree.
     */
    enum Traversal {
        PREORDER, INORDER, POSTORDER
    }

    /**
     * Specifies an iterator based on the order of traversal.
     * @param orderType the order of traversal
     * @return iterator based on the specified traversal order
     * @implNote In addition to this implementation, one must implement a separate iterator method due to the Iterable interface.
     *           That should provide iteration in the "natural" order of tree elements, which for most cases in this context,
     *           would be inorder traversal.
     */
    Iterator<T> getIterator(Traversal orderType);

    /**
     * Returns a string representation of the Binary Search Tree.
     * @return a string representation of the tree
     */
    String toString();
}