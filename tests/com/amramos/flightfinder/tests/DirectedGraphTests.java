package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.DirectedGraph;
import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Test;

public class DirectedGraphTests {
    @Test
    public void isNeighbor_parentAndNeighbor_areNeighborsButNeighborIsNotAParent() {
        Node<String> parent = Node.of("A");
        Node<String> neighbor = Node.of("1");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, neighbor);

        Assert.assertTrue(graph.isNeighbor(parent, neighbor));
        Assert.assertFalse(graph.isNeighbor(neighbor, parent));
    }

    @Test
    public void isNeighbor_parentWithTwoDifferentNeighbor_hasBothNeighbors() {
        Node<String> parent = Node.of("A");
        Node<String> neighbor1 = Node.of("1");
        Node<String> neighbor2 = Node.of("2");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, neighbor1);
        graph.add(parent, neighbor2);

        Assert.assertTrue(graph.isNeighbor(parent, neighbor1));
        Assert.assertTrue(graph.isNeighbor(parent, neighbor2));
        Assert.assertFalse(graph.isNeighbor(neighbor1, parent));
        Assert.assertFalse(graph.isNeighbor(neighbor2, parent));
    }

    @Test
    public void isNeighbor_twoDifferentParents_bothExistButNotNeighborsWithEachOther() {
        Node<String> parent1 = Node.of("A");
        Node<String> neighbor1 = Node.of("1");

        Node<String> parent2 = Node.of("B");
        Node<String> neighbor2 = Node.of("2");

        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent1, neighbor1);
        graph.add(parent2, neighbor2);

        Assert.assertTrue(graph.isNeighbor(parent1, neighbor1));
        Assert.assertTrue(graph.isNeighbor(parent2, neighbor2));

        Assert.assertFalse(graph.isNeighbor(parent1, parent2));
        Assert.assertFalse(graph.isNeighbor(parent2, parent1));
    }

    @Test
    public void isNeighbor_twoDifferentParentsWithSameNeighbor_bothHaveSameNeighborButNeighborIsNotParentOfEither() {
        Node<String> parent1 = Node.of("A");
        Node<String> parent2 = Node.of("B");
        Node<String> neighbor = Node.of("1");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent1, neighbor);
        graph.add(parent2, neighbor);

        Assert.assertTrue(graph.isNeighbor(parent1, neighbor));
        Assert.assertTrue(graph.isNeighbor(parent2, neighbor));

        Assert.assertFalse(graph.isNeighbor(neighbor, parent1));
        Assert.assertFalse(graph.isNeighbor(neighbor, parent2));
    }

    @Test
    public void isNeighbor_twoParentsWithDifferentNeighborSameData_bothHaveSameNeighbor() {
        Node<String> parent1 = Node.of("A");
        Node<String> parent2 = Node.of("B");
        Node<String> sameNeighbor1 = Node.of("1");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent1, sameNeighbor1);
        graph.add(parent2, sameNeighbor1);

        Assert.assertTrue(graph.isNeighbor(parent1, Node.of("1")));
        Assert.assertTrue(graph.isNeighbor(parent2, Node.of("1")));
    }

    @Test
    public void isNeighbor_parentNeighborToItself_isTrue() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, parent);

        Assert.assertTrue(graph.isNeighbor(parent, parent));
    }

    @Test
    public void isNeighbor_parentAndNull_parentNotANeighborWithNull() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, null);

        Assert.assertFalse(graph.isNeighbor(parent, null));
        Assert.assertTrue(graph.neighbors(parent).isEmpty());
    }

    @Test
    public void isNeighbor_nullAndNeighbor_nullIsNotANeighborWithParent() {
        Node<String> neighbor = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(null, neighbor);

        Assert.assertFalse(graph.isNeighbor(null, neighbor));
        Assert.assertTrue(graph.neighbors(null).isEmpty());
    }

    @Test
    public void neighbors_parentAndNull_parentHasNoNeighbors() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, null);

        Assert.assertTrue(graph.neighbors(parent).isEmpty());
    }

    @Test
    public void neighbors_nullAndNeighbor_nullHasNoNeighbors() {
        Node<String> neighbor = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(null, neighbor);

        Assert.assertTrue(graph.neighbors(null).isEmpty());
    }

    @Test
    public void neighbors_parentHasOneNeighbor_returnsOneNeighbor() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, Node.of("B"));

        Assert.assertEquals(1, graph.neighbors(parent).size());
    }

    @Test
    public void neighbors_parentHasTwoNeighbors_returnsTwoNeighbors() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, Node.of("B"));
        graph.add(parent, Node.of("C"));

        Assert.assertEquals(2, graph.neighbors(parent).size());
    }

    @Test
    public void neighbors_parentHasTwoSameNeighbors_returnsOneNeighbor() {
        Node<String> parent = Node.of("A");
        DirectedGraph<String> graph = DirectedGraph.fresh();

        graph.add(parent, Node.of("B"));
        graph.add(parent, Node.of("B"));

        Assert.assertEquals(1, graph.neighbors(parent).size());
    }
}
