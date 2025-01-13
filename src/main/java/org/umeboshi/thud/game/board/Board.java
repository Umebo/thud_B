package org.umeboshi.thud.game.board;

public class Board {
    private final int[][] board;

    public Board() {
        this.board = new int[BoardConstants.BOARD_SIZE][BoardConstants.BOARD_SIZE];
    }

    public int[][] getBoard() {
        return board;
    }
}