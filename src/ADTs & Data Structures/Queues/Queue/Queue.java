/**
 * Linked-List based implementation of a Queue.
 * Adds to the back (Enqueues), takes from the front (Dequeues). FIFO.
 * @param <T>
 */

public class Queue<T> implements QueueADT<T>, QueuePlusADT<T> {

    private DoublyLinkedList<T> list;
    private int MAX_CAPACITY = 10 * 1000; // for testing purposes. If using an array, this should be array.length instead.

    /**
     * Initialize our implementation of a List to use for our Queue
     */
    public Queue(){
        list = new DoublyLinkedList<T>();
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {

        if (size() == MAX_CAPACITY){
            throw new QueueOverflowException("QueueOverflowException: attempted to enqueue over maximum capacity.");
        }
        list.addToBack(element);
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()){
            throw new QueueUnderflowException("QueueUnderflowException: attempted to dequeue an empty Queue");
        }
        return list.removeFromFront();
    }

    /**
     * Accessor method for the front of the queue
     * @return the highest priority element in the Queue
     * @throws QueueUnderflowException if the Queue is empty
     */
    public T peek() throws QueueUnderflowException {
        if (isEmpty()){
            throw new QueueUnderflowException("QueueUnderflowException: attempted to dequeue an empty Queue");
        }
        return list.getHeadData().getData();
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


    public int size() {
        return list.getSize();
    }


    /**
     * Resets the front variable of an array-based implementation to 0.
     * @implSpec for a Linked List, this has no particular use
     */
    @Override
    public void resetFront() {
    }

    /**
     * Resizes the Queue to a new Maximum Size.
     * @implSpec Our Linked List implementation has no real maximum size except our enforced constant, so this is trivial.
     * @implSpec If this was an Array implementation, then this should resize the array to the new size.
     * @param newMaxSize the new capacity of the Queue
     * @throws InsufficientSizeException if the resizing deletes some previous data from the Array
     */
    @Override
    public void changeSize(int newMaxSize) throws InsufficientSizeException {
        if (newMaxSize < list.getSize()){
            throw new InsufficientSizeException("InsufficientSizeException: attempted to illegally resize the capacity of the queue");
        }
        MAX_CAPACITY = newMaxSize;
    }


    public int getMaxCapacity(){
        return MAX_CAPACITY;
    }

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
