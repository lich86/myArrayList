package chervonnaya;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<T> {
    private T[] list;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else {
            this.list = (T[]) new Object[initialCapacity];
        }
    }

    public MyArrayList() {
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean add(T item) {
        if (size == list.length) {
            list = grow();
        }
        list[size++] = item;
        return true;
    }

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

    public T set(int index, T item) {
        rangeCheck(index);
        T oldValue = list[index];
        list[index] = item;
        return oldValue;
    }

    public T get(int index) {
        rangeCheck(index);
        return list[index];
    }

    public T remove(int index) {
        rangeCheck(index);
        T oldValue = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return oldValue;
    }

    public boolean remove(T item) {
        int index = getIndex(item);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        Arrays.fill(list, 0, size, null);
        size = 0;
    }

    public void sort(Comparator<? super T> comparator) {
        Arrays.sort(list, 0, size, comparator);
    }

    public int size() {
        return size;
    }

    private T[] grow() {
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return Arrays.copyOf(list, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

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
