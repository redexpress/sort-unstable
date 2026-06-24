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
    void testNullArrayThrowsNpe() {
        Integer[] a1 = null;
        Integer[] a2 = null;
        Integer[] a3 = null;
        List<Integer> l1 = null;
        List<Integer> l2 = null;

        org.junit.jupiter.api.function.Executable e1 = () -> Sort.sort(a1);
        org.junit.jupiter.api.function.Executable e2 = () -> Sort.sort(a2, 0, 0);
        org.junit.jupiter.api.function.Executable e3 = () -> Sort.sort(a3, Comparator.naturalOrder());
        org.junit.jupiter.api.function.Executable e4 = () -> Sort.sort(l1);
        org.junit.jupiter.api.function.Executable e5 = () -> Sort.sort(l2, Comparator.naturalOrder());

        assertThrows(NullPointerException.class, e1);
        assertThrows(NullPointerException.class, e2);
        assertThrows(NullPointerException.class, e3);
        assertThrows(NullPointerException.class, e4);
        assertThrows(NullPointerException.class, e5);
    }
}