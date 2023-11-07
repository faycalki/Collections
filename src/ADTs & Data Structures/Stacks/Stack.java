
/**
 * An implementation of the StackADT in Java.
 * @author Faycal Kilali
 * @version 2023-10-12
 * @implSpec this implementation of the stackADT uses Linked Lists as the underlying Data Structure.
 */
public class Stack<T> extends LinkedList<T> implements StackADT<T>{
    private LinkedList<T> stack;
    public Stack(){
        stack = new LinkedList<T>();
    }

    /**
     * Pushes the element to the top of the stack.
     * @implNote StackoverflowException should never arise given that a Linked List has no size limitation (besides memory limitations if it exceeds the machine's possible allocation)
     * @param element the element to push on to the stack.
     * @throws StackOverflowException when the size limit is reached for the underlying data structure.
     */
    public void push(T element) throws StackOverflowException{
        stack.addToFront(element);
    }


    /**
     * Pops the element from the top of the stack
     * @throws StackUnderflowException when the caller attempts to access or remove from the stack when its empty
     */
    public void pop() throws StackUnderflowException {
        if (stack.isEmpty() == true) {
            throw new StackUnderflowException("StackUnderflowException: pop attempted on empty Stack.");
        } else {
            stack.removeFromBack();
        }
    }


    /**
     * Mutator method for Stack
     * @return top element in Stack
     * @throws StackUnderflowException when the caller attempts to access or pop an empty stack's element
     */
    public T top() throws StackUnderflowException {
        Node<T> headNode = stack.getHeadData();
        if (headNode != null) {
            return headNode.getData(); // returns the object reference inside the underlying Linked List current Node
        }
        throw new StackUnderflowException("StackUnderflowException: top attempted on empty Stack.");
    }


    /**
     * Accessor Method for upper size boundary of Stack
     * @return false because a Linked List can never be full (a memory exception can be thrown but it'll be dealt with separately)
     */
    public boolean isFull(){
        return false;
    }

    /**
     * Accessor Method for whether the Stack is empty or no
     */
    public boolean isEmpty(){
        return stack.isEmpty();
    }


}