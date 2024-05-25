package chervonnaya;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A generic resizable array implementation similar to Java's ArrayList.
 *
 * @param <T> the type of elements in this list
 */
public class MyArrayList<T> {
    private T[] list;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else {
            this.list = (T[]) new Object[initialCapacity];
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param item element to be appended to this list
     * @return {@code true}
     */
    public boolean add(T item) {
        if (size == list.length) {
            list = grow();
        }
        list[size++] = item;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param item element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    public void add(int index, T item) {
        rangeCheckForAdd(index);
        if (size == list.length) {
            list = grow();
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param item element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T set(int index, T item) {
        rangeCheck(index);
        T oldValue = list[index];
        list[index] = item;
        return oldValue;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index) {
        rangeCheck(index);
        return list[index];
    }

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     *
     * @param item element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(T item) {
        int index = getIndex(item);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        Arrays.fill(list, 0, size, null);
        size = 0;
    }

    /**
     * Sorts this list according to the order induced by the specified {@link Comparator}.
     *
     * @param comparator the {@code Comparator} used to compare list elements.
     */
    public void sort(Comparator<? super T> comparator) {
        Arrays.sort(list, 0, size, comparator);
    }

    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this {@code MyArrayList} instance, if necessary,
     * to ensure that it can hold at least the number of elements specified by the capacity argument.
     *
     * @return the new array with increased capacity
     */
    private T[] grow() {
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return Arrays.copyOf(list, newCapacity);
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate runtime exception.
     * This method does not check if the index is strictly less than {@code size}.
     * It is intended to be used in methods that can add elements at the end of the list.
     *
     * @param index the index to be checked
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate runtime exception.
     *
     * @param index the index to be checked
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * @param index the index that is out of bounds
     * @return the detailed message string
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param item element to search for
     * @return the index of the first occurrence of the specified element in this list,
     *         or -1 if this list does not contain the element
     */
    private int getIndex(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(list[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
