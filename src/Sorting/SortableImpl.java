public class SortableImpl implements Sortable {


//    @Override
//    public void sort(int[] array) {
//        Integer[] arrayNew = new Integer[array.length];
//        for (int i = 0; i < array.length; i++){
//            arrayNew[i] = array[i];
//        }
//        selectionSort(arrayNew);
//
//        for (int i = 0; i < array.length; i++){
//            array[i] = arrayNew[i];
//        }
//    }

    @Override
    public void sort(int[] array) {
        quickSort(array);


    }




}
