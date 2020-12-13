package be.smith.tictactoe.service;

import be.smith.tictactoe.model.*;
import org.springframework.stereotype.*;

@Service
public class GameService {

    public GameService() { }

    public Game startNewGame() {
        return new Game();
    }

    public void play(Game game, int rowIndex, int columnIndex) {
        game.play(rowIndex, columnIndex);
    }
}
