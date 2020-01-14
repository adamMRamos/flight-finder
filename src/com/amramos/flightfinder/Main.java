package com.amramos.flightfinder;

import javafx.util.Pair;
import java.io.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Pair<Boolean, String> result = run(args);
        boolean answer = result.getKey();
        String error = result.getValue();

        if (error.isEmpty())
            System.out.println(answer);
        else
            System.out.println(error);
    }

    public static Pair<Boolean, String> run(String[] args) {
        if (args.length < 3) {
            return new Pair<>(
                    false,
                    "\nError: wrong number of arguments" +
                            "\nExpected: 3, Actual: " + args.length + "\n");
        }
        else {
            String file = args[0], start = args[1], end = args[2];

            Pair<DirectedGraph<String>, String> result = buildGraph(
                    file,
                    e -> "\nError: failed to read and parse the file\n"
                            + e.getMessage() + "\n");

            DirectedGraph<String> graph = result.getKey();
            String errors = result.getValue();

            return new Pair<>(
                    BreadthFirstSearch.pathExists(graph, start, end),
                    errors);
        }
    }

    private static Pair<DirectedGraph<String>, String> buildGraph(
            String file,
            Function<IOException, String> onError) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return new Pair<>(BuildGraph.from(reader), "");
        } catch (IOException e) {
            return new Pair<>(DirectedGraph.fresh(), onError.apply(e));
        }
    }
}
