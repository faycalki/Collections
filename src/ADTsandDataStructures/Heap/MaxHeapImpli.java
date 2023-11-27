
/**
 * Write a description of class HeapImpli here.
 *
 * @author Dylan Kim
 * @version 1.0 (11/20/2023)
 */
public class MaxHeapImpli<T extends Comparable<T>> implements MaxHeapADT<T>
{
    private T[] heapArray;
    private int crrSize;
    private int maxSize;
    private final int DEF_SIZE = 32+1;
    private int tail;

    /**
     * Constructor for objects of class HeapImpli
     */
    public MaxHeapImpli(int size)
    {
        heapArray = (T[]) new Object[size];
        crrSize = 0;
        maxSize = size;
        tail = 0;
    }

    public MaxHeapImpli(){
        heapArray = (T[]) new Object[DEF_SIZE];
        crrSize = 0;
        maxSize = DEF_SIZE;
        tail = 0;
    }

    public T peekMax(){
        return heapArray[0];
    }

    private int findBigger(int first, int second){
        return (heapArray[first].compareTo(heapArray[second])==1) ? first : second;
    }

    private void swap(int first, int second){
        T temp = heapArray[first];
        heapArray[first] = heapArray[second];
        heapArray[second] = temp;
    }

    private void reHeapDown(int index){
        int biggerChild;
        if(heapArray[2*index+1]==null){
            return;
        }
        else if(heapArray[2*index+2]==null){
            biggerChild = 2*index+1;
        }
        else{
            biggerChild = findBigger(2*index+1, 2*index+2);
        }

        if(heapArray[index].compareTo(heapArray[biggerChild])>-1){
            return;
        }
        else{
            swap(index, biggerChild);
            reHeapDown(biggerChild);
        }

    }

    private void reHeapUp(int index){
        int parentIndex = -1;
        if(index%2==0){
            parentIndex = (index-2)/2;
        }
        else{
            parentIndex = (index-1)/2;
        }

        if(heapArray[index].compareTo(heapArray[parentIndex])>0){
            swap(index, parentIndex);

            reHeapUp(parentIndex);
        }
        else{
            return;
        }
    }

    private void arrayEnlargement(){
        maxSize = (maxSize-1)*2+1;
        T[] tempArray = (T[]) new Object[maxSize];
        for(int i=0; i<crrSize; i++){
            tempArray[i] = heapArray[i];
        }
        heapArray = tempArray;
    }

    public void add(T data){
        if(isFull()){
            arrayEnlargement();
        }
        heapArray[crrSize+1] = data;
        reHeapUp(crrSize+1);
        crrSize++;
    }

    public void removeMax(){
        if(isEmpty()) {
            throw new IllegalArgumentException("The heap is empty");
        }
        heapArray[0] = heapArray[crrSize];
        heapArray[crrSize] = null;
        reHeapDown(0);
        crrSize--;
    }

    public void replaceMax(T data){
        heapArray[0] = data;
        reHeapDown(0);
    }

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
     * This is very useful for using a Heap for sorting!
     *
     */
    public void heapify(T[] data){
        if(isEmpty()){
            throw new IllegalArgumentException("The heap is already created.");
        }
        for(int i=0;i<data.length;i++){

        }
    }

    public int size(){
        return crrSize;
    }

    public boolean isEmpty(){
        return (crrSize==0);
    }

    public boolean isFull(){
        return (crrSize == maxSize);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<maxSize;i++){
            sb.append(heapArray[i]);
            if(i!=maxSize-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
