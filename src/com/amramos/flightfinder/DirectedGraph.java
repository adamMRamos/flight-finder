package com.amramos.flightfinder;

import java.util.*;

public class DirectedGraph<T> {
    Map<T, Set<T>> adjacencies;

    public static <T> DirectedGraph<T> fresh() {
        return new DirectedGraph<>();
    }
    private DirectedGraph() { adjacencies = new HashMap<>(); }

    public void add(T parent, T neighbor) {
        Set<T> neighbors = neighbors(parent);

        if (neighbor != null)
            neighbors.add(neighbor);

        if (parent != null) {
            adjacencies.put(parent, neighbors);
            addSingleNode(neighbor);
        }
    }

    private void addSingleNode(T node) {
        if (node != null)
            adjacencies.put(node, neighbors(node));
    }

    public Set<T> neighbors(T node) {
        Set<T> neighbors = adjacencies.get(node);

        return neighbors != null ? neighbors : new HashSet<>();
    }

    public boolean isNeighbor(T parent, T neighbor) {
        Set<T> neighbors = neighbors(parent);

        return !(neighbors.add(neighbor) && neighbors.remove(neighbor));
    }

    public int totalNodes() {
        return this.adjacencies.size();
    }
}
