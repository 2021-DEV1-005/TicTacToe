package be.smith.tictactoe.model;

import be.smith.tictactoe.exception.*;
import org.junit.*;

public class GameTest {

    @Test
    public void xShouldGoFirst() {
        Game game = new Game();
        Assert.assertEquals("X doesn't go first", new Character('X'), game.getTurn());
    }

    @Test
    public void boardShouldHaveRightDimension() {
        Game game = new Game();
        int rowCount = 0;
        for (Character[] rows : game.getBoard()) {
            rowCount += 1;
            int columnCount = 0;
            for (Character position : rows) {
                columnCount += 1;
            }
            Assert.assertEquals("Board doesn't have 3 colums", Game.DIMENSION, columnCount);
        }
        Assert.assertEquals("Board doesn't have 3 rows", Game.DIMENSION, rowCount);
    }

    @Test
    public void allPositionsShouldBeNullAtBeginning() {
        Game game = new Game();
        for (Character[] rows : game.getBoard()) {
            for (Character position : rows) {
                Assert.assertNull("Positions are not all null at beginning", position);
            }
        }
    }

    @Test
    public void positionShouldBeSetToXAfterFirstPlay() {
        Game game = new Game();
        game.play(0, 0);
        for (int i = 0; i < Game.DIMENSION; i++) {
            for (int j = 0; j < Game.DIMENSION; j++) {
                if (i == 0 && j == 0) {
                    Assert.assertEquals("Position is not set to 'X' after first play",
                                        new Character('X'),
                                        game.getBoard()[i][j]);
                } else {
                    Assert.assertNull(String.format("Position %d,%d is not null after first play on position 0,0",
                                                    i,
                                                    j), game.getBoard()[i][j]);
                }
            }
        }
    }

    @Test
    public void turnShouldBeOAfterFirstPlay() {
        Game game = new Game();
        game.play(0, 0);
        Assert.assertEquals("Turn is not 'O' after first play", new Character('O'), game.getTurn());
    }

    @Test(expected = IllegalPositionException.class)
    public void shouldThrowIllegalPositionExceptionWhenPlayingAnAlreadyPlayedPosition() {
        Game game = new Game();
        game.play(0, 0);
        try {
            game.play(0, 0);
        } catch (IllegalPositionException e) {
            Assert.assertEquals("Position already played", e.getMessage());
            Assert.assertEquals("Position has been played over", new Character('X'), game.getBoard()[0][0]);
            throw e;
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void shouldThrowIllegalPositionExceptionWhenXCoordinateIsNegative() {
        Game game = new Game();
        try {
            game.play(-1, 0);
        } catch (IllegalPositionException e) {
            Assert.assertEquals("Coordinates are out of bound", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void shouldThrowIllegalPositionExceptionWhenYCoordinateIsNegative() {
        Game game = new Game();
        try {
            game.play(0, -1);
        } catch (IllegalPositionException e) {
            Assert.assertEquals("Coordinates are out of bound", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void shouldThrowIllegalPositionExceptionWhenXCoordinateIsTooBig() {
        Game game = new Game();
        try {
            game.play(3, 0);
        } catch (IllegalPositionException e) {
            Assert.assertEquals("Coordinates are out of bound", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void shouldThrowIllegalPositionExceptionWhenYCoordinateIsTooBig() {
        Game game = new Game();
        try {
            game.play(0, 3);
        } catch (IllegalPositionException e) {
            Assert.assertEquals("Coordinates are out of bound", e.getMessage());
            throw e;
        }
    }
}
