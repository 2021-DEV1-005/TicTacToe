package be.smith.tictactoe.model;

public class Game {

    private Character turn;

    public Game() {
        turn = 'X';
    }

    public Character getTurn() {
        return turn;
    }
}
