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
        for(Character[] rows : game.getBoard()) {
            for (Character position : rows) {
                Assert.assertNull("Positions are not all null at beginning", position);
            }
        }
    }
}
