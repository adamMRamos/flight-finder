package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.BuildGraph;
import com.amramos.flightfinder.DirectedGraph;
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

        Assert.assertTrue(graph.isNeighbor("A", "B"));
        Assert.assertTrue(graph.isNeighbor("B", "C"));
        Assert.assertTrue(graph.isNeighbor("D", "C"));
        Assert.assertTrue(graph.isNeighbor("E", "F"));
        Assert.assertEquals(6, graph.totalNodes());
    }

    @Test
    public void from_withLoopedBackEdge_hasGraphWithLoop() {
        BufferedReader withLoopedBackEdge = new BufferedReader(new StringReader(
                "A,A"));
        DirectedGraph<String> graph = BuildGraph.from(withLoopedBackEdge);

        Assert.assertTrue(graph.isNeighbor("A", "A"));
        Assert.assertEquals(1, graph.totalNodes());
    }

    @Test
    public void from_multiCharacterEdges_includesNodesWithMultipleCharacters() {
        BufferedReader multiCharacterEdges = new BufferedReader(new StringReader(
                "A,B\nB,C D E\nC D E,F"));
        DirectedGraph<String> graph = BuildGraph.from(multiCharacterEdges);

        Assert.assertTrue(graph.isNeighbor("A", "B"));
        Assert.assertTrue(graph.isNeighbor("B", "C D E"));
        Assert.assertTrue(graph.isNeighbor("C D E", "F"));
        Assert.assertEquals(4, graph.totalNodes());
    }

    @Test
    public void from_whiteSpaces_ignoresBlankWords() {
        BufferedReader whiteSpaces = new BufferedReader(new StringReader(
                "A,B\nC,   \n   ,D"));
        DirectedGraph<String> graph = BuildGraph.from(whiteSpaces);

        Assert.assertTrue(graph.isNeighbor("A", "B"));

        Assert.assertFalse(graph.isNeighbor("C", "   "));
        Assert.assertFalse(graph.isNeighbor("   ", "D"));
        Assert.assertEquals(2, graph.totalNodes());
    }

    @Test
    public void from_trimsWhiteSpaces_trimsWhiteSpaceAroundWords() {
        BufferedReader trimsWhiteSpaces = new BufferedReader(new StringReader(
                " A ,B\nB, C \nC,D"));
        DirectedGraph<String> graph = BuildGraph.from(trimsWhiteSpaces);

        Assert.assertTrue(graph.isNeighbor("A", "B"));
        Assert.assertTrue(graph.isNeighbor("B", "C"));
        Assert.assertTrue(graph.isNeighbor("C", "D"));
        Assert.assertEquals(4, graph.totalNodes());
    }

    @Test
    public void from_withInvalidLine_hasOnlyValidEdge() {
        BufferedReader withInvalidLine = new BufferedReader(new StringReader(
                "A,B\nB C"));
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLine);

        Assert.assertTrue(graph.isNeighbor("A", "B"));
        Assert.assertFalse(graph.isNeighbor("B", "C"));
        Assert.assertEquals(2, graph.totalNodes());
    }

    @Test
    public void from_withInvalidLines_hasOnlyValidEdge() {
        BufferedReader withInvalidLines = new BufferedReader(new StringReader(
                "AB\nC,\nD\nH,I\nE.F\n   "));
        DirectedGraph<String> graph = BuildGraph.from(withInvalidLines);

        Assert.assertTrue(graph.isNeighbor("H", "I"));

        Assert.assertFalse(graph.isNeighbor("A", "B"));
        Assert.assertFalse(graph.isNeighbor("C", "D"));
        Assert.assertFalse(graph.isNeighbor("D", "E"));
        Assert.assertFalse(graph.isNeighbor("E", "F"));
        Assert.assertEquals(2, graph.totalNodes());
    }
}
