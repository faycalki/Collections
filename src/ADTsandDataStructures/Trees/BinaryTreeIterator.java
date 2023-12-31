import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.Queue;
import java.util.Stack;

/**
 * An Iterator over trees using a Queue for direct traversals and a Stack for reverse traversals.
 * @implSpec The reverse traversals are actually just reverses of which subtrees to visit, not reverses of the entire traversals. This is a bit misleading, so it is important to keep in mind. Refer to wikipedia for further information if needed.
 * @implNote Level-Order traversal should be implemented as it can be quite useful for rebalancing purposes potentially.
 * @author Faycal Kilali
 * @version 1.0
 * @param <T> the type of elements in the iterator
 */
public class BinaryTreeIterator<T> implements Iterator<T> {

    private Queue<BTNode<T>> queue;
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
        queue = new LinkedList<>();
        stack = new Stack<BTNode<T>>();
        if (root != null){
            initializeTraversal(root);
        }
    }


    /**
     * Initialize traversal based on available order implementations
     * @param node the node to begin traversing from
     */
    public void initializeTraversal(BTNode<T> node) {
        switch (traversalOrder) {
            case PREORDER:
                initializePreOrder(node);
                break;
            case INORDER:
                initializeInOrder(node);
                break;
            case POSTORDER:
                initializePostOrder(node);
                break;
            case PREORDER_REVERSE:
                initializePreOrderReverse(node);
                break;
            case INORDER_REVERSE:
                initializeInOrderReverse(node);
                break;
            case POSTORDER_REVERSE:
                initializePostOrderReverse(node);
                break;
            default:
                throw new IllegalArgumentException("Unsupported traversal order");
        }
    }

    /**
     * Builds a queue that represents a traversal of the Binary Tree in Pre-order (NLR), also known as Depth-First Order.
     * Precondition: parameterized node is not null.
     * @param node the current node to traverse from.
     */
    private void initializePreOrder(BTNode<T> node){

        // General case
        queue.add(node); // enqueue current Node

        // Recursive Step
        if (node.getLeftChild() != null){ // base case is when both this check and the next check fail
            initializePreOrder(node.getLeftChild()); // push left Node
        }
        if (node.getRightChild() != null){
            initializePreOrder(node.getRightChild()); // push right Node
        }
        }

    /**
     * Initializes a queue that represents a traversal of the Binary Tree of In-Order (LNR).
     * Precondition: parameterized node is not null.
     * @param node the current node to traverse from.
     */
    private void initializeInOrder(BTNode<T> node){


            if (node.getLeftChild() != null){
                initializeInOrder(node.getLeftChild());
            }
            queue.add(node);
            if (node.getRightChild() != null){
                initializeInOrder(node.getRightChild());
            }

        }

    /**
     * Initialize the queue based upon post-order (LRN).
     * @param node the node to begin initializing from
     */
    private void initializePostOrder(BTNode<T> node){

            if (node.getLeftChild() != null){
                initializePostOrder(node.getLeftChild());
            }
            if (node.getRightChild() != null){
                initializePostOrder(node.getRightChild());
            }
            queue.add(node);
        }

        // Now for the reverses.


    /**
     * Initializes the stack based upon reverse pre-order (NRL)
     * @param node the node to traverse from
     */
    private void initializePreOrderReverse(BTNode<T> node) {
        if (node.getRightChild() != null){
            initializePreOrderReverse(node.getLeftChild());
        }
        if (node.getLeftChild() != null){
            initializePreOrderReverse(node.getRightChild());
        }
        stack.push(node);
    }

    /**
     * Initializes the stack based upon reverse in-order (RNL)
     * @param node the node to traverse from
     */
    private void initializeInOrderReverse(BTNode<T> node) {
        if (node.getLeftChild() != null) {
            initializeInOrderReverse(node.getLeftChild());
        }
        stack.push(node);
        if (node.getRightChild() != null) {
            initializeInOrderReverse(node.getRightChild());
        }
    }



    /**
     * Initializes the stack to produce reverse Post-Order (RNL)
     * @param node the node to traverse from
     */
    private void initializePostOrderReverse(BTNode<T> node) {


        // Recursive Step
        if (node.getRightChild() != null) {
            initializePostOrderReverse(node.getRightChild());
        }
        if (node.getLeftChild() != null) {
            initializePostOrderReverse(node.getLeftChild());
        }

        // General case
        queue.add(node); // push current Node
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
        if (!stack.isEmpty() || !queue.isEmpty()){
            return true;
        }
        return false;
        //return !stack.isEmpty();
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
        return getNext().getData();
    }

    private BTNode<T> getNext() {
        switch (traversalOrder) {
            case PREORDER:
            case INORDER:
            case POSTORDER:
            case POSTORDER_REVERSE:
                return queue.remove();
            case PREORDER_REVERSE:
            case INORDER_REVERSE:
                return stack.pop();
            default:
                throw new IllegalArgumentException();
        }
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
