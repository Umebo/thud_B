package org.umeboshi.thud.game.movement;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import org.umeboshi.thud.game.entities.Board;
import org.umeboshi.thud.game.entities.Tile;
import org.umeboshi.thud.game.gameplay.GameplayRepository;
import org.umeboshi.thud.game.entities.PlayerType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.umeboshi.thud.game.GameConstants.DIRECTIONS;
import static org.umeboshi.thud.game.GameConstants.OUT_OF_BOARD;
import static org.umeboshi.thud.game.GameConstants.TILES_TO_COORDINATES;

@Service
abstract class MovementService implements IMovementService{

    protected final GameplayRepository gameplayRepository;

    public MovementService(GameplayRepository gameplayRepository) {
        this.gameplayRepository = gameplayRepository;
    }

    boolean coordinatesOutOfBoard(Pair<Integer, Integer> coordinates) {
        return OUT_OF_BOARD.contains(coordinates.getRight()) || OUT_OF_BOARD.contains(coordinates.getLeft());
    }

    Pair<Integer, Integer> sumCoordinates(Pair<Integer, Integer> coordinates, Pair<Integer, Integer> direction) {
        return Pair.of(
                coordinates.getLeft() + direction.getLeft(),
                coordinates.getRight() + direction.getRight()
        );
    }

    Pair<Integer, Integer> getOppositeDirection(Pair<Integer, Integer> direction) {
        return Pair.of(direction.getLeft() * -1, direction.getRight() * -1);
    }

    Set<Tile> getSurroundings(Board board, String tileSignature) {
        Pair<Integer, Integer> currentCoordinates = TILES_TO_COORDINATES.get(tileSignature);
        ArrayList<Tile> surroundingTiles = new ArrayList<>();

        DIRECTIONS.forEach(direction -> {
            Pair<Integer, Integer> nextCoordinates = sumCoordinates(currentCoordinates, direction);
            if (coordinatesOutOfBoard(nextCoordinates)) {
                return;
            }
            Tile nextTile = board.getTile(nextCoordinates);
            if (!nextTile.isOutside()) {
                surroundingTiles.add(nextTile);
            }
        });

        return new HashSet<>(surroundingTiles);
    }

    Set<String> getSurroundingDwarfs(Set<Tile> surroundings) {
        ArrayList<String> surroundingDwarfs = new ArrayList<>();

        surroundings.forEach(tile -> {
            if (tile.getValue() == PlayerType.DWARF.value) surroundingDwarfs.add(tile.getSignature());
        });

        return new HashSet<>(surroundingDwarfs);
    }

    int countAdjacentPawns(Board board, PlayerType playerType, Tile currentTile, Pair<Integer, Integer> direction) {
        int counter = 0;

        do{
            Pair<Integer, Integer> nextCoordinates = sumCoordinates(currentTile.getCoordinates(), direction);
            if (coordinatesOutOfBoard(nextCoordinates)) {
                break;
            }
            currentTile = board.getTile(nextCoordinates);
            if (currentTile.getValue() == playerType.value) {
                counter++;
            }
        } while (currentTile.getValue() == playerType.value);

        return counter;
    }
}
