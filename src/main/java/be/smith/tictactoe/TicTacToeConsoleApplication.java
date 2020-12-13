package be.smith.tictactoe;

import be.smith.tictactoe.exception.*;
import be.smith.tictactoe.model.*;
import be.smith.tictactoe.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class TicTacToeConsoleApplication implements CommandLineRunner {

    @Autowired
    GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeConsoleApplication.class, args);
    }

    @Override public void run(String... args) {
        System.out.println("Welcome to awesome Tic Tac Toe!");
        boolean go = true;
        while(go) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Start a new game ? (y/n)");
            try {
                String response = scanner.nextLine();
                switch (response) {
                    case "y":
                        doGame();
                        break;
                    case "n":
                        go = false;
                        break;
                    default:
                        System.out.println("Answer should be 'y' or 'n'");
                }
            } catch (Exception e) {
                System.out.println("Answer should be 'y' or 'n'");
            }
        }
        System.out.println("Bye bye!");
    }

    private void doGame() {
        System.out.println("Starting new game");
        Game game = gameService.startNewGame();
        while (true) {
            System.out.println(showBoard(game));
            System.out.println(String.format("%s turn!", game.getTurn()));
            System.out.println("Select row (1-3):");
            int row = getCoordinate();
            System.out.println("Select column (1-3):");
            int column = getCoordinate();
            try {
                gameService.play(game, row - 1, column - 1);
                if (game.isWinFor('X')) {
                    System.out.println("X wins!");
                    return;
                } else if (game.isWinFor('O')) {
                    System.out.println("O wins!");
                    return;
                } else if (game.isDraw()) {
                    System.out.println("It's a draw!");
                    return;
                }
            } catch (IllegalPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getCoordinate() {
        int coordinate = -1;
        while (coordinate == -1) {
            Scanner scanner = new Scanner(System.in);
            try {
                coordinate = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Should be a number between 1 and 3");
            }
        }
        return coordinate;
    }

    private String showBoard(Game game) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Game.DIMENSION; i++) {
            result.append(showRow(game, i)).append("\n");
            if (i < Game.DIMENSION - 1) {
                result.append("-+-+-\n");
            }
        }
        return result.toString();
    }

    private String showRow(Game game, int row) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Game.DIMENSION; i++) {
            result.append(showPosition(game, row, i));
            if (i < Game.DIMENSION - 1) {
                result.append("|");
            }
        }
        return result.toString();
    }

    private char showPosition(Game game, int row, int column) {
        return game.getBoard()[row][column] != null ? game.getBoard()[row][column] : ' ';
    }
}

