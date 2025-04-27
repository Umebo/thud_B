package org.umeboshi.thud.game.entities;

import org.apache.commons.lang3.tuple.Pair;

import static org.umeboshi.thud.game.GameConstants.TILES_TO_COORDINATES;

public record Board(Tile[][] boardTiles) {

    public Tile getTile(String tileSignature) {
        Pair<Integer, Integer> tilePosition = TILES_TO_COORDINATES.get(tileSignature);
        return boardTiles[tilePosition.getLeft()][tilePosition.getRight()];
    }

    public Tile getTile(Pair<Integer, Integer> coordinates) {
        return boardTiles[coordinates.getLeft()][coordinates.getRight()];
    }

}