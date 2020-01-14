package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.BreadthFirstSearch;
import com.amramos.flightfinder.DirectedGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BreadthFirstSearchTests {
    private DirectedGraph<String> graph;

    @Before
    public void init() {
        graph = DirectedGraph.fresh();
        graph.add("A", "B");
        graph.add("B", "C");
        graph.add("D", "C");
    }

    @Test
    public void pathExists_thereIsAPath_true() {
        Assert.assertTrue(BreadthFirstSearch.pathExists(
                graph,
                "A",
                "C"));
    }

    @Test
    public void pathExists_thereIsNoPath_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                "A",
                "D"));
    }

    @Test
    public void pathExists_startExistsEndDoesNot_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                "A",
                "E"));
    }

    @Test
    public void pathExists_startDoesNotExistEndDoes_false() {
        Assert.assertFalse(BreadthFirstSearch.pathExists(
                graph,
                "E",
                "C"));
    }
}
