
/**
 * Interface defining the generic behavior of a PriorityQueue.
 * 
 *
 * @author Ada Clevinger
 * @version Oct 13, 2023
 */
public interface PriorityQueueADT <T extends Comparable<T>>
{
    
    public abstract void enqueue(T element) throws PriQueueOverflowException;
    
    public abstract T dequeue() throws PriQueueUnderflowException;
    
    public abstract boolean isFull();
    
    public abstract boolean isEmpty();
    
    public abstract int size();
    
    class PriQueueOverflowException extends Exception { }
    
    class PriQueueUnderflowException extends Exception { }
    
}
