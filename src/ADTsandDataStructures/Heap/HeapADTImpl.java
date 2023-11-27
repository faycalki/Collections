import java.util.ArrayList;
/**
 * Implementation of a Max-Heap by an Array as the underlying Data Structure
 * @implSpec We implemented those separately whilst discussing our approaches together and we'd appreciate comments on our different approaches!
 * @author Faycal Kilali
 * @param <T> the generic parameter
 */

public class HeapADTImpl<T extends Comparable<T>> implements HeapADT<T> {
    private T[] heap;
    private int size; // serves as the next free-slot in the array
    private int CAPACITY = 2^5 - 1; // better to use an ArrayList for this, but, this is a decent starting capacity. Note a complete binary tree has (2^n) - 1 nodes (due to the root node), for n levels.

    public HeapADTImpl(){
        heap = (T[]) new Comparable[CAPACITY];
        size = 0;
    }

    public HeapADTImpl(int inCapacity){
        if (inCapacity % 2 != 1 && inCapacity > heap.length){
            throw new IllegalArgumentException("A complete binary tree requires 2^n - 1 nodes, the argument you provided does not satisfy the constraint.");
        }
        heap = (T[]) new Comparable[inCapacity];
        size = 0;
    }


    public HeapADTImpl(int inCapacity, T[] inArray){
        if (inCapacity % 2 != 1 && inCapacity > inArray.length){
            throw new IllegalArgumentException("A complete binary tree requires 2^n - 1 nodes, the argument you provided does not satisfy the constraint.");
        }
        heap = inArray;
        size = 0;
    }



    /**
     * Function that retrieves the value of the
     * root node of the heap without removing it.
     */
    @Override
    public T peekTop() {
        return heap[0];
    }

    /**
     * Function that changes this heap from a MinHeap
     * to a MaxHeap or vice-versa.
     */
    @Override
    public void swapMaxMin() {

    }

    /**
     * Function that checks whether the heap is
     * a MaxHeap or not.
     */
    @Override
    public boolean isMaxHeap() {
        return false;
    }

    /**
     * Function that checks whether the heal is
     * a MinHeap or not.
     */
    @Override
    public boolean isMinHeap() {
        return false;
    }



    /**
     * Function that adds a new value to the heap
     * into the next empty spot of the heap, then
     * HeapUps the value into its proper place.
     * @implSpec Iterative Implementation is better performant in this particular case
     * @param data
     */
    @Override
    public void add(T data) {
        // Add the element
        if (size < CAPACITY){
                heap[size] = data;
        }
        else{
            int curPosition = CAPACITY;
            enLarge();
            heap[curPosition] = data;
        }

        size++;
        // call reHeapUp
        reHeapUp();
        }




    /**
     * Enlarges the current underlying Data Structure
     */
    private void enLarge(){
            // enlarge array, only if no empty slot is found.
            CAPACITY = 2^(CAPACITY + 1) - 1;
            T[] enlargedHeap = (T[]) new Comparable[CAPACITY]; // (2^n+1) - 1 length
            System.arraycopy(heap, 0, enlargedHeap, 0, CAPACITY);
            heap = enlargedHeap;
        }

        private void reHeapUp(){
        reHeapUp(size);
        }
    /**
     * Recursive method to swap up the element to its rightful position in order to maintain the order of the max-heap.
     * @param currentIndex the current index of the Node to compare with its parents
     */
    private void reHeapUp(int currentIndex){
        // Last added element always at position size - 1.

        // base case
        if (currentIndex != 0 && heap[currentIndex] != null) {
            if (heap[currentIndex].compareTo(heap[(currentIndex - 1) / 2]) > 0) { // if higher than preceding (in terms of priority, that is, value),
            // Perform swap
            T temp = heap[(currentIndex - 1) / 2];
            heap[(currentIndex - 1) / 2] = heap[currentIndex];
            heap[currentIndex] = temp;
        }
            else{
                return; // can't swap further up
            }
        }
        reHeapUp((currentIndex - 1) / 2);

        }
    /**
     * Function that removes the value at the root
     * of the heap, then replaces the value with the
     * appropriate value which is HeapDowned into place.
     */
    @Override
    public void removeTop() {
        if (isEmpty()){
            throw new NullPointerException("The Heap is Empty");
        }
    heap[0] = heap[size];
    heap[size] = null;
    size--;
    reHeapDown(0);
    }

    /**
     * Performs the reheapDown operation, performing comparisons of the node at Index with its children to move it to its appropriate slot.
     */
    private void reHeapDown(){

        reHeapDown(0);
    }

    /**
     * Recursive ReHeapDown
     * @param index current Index to recurse
     */
    private void reHeapDown(int index){

        // base case
        if (index >= size || heap[index] == null) {
            return;
        }

        // General case
        if (heap[2 * index + 1] != null && heap[index].compareTo(heap[2 * index + 1]) <= 0
                && (heap[2 * index + 2] == null || heap[2 * index + 1].compareTo(heap[2 * index + 2]) >= 0)) {
            // If Left Child >= Root and Left Child > Right Child (or Right Child is null)
            // Swap child
            T temp = heap[index];
            heap[index] = heap[2 * index + 1];
            heap[2 * index + 1] = temp;
            // Recursive Step
            reHeapDown(index * 2 + 1);
        }

        // General case
        else if (heap[2 * index + 2] != null && heap[0] != null
                && heap[0].compareTo(heap[2 * index + 2]) <= 0 && heap[2 * index + 2].compareTo(heap[2 * index + 1]) >= 0) {
            // If Right Child >= Root and Right Child > Left Child
            // Swap child
            T temp = heap[index];
            heap[index] = heap[2 * index + 2];
            heap[2 * index + 2] = temp;

            // Recursive Step
            reHeapDown(index * 2 + 2);
        }

    }



    /**
     * Function that removes the root value and places
     * a new value into its place, then HeapDowns it into
     * place.
     * <p>
     * This is a common real-world behavior to avoid having
     * to do two heapUp/Down actions in a row when one could
     * suffice if we added and removed at the same time.
     * @implSpec Based on the requirement, we must first remove and then add, if the first slot was null (hence nothing existed there) we can't add, so we throw a nullpointer.
     * @throws NullPointerException if the root is null/non-existent as we can't remove it.
     * @param data the new value to add ontop of the root position before reheaping down
     */
    @Override
    public void replaceTop(T data) {
        if (isEmpty()){
            throw new NullPointerException();
        }

        heap[0] = data;
        reHeapDown();
    }

    /**
     * Function that takes an array of data and translates it
     * into making a new Heap (that will be the object calling
     * Heapify).
     * <p>
     * If the Heap is not empty and this is called, throw an exception.
     * <p>
     * The intention is that this is not a way to add many values at
     * once to the Heap, but to do a direct translation of an array of
     * values to its own distinct Heap.
     * <p>
     * This is very useful for using a Heap for sorting!
     *
     * @param data
     */
    @Override
    public void heapify(T[] data) {
        if (!isEmpty()){
            throw new UnsupportedOperationException("Heap is not Empty");
        }

        heap = data;

        // We just need to check if we are at a leaf node, if we are, we stop the recursion, as well as if they are a null value.
        heapify(data, 0);
    }

    /**
     * Function that takes a second HeapADT object and merges its
     * values with those in the calling HeapADt object to make a
     * distinct, *third* HeapADT object that contains all the values
     * of the original two HeapADT objects.
     * <p>
     * This HeapADT object and the one given as argument to this
     * function should remain unchanged by this operation
     * (may be manipulated during the process, should be normal
     * again at the end).
     *
     * @param otherHeap
     */
    @Override
    public HeapADT<T> merge(HeapADT<T> otherHeap) {
        // Pull all elements out of the heaps, create a third heap, then put them back in accordingly.

        HeapADTImpl<T> newHeap = new HeapADTImpl<T>();
        ArrayList<T> list = new ArrayList<T>();
        // Create arraylist
        while (otherHeap.size() > 0){
            // Remove and add to new Heap
        newHeap.add(otherHeap.peekTop());
            list.add(otherHeap.peekTop());
            otherHeap.removeTop();
        }
        // Re-add to the heap to maintain it
        while (list.size() > 0){
            otherHeap.add(list.get(0));
            list.remove(0);
        }

        // Current heap
        while (size() > 0){
            // Remove and add to new Heap
            newHeap.add(peekTop());
            list.add(peekTop());
            removeTop();
        }
        // Re-add to the current heap to maintain it
        while (list.size() > 0){
            add(list.get(0));
            list.remove(0);
        }

        return newHeap;
    }


    private void heapify(T[] data, int index){

        // base case 1, Leaf Node check
            if (index * 2 + 1 > data.length){
                // we reached our base case, no point of reHeapingDown the leaf node
                return;
            }
            // base case 2
            if (heap[index * 2 + 1].equals(null)){
                return;
            }

            // General case, sort object at Index
            reHeapDown(index);

            // Recursive Step, sort child
            heapify(heap, index * 2 + 1);

            // Base case 3 for right child
            if (heap[index * 2 + 2].equals(null)){
                return;
            }
            // Recursive Step, right child
            heapify(heap, index * 2 + 2);

        }

    /**
     * Function that returns the number of values in this Heap.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Function that queries whether the Heap is empty.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Function that queries whether the Heap is full (if it
     * is possible for your idea of the Heap for it to be full).
     */
    @Override
    public boolean isFull() {
        if (size == CAPACITY){
            return true;
        }
        return false;
    }


}
