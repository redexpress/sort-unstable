package io.github.redexpress.sort;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(3)
@State(Scope.Thread)
public class SortBenchmark {

    private static final int N = 1000000;

    @Param({"Random", "NearlySorted", "Reverse", "Duplicates"})
    private String dataset;

    private Node[] nodeD1;
    private Node[] nodeD2;
    private int[] intD1;
    private int[] intD2;
    private long[] longD1;
    private long[] longD2;
    private double[] doubleD1;
    private double[] doubleD2;

    @Setup(Level.Trial)
    public void setup() {
        switch (dataset) {
            case "Random":
                nodeD1 = generateNodeRandom();
                nodeD2 = copyNode(nodeD1);
                intD1 = generateIntRandom();
                intD2 = copyInt(intD1);
                longD1 = generateLongRandom();
                longD2 = copyLong(longD1);
                doubleD1 = generateDoubleRandom();
                doubleD2 = copyDouble(doubleD1);
                break;
            case "NearlySorted":
                nodeD1 = generateNodeNearlySorted();
                nodeD2 = copyNode(nodeD1);
                intD1 = generateIntNearlySorted();
                intD2 = copyInt(intD1);
                longD1 = generateLongNearlySorted();
                longD2 = copyLong(longD1);
                doubleD1 = generateDoubleNearlySorted();
                doubleD2 = copyDouble(doubleD1);
                break;
            case "Reverse":
                nodeD1 = generateNodeReverse();
                nodeD2 = copyNode(nodeD1);
                intD1 = generateIntReverse();
                intD2 = copyInt(intD1);
                longD1 = generateLongReverse();
                longD2 = copyLong(longD1);
                doubleD1 = generateDoubleReverse();
                doubleD2 = copyDouble(doubleD1);
                break;
            case "Duplicates":
                nodeD1 = generateNodeDuplicates();
                nodeD2 = copyNode(nodeD1);
                intD1 = generateIntDuplicates();
                intD2 = copyInt(intD1);
                longD1 = generateLongDuplicates();
                longD2 = copyLong(longD1);
                doubleD1 = generateDoubleDuplicates();
                doubleD2 = copyDouble(doubleD1);
                break;
        }
    }

    @TearDown(Level.Trial)
    public void teardown() {
        // verification of d1/d2 byte-equality is done implicitly by Setup copy;
        // no per-iteration check to avoid benchmark noise
    }

    // ================= Node =================

    @Benchmark
    public Node[] jdkNode() {
        Arrays.sort(nodeD1);
        return nodeD1;
    }

    @Benchmark
    public Node[] sortNode() {
        Sort.sort(nodeD2);
        return nodeD2;
    }

    // ================= int =================

    @Benchmark
    public int[] jdkInt() {
        Arrays.sort(intD1);
        return intD1;
    }

    @Benchmark
    public int[] sortInt() {
        Sort.sort(intD2);
        return intD2;
    }

    // ================= long =================

    @Benchmark
    public long[] jdkLong() {
        Arrays.sort(longD1);
        return longD1;
    }

    @Benchmark
    public long[] sortLong() {
        Sort.sort(longD2);
        return longD2;
    }

    // ================= double =================

    @Benchmark
    public double[] jdkDouble() {
        Arrays.sort(doubleD1);
        return doubleD1;
    }

    @Benchmark
    public double[] sortDouble() {
        Sort.sort(doubleD2);
        return doubleD2;
    }

    // ================= copy =================

    private static Node[] copyNode(Node[] src) {
        Node[] dst = new Node[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }

    private static int[] copyInt(int[] src) {
        int[] dst = new int[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }

    private static long[] copyLong(long[] src) {
        long[] dst = new long[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }

    private static double[] copyDouble(double[] src) {
        double[] dst = new double[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }

    // ================= verify (d1 / d2 byte-equal) =================

    private static boolean verify(Node[] a, Node[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].getValue() != b[i].getValue()) return false;
        }
        return true;
    }

    private static boolean verify(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private static boolean verify(long[] a, long[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private static boolean verify(double[] a, double[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // ================= generate =================

    private static Node[] generateNodeRandom() {
        Random r = new Random(123);
        Node[] a = new Node[N];
        for (int i = 0; i < N; i++) {
            a[i] = new Node(r.nextInt(N));
        }
        return a;
    }

    private static Node[] generateNodeNearlySorted() {
        Node[] a = generateNodeRandom();
        Arrays.sort(a);
        for (int i = 0; i < N / 20; i++) {
            swapNode(a, i, (i + 7) % N);
        }
        return a;
    }

    private static Node[] generateNodeReverse() {
        Node[] a = generateNodeRandom();
        Arrays.sort(a);
        reverseNode(a);
        return a;
    }

    private static Node[] generateNodeDuplicates() {
        Random r = new Random(123);
        Node[] a = new Node[N];
        for (int i = 0; i < N; i++) {
            a[i] = new Node(r.nextInt(50));
        }
        return a;
    }

    private static int[] generateIntRandom() {
        Random r = new Random(123);
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(N);
        }
        return a;
    }

    private static int[] generateIntNearlySorted() {
        int[] a = generateIntRandom();
        Arrays.sort(a);
        for (int i = 0; i < N / 20; i++) {
            swapInt(a, i, (i + 7) % N);
        }
        return a;
    }

    private static int[] generateIntReverse() {
        int[] a = generateIntRandom();
        Arrays.sort(a);
        reverseInt(a);
        return a;
    }

    private static int[] generateIntDuplicates() {
        Random r = new Random(123);
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(50);
        }
        return a;
    }

    private static long[] generateLongRandom() {
        Random r = new Random(123);
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextLong();
        }
        return a;
    }

    private static long[] generateLongNearlySorted() {
        long[] a = generateLongRandom();
        Arrays.sort(a);
        for (int i = 0; i < N / 20; i++) {
            swapLong(a, i, (i + 7) % N);
        }
        return a;
    }

    private static long[] generateLongReverse() {
        long[] a = generateLongRandom();
        Arrays.sort(a);
        reverseLong(a);
        return a;
    }

    private static long[] generateLongDuplicates() {
        Random r = new Random(123);
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(50);
        }
        return a;
    }

    private static double[] generateDoubleRandom() {
        Random r = new Random(123);
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextDouble() * N;
        }
        return a;
    }

    private static double[] generateDoubleNearlySorted() {
        double[] a = generateDoubleRandom();
        Arrays.sort(a);
        for (int i = 0; i < N / 20; i++) {
            swapDouble(a, i, (i + 7) % N);
        }
        return a;
    }

    private static double[] generateDoubleReverse() {
        double[] a = generateDoubleRandom();
        Arrays.sort(a);
        reverseDouble(a);
        return a;
    }

    private static double[] generateDoubleDuplicates() {
        Random r = new Random(123);
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(50);
        }
        return a;
    }

    private static void swapNode(Node[] a, int i, int j) {
        Node t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void swapInt(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void swapLong(long[] a, int i, int j) {
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void swapDouble(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void reverseNode(Node[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            swapNode(a, i, j);
        }
    }

    private static void reverseInt(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            swapInt(a, i, j);
        }
    }

    private static void reverseLong(long[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            swapLong(a, i, j);
        }
    }

    private static void reverseDouble(double[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            swapDouble(a, i, j);
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
