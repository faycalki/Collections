package ADTsandDataStructures.Trees;

import java.util.Iterator;

/**
 * Implementation of a BST Tree.
 * @implSpec There are 6 orders of traversal defined. However, currently, you have to change the method-call to the appropriate traversal per method, as I attempt to find a decent way to have the user have the same traversal for all methods of their choice.
 * @author Faycal Kilali
 * @version 1.0
 * @param <T>
 */
public class BSTADTImpl<T extends Comparable<T>> extends BSTNode<T> implements BSTADT<T> {
    BSTNode<T> root;
    int size;


    public BSTADTImpl(){
        root = null; // a tree should start with no nodes at all.
        size = 0;
    }


    /**
     * Accessor for the minimum value in the tree.
     *
     * @return minimum value in tree
     */
    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }

        // Initialize iterator for in-order traversal as its most suitable for this due to leftmost node in a binary tree having the minimum value.
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.TraversalOrder.INORDER);

        while (iterator.hasNext()) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }

        return null;
    }

    /**
     * Accessor for the maximum value in the tree.
     *
     * @return maximum value in tree
     */
    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }

        // Initialize iterator for reverse in-order traversal, because the right-most node is the maximum in a BST.
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.TraversalOrder.INORDER_REVERSE);
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }

        return null;
    }

    /**
     * Removes the minimum value from the tree.
     */
    @Override
    public void removeMin() {
        if (!isEmpty()) {
            // Initialize iterator for in-order traversal
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.TraversalOrder.INORDER);

            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove(); // must implement first
                size--;
            }
        }
    }

    /**
     * Removes the maximum value from the tree.
     */
    @Override
    public void removeMax() {
        if (!isEmpty()) {
            // Initialize iterator for reverse in-order traversal
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<>(root, BinaryTreeIterator.TraversalOrder.INORDER_REVERSE);

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
     * @implSpec incomplete implementation
     *
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
        Iterator<T> iterator = getIterator(TraversalOrder.INORDER);
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
     * Specifies an iterator based on the order of traversal.
     *
     * @param orderType the order of traversal
     * @return iterator based on the specified traversal order
     * @implNote In addition to this implementation, one must implement a separate iterator method due to the Iterable interface.
     * That should provide iteration in the "natural" order of tree elements, which for most cases in this context,
     * would be inorder traversal.
     */
    @Override
    public Iterator<T> getIterator(Traversal orderType) {
        switch (orderType) {
            case PREORDER:
                return getPreOrderIterator();
            case INORDER:
                return getInOrderIterator();
            case POSTORDER:
                return getPostOrderIterator();
            default:
                throw new IllegalArgumentException("Unsupported traversal order");
        }
    }

        /**
         * Returns an iterator over elements of type T
         * @implNote defaults to in-order if no order is specified.
         * @return the iterator
         */
        @Override
        public Iterator<T> iterator () {
            return getInOrderIterator();
        }



    /**
     * Attempts to add an element to the collection
     *
     * @param element the element to add to the collection.
     * @return true if e is successfully added to the collection, false otherwise.
     */
    @Override
    public boolean add(T element) {
        if (root == null) {
            root = new BSTNode<>(element);
            size++;
            return true;
        }

        return add(root, element, null);
    }

    private boolean add(BSTNode<T> node, T element, BSTNode<T> parent) {

        // Base case
        if (node == null) {
            // We've reached a null position, we'll insert the new element here
            BSTNode<T> newNode = new BSTNode<>(element);
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

        /**
         * Attempts to retrieve an element e such that e.equals() is target.
         *
         * @param target the e.equals() value we seek
         * @return e if successful, otherwise null.
         */
        @Override
        public T get (T target){
            Iterator<T> iterator = getInOrderIterator(); // Perhaps there's a decent way to determine a form of traversal that isn't hard-coded
            while (iterator.hasNext()) {
                T current = iterator.next();
                if (target.equals(current)) {
                    return current; // Element found
                }
            }
            return null; // Element not found
        }

        /**
         * Confirms whether an element e such that e.equals() is target exists in the Collection.
         *
         * @param target the e.equals() value we seek.
         * @return true if e is found, false otherwise.
         */
        @Override
        public boolean contains (T target){
            if (get(target) != null){
                return true;
            }
            return false;
        }

        /**
         * Attempts to remove an element e such that e.equals() is target from the Collection.
         *
         * @param target the e.equals() value we seek.
         * @return true if e is removed, false otherwise.
         * @implSpec incomplete method
         */
        @Override
        public boolean remove (T target){
            size--;
            return true;
        }

        /**
         * Checks whether the Collection is full. This will actually never be full in our implementation.
         *
         * @return true if the Collection is full, false otherwise.
         */
        @Override
        public boolean isFull () {
            return false;
        }

        /**
         * Checks whether the collection is empty.
         *
         * @return true if the Collection is empty, false otherwise.
         */
        @Override
        public boolean isEmpty () {
            if (root == null) {
                return true;
            }
            return false;
        }

        /**
         * Retrieves the size of the Collection.
         *
         * @return number of elements in the Collection.
         */
        @Override
        public int size () {
            if (isEmpty() == true) {
                return 0;
            } else {
                return size;
                // traverse the tree to find the amount of Nodes.
            }
        }



        private Iterator<T> getPreOrderIterator () {
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, TraversalOrder.PREORDER);
            return iterator;
        }
        private Iterator<T> getInOrderIterator () {
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, TraversalOrder.INORDER);
            return iterator;
        }

        private Iterator<T> getPostOrderIterator () {
            BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, TraversalOrder.POSTORDER);
            return iterator;
        }

    }