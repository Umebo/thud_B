package org.umeboshi.thud.game.movement;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import org.umeboshi.thud.game.entities.Board;
import org.umeboshi.thud.game.entities.GameSession;
import org.umeboshi.thud.game.entities.Tile;
import org.umeboshi.thud.game.gameplay.GameplayRepository;
import org.umeboshi.thud.game.movement.dto.AvailableMovesResponse;
import org.umeboshi.thud.game.movement.dto.AvailableMovesRequest;
import org.umeboshi.thud.game.entities.PlayerType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.umeboshi.thud.game.GameConstants.DIRECTIONS;

@Service
public class ClassicThudMovementService extends MovementService {

    public ClassicThudMovementService(GameplayRepository gameplayRepository) {
        super(gameplayRepository);
    }

    @Override
    public AvailableMovesResponse getAvailableMoves(AvailableMovesRequest availableMovesRequest) {
        Optional<GameSession> session = gameplayRepository.findById(availableMovesRequest.gameId());
        try {
            Board board = session.get().getBoard();
            String tileSignature = availableMovesRequest.tileSignature();
            return switch (availableMovesRequest.playerType()) {
                case DWARF -> new AvailableMovesResponse(getDwarfAvailableMoves(board, tileSignature), getDwarfAvailableHurls(board, tileSignature));
                case TROLL -> new AvailableMovesResponse(getTrollAvailableMoves(board, tileSignature), getTrollAvailableShoves(board, tileSignature));
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Set<String>> getDwarfAvailableMoves(Board board, String tileSignature) {
        Tile initialTile = board.getTile(tileSignature);
        Map<String, Set<String>> availableMoves = new HashMap<>();

        DIRECTIONS.forEach(direction -> {
            Tile currentTile = initialTile;
            do{
                Pair<Integer, Integer> nextCoordinates = sumCoordinates(currentTile.getCoordinates(), direction);
                if (coordinatesOutOfBoard(nextCoordinates)) {
                    return;
                }
                currentTile = board.getTile(nextCoordinates);
                if (!currentTile.isEmpty() || currentTile.isOutside()) {
                    return;
                }
                availableMoves.put(currentTile.getSignature(), Set.of());
            } while (!currentTile.isOnEdge());
        });

        return availableMoves;
    }

    private Map<String, Set<String>> getDwarfAvailableHurls(Board board, String tileSignature) {
        Tile initialTile = board.getTile(tileSignature);
        Map<String, Set<String>> availableHurls = new HashMap<>();

        DIRECTIONS.forEach(direction -> {
            Tile currentTile = initialTile;
            Pair<Integer, Integer> oppositeDirection = getOppositeDirection(direction);
            int dwarfCounter = countAdjacentPawns(board, PlayerType.DWARF, currentTile, direction);

            while (dwarfCounter >= 0) {
                Pair<Integer, Integer> nextCoordinates = sumCoordinates(currentTile.getCoordinates(), oppositeDirection);
                if (coordinatesOutOfBoard(nextCoordinates)) {
                    return;
                }
                currentTile = board.getTile(nextCoordinates);
                if (currentTile.getValue() == PlayerType.TROLL.value) {
                    availableHurls.put(currentTile.getSignature(), Set.of(currentTile.getSignature()));
                    return;
                }
                dwarfCounter--;
            }
        });

        return availableHurls;
    }

    private Map<String, Set<String>> getTrollAvailableMoves(Board board, String tileSignature) {
        Set<Tile> initialSurroundings = getSurroundings(board, tileSignature);
        Map<String, Set<String>> availableMoves = new HashMap<>();

        initialSurroundings.forEach(surroundingTile -> {
            if (!surroundingTile.isEmpty()) return;
            Set<Tile> newSurroundings = getSurroundings(board, surroundingTile.getSignature());
            availableMoves.put(surroundingTile.getSignature(), getSurroundingDwarfs(newSurroundings));
        });

        return availableMoves;
    }

    private Map<String, Set<String>> getTrollAvailableShoves(Board board, String tileSignature) {
        Tile initialTile = board.getTile(tileSignature);
        Map<String, Set<String>> availableShoves = new HashMap<>();

        DIRECTIONS.forEach(direction -> {
            Tile currentTile = initialTile;
            Pair<Integer, Integer> oppositeDirection = getOppositeDirection(direction);
            int trollCounter = countAdjacentPawns(board, PlayerType.TROLL, currentTile, direction);

            while (trollCounter >= 1) {
                Pair<Integer, Integer> nextCoordinates = sumCoordinates(currentTile.getCoordinates(), oppositeDirection);
                if (coordinatesOutOfBoard(nextCoordinates)) {
                    return;
                }
                currentTile = board.getTile(nextCoordinates);
                Set<Tile> surroundings = getSurroundings(board, currentTile.getSignature());
                Set<String> surroundingDwarfs = getSurroundingDwarfs(surroundings);

                if (currentTile.isEmpty() && !surroundingDwarfs.isEmpty()) {
                    availableShoves.put(currentTile.getSignature(), surroundingDwarfs);
                }
                trollCounter--;
            }
        });

        return availableShoves;
    }

}
