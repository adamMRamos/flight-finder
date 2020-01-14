package com.amramos.flightfinder;

import java.io.Serializable;

public class Node<T> implements Serializable {

    public static <T> Node<T> of(T data) { return new Node<>(); }
    private Node() { }

    public void visit() { }

    public boolean visited() { return false; }
}
