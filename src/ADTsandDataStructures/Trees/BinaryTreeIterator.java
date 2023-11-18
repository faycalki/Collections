package ADTsandDataStructures.Trees;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.Stack;

/**
 * An Iterator over trees using a Stack as the underlying data structure.
 * @author Faycal Kilali
 * @version 1.0
 * @param <T>
 */
public class BinaryTreeIterator<T> implements Iterator<T> {


    private Stack<BTNode<T>> stack;
    private Traversal traversalOrder;

    /**
     * Enforce the following actual parameters for the TraversalOrder formal parameters.
     */
    public enum Traversal {
        PREORDER, INORDER, POSTORDER, PREORDER_REVERSE, INORDER_REVERSE, POSTORDER_REVERSE
    }

    public BinaryTreeIterator(BTNode<T> root, Traversal orderChoice){
        traversalOrder = orderChoice;
        stack = new Stack<BTNode<T>>();
        if (root != null){
            initializeStack(root);
        }
    }


    /**
     * Initialize stack based on available order implementations
     * @param node the node to begin traversing from
     * @implNote The lowest element in the stack (at the bottom) is the first element traversed to.
     * In other words, the order of pushing elements onto the stack determines the traversal order when popping them off. The first element pushed onto the stack is the first one to be processed, and the last element pushed is the last one to be processed.
     */
    public void initializeStack(BTNode<T> node) {
        switch (traversalOrder) {
            case PREORDER:
                initializePreOrderStack(node);
            case INORDER:
                initializeInOrderStack(node);
            case POSTORDER:
                initializePostOrderStack(node);
            case PREORDER_REVERSE:
                initializePreOrderReverseStack(node);
            case INORDER_REVERSE:
                initializeInOrderReverseStack(node);
            case POSTORDER_REVERSE:
                initializePostOrderReverseStack(node);
            default:
                throw new IllegalArgumentException("Unsupported traversal order");
        }
    }

    /**
     * Builds a stack that represents a traversal of the Binary Tree in Pre-order (NLR), also known as Depth-First Order.
     * Precondition: parameterized node is not null.
     * @param node the current node to traverse from.
     * @implNote
     */
    private void initializePreOrderStack(BTNode<T> node){

        // General case
        stack.push(node); // push current Node

        // Recursive Step
        if (node.getLeftChild() != null){ // base case is when both this check and the next check fail
            initializePreOrderStack(node.getLeftChild()); // push left Node
        }
        if (node.getRightChild() != null){
            initializePostOrderStack(node.getRightChild()); // push right Node
        }
        }

    /**
     * Initializes a stack that represents a traversal of the Binary Tree of In-Order (LNR).
     * Precondition: parameterized node is not null.
     * @param node the current node to traverse from.
     */
    private void initializeInOrderStack(BTNode<T> node){


            if (node.getLeftChild() != null){
                initializeInOrderStack(node.getLeftChild());
            }
            stack.push(node);
            if (node.getRightChild() != null){
                initializeInOrderStack(node.getRightChild());
            }

        }

    /**
     * Initialize the stack based upon post-order (LRN).
     * @param node the node to begin initializing from
     */
    private void initializePostOrderStack(BTNode<T> node){

            if (node.getLeftChild() != null){
                initializePostOrderStack(node.getLeftChild());
            }
            if (node.getRightChild() != null){
                initializePostOrderStack(node.getRightChild());
            }
            stack.push(node);
        }

        // Now for the reverses.

    /**
     * Initializes the stack based upon reverse pre-order (NRL)
     * @param node the node to traverse from
     */
    private void initializePreOrderReverseStack(BTNode<T> node) {
        // General case
        stack.push(node); // push current Node

        // Recursive Step (this one is easy, we just reverse the order)
        if (node.getRightChild() != null) {
            initializePreOrderReverseStack(node.getRightChild()); // push right Node
        }
        if (node.getLeftChild() != null) {
            initializePreOrderReverseStack(node.getLeftChild()); // push left Node
        }
    }

    /**
     * Initializes the stack based upon reverse in-order (RNL)
     * @param node the node to traverse from
     */
    private void initializeInOrderReverseStack(BTNode<T> node) {
        if (node.getLeftChild() != null) {
            initializeInOrderReverseStack(node.getLeftChild());
        }
        stack.push(node);
        if (node.getRightChild() != null) {
            initializeInOrderReverseStack(node.getRightChild());
        }
    }

    /**
     * Initializes the stack based upon reverse Post-Order (RLN)
     * @param node the node to traverse from
     */
    private void initializePostOrderReverseStack(BTNode<T> node) {
        if (node.getRightChild() != null){
            initializePostOrderReverseStack(node.getRightChild());
        }
        if (node.getLeftChild() != null){
            initializePostOrderReverseStack(node.getLeftChild());
        }
        stack.push(node);
    }



    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws IllegalStateException if the iteration has no more elements
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements in the iteration.");
        }
        return stack.pop().getData();
    }

    /**
     * Returns the next Node in the iteration.
     *
     * @return the next Node in the iteration
     * @throws IllegalStateException if the iteration has no more Nodes
     */
    public BTNode<T> nextNode() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements in the iteration.");
        }
        return stack.pop();
    }


    /**
     * incomplete implementation, it'd be wise to call remove() from the binary tree itself on the node for this.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove operation is not supported by this iterator");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  Actions are
     * performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to the caller.
     * <p>
     * The behavior of an iterator is unspecified if the action modifies the
     * collection in any way (even by calling the {@link #remove remove} method
     * or other mutator methods of {@code Iterator} subtypes),
     * unless an overriding class has specified a concurrent modification policy.
     * <p>
     * Subsequent behavior of an iterator is unspecified if the action throws an
     * exception.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }

}
