package org.umeboshi.thud.game.gameplay;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import org.umeboshi.thud.game.entities.Board;
import org.umeboshi.thud.game.entities.GameSession;
import org.umeboshi.thud.game.entities.Tile;
import org.umeboshi.thud.game.entities.Player;
import org.umeboshi.thud.game.entities.PlayerType;

import static org.umeboshi.thud.game.GameConstants.BOARD_SIZE;
import static org.umeboshi.thud.game.GameConstants.CLASSIC_DWARF_STARTING_TILES;
import static org.umeboshi.thud.game.GameConstants.CLASSIC_OUTSIDE_TILES;
import static org.umeboshi.thud.game.GameConstants.CLASSIC_TROLL_STARTING_TILES;
import static org.umeboshi.thud.game.GameConstants.COORDINATES_TO_TILES;
import static org.umeboshi.thud.game.entities.Tile.EMPTY_TILE_VALUE;
import static org.umeboshi.thud.game.entities.Tile.OUTSIDE_TILE_VALUE;

@Service
public class ClassicThudGameplayService implements IGameplayService{

    private final GameplayRepository gameplayRepository;

    public ClassicThudGameplayService(GameplayRepository gameplayRepository) {
        this.gameplayRepository = gameplayRepository;
    }

    @Override
    public GameSession createNewGame(Player firstPlayer) {
        GameSession newSession = new GameSession(firstPlayer, initializeNewBoard());
        gameplayRepository.save(newSession);
        return newSession;
    }

    private Board initializeNewBoard() {
        Tile[][] boardTiles = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                String tileSignature = COORDINATES_TO_TILES.get(Pair.of(i,j));

                int tileValue = switch (tileSignature) {
                    case String s when CLASSIC_DWARF_STARTING_TILES.contains(s) -> PlayerType.DWARF.value;
                    case String s when CLASSIC_TROLL_STARTING_TILES.contains(s) -> PlayerType.TROLL.value;
                    case String s when CLASSIC_OUTSIDE_TILES.contains(s) -> OUTSIDE_TILE_VALUE;
                    default -> EMPTY_TILE_VALUE;
                };

                boardTiles[i][j] = new Tile(tileSignature, Pair.of(i,j), tileValue);
            }
        }
        return new Board(boardTiles);
    }
}
