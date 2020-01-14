package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Test;

public class NodeTests {
    @Test
    public void isVisited_nodeNotVisited_isFalse() {
        Node node = Node.of("A");
        Assert.assertFalse(node.visited());
    }

    @Test
    public void visit_node_isVisited() {
        Node node = Node.of("A");
        node.visit();
        Assert.assertTrue(node.visited());
    }

    @Test
    public void equals_nodesWithSameData_equalEachOther() {
        String sameData = "A";
        Node node1 = Node.of(sameData);
        Node node2 = Node.of(sameData);

        Assert.assertEquals(node1, node2);
    }

    @Test
    public void equals_nodesWithDifferentData_doNotEqualEachOther() {
        Node node1 = Node.of("A");
        Node node2 = Node.of("B");

        Assert.assertNotEquals(node1, node2);
    }

    @Test
    public void equals_nodesWithSameDataDifferentVisited_doNotEqualEachOther() {
        String sameData = "A";
        Node node1 = Node.of(sameData);
        Node node2 = Node.of(sameData);

        node1.visit();

        Assert.assertNotEquals(node1, node2);
    }
}
