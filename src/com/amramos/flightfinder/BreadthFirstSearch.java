package com.amramos.flightfinder;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * A class that contains a static method for performing a breadth first search
 * on a directed graph.
 */
public class BreadthFirstSearch {

    /**
     * Determine if a path exists from a given start node to an end node. The
     * algorithm does not terminate upon finding the end node and will visit
     * every node possible.
     * Performing this algorithm on the same graph again but with different
     * start and end nodes will not return the correct result.
     * @param graph the graph
     * @param from the starting node
     * @param to the end node
     * @param <T> the type of data in the graph
     * @return true if after running the algorithm the end node has been visited
     */
    public static <T> boolean pathExists(DirectedGraph<T> graph, T from, T to) {
        LinkedList<T> queue = new LinkedList<>();
        Consumer<T> enqueue = visitAndEnqueueIfNotVisited(graph, queue);

        // push the start node onto the queue
        enqueue.accept(from);

        // continue the algorithm as long as there are more nodes to visit
        while (!queue.isEmpty())
            graph.neighbors(queue.pop()).forEach(enqueue);

        return graph.isVisited(to);
    }

    /**
     * Get the Consumer used to push a new node onto a queue. The Consumer only
     * pushes a new node if the node has not been visited in the graph.
     * @param graph the graph to use when checking if the node has been visited
     * @param queue the queue to push new nodes onto
     * @param <T> the type of data in the graph
     * @return the Consumer that pushed new nodes onto a queue.
     */
    public static <T> Consumer<T> visitAndEnqueueIfNotVisited(
            DirectedGraph<T> graph,
            LinkedList<T> queue) {
        return node -> {
            if (!graph.isVisited(node)) {
                graph.visit(node);
                queue.push(node);
            }
        };
    }
}
