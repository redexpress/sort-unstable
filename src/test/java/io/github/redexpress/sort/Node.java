package io.github.redexpress.sort;

public class Node implements Comparable<Node> {
    private int value;
    private Color color;

    public Node(int value) {
        this(value, Color.BLACK);
    }

    public Node(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.value, other.value);
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return String.valueOf(value);
        }
        return value + "r";
    }
}
