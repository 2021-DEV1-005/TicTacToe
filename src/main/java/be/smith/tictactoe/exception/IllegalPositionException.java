package be.smith.tictactoe.exception;

public class IllegalPositionException extends RuntimeException {
    public IllegalPositionException(String message) {
        super(message);
    }
}
