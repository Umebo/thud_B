package org.umeboshi.thud.game.board;

import org.apache.commons.lang3.tuple.Pair;

import static org.umeboshi.thud.game.GameConstants.ON_THE_EDGE;

public class Tile {

    public static final int EMPTY_TILE_VALUE = 0;
    public static final int OUTSIDE_TILE_VALUE = -1;

    private final String signature;
    private final Pair<Integer, Integer> coordinates;
    private final Boolean isOnEdge;
    private int value;

    public Tile(String signature, Pair<Integer, Integer> coordinates, int value) {
        this.signature = signature;
        this.coordinates = coordinates;
        this.isOnEdge = setIsOnEdge();
        this.value = value;
    }

    private Boolean setIsOnEdge() {
        return ON_THE_EDGE.contains(this.coordinates.getRight()) ||
                ON_THE_EDGE.contains(this.coordinates.getLeft());
    }

    public Boolean isEmpty() {
        return value == EMPTY_TILE_VALUE;
    }

    public Boolean isOutside() {
        return value == OUTSIDE_TILE_VALUE;
    }

    public String getSignature() {
        return signature;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public Boolean isOnEdge() {
        return isOnEdge;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

