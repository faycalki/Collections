
/**
 * Interface defining the generic behavior of a PriorityQueue.
 *
 * @author Ada Clevinger
 * @version Oct 9, 2023
 */
public interface PriorityQueueADT <T extends Priority>
{

    public abstract void enqueue(T element) throws PriQueueOverflowException;

    public abstract T dequeue() throws PriQueueUnderflowException;

    public abstract boolean isFull();

    public abstract boolean isEmpty();

    public abstract int size();



}
