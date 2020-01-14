package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.BuildGraph;
import com.amramos.flightfinder.DirectedGraph;
import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;

public class BuildGraphTests {
    BufferedReader edgeList = new BufferedReader(new StringReader(
            "A,B\nB,C\nD,C\nE,F"));
    BufferedReader withLoopedBackEdge = new BufferedReader(new StringReader(
            "A,A"));
    BufferedReader withInvalidLine = new BufferedReader(new StringReader(
            "A,B\nB C"));
    BufferedReader withInvalidLines = new BufferedReader(new StringReader(
            "AB\nC,\nD\nH,I\nE.F"));

    @Test
    public void from_edgeList_hasAllEdges() {
        DirectedGraph<String> graph = BuildGraph.from(edgeList);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertTrue(graph.isNeighbor(Node.of("B"), Node.of("C")));
        Assert.assertTrue(graph.isNeighbor(Node.of("D"), Node.of("C")));
        Assert.assertTrue(graph.isNeighbor(Node.of("E"), Node.of("F")));
    }

    @Test
    public void from_withLoopedBackEdge_hasGraphWithLoop() {
        DirectedGraph<String> graph = BuildGraph.from(withLoopedBackEdge);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("A")));
    }

    @Test
    public void from_withInvalidLine_hasOnlyValidEdge() {
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLine);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertFalse(graph.isNeighbor(Node.of("B"), Node.of("C")));
    }

    @Test
    public void from_withInvalidLines_hasOnlyValidEdge() {
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLines);

        Assert.assertTrue(graph.isNeighbor(Node.of("H"), Node.of("I")));

        Assert.assertFalse(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertFalse(graph.isNeighbor(Node.of("C"), Node.of("D")));
        Assert.assertFalse(graph.isNeighbor(Node.of("D"), Node.of("E")));
        Assert.assertFalse(graph.isNeighbor(Node.of("E"), Node.of("F")));
    }
}
