/**
 * 
 * Interface to define the typical behaviors of a Heap data
 * structure; notably, the terminology has been changed to
 * peek/remove 'Top' instead of Max/Min.
 * 
 * This is because this Heap can dynamically swap to restructure
 * itself to be a max or min heap! Note that this will require
 * a full reconstruction of your Heap when it swaps.
 * 
 * Your implementation should use an array as the underlying
 * data structure.
 *
 * @author Ada Clevinger
 * @version Nov 17, 2023
 * 
 */

public interface HeapADT <T extends Comparable<T>>
{
    
    /**
     * 
     * Function that changes this heap from a MinHeap
     * to a MaxHeap or vice-versa.
     * 
     */
    
    public abstract void swapMaxMin();
    
    /**
     * 
     * Function that checks whether the heap is
     * a MaxHeap or not.
     * 
     */
    
    public abstract boolean isMaxHeap();
    
    /**
     * 
     * Function that checks whether the heal is
     * a MinHeap or not.
     * 
     */
    
    public abstract boolean isMinHeap();
    
    /**
     * 
     * Function that retrieves the value of the
     * root node of the heap without removing it.
     * 
     */
    
    public abstract T peekTop();
    
    /**
     * 
     * Function that adds a new value to the heap
     * into the next empty spot of the heap, then
     * HeapUps the value into its proper place.
     * 
     */
    
    public abstract void add(T data);
    
    /**
     * 
     * Function that removes the value at the root
     * of the heap, then replaces the value with the
     * appropriate value which is HeapDowned into place.
     * 
     */
    
    public abstract void removeTop();
    
    /**
     * 
     * Function that removes the root value and places
     * a new value into its place, then HeapDowns it into
     * place.
     * 
     * This is a common real-world behavior to avoid having
     * to do two heapUp/Down actions in a row when one could
     * suffice if we added and removed at the same time.
     * 
     */
    
    public abstract void replaceTop(T data);
    
    /**
     * 
     * Function that takes an array of data and translates it
     * into making a new Heap (that will be the object calling
     * Heapify).
     * 
     * If the Heap is not empty and this is called, throw an exception.
     * 
     * The intention is that this is not a way to add many values at
     * once to the Heap, but to do a direct translation of an array of
     * values to its own distinct Heap.
     * 
     */
    
    public abstract void heapify(T[] data);
    
    /**
     * 
     * Function that takes a second HeapADT object and merges its
     * values with those in the calling HeapADt object to make a
     * distinct, *third* HeapADT object that contains all the values
     * of the original two HeapADT objects.
     * 
     * This HeapADT object and the one given as argument to this
     * function should remain unchanged by this operation
     * (may be manipulated during the process, should be normal
     * again at the end).
     * 
     */
    
    public abstract HeapADT<T> merge(HeapADT<T> otherHeap);
    
    /**
     * 
     * Function that returns the number of values in this Heap.
     * 
     */
    
    public abstract int size();
    
    /**
     * 
     * Function that queries whether the Heap is empty.
     * 
     */
    
    public abstract boolean isEmpty();
    
    /**
     * 
     * Function that queries whether the Heap is full (if it
     * is possible for your idea of the Heap for it to be full).
     * 
     */
    
    public abstract boolean isFull();
    
    /**
     * 
     * Function that creates a textual representation of the contents
     * of this Heap.
     * 
     */
    
    public abstract String toString();
    
}
