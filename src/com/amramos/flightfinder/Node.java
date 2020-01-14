package com.amramos.flightfinder;

public class Node<T> {
    public final T data;
    private boolean visited = false;

    public static <T> Node<T> of(T data) { return new Node<>(data); }
    private Node(T data) { this.data = data; }

    public void visit() { this.visited = true; }

    public boolean visited() { return visited; }

    @Override
    public int hashCode() {
        return data.hashCode() * 13;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node<?> &&
                data.equals(((Node<?>) obj).data);
    }
}
