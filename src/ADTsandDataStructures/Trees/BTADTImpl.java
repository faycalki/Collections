package ADTsandDataStructures.Trees;

import java.util.Iterator;

public class BTADTImpl<T> implements BTADT<T> {

    BTNode<T> root;
    int size;


    public BTADTImpl(){
        root = null; // a tree should start with no nodes at all.
        size = 0;
    }

    /**
     * Attempts to add an element to the Binary tree.
     * @implSpec adds in breadth-first order.
     *
     * @param element the element to add to the Binary tree.
     * @return true if e is successfully added to the Binary tree, false otherwise.
     */
    @Override
    public boolean add(T element) {
        if (root == null){
            BTNode<T> newNode = new BTNode(element);
            root = newNode;
            return true; // successfully added
        }

        return add(root, element, null);
    }

    /**
     * Recursive Function for inserting into a Binary Tree in some particular order.
     * @implNote may be more optimal to perform breadth-first
     * @param node the current Node we are at
     * @param target the data we wish to add (as a Node)
     * @param parent where we wish to add the Node
     * @return
     */
    private boolean add(BTNode<T> node, T target, BTNode<T> parent){
        // base case
        if (node == null){
            BTNode<T> newNode = new BTNode(target);
            if (parent.getLeftChild() == null){
                parent.setLeftChild(newNode);
            }
            else{
                parent.setRightChild(newNode);
            }
            size++;
            return true;
        }


        // General case
        int leftHeight = getHeight(node.getLeftChild());
        int rightHeight = getHeight(node.getRightChild());

        // Recursive Step
        if (leftHeight <= rightHeight) {
            // Move to the left-child
            return add(node.getLeftChild(), target, node);
        } else {
            // Move to the right-child
            return add(node.getRightChild(), target, node);
        }

    }

    /**
     * Obtains the height of a given subtree
     * @param node the root node of the subtree to calculate the height of
     * @return the height of the subtree
     */
    private int getHeight(BTNode<T> node){
        if (node == null){
            return 0;
        }
        int leftSubtreeHeight = getHeight(node.getLeftChild());
        int rightSubtreeHeight = getHeight(node.getRightChild());
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }


    /**
     * Inserts a new node with the given data into the binary tree.
     *
     * @param data the data to be inserted
     */
    @Override
    public void insert(T data) {
        BTADT.super.insert(data);
    }

    /**
     * Deletes the node with the given data from the binary tree.
     *
     * @param data the data to be deleted
     */
    @Override
    public void delete(T data) {
        BTADT.super.delete(data);
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
     * Attempts to remove an element e such that e.equals() is target from the Binary Tree.
     * @implNote This method is yet to be completed. It searches and finds the node and tells you if its removable, but does not perform the removal.
     * @param target the e.equals() value we seek.
     * @return true if e is removed, false otherwise.
     */
    @Override
    public boolean remove (T target){
        // Check
        if (isEmpty()){
            return false;
        }
        if (size() == 1){
            root = null;
            return true;
        }

        // Find element
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, BinaryTreeIterator.Traversal.INORDER);
        while (iterator.hasNext() == true){
            if (iterator.next().equals(target)){
                System.out.println("Element found:" + target);
                size--;
                return true; // incomplete, removal not performed yet. Must implement.
            }
        }

        /*
        Note we have to consider the following cases
         a. where there is no child (leaf node, easy removal, set parent to null)
         b. one child of the node to remove (set parent to child)
         c. two children of node to remove (make a decision on which one will be the new child of the parent)
         */
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
        }
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


    private Iterator<T> getPreOrderIterator () {
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, BinaryTreeIterator.Traversal.PREORDER);
        return iterator;
    }
    private Iterator<T> getInOrderIterator () {
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, BinaryTreeIterator.Traversal.INORDER);
        return iterator;
    }

    private Iterator<T> getPostOrderIterator () {
        BinaryTreeIterator<T> iterator = new BinaryTreeIterator<T>(root, BinaryTreeIterator.Traversal.POSTORDER);
        return iterator;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator<T> iterator = getInOrderIterator(); // Default to inorder traversal

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }

        return result.toString();
    }



}
