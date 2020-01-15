package com.amramos.flightfinder;

import javafx.util.Pair;
import java.io.*;
import java.util.function.Function;

/**
 * The main class of the program that expects 3 parameters.<br/><br/>
 * 1) a file name<br/>
 * 2) an origin city<br/>
 * 3) a destination city<br/><br/>
 * The program outputs true if, based on contents in the file, there is a flight
 * from the origin to the destination.
 */
public class Main {

    /**
     * The main method of the program. It runs the program and checks the
     * results. If there were any errors it prints the errors otherwise it
     * prints whether or not a flight exists from an origin to a destination
     * city.
     * @param args command line args
     */
    public static void main(String[] args) {
        Pair<Boolean, String> result = run(args);
        boolean answer = result.getKey();
        String error = result.getValue();

        if (error.isEmpty())
            System.out.println(answer);
        else
            System.out.println(error);
    }

    /**
     * Runs the program on the given arguments and returns the result of that
     * execution. The result contains whether or not a flight exists from an
     * origin city to a destination city and any errors that may have occurred.
     * The program expects 3 arguments:<br/>
     * 1) a file name<br/>
     * 2) an origin city<br/>
     * 3) a destination city<br/><br/>
     * The program returns a result with an error message if any of the 3
     * arguments are missing.
     * @param args the 3 arguments of the program
     * @return whether a flight exists and any errors that may have happened
     */
    public static Pair<Boolean, String> run(String[] args) {
        // terminate the program if there are less than 3 parameters
        if (args.length < 3) {
            return new Pair<>(
                    false,
                    "\nError: wrong number of arguments" +
                            "\nExpected: 3, Actual: " + args.length + "\n");
        }
        else {
            Function<IOException, String> failedToReadFile =
                    e -> "\nError: failed to read and parse the file\n"
                            + e.getMessage() + "\n";
            String file = args[0], start = args[1], end = args[2];

            // Attempt to read the file into a graph. Get an empty graph if the
            // read fails and the associated error message.
            Pair<DirectedGraph<String>, String> result =
                    buildGraph(file, failedToReadFile);

            DirectedGraph<String> graph = result.getKey();
            String errors = result.getValue();

            // Perform bfs on the graph. If the file read failed then the graph
            // will be empty and bfs will return false.
            boolean flightExists = BreadthFirstSearch.pathExists(
                    graph,
                    start,
                    end);

            return new Pair<>(flightExists, errors);
        }
    }

    /**
     * Read and build a graph from the file. This method also excepts a Function
     * as a callback that builds and returns an error message if an IOException
     * occurs. If an error occurs also return an empty graph.
     * @param file the file to read from
     * @param onError a callback to build the error message
     * @return a graph and any error messages while reading the file
     */
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
