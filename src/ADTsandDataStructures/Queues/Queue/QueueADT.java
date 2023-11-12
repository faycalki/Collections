
/**
 * Interface defining the generic behavior of a Queue.
 * 
 * In the interests of a more interesting implementation,
 * you should use an array as your data structure for this
 * and include the wrap-around logic when enqueueing/dequeuing
 * elements.
 *
 * @author Ada Clevinger
 * @version Oct 9, 2023
 */
public interface QueueADT <T>
{
    
    public abstract void enqueue(T element) throws QueueOverflowException;
    
    public abstract T dequeue() throws QueueUnderflowException;
    
    public abstract boolean isFull();
    
    public abstract boolean isEmpty();
    
    public abstract int size();
    

    
}
