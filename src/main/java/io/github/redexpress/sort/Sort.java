package io.github.redexpress.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class Sort {

    private static final int INSERTION = 11;

    private Sort() {}

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        if (a == null || a.length <= 1) return;
        quick(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, int from, int to) {
        if (a == null || a.length <= 1) return;
        rangeCheck(a.length, from, to);
        quick(a, from, to);
    }

    public static <T> void sort(T[] a, Comparator<? super T> c) {
        if (a == null || a.length <= 1) return;
        quick(a, 0, a.length - 1, c);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> a) {
        if (a == null || a.size() <= 1) return;
        quick(a, 0, a.size() - 1);
    }

    public static <T> void sort(List<T> a, Comparator<? super T> c) {
        if (a == null || a.size() <= 1) return;
        quick(a, 0, a.size() - 1, c);
    }

    public static void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        Arrays.sort(a);
    }

    public static void sort(long[] a) {
        if (a == null || a.length <= 1) return;
        Arrays.sort(a);
    }

    public static void sort(double[] a) {
        if (a == null || a.length <= 1) return;
        Arrays.sort(a);
    }

    // ================= object (Comparable) =================

    private static <T extends Comparable<? super T>> void quick(T[] a, int lo, int hi) {
        if (hi - lo <= INSERTION) {
            insertion(a, lo, hi);
            return;
        }

        int i = lo, j = hi;
        T pivot = a[(lo + hi) >>> 1];

        while (i <= j) {
            while (a[i].compareTo(pivot) < 0) i++;
            while (a[j].compareTo(pivot) > 0) j--;

            if (i <= j) {
                T temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        if (lo < j) quick(a, lo, j);
        if (i < hi) quick(a, i, hi);
    }

    // ================= object (Comparator) =================

    private static <T> void quick(T[] a, int lo, int hi, Comparator<? super T> c) {
        if (hi - lo <= INSERTION) {
            insertion(a, lo, hi, c);
            return;
        }

        int i = lo, j = hi;
        T pivot = a[(lo + hi) >>> 1];

        while (i <= j) {
            while (c.compare(a[i], pivot) < 0) i++;
            while (c.compare(a[j], pivot) > 0) j--;

            if (i <= j) {
                T t = a[i];
                a[i++] = a[j];
                a[j--] = t;
            }
        }

        if (lo < j) quick(a, lo, j, c);
        if (i < hi) quick(a, i, hi, c);
    }

    // ================= List (Comparator) =================

    private static <T> void quick(List<T> a, int lo, int hi, Comparator<? super T> c) {
        if (hi - lo <= INSERTION) {
            insertion(a, lo, hi, c);
            return;
        }

        int i = lo, j = hi;
        T pivot = a.get((lo + hi) >>> 1);

        while (i <= j) {
            while (c.compare(a.get(i), pivot) < 0) i++;
            while (c.compare(a.get(j), pivot) > 0) j--;

            if (i <= j) {
                T temp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, temp);
                i++;
                j--;
            }
        }

        if (lo < j) quick(a, lo, j, c);
        if (i < hi) quick(a, i, hi, c);
    }

    // ================= List (Comparable) =================

    private static <T extends Comparable<? super T>> void quick(List<T> a, int lo, int hi) {
        if (hi - lo <= INSERTION) {
            insertion(a, lo, hi);
            return;
        }

        int i = lo, j = hi;
        T pivot = a.get((lo + hi) >>> 1);

        while (i <= j) {
            while (a.get(i).compareTo(pivot) < 0) i++;
            while (a.get(j).compareTo(pivot) > 0) j--;

            if (i <= j) {
                T t = a.get(i);
                a.set(i, a.get(j));
                a.set(j, t);
                i++;
                j--;
            }
        }

        if (lo < j) quick(a, lo, j);
        if (i < hi) quick(a, i, hi);
    }

    // ================= insertion =================

    private static <T extends Comparable<? super T>> void insertion(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= lo && a[j].compareTo(key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static <T> void insertion(T[] a, int lo, int hi, Comparator<? super T> c) {
        for (int i = lo + 1; i <= hi; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= lo && c.compare(a[j], key) > 0) {
                a[j + 1] = a[j--];
            }
            a[j + 1] = key;
        }
    }

    private static <T extends Comparable<? super T>> void insertion(List<T> a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            T key = a.get(i);
            int j = i - 1;
            while (j >= lo && a.get(j).compareTo(key) > 0) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, key);
        }
    }

    private static <T> void insertion(List<T> a, int lo, int hi, Comparator<? super T> c) {
        for (int i = lo + 1; i <= hi; i++) {
            T key = a.get(i);
            int j = i - 1;
            while (j >= lo && c.compare(a.get(j), key) > 0) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, key);
        }
    }

    private static void rangeCheck(int len, int from, int to) {
        if (from < 0 || to >= len || from > to) throw new IndexOutOfBoundsException();
    }
}
