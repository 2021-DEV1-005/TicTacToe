package be.smith.tictactoe.model;

import org.junit.*;

public class GameTest {

    @Test
    public void xShouldGoFirst() {
        Game game = new Game();
        Assert.assertEquals("X doesn't go first", new Character('X'), game.getTurn());
    }

    @Test
    public void boardShouldHave3RowsOf3Columns() {
        Game game = new Game();
        int rowCount = 0;
        for (Character[] rows : game.getBoard()) {
            rowCount += 1;
            int columnCount = 0;
            for (Character position : rows) {
                columnCount += 1;
            }
            Assert.assertEquals("Board doesn't have 3 colums", 3, columnCount);
        }
        Assert.assertEquals("Board doesn't have 3 rows", 3, rowCount);
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
        for (int i=0; i <3 ; i++) {
            for (int j=0 ; j<3 ; j++) {
                if (i == 0 && j == 0) {
                    Assert.assertEquals("Position is not set to 'X' after first play",
                                        new Character('X'),
                                        game.getBoard()[i][j]);
                } else {
                    Assert.assertNull(String.format("Position %d,%d is not null after first play on position 0,0", i, j), game.getBoard()[i][j]);
                }
            }
        }
    }
}
