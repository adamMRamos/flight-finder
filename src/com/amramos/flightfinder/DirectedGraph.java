package com.amramos.flightfinder;

import java.util.*;

public class DirectedGraph<T> {
    private Map<T, State<T>> adjacencies;

    public static <T> DirectedGraph<T> fresh() {
        return new DirectedGraph<>();
    }
    private DirectedGraph() { adjacencies = new HashMap<>(); }

    public void add(T parent, T neighbor) {
        State<T> state = state(parent);

        if (neighbor != null)
            state.neighbors.add(neighbor);

        if (parent != null) {
            adjacencies.put(parent, state);
            addSingleNode(neighbor);
        }
    }

    private void addSingleNode(T node) {
        if (node != null)
            adjacencies.put(node, state(node));
    }

    public Set<T> neighbors(T node) {
        return state(node).neighbors;
    }

    private State<T> state(T node) {
        State<T> neighbors = adjacencies.get(node);

        return neighbors != null ? neighbors : new State<>();
    }

    public boolean isNeighbor(T parent, T neighbor) {
        Set<T> neighbors = neighbors(parent);

        return !(neighbors.add(neighbor) && neighbors.remove(neighbor));
    }

    public void visit(T node) {
        state(node).isVisited = true;
    }

    public boolean isVisited(T node) {
        return state(node).isVisited;
    }

    public int totalNodes() {
        return this.adjacencies.size();
    }

    private static final class State<T> {
        boolean isVisited = false;
        Set<T> neighbors = new HashSet<>();
    }
}
