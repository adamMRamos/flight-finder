package com.amramos.flightfinder;

import javafx.util.Pair;
import java.io.BufferedReader;
import java.util.function.Consumer;

/**
 * A class that contains a static method for reading contents of a file into a
 * directed graph. The file is expected to be in csv format where each line
 * contains two words separated by a comma.
 * <br/><br/> For example: A,B <br/>
 * This means there is a directed edge from A to B. Any lines that do not
 * conform to csv format will be ignored.<br/>
 * Any extra comma delimited data in the line will likewise not get added to
 * the graph.
 * <br/><br/>For example: A,B,C<br/>
 * C will not get added to the graph but A and B will.
 */
public class BuildGraph {
    /**
     * Read and parse a file into a graph. Any runtime errors that occur while
     * reading the file will get thrown up to the caller.
     * @param file the file to read from
     * @return a new graph containing all nodes and edges from the file
     */
    public static DirectedGraph<String> from(BufferedReader file) {
        DirectedGraph<String> graph = DirectedGraph.fresh();
        Consumer<String> addEdge = addEdgeToGraph(graph);
        file.lines().forEach(addEdge);
        return graph;
    }

    /**
     * Get the Consumer used to parse a line of text into an edge and add it to
     * the graph.
     * @param graph the graph to add the new edge to
     * @return the Consumer used to parse a line of text and add an edge
     */
    private static Consumer<String> addEdgeToGraph(
            DirectedGraph<String> graph) {

        return line -> {
            Pair<String, String> edge = toEdge(line);
            if (edge != null)
                graph.add(edge.getKey(), edge.getValue());
        };
    }

    /**
     * Parse a line of text into a pair of nodes where the key of the pair is
     * the parent node and the value represents the neighbor node.
     * returns null if the line is not comma delimited with a word before and
     * after the comma.<br/><br/>
     * For example: A,B
     * @param line the line of text to parse
     * @return an edge as a pair of nodes
     */
    private static Pair<String, String> toEdge(String line) {
        String parent = null;
        String neighbor = null;

        // Split a line of text by commas. The first 2 elements will act as
        // parent and neighbor respectively.
        String[] edge = line.split(",");
        if (edge.length > 1) {
            // Trim whitespace off of the parent and neighbor data.
            parent = edge[0].trim();
            neighbor = edge[1].trim();
        }

        // Only return a new pair if both the parent and neighbor are neither
        // null nor empty.
        if (isNotNullOrEmpty(parent) && isNotNullOrEmpty(neighbor))
            return new Pair<>(parent, neighbor);
        else
            return null;
    }

    /**
     * A Helper method for determining if a string is not null or empty.
     * @param data the string to check
     * @return true if the string is neither null nor empty
     */
    private static boolean isNotNullOrEmpty(String data) {
        return data != null && !data.isEmpty();
    }
}
