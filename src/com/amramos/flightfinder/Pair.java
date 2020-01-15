package com.amramos.flightfinder;

public class Pair<T, U> {
    private final T item1;
    private final U item2;

    public static <T, U> Pair<T, U> of(T i1, U i2) {
        return new Pair<>(i1, i2);
    }
    private Pair(T item1, U item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T item1() { return item1; }
    public U item2() { return item2; }
}
