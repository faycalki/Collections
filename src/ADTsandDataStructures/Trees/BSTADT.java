import java.util.Iterator;

/**
 * Specification for a Binary Search Tree
 * @author Faycal Kilali
 * @version 1.0
 * @param <T> the generic parameter
 */
public interface BSTADT<T extends Comparable<T>> extends BTADT<T> {

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


}