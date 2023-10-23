
/**
 * Interface for a Linked List ADT in Java.
 *
 * @author Faycal Kilali, Ada Clevinger
 * @version October 19, 2023
 * @interface enforces addToFront, addToBack, removeFromFront, removeFromBack, addAfter, removeFirstInstance, removeAllInstances, toString.
 */
public interface LinkedListADT<T>
{

    /**
     * Adds a Node to the front of the Linked List
     * @param data the memory pointer of the object held within the added Node
     */
    public abstract void addToFront(T data);

    /**
     * Adds a Node to the back of the Linked List
     * @param data the memory pointer of the object held within the added Node
     */
    public abstract void addToBack(T data);

    /**
     * Removes a Node from the front of the Linked List
     * @return the removed Node's data
     */
    public abstract T removeFromFront();


    /**
     * Removes a Node from the back of the Linked List
     * @return the removed Node's data
     */
    public abstract T removeFromBack();

    /**
     * Adds a Node containing dataInsert after a node that contains dataFind inside it.
     * @param dataInsert the data we'll use to create a new Node
     * @param dataFind the data of the Node we'll place the new Node after.
     */
    public abstract void addAfter(T dataInsert, T dataFind);
    //public abstract void addAfter(T dataInsert, Node<T> findNode);


    /**
     * Removes the first instance of a particular data in any given Node
     * @param data the information we seek to remove the first instance of
     */
    public abstract void removeFirstInstance(T data);

    /**
     * Removes all instances of a particular data in all given Nodes within the Linked List.
     * @param data the information we seek to remove all the instances of
     */
    public abstract void removeAllInstances(T data);

    /**
     * Simple method to present a textual representation of the Linked List
     * @return
     */
    public abstract String toString();
    
}
