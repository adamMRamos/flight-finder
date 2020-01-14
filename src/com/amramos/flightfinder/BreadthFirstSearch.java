package com.amramos.flightfinder;

import java.util.LinkedList;
import java.util.function.Consumer;

public class BreadthFirstSearch {
    public static <T> boolean pathExists(
            DirectedGraph<T> graph, T from, T to) {
        LinkedList<T> queue = new LinkedList<>();
        Consumer<T> pushOntoQueue = pushOntoQueueIfNotVisited(graph, queue);

        queue.push(from);

        while (!queue.isEmpty()) {
            T next = queue.pop();
            graph.visit(next);
            graph.neighbors(next).forEach(pushOntoQueue);
        }

        return graph.isVisited(to);
    }

    public static <T> Consumer<T> pushOntoQueueIfNotVisited(
            DirectedGraph<T> graph,
            LinkedList<T> queue) {
        return node -> {
            if (!graph.isVisited(node))
                queue.push(node);
        };
    }
}
