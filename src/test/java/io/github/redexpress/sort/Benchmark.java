package io.github.redexpress.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int SIZE = 2000000;
    private static final int WARMUP = 20;
    private static final int RUN = 10;

    @Test
    void compare() {

        int[] random = generateInt();
        int[] nearly = nearlySorted(random);
        int[] reverse = reverseSorted(random);
        int[] duplicates = duplicates();

        Node[] randomNode = toNode(random);
        Node[] nearlyNode = toNode(nearly);
        Node[] reverseNode = toNode(reverse);
        Node[] duplicatesNode = toNode(duplicates);

        runNode("custom object random\tArrays.sort", randomNode, false);
        runNode("custom object random\tSort.sort",   randomNode, true);
        runNode("custom object nearly sorted\tArrays.sort", nearlyNode, false);
        runNode("custom object nearly sorted\tSort.sort",   nearlyNode, true);
        runNode("custom object reverse\tArrays.sort", reverseNode, false);
        runNode("custom object reverse\tSort.sort",   reverseNode, true);
        runNode("custom object duplicates\tArrays.sort", duplicatesNode, false);
        runNode("custom object duplicates\tSort.sort",   duplicatesNode, true);
    }

    private void runNode(String name, Node[] source, boolean useCustom) {
        for (int i = 0; i < WARMUP; i++) {
            Node[] data = copyNode(source);
            if (useCustom) Sort.sort(data); else Arrays.sort(data);
        }
        double total = 0;
        for (int i = 0; i < RUN; i++) {
            Node[] data = copyNode(source);
            long start = System.nanoTime();
            if (useCustom) Sort.sort(data); else Arrays.sort(data);
            long end = System.nanoTime();
            total += (end - start) / 1000000.0;
        }
        System.out.printf("%s\t%.3f ms%n", name, total / RUN);
    }

    private int[] generateInt() {
        Random r = new Random(42);
        int[] data = new int[SIZE];
        for (int i = 0; i < SIZE; i++) data[i] = r.nextInt();
        return data;
    }

    private int[] nearlySorted(int[] base) {
        int[] data = base.clone();
        Arrays.sort(data);
        for (int i = 0; i < SIZE / 20; i++) {
            int a = i;
            int b = (i + 7) % SIZE;
            int t = data[a]; data[a] = data[b]; data[b] = t;
        }
        return data;
    }

    private int[] reverseSorted(int[] base) {
        int[] data = base.clone();
        Arrays.sort(data);
        for (int i = 0, j = data.length - 1; i < j; i++, j--) {
            int t = data[i]; data[i] = data[j]; data[j] = t;
        }
        return data;
    }

    private int[] duplicates() {
        Random r = new Random(42);
        int[] data = new int[SIZE];
        for (int i = 0; i < SIZE; i++) data[i] = r.nextInt(50);
        return data;
    }

    private Node[] toNode(int[] base) {
        Node[] data = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) data[i] = new Node(base[i]);
        return data;
    }

    private Node[] copyNode(Node[] src) {
        Node[] data = new Node[src.length];
        for (int i = 0; i < src.length; i++) {
            data[i] = new Node(src[i].getValue(), src[i].getColor());
        }
        return data;
    }
}
