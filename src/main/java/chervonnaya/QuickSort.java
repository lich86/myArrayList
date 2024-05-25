package chervonnaya;

import java.util.Comparator;

public class QuickSort {

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pi = partition(array, low, high, comparator);

            quickSort(array, low, pi - 1, comparator);
            quickSort(array, pi + 1, high, comparator);
        }
    }

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