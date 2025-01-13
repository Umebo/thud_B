package org.umeboshi.thud.game.gameplay;

import org.umeboshi.thud.game.player.Player;

import java.util.UUID;

public class GameSession {
    public enum GameStatus {
        NEW,
        IN_PROGRESS,
        FINISHED
    }

    public enum Round {
        FIRST,
        SECOND
    }

    private final UUID gameId;
    private final Player firstPlayer;
    private Player secondPlayer;
    private Round round;
    private GameStatus gameStatus;

    public GameSession(Player firstPlayer) {
        this.gameId = UUID.randomUUID();
        this.firstPlayer = firstPlayer;
        this.round = Round.FIRST;
        this.gameStatus = GameStatus.NEW;
    }

    public UUID getGameId() {
        return gameId;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
