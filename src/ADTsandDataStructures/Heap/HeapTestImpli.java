
/**
 * Heap Implimentation written by Dylan Kim
 * @implSpec We implemented those separately whilst discussing our approaches together and we'd appreciate comments on our different approaches!
 * @author Dylan Kim
 * @version 1.0 (11/20/2023)
 */
public class HeapTestImpli<T extends Comparable<T>> implements HeapADT<T>{
    private T[] heapArray;
    private int crrSize;
    private int maxSize;
    private final int DEF_SIZE = 16+1;
    private boolean isMax;

    public HeapTestImpli(){
        heapArray = (T[]) new Object[DEF_SIZE];
        crrSize = 0;
        maxSize = DEF_SIZE;
        isMax = true;
    }

    public void swapMaxMin(){

    }

    public boolean isMaxHeap(){
        return isMax;
    }

    public boolean isMinHeap(){
        return !isMax;
    }

    public T peekTop(){
        if(heapArray==null){
            throw new IllegalArgumentException("The heap is empty");
        }
        return heapArray[0];
    }

    /**
     * Helper method that compare two element with respect to max/min and return the higher priority
     * @param first
     * @param second
     * @return either first or second that has higher priority
     */
    private int findHigherPriority(int first, int second){
        return (comparePriority(heapArray[first], heapArray[second])==1) ? first : second;
    }

    /**
     * Helper method that swap two elements in heapArray
     * @param first
     * @param second
     */
    private void swap(int first, int second){
        T temp = heapArray[first];
        heapArray[first] = heapArray[second];
        heapArray[second] = temp;
    }

    /**
     * new compareTo method: if the heap is max heap, then priority would follow the compareTo method, so return the original result.
     *      if the heap is min heap, then the priority is reversed to compareTo method(Ex) if first.compareTo(second) = 1) then first is
     *      lower priority according to min heap. Thus, it return after multiply -1 to the result of compareTo
     * @param first
     * @param second
     * @return
     */
    private int comparePriority(T first, T second){
        if(isMax){
            return first.compareTo(second);
        }
        else{
            return (-1)*(first.compareTo(second));
        }
    }

    private void reHeapDown(int index){
        int biggerChild;
        if(2*index+2<=maxSize){
            if(heapArray[2*index+1]==null){
                return;
            }
            else if(heapArray[2*index+2]==null){
                biggerChild = 2*index+1;
            }
            else{
                biggerChild = findHigherPriority(2*index+1, 2*index+2);
            }

            if(comparePriority(heapArray[index], heapArray[biggerChild])>-1){
                return;
            }
            else{
                swap(index, biggerChild);
                reHeapDown(biggerChild);
            }
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

        if(comparePriority(heapArray[index], heapArray[parentIndex])>0){
            swap(index, parentIndex);

            reHeapUp(parentIndex);
        }
        else{
            return;
        }
    }

    /**
     * Creating a new level to the array.
     */
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

    public void removeTop(){
        if(isEmpty()) {
            throw new IllegalArgumentException("The heap is empty");
        }
        heapArray[0] = heapArray[crrSize];
        heapArray[crrSize] = null;
        reHeapDown(0);
        crrSize--;
    }

    public void replaceTop(T data){
        if(isEmpty()){
            throw new IllegalArgumentException("The heap is empty");
        }
        heapArray[0] = data;
        reHeapDown(0);
    }

    public void heapify(T[] data){

    }

    public HeapADT<T> merge(HeapADT<T> otherHeap){
        return null;

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
