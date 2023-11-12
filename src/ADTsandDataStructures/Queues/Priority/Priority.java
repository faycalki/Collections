
/**
 * Interface that enables a class to compare itself to other
 * objects to decide which has a greater 'priority' in the
 * context of a Priority Queue.
 * 
 * The provided generic type T represents the data type that
 * the class that implements this interface is compared against
 * for which has a greater 'priority'.
 * 
 * Often, that generic type T will be the same class that is
 * implementing this interface.
 * 
 * For example, 'class PriorityString implements Priority<PriorityString>'
 * 
 * This would enable your method from this interface to compare
 * objects of type PriorityString to other objects of type PriorityString.
 * 
 * PriorityString would be a wrapper class around a String value that
 * provides this additional Priority functionality.
 * 
 * @author Ada Clevinger, Faycal Kilali
 * @version Oct 13, 2023
 */
public interface Priority<T>
{
    

    /**
     * This is a method to decide if the object of the class that implements the Priority interface has a higher or lower
     * priority than the provided object of type T.
     * As this is an abstract method, we get to dictate how we wish to determine which element is more important.
     * @param other the other element to compare with
     * @return whether the element is more important than the other element
     */
    public abstract boolean isMoreImportant(T other);
    
}
