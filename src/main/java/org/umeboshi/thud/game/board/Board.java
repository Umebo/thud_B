package org.umeboshi.thud.game.board;

import org.apache.commons.lang3.tuple.Pair;

import static org.umeboshi.thud.game.GameConstants.TILES_TO_COORDINATES;

public class Board {
    private final Tile[][] boardTiles;

    public Board(Tile[][] boardTiles) {
        this.boardTiles = boardTiles;
    }

    public Tile getTile(String tileSignature) {
        Pair<Integer, Integer> tilePosition = TILES_TO_COORDINATES.get(tileSignature);
        return boardTiles[tilePosition.getLeft()][tilePosition.getRight()];
    }

    public Tile getTile(Pair<Integer, Integer> coordinates) {
        return boardTiles[coordinates.getLeft()][coordinates.getRight()];
    }

}