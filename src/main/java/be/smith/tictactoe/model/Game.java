package be.smith.tictactoe.model;

import be.smith.tictactoe.exception.*;

public class Game {

    public static final int DIMENSION = 3;
    private Character turn;
    private Character[][] board;

    public Game() {
        turn = 'X';
        board = new Character[DIMENSION][DIMENSION];
    }

    public Character getTurn() {
        return turn;
    }

    public Character[][] getBoard() {
        return board;
    }

    /**
     * Play turn at a position
     * @param rowIndex row index of the position to play
     * @param columnIndex column index of the position to play
     * @throws IllegalPositionException if position is already played or if indexes are out of bound
     */
    public void play(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex > DIMENSION - 1 || columnIndex < 0 || columnIndex > DIMENSION - 1) {
            throw new IllegalPositionException("Coordinates are out of bound");
        }
        if (board[rowIndex][columnIndex] != null) {
            throw new IllegalPositionException("Position already played");
        }
        board[rowIndex][columnIndex] = turn;
        togglePosition();
    }

    public boolean isWinFor(Character turn) {
        boolean win = false;
        for (int i = 0 ; i < DIMENSION ; i++) {
            win = win || rowMatch(i, turn);
        }
        for (int j = 0 ; j < DIMENSION ; j++) {
            win = win || columnMatch(j, turn);
        }
        win = win || (turn.equals(board[0][0]) && turn.equals(board[1][1]) && turn.equals(board[2][2]));
        win = win || (turn.equals(board[0][2]) && turn.equals(board[1][1]) && turn.equals(board[2][0]));
        return win;
    }

    public boolean isDraw() {
        return isFull() && !isWinFor('X') && !isWinFor('O');
    }

    private boolean isFull() {
        for (Character[] rows : board) {
            for(Character position : rows) {
                if (position == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowMatch(int i, Character turn) {
        for (int j = 0 ; j < DIMENSION ; j ++) {
            if (!turn.equals(board[i][j])) {
                return false;
            }
        }
        return true;
    }

    private boolean columnMatch(int j, Character turn) {
        for (int i = 0 ; i < DIMENSION ; i ++) {
            if (!turn.equals(board[i][j])) {
                return false;
            }
        }
        return true;
    }

    private void togglePosition() {
        turn = turn == 'X' ? 'O' : 'X';
    }
}
