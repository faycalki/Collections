import java.util.Iterator;

/**
 * Specification for a Binary Tree.
 * Definition: A binary tree is a tree where each Node has at most 2 children.
 * @author Faycal Kilali
 * @version 1.0
 * @param <T> the generic parameter
 */
public interface BTADT<T> extends CollectionADT<T>, Iterable<T> {

    /**
     * Inserts a new node with the given data into the binary tree.
     * @param data the data to be inserted
     */
    default void insert(T data){
        add(data);
    }

    /**
     * Deletes the node with the given data from the binary tree.
     * @param data the data to be deleted
     */
    default void delete(T data){
        remove(data);
    }

    /**
     * Checks if the binary tree contains the given data.
     * @param data the data to be checked
     * @return true if the data is found, false otherwise
     */
    boolean contains(T data);

    /**
     * Accessor for the size (number of nodes) of the binary tree.
     * @return the size of the binary tree
     */
    int size();

    /**
     * Checks if the binary tree is empty.
     * @return true if the tree is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Specifies traversal order for the Binary Tree.
     * @implSpec We
     */
    enum Traversal {
        PREORDER, INORDER, POSTORDER, PREORDER_REVERSE, INORDER_REVERSE, POSTORDER_REVERSE
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
     * Returns a string representation of the Binary Tree.
     * @return a string representation of the tree
     */
    String toString();

    String toString(Traversal orderType);
}
