package io.github.redexpress.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    @Test
    void testArrayBasic() {
        Integer[] arr = {9, 23, 45, 78, 34, 91, 56, 67, 29, 41, 83, 11, 15, 19, 2};
        Sort.sort(arr);

        assertArrayEquals(
                new Integer[]{2, 9, 11, 15, 19, 23, 29, 34, 41, 45, 56, 67, 78, 83, 91},
                arr
        );
    }

    @Test
    void testArrayWithComparator() {
        Node[] arr = {
                new Node(9),
                new Node(23, Color.RED),
                new Node(45),
                new Node(78),
                new Node(34, Color.RED),
                new Node(91),
                new Node(56),
                new Node(67),
                new Node(29, Color.RED),
                new Node(41),
                new Node(83),
                new Node(11),
                new Node(15, Color.RED),
                new Node(19),
                new Node(2)
        };

        Sort.sort(arr, Comparator.comparingInt(Node::getValue));

        int[] actual = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            actual[i] = arr[i].getValue();
        }

        assertArrayEquals(
                new int[]{2, 9, 11, 15, 19, 23, 29, 34, 41, 45, 56, 67, 78, 83, 91},
                actual
        );
    }

    @Test
    void testListBasic() {
        List<Integer> list = new ArrayList<>(Arrays.asList(
                9, 23, 45, 78, 34, 91, 56, 67, 29, 41, 83, 11, 15, 19, 2
        ));

        Sort.sort(list);

        assertEquals(
                Arrays.asList(2, 9, 11, 15, 19, 23, 29, 34, 41, 45, 56, 67, 78, 83, 91),
                list
        );
    }

    @Test
    void testListWithComparator() {
        List<Node> list = new ArrayList<>(Arrays.asList(
                new Node(9),
                new Node(23),
                new Node(45),
                new Node(78),
                new Node(34),
                new Node(91),
                new Node(56),
                new Node(67),
                new Node(29),
                new Node(41),
                new Node(83),
                new Node(11),
                new Node(15),
                new Node(19),
                new Node(2, Color.RED)
        ));

        Sort.sort(list, Comparator.comparingInt(Node::getValue));

        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).getValue();
        }

        assertArrayEquals(
                new int[]{2, 9, 11, 15, 19, 23, 29, 34, 41, 45, 56, 67, 78, 83, 91},
                actual
        );
    }

    @Test
    void testIntArray() {
        int[] arr = {9, 23, 45, 78, 34, 91, 56, 67, 29, 41, 83, 11, 15, 19, 2};

        Sort.sort(arr);

        assertArrayEquals(new int[]{2, 9, 11, 15, 19, 23, 29, 34, 41, 45, 56, 67, 78, 83, 91}, arr);
    }

    @Test
    void testLongArray() {
        long[] arr = {9L, 23L, 45L, 78L, 34L, 91L, 56L, 67L, 29L, 41L, 83L, 11L, 15L, 19L, 2L};

        Sort.sort(arr);

        assertArrayEquals(new long[]{2L, 9L, 11L, 15L, 19L, 23L, 29L, 34L, 41L, 45L, 56L, 67L, 78L, 83L, 91L}, arr);
    }

    @Test
    void testDoubleArray() {
        double[] arr = {9.9, 23.2, 45.5, 78.1, 34.3, 91.0, 56.6, 67.7, 29.9, 41.4, 83.8, 11.1, 15.5, 19.9, 2.2};

        Sort.sort(arr);

        assertArrayEquals(new double[]{2.2, 9.9, 11.1, 15.5, 19.9, 23.2, 29.9, 34.3, 41.4, 45.5, 56.6, 67.7, 78.1, 83.8, 91.0}, arr);
    }

    @Test
    void testRangeArray() {
        Integer[] arr = {9, 23, 45, 78, 34, 91, 56, 67, 29, 41, 83, 11, 15, 19, 2};

        Sort.sort(arr, 3, 10);

        assertTrue(arr.length == 15);
        assertEquals(9, (int) arr[0]);
        assertEquals(23, (int) arr[1]);
    }

    @Test
    void testEmptyArray() {
        Integer[] arr = new Integer[]{};
        Sort.sort(arr);
        assertArrayEquals(new Integer[]{}, arr);
    }

    @Test
    void testSingleElement() {
        Integer[] arr = {42};
        Sort.sort(arr);
        assertArrayEquals(new Integer[]{42}, arr);
    }

    @Test
    void testNullSafety() {
        Integer[] arr = null;
        Sort.sort(arr);
        assertNull(arr);
    }
}