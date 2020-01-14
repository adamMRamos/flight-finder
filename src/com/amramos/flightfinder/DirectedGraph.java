package com.amramos.flightfinder;

import java.util.*;

public class DirectedGraph<T> {
    Map<Node<T>, Set<Node<T>>> adjacencies;

    public static <T> DirectedGraph<T> fresh() {
        return new DirectedGraph<>();
    }
    private DirectedGraph() { adjacencies = new HashMap<>(); }

    public void add(Node<T> parent, Node<T> neighbor) {
        Set<Node<T>> neighbors = neighbors(parent);

        if (neighbor != null)
            neighbors.add(neighbor);

        if (parent != null) {
            adjacencies.put(parent, neighbors);
            addSingleNode(neighbor);
        }
    }

    private void addSingleNode(Node<T> node) {
        if (node != null)
            adjacencies.put(node, neighbors(node));
    }

    public Set<Node<T>> neighbors(Node<T> node) {
        Set<Node<T>> neighbors = adjacencies.get(node);

        return neighbors != null ? neighbors : new HashSet<>();
    }

    public boolean isNeighbor(Node<T> parent, Node<T> neighbor) {
        Set<Node<T>> neighbors = neighbors(parent);

        return !(neighbors.add(neighbor) && neighbors.remove(neighbor));
    }
}
