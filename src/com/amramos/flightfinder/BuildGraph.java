package com.amramos.flightfinder;

import com.sun.deploy.util.StringUtils;
import javafx.util.Pair;
import java.io.BufferedReader;
import java.util.function.Consumer;

public class BuildGraph {
    public static DirectedGraph<String> from(BufferedReader file) {
        DirectedGraph<String> graph = DirectedGraph.fresh();
        file.lines().forEach(addEdgeToGraph(graph));
        return graph;
    }

    private static Consumer<String> addEdgeToGraph(
            DirectedGraph<String> graph) {
        return line -> {
            Pair<String, String> edge = toEdge(line);
            if (edge != null)
                graph.add(edge.getKey(), edge.getValue());
        };
    }

    private static Pair<String, String> toEdge(String line) {
        String parent = null;
        String neighbor = null;
        String[] edge = line.split(",");

        if (edge.length > 1) {
            parent = StringUtils.trimWhitespace(edge[0]);
            neighbor = StringUtils.trimWhitespace(edge[1]);
        }

        if (isNotNullOrEmpty(parent) && isNotNullOrEmpty(neighbor))
            return new Pair<>(parent, neighbor);
        else
            return null;
    }

    private static boolean isNotNullOrEmpty(String data) {
        return data != null && !data.isEmpty();
    }
}
