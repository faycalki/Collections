import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortableTest {

    @Test
    public void testSort() {
        // Test case 1: Unsorted array
        int[] unsortedArray = {5, 3, 9, 1, 7};
        int[] expectedSortedArray = {1, 3, 5, 7, 9};
        testSortHelper(unsortedArray, expectedSortedArray);

        // Test case 2: Already sorted array
        int[] sortedArray = {1, 2, 3, 4, 5};
        testSortHelper(sortedArray, sortedArray);

        // Test case 3: Array with duplicate elements
        int[] arrayWithDuplicates = {4, 2, 6, 2, 4};
        int[] expectedSortedArrayWithDuplicates = {2, 2, 4, 4, 6};
        testSortHelper(arrayWithDuplicates, expectedSortedArrayWithDuplicates);

        // Test case 4: Array with negative numbers
        int[] arrayWithNegatives = {-4, 2, -6, 0, 4};
        int[] expectedSortedArrayWithNegatives = {-6, -4, 0, 2, 4};
        testSortHelper(arrayWithNegatives, expectedSortedArrayWithNegatives);

        // Test case 5: Large array
        int[] largeArray = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expectedSortedLargeArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        testSortHelper(largeArray, expectedSortedLargeArray);
    }



    @Test
    public void testSortComplexity() {
        // Test case 1: Time complexity and correctness
        int[] unsortedArray = generateLargeRandomArray(10000);
        long startTime = System.currentTimeMillis();
        Sortable sortable = new SortableImpl();
        System.out.println("Unsorted large array: " + sortable.arrayToString(unsortedArray));
        sortable.sort(unsortedArray);
        System.out.println("Sorted large array: " + sortable.arrayToString(unsortedArray));
        long endTime = System.currentTimeMillis();
        assertTrue(isSorted(unsortedArray));
        System.out.println("Time taken for 10,000 elements: " + (endTime - startTime) + " ms");

        // Test case 2: Space complexity
        int[] arrayWithDuplicates = {4, 2, 6, 2, 4};
        int[] expectedSortedArrayWithDuplicates = {2, 2, 4, 4, 6};
        testSortHelper(arrayWithDuplicates, expectedSortedArrayWithDuplicates);
        System.out.println("Space used for array with duplicates: " + getMemoryUsage() + " bytes");
    }



    // Helpers
    private void testSortHelper(int[] inputArray, int[] expectedSortedArray) {
        Sortable sortable = new SortableImpl();
        sortable.sort(inputArray);
        assertArrayEquals(expectedSortedArray, inputArray);
    }

    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private int[] generateLargeRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }

    private long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }


    @Test
    public void testQuickSort() {
        // Test case 1: Unsorted array
        int[] unsortedArray = {5, 3, 9, 1, 7};
        int[] expectedSortedArray = {1, 3, 5, 7, 9};
        testSortHelper(unsortedArray, expectedSortedArray);

        // Test case 2: Already sorted array
        int[] sortedArray = {1, 2, 3, 4, 5};
        testSortHelper(sortedArray, sortedArray);

        // Test case 3: Array with duplicate elements
        int[] arrayWithDuplicates = {4, 2, 6, 2, 4};
        int[] expectedSortedArrayWithDuplicates = {2, 2, 4, 4, 6};
        testSortHelper(arrayWithDuplicates, expectedSortedArrayWithDuplicates);

        // Test case 4: Array with negative numbers
        int[] arrayWithNegatives = {-4, 2, -6, 0, 4};
        int[] expectedSortedArrayWithNegatives = {-6, -4, 0, 2, 4};
        testSortHelper(arrayWithNegatives, expectedSortedArrayWithNegatives);

        // Test case 5: Large array
        int[] largeArray = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expectedSortedLargeArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        testSortHelper(largeArray, expectedSortedLargeArray);

        // Test case 6: Array with one element
        int[] singleElementArray = {42};
        int[] expectedSortedSingleElementArray = {42};
        testSortHelper(singleElementArray, expectedSortedSingleElementArray);

        // Test case 7: Empty array
        int[] emptyArray = {};
        int[] expectedSortedEmptyArray = {};
        testSortHelper(emptyArray, expectedSortedEmptyArray);
    }


    @Test
    public void testInsertionSort() {
        // Test case 1: Unsorted array
        Integer[] unsortedArray = {5, 3, 9, 1, 7};
        Integer[] expectedSortedArray = {1, 3, 5, 7, 9};
        testHelperInsertionSort(unsortedArray, expectedSortedArray);

        // Test case 2: Already sorted array
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        testHelperInsertionSort(sortedArray, sortedArray);

        // Test case 3: Array with duplicate elements
        Integer[] arrayWithDuplicates = {4, 2, 6, 2, 4};
        Integer[] expectedSortedArrayWithDuplicates = {2, 2, 4, 4, 6};
        testHelperInsertionSort(arrayWithDuplicates, expectedSortedArrayWithDuplicates);

        // Test case 4: Array with negative numbers
        Integer[] arrayWithNegatives = {-4, 2, -6, 0, 4};
        Integer[] expectedSortedArrayWithNegatives = {-6, -4, 0, 2, 4};
        testHelperInsertionSort(arrayWithNegatives, expectedSortedArrayWithNegatives);

        // Test case 5: Large array
        Integer[] largeArray = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] expectedSortedLargeArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        testHelperInsertionSort(largeArray, expectedSortedLargeArray);
    }

    // Helper method for testing
    private <T extends Comparable<T>> void testHelperInsertionSort(T[] inputArray, T[] expectedSortedArray) {
        Sortable sortable = new SortableImpl();
        sortable.insertionSort(inputArray);
        assertArrayEquals(expectedSortedArray, inputArray);
    }

    @Test
    public void testArrayToString() {
        SortableImpl sortable = new SortableImpl();

        // Test case: Array with positive numbers
        int[] positiveArray = {3, 1, 4, 1, 5, 9};
        String expectedPositiveString = "[3, 1, 4, 1, 5, 9]";
        assertEquals(expectedPositiveString, sortable.arrayToString(positiveArray));

        // Test case: Array with negative numbers
        int[] negativeArray = {-2, -4, -6, -8};
        String expectedNegativeString = "[-2, -4, -6, -8]";
        assertEquals(expectedNegativeString, sortable.arrayToString(negativeArray));

        // Test case: Empty array
        int[] emptyArray = {};
        String expectedEmptyString = "[]";
        assertEquals(expectedEmptyString, sortable.arrayToString(emptyArray));

    }


}