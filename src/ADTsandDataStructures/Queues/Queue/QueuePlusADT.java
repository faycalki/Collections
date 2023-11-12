
/**
 * Interface that adds additional functionality to
 * a Queue object (assuming an array-based implementation).
 * 
 * This interface should be implemented alongside the
 * QueueADT interface in your regular Queue class, as
 * you need access to the underlying data structure
 * to do these things.
 * 
 * You may use data structures we have seen previously
 * in this class to help you in these operations, as
 * well as additional instantiations of the Queue class
 * you just wrote.
 * 
 * Realistically, these methods would not be public as they
 * reveal what is under the hood of our Queue class (recall
 * the idea of the Abstract Data Type). Providing an interface
 * such as this requires the public visibility.
 * 
 * Interestingly, abstract classes *can* define protected
 * abstract methods.
 * 
 * @author Ada Clevinger
 * @version Oct 10, 2023
 */
public interface QueuePlusADT<T>
{
    /*
     * This method should manipulate the values inside
     * the Queue to reset the front value to be 0 again
     * (and appropriately have whatever value used to
     * be at the front of the Queue be shifted to index 0).
     * 
     * Be mindful of how your Queue's array has wrapped
     * around to the start, and how best to maintain the
     * correct order while changing the positions of all
     * the elements.
     * 
     * Consider edge cases such as the Queue being empty.
     * How should that be handled? Is it a problem, or a 
     * trivial case?
     * 
     */
    
    public abstract void resetFront();
    
    /*
     * This method should update the size of the underlying
     * array to the new maximum size.
     * 
     * If this would cause a loss of data within the Queue,
     * throw an InsufficientSizeException. Consider what input
     * could cause this error to occur.
     * 
     */
    
    public abstract void changeSize(int newMaxSize) throws InsufficientSizeException;
    
    
}
