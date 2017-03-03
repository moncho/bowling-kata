package bowling;

import org.junit.Assert;
import org.junit.Test;

public class BowlingGameTest {

    @Test
    public void testGame_NoThrows() {
        BowlingGame bowlingGame = new BowlingGame();

        Assert.assertEquals(0, bowlingGame.gameScore());
    }

    @Test
    public void testGame_NoStrikesNoSpares() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.frame(5, 0);

        Assert.assertEquals(5, bowlingGame.gameScore());
    }

    @Test
    public void testGame_OneSpare() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame
            .frame(5, 5)
            .frame(2, 0);

        Assert.assertEquals(14, bowlingGame.gameScore());
    }

    @Test
    public void testGame_OneSpareAtTheLastFrame() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            bowlingGame.frame(0, 0);
        }
        bowlingGame.frame(5, 5);
        bowlingGame.frame(2, 0);
        Assert.assertEquals(12, bowlingGame.gameScore());
    }

    @Test
    public void testGame_OneStrike() {
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame
            .strike()
            .frame(5, 0);
        for (int i = 0; i < 9; i++) {
            bowlingGame.frame(0, 0);
        }

        Assert.assertEquals(20, bowlingGame.gameScore());
    }

    @Test
    public void testGame_OneStrikeAtTheLastFrame() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            bowlingGame.frame(0, 0);
        }
        bowlingGame
            .strike()
            .frame(5, 5);

        Assert.assertEquals(20, bowlingGame.gameScore());

    }

    @Test
    public void testGame_DoublePinfall() {
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame
            .strike()
            .strike()
            .frame(9, 0);

        Assert.assertEquals(57, bowlingGame.gameScore());

    }

    @Test
    public void testGame_TurkeyPinfall() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int i = 0; i < 3; i++) {
            bowlingGame.strike();
        }
        bowlingGame.frame(0, 9);

        Assert.assertEquals(78, bowlingGame.gameScore());

    }

    @Test
    public void testGame_PerfectGame() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int i = 0; i < 12; i++) {
            bowlingGame.strike();
        }

        Assert.assertEquals(300, bowlingGame.gameScore());

    }

}
