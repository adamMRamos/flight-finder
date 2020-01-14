package com.amramos.flightfinder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Consumer;

public class BreadthFirstSearch {
    public static <T> boolean pathExists(
            DirectedGraph<T> graph, T from, T to) {
        Set<T> visited = new HashSet<>();
        LinkedList<T> queue = new LinkedList<>();
        Consumer<T> pushOntoQueue = pushOntoQueueIfNotVisited(visited, queue);

        queue.push(from);

        while (!queue.isEmpty()) {
            T next = queue.pop();
            visited.add(next);
            graph.neighbors(next).forEach(pushOntoQueue);
        }

        return visited.contains(to);
    }

    public static <T> Consumer<T> pushOntoQueueIfNotVisited(
            Set<T> visited,
            LinkedList<T> queue) {
        return node -> {
            if (!visited.contains(node))
                queue.push(node);
        };
    }
}
