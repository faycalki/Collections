package Sorting;

/**
 * This interface defines the contract for the Merge Sort algorithm.
 * Merge Sort is a divide-and-conquer algorithm that recursively divides
 * the array into halves, sorts each half, and then merges the sorted halves.
 * @author Faycal Kilali, Dylan Kim
 * @version 1.0
 */
public interface MergeSort {

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     *
     * @param array      the array containing the subarrays to be merged
     * @param leftFirst  the index of the first element in the left subarray
     * @param leftLast   the index of the last element in the left subarray
     * @param rightFirst the index of the first element in the right subarray
     * @param rightLast  the index of the last element in the right subarray
     */
    void merge(int[] array, int leftFirst, int leftLast, int rightFirst, int rightLast);

    /**
     * Sorts the specified range of the array using the Merge Sort algorithm.
     *
     * @param array the array to be sorted
     * @param first the index of the first element (inclusive) in the range to be sorted
     * @param last  the index of the last element (inclusive) in the range to be sorted
     */
    void mergeSort(int[] array, int first, int last);
}


