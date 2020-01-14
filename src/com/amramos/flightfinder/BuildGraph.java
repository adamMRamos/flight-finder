package com.amramos.flightfinder;

import java.io.BufferedReader;

public class BuildGraph {
    public static DirectedGraph<String> from(BufferedReader file) {
        DirectedGraph<String> graph = DirectedGraph.fresh();
        file.lines().forEach(System.out::println);
        return graph;
    }
}
