package be.smith.tictactoe.model;

public class Game {

    private Character turn;
    private Character[][] board;

    public Game() {
        turn = 'X';
        board = new Character[3][3];
    }

    public Character getTurn() {
        return turn;
    }

    public Character[][] getBoard() {
        return board;
    }

    public void play(int rowIndex, int columnIndex) {
        board[rowIndex][columnIndex] = turn;
    }
}
