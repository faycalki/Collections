
/**
 * Interface defining the generic behavior of a PriorityQueue.
 *
 * For today's activity, you should implement a doubly-linked
 * list for the underlying data structure. It will not have
 * the same <T extends Priority> that this interface expects.
 *
 * @author Ada Clevinger
 * @version Oct 13, 2023
 */
public interface PriorityQueueAltADT <T extends Priority>
{

    public abstract void enqueue(T element) throws PriQueueOverflowException;

    public abstract T dequeue() throws PriQueueUnderflowException;

    public abstract boolean isFull();

    public abstract boolean isEmpty();

    public abstract int size();



}
