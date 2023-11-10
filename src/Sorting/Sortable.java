/**
 * This interface defines the contract for a sortable data structure.
 * Implementing classes are expected to provide functionality for sorting
 * elements within the data structure using a specific sorting algorithm.
 *
 * <p>The sorting algorithm details are left to the implementing classes,
 * allowing different sorting implementations to be used interchangeably.</p>
 * An attempt may be made to sort the algorithms in such a manner that they are ordered in terms of better worst-case scenarios to worse worst-case scenarios, as long as the implementation is competent.
 *
 * @implNote Some algorithms are provided with a default implementation, for the convenience of the user. It is recommended to review the implementation notes for specific details on the provided methods.
 * @author Faycal Kilali
 * @version 1.2
 */
public interface Sortable {

    /**
     * Sorts the elements within the data structure.
     *
     * @param array the data structure to be sorted
     */
    void sort(int[] array);


    /**
     * Time Complexity: Ranges from $O(N log_2(N))$ to $O(N^2)$ based on how often the splits are lopsided. The less, the better.
     * Note average-case and best-case complexity is the same, which is $\phi(N log_2(N))$
     * Space Complexity: Ranges from $O(log_2N)$ to $O(N)$.
     * @param array array to be sorted
     */
    default void quickSort(int[] array){
        // Base case (already sorted if only 1 or 0 elements)
        if (array.length < 2){
            return;
        }

        // Check if the range is small enough for counting sort to be efficient
        int[] minMax = findMinMax(array);
        int min = minMax[0];
        int max = minMax[1];

        // It is more efficient to use Counting Sort when (max-min)/4 < array.length
        if ((max - min) / 4 < array.length) {
            countingSort(array);
        } else {
            quickSort(array, 0, array.length - 1);
        }
    }

    /**
     * Method for finding the minimum and maximum from an Array
     * @param array minimum and maximum of this array
     * @return returns an array of minimum at position 0, maximum at position 1.
     */
    private int[] findMinMax(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        return new int[]{min, max};
    }



    /**
     * Helper method to recursively perform QuickSort on the array.
     *
     * @param array array to be sorted
     * @param low   the starting index of the subarray
     * @param high  the ending index of the subarray
     */
    private void quickSort(int[] array, int low, int high){

        if (low < high) {
            // Choose the pivot, this should be randomly sampled but for now isn't for dependency purposes.
            int pivotIndex = choosePivot(array, low, high);

            // Partition the array
            int partitionIndex = partition(array, low, high, pivotIndex);

            // Recursive step (sort the subarrays)
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    /**
     * Chooses the pivot for the QuickSort algorithm.
     * @implNote you may wish to generate a random median for the pivot rather than the pre-selection here.
     * @param array array to be sorted
     * @param low   the starting index of the subarray
     * @param high  the ending index of the subarray
     * @return the chosen pivot
     */
    default int choosePivot(int[] array, int low, int high){
        int a = array[low];
        int b = array[low + (high - low) / 2];
        int c = array[high];

        int pivotIndex = -1;

        // Sample 3 values, pick median. This should be as random as possible, but for the sake of dependencies we'll just sample the first element, last element, and middle element then find the median.
        if ((a <= b && b <= c) || (c <= b && b <= a)) {
            pivotIndex = low + (high - low) / 2;
        } else if ((b <= a && a <= c) || (c <= a && a <= b)) {
            pivotIndex = low;
        } else {
            pivotIndex = high;
        }

        // Swap the chosen pivot with the last element
        int temp = array[pivotIndex];
        array[pivotIndex] = array[high];
        array[high] = temp;

        // Pivot is now at right-most position

        return temp;
    }

    /**
     * Partitions the array for the QuickSort algorithm.
     *
     * @param array      array to be sorted
     * @param low        the starting index of the subarray
     * @param high       the ending index of the subarray
     * @param pivot the value of the chosen pivot
     * @return the index of the pivot after partitioning
     */
    private int partition(int[] array, int low, int high, int pivot){

        // Index of smaller element
        int i = low - 1;

        // Traverse the array
        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (array[j] <= pivot) {
                i++;

                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (put the pivot in its correct place)
        int temp = array[i + 1];
        array[i + 1] = pivot;
        array[high] = temp;

        // Return the index of the pivot
        return i + 1;
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     * O(n)
     * @param array      the array containing the subarrays to be merged
     * @param leftArray  the left subarray to be merged
     * @param rightArray the right subarray to be merged
     */
    default void merge(int[] array, int[] leftArray, int[] rightArray) {
        int i = 0; // Index for leftArray
        int j = 0; // Index for rightArray
        int k = 0; // Index for the merged array

        // Compare elements from both left and right arrays and place them in sorted order
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // Copy the remaining elements from the left array, if any
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        // Copy the remaining elements from the right array, if any
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }

    /**
     * Sorts the specified range of the array using the Merge Sort algorithm.
     *
     * @param array the array to be sorted
     * @implNote Default implementation provided for convenience and educational purposes.
     * This provided default implementation follows the Merge Sort algorithm, which recursively divides
     * the array into left and right halves until base cases are reached. During the unwinding
     * phase of the recursion (that is, at each level), it merges the subarrays into a sorted array. This process is
     * repeated for both left and right halves, resulting in a fully sorted array.
     *
     * The base case stops the recursion when the array size is 1 or less, as an array
     * of this size is inherently sorted.
     */
    default void mergeSort(int[] array) {
        // Base case: Stop recursion when array size is 1 or less
        if (array.length <= 1) {
            return;
        }

        // General case
        int[] leftArray = new int[array.length / 2];
        int[] rightArray = new int[array.length - leftArray.length];

        // Native-Code, more optimized than the for loops
        System.arraycopy(array, 0, leftArray, 0, array.length / 2);
        System.arraycopy(array, array.length / 2, rightArray, 0, array.length - (array.length / 2));

        // Recursive step
        mergeSort(leftArray); // Sort all the left sub-arrays first
        mergeSort(rightArray); // Sort all the right sub-arrays
        merge(array, leftArray, rightArray);
    }

    /**
     * Generalized specification for a insertion sort algorithm.
     * Complexities: O(n^2), Θ(n^2), Ω(n)
     * @implSpec Better performance can be obtained if one creates an implementation that is strictly for the primitive types (as that does not require objects), if their use case is only primitives.
     * @param array the array to be sorted
     * @param <T> the generic parameter type
     * @implNote Default implementation provided for conveneince. This implementation sorts in <p> ascending order </p>, for your purposes you may wish to sort in descending order.
     * A simple resolution to sort in descending order is to simply change the condition of comparison to larger than 0.
     */
    default <T extends Comparable<T>> void insertionSort(T[] array){
        for (int index = 0; index < array.length; index++){
            for (int innerIndex = index; innerIndex > 0; innerIndex--){
                if (array[innerIndex].compareTo(array[innerIndex - 1]) < 0){
                    // if this object is smaller than other object, swap references (so we only do this if they are in the wrong order)
                    T temp = array[innerIndex - 1];
                    array[innerIndex - 1] = array[innerIndex];
                    array[innerIndex] = temp;
                }
            }
        }
    }


    /**
     * Generalized specification for a Selection Sort algorithm
     * O(n^2)
     * @implNote A default implementation is provided for convenience.
     * @param array the array to be sorted
     * @param <T> the type of elements in the array
     */

    default <T extends Comparable<T>> void selectionSort(T[] array){
        for (int i = 0; i < array.length - 1; i++){
            int minIndex = i;
            for (int k = i + 1; k < array.length; k++) {
                if (array[k].compareTo(array[minIndex]) < 0) { // if current object is smaller than other object
                    minIndex = k;
                }
            }
                    T temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;
        }
    }

    /**
     * Counting Sort Specification
     * Generally O(n + k) to sort, where k is the range of the non-negative key values.
     * @param array the array to be sorted
     */
    default void countingSort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find the minimum and maximum values in the array
        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        // Calculate the range (shift if there are negative numbers)
        int range = max - min + 1;

        // Initialize the count array
        int[] count = new int[range];

        // Update the count array with the occurrences of each element from the parameterized array.
        for (int num : array) {
            count[num - min]++;
        }

        // Update the count array to represent the cumulative count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // We'll build the sorted array using the count array
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        // Copying the sorted array to the original array
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }


        /**
         * Converts the array to a string representation.
         *
         * @param array the array to be converted to a string
         * @return a string representation of the array
         */
    default String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}