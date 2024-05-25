package chervonnaya;

import java.util.Comparator;

/**
 * A utility class that provides a generic implementation of the QuickSort algorithm.
 */
public class QuickSort {

    /**
     * Sorts the specified array of objects according to the order induced by the specified comparator.
     *
     * @param <T>        the class of the objects to be sorted
     * @param array      the array to be sorted
     * @param comparator the comparator to determine the order of the array.
     */
    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    /**
     * Recursively sorts the subarray specified by the indices {@code low} and {@code high}.
     *
     * @param <T>        the class of the objects to be sorted
     * @param array      the array to be sorted
     * @param low        the index of the first element, inclusive, to be sorted
     * @param high       the index of the last element, inclusive, to be sorted
     * @param comparator the comparator to determine the order of the array.
     */
    private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pi = partition(array, low, high, comparator);

            quickSort(array, low, pi - 1, comparator);
            quickSort(array, pi + 1, high, comparator);
        }
    }

    /**
     * Partitions the specified subarray around a pivot element.
     *
     * @param <T>        the class of the objects to be sorted
     * @param array      the array to be sorted
     * @param low        the index of the first element, inclusive, to be sorted
     * @param high       the index of the last element, inclusive, to be sorted
     * @param comparator the comparator to determine the order of the array.
     * @return the index of the pivot element
     */
    private static <T> int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;

                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}