package project1;

/**
 * Created by ERIC_LAI on 2017-01-27.
 */
public class QuickSort {

    /**
     * sort
     *
     * @param array the array need to be sorted
     * @param begin the first index of the array
     * @param last  the last index of the array
     */
    public static void sort(Employee[] array, int begin, int last) {
        if (begin < last) {
            int q = partition(array, begin, last);
            sort(array, begin, q - 1);
            sort(array, q + 1, last);
        }
    }

    /**
     * partition
     *
     * @param array the array need to be sorted
     * @param begin the first index of the array
     * @param pivot the last index of the array
     * @return the sorted array
     */
    private static int partition(Employee[] array, int begin, int pivot) {
        // pointer of the last item which is smaller than the pivot
        int i = begin - 1;
        // j is the pointer of the current item
        for (int j = begin; j < pivot; j++) {
            // if tht current item smaller than the pivot, i move forward a
            // step then exchange it with the item[i]
            if (array[j].getEmpId() < array[pivot].getEmpId()) {
                i++;
                if (i != j) {
                    Employee tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        // put the pivot in the sorted position
        Employee tmp = array[pivot];
        array[pivot] = array[i + 1];
        array[i + 1] = tmp;
        return i + 1;
    }
}
