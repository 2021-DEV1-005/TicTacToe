package be.smith.tictactoe.model;

import org.junit.*;

public class GameTest {

    @Test
    public void xShouldGoFirst() {
        Game game = new Game();
        Assert.assertEquals("X doesn't go first", new Character('X'), game.getTurn());
    }
}
