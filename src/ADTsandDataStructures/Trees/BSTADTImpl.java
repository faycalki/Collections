package ADTsandDataStructures.Trees;
import java.util.Iterator;

/**
 * Implementation of a BST Tree.
 * @implSpec There are 6 orders of traversal defined. However, currently, you have to change the method-call to the appropriate traversal per method, as I attempt to find a decent way to have the user have the same traversal for all methods of their choice.
 * @author Faycal Kilali
 * @version 1.0
 * @param <T>
 */
public class BSTADTImpl<T extends Comparable<T>> extends BTADTImpl<T> implements BSTADT<T> {
    BTNode<T> root;
    int size;


    public BSTADTImpl(){
        root = null; // a tree should start with no nodes at all.
        size = 0;
    }


    /**
     * Accessor for the minimum value in the Binary Search Tree.
     *
     * @return minimum value in tree
     */
    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }

        // Initialize iterator for in-order traversal as its most suitable for this due to leftmost node in a binary tree having the minimum value.
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.Traversal.INORDER);

        while (iterator.hasNext()) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }

        return null;
    }

    /**
     * Accessor for the maximum value in the Binary Search Tree.
     *
     * @return maximum value in tree
     */
    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }

        // Initialize iterator for reverse in-order traversal, because the right-most node is the maximum in a BST.
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.Traversal.INORDER_REVERSE);
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }

        return null;
    }

    /**
     * Removes the minimum value from the Binary Search Tree.
     */
    @Override
    public void removeMin() {
        if (!isEmpty()) {
            // Initialize iterator for in-order traversal
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.Traversal.INORDER);

            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove(); // must implement first
                size--;
            }
        }
    }

    /**
     * Removes the maximum value from the Binary Search Tree.
     */
    @Override
    public void removeMax() {
        if (!isEmpty()) {
            // Initialize iterator for reverse in-order traversal
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.Traversal.INORDER_REVERSE);

            // The rightmost node is the maximum
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove(); // must implement first
                size--;
            }
        }
    }

    /**
     * Balances the Binary Search Tree to maintain its efficiency.
     */
    @Override
    public void balance() {

    }

    /**
     * Finds the closest value to the given data in the Binary Search Tree using an iterator.
     *
     * @param data the data we are trying to find the closest value of
     * @return the closest value in our tree to the parameterized data
     */
    @Override
    public T getClosestValue(T data) {
        // Simple nullity check
        if (isEmpty()) {
            return null; // Tree is empty
        }
        Iterator<T> iterator = getIterator(Traversal.INORDER);
        T closestValue = iterator.next();

        while (iterator.hasNext()) {
            T current = iterator.next();


            // In here, we'll use the magnitudes as we only care about the distance of difference between the values.
            if (Math.abs(data.compareTo(current)) < Math.abs(data.compareTo(closestValue))) {
                closestValue = current;
            }

            if (data.equals(current)) {
                return current; // found an exact match
            }
        }

        return closestValue;

    }




    /**
     * Attempts to add an element to the Binary Search Tree.
     *
     * @param element the element to add to the Binary Search Tree.
     * @return true if e is successfully added to the Binary Search Tree, false otherwise.
     */
    @Override
    public boolean add(T element) {
        if (root == null) {
            root = new BTNode<>(element);
            size++;
            return true;
        }

        return add(root, element, null);
    }

    /**
     * Recursively adds a new element to the binary tree starting from the given node.
     *
     * @param node   The current node being considered during the recursive addition.
     * @param element The element to be added to the binary tree.
     * @param parent The parent of the current node (used for linking the new node).
     * @return {@code true} if the element is successfully added, {@code false} otherwise.
     * @implNote This method follows a recursive approach to add a new element to the binary tree.
     *          If the provided node is null, a new node containing the element is created,
     *          and its placement is determined based on a comparison with the parent's data.
     *          The method returns true upon successful addition, and the tree size is incremented.
     *          In the general case, the method compares the element with the current node's data.
     *          If the element is smaller, the method recursively moves to the left child.
     *          If the element is larger, the method recursively moves to the right child.
     *          In the case of a duplicate value, the duplicate count is incremented, and the
     *          method returns true. Alternatively, if you want the tree to add duplicates to
     *          the right side, you can uncomment the corresponding line in the else block.
     */
    private boolean add(BTNode<T> node, T element, BTNode<T> parent) {

        // Base case
        if (node == null) {
            // We've reached a null position, we'll insert the new element here
            BTNode<T> newNode = new BTNode<>(element);
            if (parent.getData().compareTo(element) >= 0) {
                parent.setLeftChild(newNode);
            } else {
                parent.setRightChild(newNode);
            }
            size++;
            return true;
        }

        // General case
        int comparison = node.getData().compareTo(element);

        // Recursive step
        if (comparison > 0) {
            // Move to the left child
            return add(node.getLeftChild(), element, node);
        } else if (comparison < 0) {
            // Move to the right child
            return add(node.getRightChild(), element, node);
        } else {
            // Duplicate value case
            // We'll increment the duplicate value
            node.setDuplicate(node.getDuplicate() + 1);
            size++;
            return true;
            // Alternatively, if we want the tree to just add to the right-side
            //return add(node.getRightChild(), element, node);
        }
    }



    }