package com.amramos.flightfinder;

import java.util.*;

/**
 * A data structure that stores data as a collection of nodes connected to one
 * another where each connection has a direction.
 * <br/>
 * For example: if A is connected to B, B is not necessarily connected to A
 * <br/>
 * This graph also allows for loops where A -> A is valid.
 * @param <T> the type of data stored in this directed graph
 */
public class DirectedGraph<T> {
    private Map<T, State<T>> adjacencies;

    /**
     * Create and return a new empty graph.
     * @param <T> the type of data stored in this directed graph
     * @return a new empty graph
     */
    public static <T> DirectedGraph<T> fresh() {
        return new DirectedGraph<>();
    }
    private DirectedGraph() { adjacencies = new HashMap<>(); }

    /**
     * Add a new edge to this graph a direction where the parent is connected to
     * the neighbor. The neighbor is also added as to this graph without any
     * connections. In the case where the neighbor already exists we do not
     * change or erase the state of the neighbor.
     * If the parent is null a new edge is not created. If the neighbor is null
     * parent is still added to the graph but without updating its neighbors.
     * @param parent the parent node
     * @param neighbor the neighbor of the parent
     */
    public void add(T parent, T neighbor) {
        if (parent != null) {
            State<T> parentState = state(parent);

            if (neighbor != null) {
                parentState.neighbors.add(neighbor);
                adjacencies.put(neighbor, state(neighbor));
            }

            adjacencies.put(parent, parentState);
        }
    }

    /**
     * Get the neighbors of a given node.
     * @param node the node
     * @return neighbors of the node
     */
    public Set<T> neighbors(T node) {
        return state(node).neighbors;
    }

    /**
     * Get the state for a given node.
     * @param node the node
     * @return the state of the node
     */
    private State<T> state(T node) {
        State<T> state = adjacencies.get(node);

        return state != null ? state : new State<>();
    }

    /**
     * Determine if there is a directed edge from the parent to the
     * neighbor.
     * @param parent the parent
     * @param neighbor the neighbor
     * @return true if they are neighbors
     */
    public boolean isNeighbor(T parent, T neighbor) {
        Set<T> neighbors = neighbors(parent);

        // Check if neighbors contains the neighbor by first adding it and then
        // removing it. If the neighbor was successfully added that means it
        // is not a neighbor for the given parent.
        return !(neighbors.add(neighbor) && neighbors.remove(neighbor));
    }

    /**
     * Mark a node as visited
     * @param node the node
     */
    public void visit(T node) {
        state(node).isVisited = true;
    }

    /**
     * Determine if a node has been visited
     * @param node the node
     * @return true if it has been visited
     */
    public boolean isVisited(T node) {
        return state(node).isVisited;
    }

    /**
     * @return the total number of nodes in this graph
     */
    public int totalNodes() {
        return this.adjacencies.size();
    }

    /**
     * An instance of this object represents the state of a node.
     * It stores all the nodes connected to the node and whether this node has
     * been visited during a search.
     * @param <T> the type of data managed in the node for this state
     */
    private static final class State<T> {
        boolean isVisited = false;
        Set<T> neighbors = new HashSet<>();
    }
}
