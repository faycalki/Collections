public interface StackADT<T>
{

    /*
     * This method should push the provided element into the
     * top of the Stack.
     * 
     * If the underlying data structure is full, this method
     * should throw a StackOverflowException.
     * 
     */
    
    public abstract void push(T element) throws StackOverflowException;
    
    /*
     * This method should remove the top element from the Stack.
     * 
     * If the underlying data structure has no elements to remove,
     * this method should throw a StackUnderflowException.
     * 
     */
    
    public abstract void pop() throws StackUnderflowException;
    
    /*
     * This method should return the value stored at the top
     * of the Stack; importantly, it does NOT remove the element.
     * 
     * If the underlying data structure has no elements to remove,
     * this method should throw a StackUnderflowException.
     * 
     */
    
    public abstract T top() throws StackUnderflowException;
    
    /*
     * This method should check if the underlying data structure
     * of the Stack is 'full' i.e. could not have an additional
     * value added to it.
     * 
     */
    
    public abstract boolean isFull();
    
    /*
     * This method should check if the underlying data structure
     * of the Stack is 'empty' i.e. there are no values in the Stack
     * to pop() or top().
     * 
     */
    
    public abstract boolean isEmpty();
 

    
}
