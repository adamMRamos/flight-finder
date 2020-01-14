package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.BreadthFirstSearch;
import com.amramos.flightfinder.DirectedGraph;
import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BreadthFirstSearchTests {
    private DirectedGraph<String> graph;

    @Before
    public void init() {
        graph = new DirectedGraph<>();
        graph.add(Node.of("A"), Node.of("B"));
        graph.add(Node.of("B"), Node.of("C"));
        graph.add(Node.of("D"), Node.of("C"));
    }

    @Test
    public void pathExists_thereIsAPath_true() {
        Assert.assertTrue(BreadthFirstSearch.pathExists(
                graph,
                Node.of("A"),
                Node.of("C")));
    }

    @Test
    public void pathExists_thereIsNoPath_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                Node.of("A"),
                Node.of("D")));
    }

    @Test
    public void pathExists_startExistsEndDoesNot_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                Node.of("A"),
                Node.of("E")));
    }

    @Test
    public void pathExists_startDoesNotExistEndDoes_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                Node.of("E"),
                Node.of("C")));
    }
}
