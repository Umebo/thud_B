package org.umeboshi.thud.game.entities;

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
    private Board board;
    private Round round;
    private GameStatus gameStatus;

    public GameSession(Player firstPlayer, Board board) {
        this.gameId = UUID.randomUUID();
        this.firstPlayer = firstPlayer;
        this.board = board;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
