/**
 * Priority Queue Implementation using Doubly Linked Lists as the underlying data structure.
 * @implSpec This could also be Implemented by extending a Queue, however, that would require changing of the PriorityQueueADT due to the PriQueueUnderflowException and PriQueueOverflowException.
 * The current implementation uses a simple approach through Linked Lists. It, forcibly, only applies the principles of a Queue (dequeue from front, enqueue to back). FIFO.
 * @implNote the performance of this Priority Queue can  be vastly improved by considering the size of the queue prior to enquiuing. If the position where we wish to enqueue is beyond half of the queue, then we should traverse backwards from the Doubly Linked List, then creating a link from there to the rest of the following nodes, as well as a link for the previous nodes.
 * If the prioritized item is to be placed somewhere before the halfway or at the halfway of the queue, then the Linked List should be traversed up to that point and a link should be created from the preceding nodes to the newly added node, and the newly added node should point towards the next nodes in the chain.
 * The performance of this Implementation is currently subpar.
 * @param <T>
 */
public class PriorityQueueAlt<T extends Priority<T>> extends DoublyLinkedList<T> implements PriorityQueueAltADT<T> {

    private DoublyLinkedList<T> list;
    private int MAX_CAPACITY = 10 * 10000; // for testing purposes. If using an array, this should be array.length instead.

    private PriorityQueueAlt<T> tempQueue;
    public PriorityQueueAlt(){
        tempQueue = null;
        list = new DoublyLinkedList<T>();
    }


    /**
     * Adds an element at its appropriate position based on the priority of the elements
     * @param element the element to attempt adding to the queue
     * @throws PriQueueOverflowException if we try to add over the maximum capacity
     */
    @Override
    public void enqueue(T element) throws PriQueueOverflowException {

        if (size() == 0){
            enqueueHelper(element);
            return;
        }

        if (isFull()){
            throw new PriQueueOverflowException("Queue is full");
        }

        boolean priorityFound = false;
        int originalSize = size();
        int priorityPosition = 0;
        tempQueue = new PriorityQueueAlt<T>();
        for (int currentPosition = 0; currentPosition < originalSize; currentPosition++){
            try {
                T frontElement = dequeue(); // We remove the current front-most element in the Queue (Dequeue)
                tempQueue.enqueueHelper(frontElement); // Add to the back of the temporary queue

                /*
                 * Track position of current placement (should be currentPosition)
                 * Remove all remaining elements in the Queue (placing them one by one in the temporary queue)
                 * Add all the elements prior to currentPosition from the temporary queue to the priority queue
                 * Add the passed-in argument element to the priority queue
                 * Add the remaining elements from the temporary queue to the priority queue
                 */

                if (element.isMoreImportant(frontElement) == true && priorityFound == false) {
                    priorityFound = true;
                    priorityPosition = currentPosition;
                }
            }
            catch(PriQueueUnderflowException e){
                System.err.println("Unexpected PriQueueUnderflow Exception");
            }
        }

        if (priorityFound == true) {
            // Add the elements up to the priority position as they were back to the queue
            for (int k = 0; k < priorityPosition; k++){
                try {
                    enqueueHelper(tempQueue.dequeueHelper());
                } catch (PriQueueUnderflowException e) {
                    throw new RuntimeException(e);
                }
            }
            // Add the priority element
            enqueueHelper(element);

            // Add the remaining elements
            for (int i = priorityPosition; i < originalSize; i++){
                try {
                    enqueueHelper(tempQueue.dequeueHelper());
                } catch (PriQueueUnderflowException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // If the actual parameter is no more important than any element in the queue, execute the following
        else{
            for (int position = 0; position < originalSize; position++){
                try {
                    enqueueHelper(tempQueue.dequeueHelper()); // regain the order
                } catch (PriQueueUnderflowException e) {
                    throw new RuntimeException(e);
                }
            }
            enqueueHelper(element); // it is obligatory that the element must be of same or less priority than the last element in the queue.
        }
    }

    @Override
    public T dequeue() throws PriQueueUnderflowException {
        try {
            return dequeueHelper();
        }
        catch (PriQueueUnderflowException e) {
            //throw new PriQueueUnderflowException("Unexpected PriQueueUnderflowException");
            throw new PriQueueUnderflowException("Unexpected PriQueueUnderflowException");
        }
    }


    /**
     * Adds to the back of the Queue
     * @param element the element to add to the back of the Queue
     * @throws PriQueueOverflowException if the queue is full
     */
    private void enqueueHelper(T element) throws PriQueueOverflowException {

        if (size() == MAX_CAPACITY){
            throw new PriQueueOverflowException("PriQueueOverflowException: attempted to enqueue over maximum capacity.");
        }
        list.addToBack(element);
    }


    public T dequeueHelper() throws PriQueueUnderflowException {
        if (isEmpty()){
            throw new PriQueueUnderflowException("PriQueueUnderflowException: attempted to dequeue an empty Queue");
        }
        return list.removeFromFront();
    }


    /**
     * Checks if we've reached the max capacity of a Queue.
     * @return
     */
    @Override
    public boolean isFull() {
        if (size() == MAX_CAPACITY){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (size() != 0){
            return false;
        }
        return true;
    }


    @Override
    public int size() {
        return list.getSize();
    }


    /**
     * Resets the front variable of an array-based implementation to 0.
     * @implSpec for a Linked List, this has no particular use
     */
    public void resetFront() {
    }

    /**
     * Resizes the Queue to a new Maximum Size.
     * @implSpec Our Linked List implementation has no real maximum size except our enforced constant, so this is trivial.
     * @implSpec If this was an Array implementation, then this should resize the array to the new size.
     * @param newMaxSize the new capacity of the Queue
     * @throws InsufficientSizeException if the resizing deletes some previous data from the Array
     */
    public void changeSize(int newMaxSize) throws InsufficientSizeException {
        if (newMaxSize < list.getSize()){
            throw new InsufficientSizeException("InsufficientSizeException: attempted to illegally resize the capacity of the queue");
        }
        MAX_CAPACITY = newMaxSize;
    }


    /**
     * Accessor for the Maximum Capacity of the underlying data structure
     * @implSpec This is useful for an array-based implementation or enforced ceiling for Linked Lists.
     * @return MAX_CAPACITY the maximum capacity
     */
    public int getMaxCapacity(){
        return MAX_CAPACITY;
    }


    /**
     * Textual representation of the Priority Queue
     * @return the Priority Queue as a textual representation
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Queue: [");
        Node<T> current = list.getHeadData();

        while (current != null) {
            result.append(current.getData());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }

        result.append("]");
        return result.toString();
    }






}