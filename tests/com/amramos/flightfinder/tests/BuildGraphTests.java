package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.BuildGraph;
import com.amramos.flightfinder.DirectedGraph;
import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

public class BuildGraphTests {
    @Test
    public void from_edgeList_hasAllEdges() {
        BufferedReader edgeList = new BufferedReader(new StringReader(
                "A,B\nB,C\nD,C\nE,F"));
        DirectedGraph<String> graph = BuildGraph.from(edgeList);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertTrue(graph.isNeighbor(Node.of("B"), Node.of("C")));
        Assert.assertTrue(graph.isNeighbor(Node.of("D"), Node.of("C")));
        Assert.assertTrue(graph.isNeighbor(Node.of("E"), Node.of("F")));
        Assert.assertEquals(6, graph.totalNodes());
    }

    @Test
    public void from_withLoopedBackEdge_hasGraphWithLoop() {
        BufferedReader withLoopedBackEdge = new BufferedReader(new StringReader(
                "A,A"));
        DirectedGraph<String> graph = BuildGraph.from(withLoopedBackEdge);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("A")));
        Assert.assertEquals(1, graph.totalNodes());
    }

    @Test
    public void from_multiCharacterEdges_includesNodesWithMultipleCharacters() {
        BufferedReader multiCharacterEdges = new BufferedReader(new StringReader(
                "A,B\nB,C D E\nC D E,F"));
        DirectedGraph<String> graph = BuildGraph.from(multiCharacterEdges);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertTrue(graph.isNeighbor(Node.of("B"), Node.of("C D E")));
        Assert.assertTrue(graph.isNeighbor(Node.of("C D E"), Node.of("F")));
        Assert.assertEquals(4, graph.totalNodes());
    }

    @Test
    public void from_whiteSpaces_ignoresBlankWords() {
        BufferedReader whiteSpaces = new BufferedReader(new StringReader(
                "A,B\nC,   \n   ,D"));
        DirectedGraph<String> graph = BuildGraph.from(whiteSpaces);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));

        Assert.assertFalse(graph.isNeighbor(Node.of("C"), Node.of("   ")));
        Assert.assertFalse(graph.isNeighbor(Node.of("   "), Node.of("D")));
        Assert.assertEquals(2, graph.totalNodes());
    }

    @Test
    public void from_trimsWhiteSpaces_trimsWhiteSpaceAroundWords() {
        BufferedReader trimsWhiteSpaces = new BufferedReader(new StringReader(
                " A ,B\nB, C \nC,D"));
        DirectedGraph<String> graph = BuildGraph.from(trimsWhiteSpaces);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertTrue(graph.isNeighbor(Node.of("B"), Node.of("C")));
        Assert.assertTrue(graph.isNeighbor(Node.of("C"), Node.of("D")));
        Assert.assertEquals(4, graph.totalNodes());
    }

    @Test
    public void from_withInvalidLine_hasOnlyValidEdge() {
        BufferedReader withInvalidLine = new BufferedReader(new StringReader(
                "A,B\nB C"));
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLine);

        Assert.assertTrue(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertFalse(graph.isNeighbor(Node.of("B"), Node.of("C")));
        Assert.assertEquals(2, graph.totalNodes());
    }

    @Test
    public void from_withInvalidLines_hasOnlyValidEdge() {
        BufferedReader withInvalidLines = new BufferedReader(new StringReader(
                "AB\nC,\nD\nH,I\nE.F\n   "));
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLines);

        Assert.assertTrue(graph.isNeighbor(Node.of("H"), Node.of("I")));

        Assert.assertFalse(graph.isNeighbor(Node.of("A"), Node.of("B")));
        Assert.assertFalse(graph.isNeighbor(Node.of("C"), Node.of("D")));
        Assert.assertFalse(graph.isNeighbor(Node.of("D"), Node.of("E")));
        Assert.assertFalse(graph.isNeighbor(Node.of("E"), Node.of("F")));
        Assert.assertEquals(2, graph.totalNodes());
    }
}
