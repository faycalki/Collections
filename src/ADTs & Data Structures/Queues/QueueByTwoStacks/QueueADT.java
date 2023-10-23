
/**
 * Interface defining the generic behavior of a Queue.
 * 
 * For this implementation, you should use two Stacks
 * to store and manipulate the data held by your Queue
 * implementation.
 * 
 * Remember to consider what JUnit tests would effectively
 * display that each method works as expected, as well as
 * that it catches potential edge cases/weird situations.
 * 
 * You may make the exceptions described in this class their
 * own classes in your project for ease of use/reference by
 * other classes.
 *
 * @author Ada Clevinger
 * @version Oct 19, 2023
 */
public interface QueueADT <T>
{
    
    public abstract void enqueue(T element) throws QueueOverflowException;
    
    public abstract T dequeue() throws QueueUnderflowException;
    
    public abstract boolean isFull();
    
    public abstract boolean isEmpty();
    
    public abstract int size();
    
    /**
     * This method should check the elements
     * in the Queue and return how far from
     * the front of the Queue the given value
     * 'data' is (where if it is at the front,
     * it would be at position '0').
     * 
     * If the value 'data' is not in the Queue,
     * return the value -1.
     * 
     */
    
    public abstract int find(T data);
    
    /**
     * This method should check the elements
     * in the Queue for whether or not the
     * given value 'data' is contained in
     * the Queue.
     * 
     */
    
    public abstract boolean contains(T data);
    
    /**
     * This method should reverse the order of
     * the elements in the Queue.
     * 
     */
    
    public abstract void reverse();
    
    /**
     * This method should produce a textual
     * representation of the elements stored
     * in this Queue.
     * 
     * Consider how best to display this information
     * to someone using your implementation
     * with no knowledge of the underlying
     * data structure being used.
     *
     */
    
    public abstract String toString();
    

    
}
