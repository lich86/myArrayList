package chervonnaya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        assertEquals(1000, list.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 999; i >= 0; i--) {
            assertEquals(i, list.remove(i));
        }

        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveByItem() {
        list.add(1);
        list.add(2);
        assertTrue(list.remove(Integer.valueOf(1)));
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
        assertFalse(list.remove(Integer.valueOf(3)));
    }

    @Test
    public void testSet() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 1000; i++) {
            list.set(i, i * 2);
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 2, list.get(i));
        }
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testSort() {
        for (int i = 0; i < 1000; i++) {
            list.add((int) (Math.random() * 1000));
        }
        list.sort(Comparator.naturalOrder());

        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i) >= list.get(i - 1));
        }
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        list.add(1);
        list.add(1);
        assertEquals(3, list.size());
    }

    @Test
    public void testPositiveInitialCapacity_Should_Succeed() {
        MyArrayList<Integer> listWithCapacity = new MyArrayList<>(20);
        assertEquals(0, listWithCapacity.size());
    }

    @Test
    public void testNegativeInitialCapacity_Should_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-1));
    }

    @Test
    public void testOutOfBounds_Should_ThrowExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 3));
    }

}