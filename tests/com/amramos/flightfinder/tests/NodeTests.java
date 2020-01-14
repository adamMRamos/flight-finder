package com.amramos.flightfinder.tests;

import com.amramos.flightfinder.Node;
import org.junit.Assert;
import org.junit.Test;

public class NodeTests {
    @Test
    public void isVisited_nodeNotVisited_isFalse() {
        Node<String> node = Node.of("A");
        Assert.assertFalse(node.visited());
    }

    @Test
    public void visit_node_isVisited() {
        Node<String> node = Node.of("A");
        node.visit();
        Assert.assertTrue(node.visited());
    }

    @Test
    public void equals_nodesWithSameData_equalEachOther() {
        String sameData = "A";
        Node<String> node1 = Node.of(sameData);
        Node<String> node2 = Node.of(sameData);

        Assert.assertEquals(node1, node2);
    }

    @Test
    public void equals_nodesWithDifferentData_doNotEqualEachOther() {
        Node<String> node1 = Node.of("A");
        Node<String> node2 = Node.of("B");

        Assert.assertNotEquals(node1, node2);
    }
}
