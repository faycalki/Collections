/**
 General Interface for a class that implements a collection of type T.
 A collection allows addition, removal, and retrieval of elements.
 For this particular Collection ADT, we dictate:
 Null elements are prohibited, duplicate elements are allowed.
 @author Faycal Kilali, Dale/Joyce/Weems
 @version 1.0
 **/


public interface CollectionADT<T>

{

    /**
     Attempts to add an element to the collection.
     @param element the element to add to the collection.
     @return true if e is successfully added to the collection, false otherwise.
     **/
    public boolean add (T element);


    /**
     * Attempts to retrieve an element e such that e.equals() is target.
     * @param target the e.equals() value we seek
     * @return e if successful, otherwise null.
     */
    public T get (T target);


    /**
     * Confirms whether an element e such that e.equals() is target exists in the Collection.
     * @param target the e.equals() value we seek.
     * @return true if e is found, false otherwise.
     */
    public boolean contains (T target);


    /**
     * Attempts to remove an element e such that e.equals() is target from the Collection.
     * @param target the e.equals() value we seek.
     * @return true if e is removed, false otherwise.
     */
    public boolean remove (T target);


    /**
     * Checks whether the Collection is full.
     * @return true if the Collection is full, false otherwise.
     */
    public boolean isFull();


    /**
     * Checks whether the collection is empty.
     * @return true if the Collection is empty, false otherwise.
     */
    public boolean isEmpty();


    /**
     * Retrieves the size of the Collection.
     * @return number of elements in the Collection.
     */
    public int size();


}