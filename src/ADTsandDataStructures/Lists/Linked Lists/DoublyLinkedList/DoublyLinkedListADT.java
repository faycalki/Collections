
/**
 * Interface for a doubly linked list that expects to
 * be given generic classes as its type.
 * 
 * Recall that your implementation of the Node class for this
 * Linked List will require some adjustments due to this being
 * a Doubly Linked List, and you will require both a head and
 * tail Node reference.
 *
 * Note that, while you may not use every method listed below
 * in your PriorityQueue class, you should implement them all
 * as part of showing your competency with working with a
 * doubly linked list.
 *
 * @implSpec minor edits to the ADT performed
 *
 * @author Ada Clevinger, Faycal Kilali
 * @version Oct 18, 2023
 */
public interface DoublyLinkedListADT <T>
{
    
    /**
     * Method to check what object of type T is stored at the given
     * position; does not remove the Node at this position.
     *
     * Should throw an exception if the position given is an illegal index
     * for the LinkedList.
     * @param position the position to check the data of
     * @return T the data at that position
     */
    public abstract T checkDataAtLocation(int position);
    
    public abstract void addToFront(T data);
    
    public abstract void addToBack(T data);
    
    public abstract T removeFromFront();
    
    public abstract T removeFromBack();
    
    public abstract void addAfter(T dataInsert, T dataFind);
    // Previous: public abstract void addAfter(T dataInsert, T dataFind);

    public abstract void removeFirstInstance(T data);
    
    public abstract void removeAllInstances(T data);
    
    public abstract String toString();
    
}
