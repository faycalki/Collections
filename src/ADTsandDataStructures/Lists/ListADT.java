
/**
 * List ADT, enforces behavior for its implementations.
 *
 * @author Faycal Kilali, Ada Clevinger
 * @version 1.0
 * @interface add, remove, getSize, and isEmpty methods required.
 */

public interface ListADT<T>{

    /**
     * Adds an element of type T to the front of the list. 
     * We assume no restrictions on the potential size of the list.
     * @param data of type T
     */
public void add(T data);

    /**
     * Removes an entry of type T from the front of the list.
     * 
     * If no element exists to be removed, return a null value.
     */
public T remove();

/**
 * Method that retrieves the number of items in the list for the ADT implementation
 * @return size of list
 */
public int getSize();

/**
 * Method that retrieves whether the list is empty (0) or not for the ADT implementation
 * @return whether the list is empty or not
 */
public boolean isEmpty();

}
