package bowling;

import java.util.Optional;

/**
 * Representation of at most two consecutive rolls in a bowling game that are considered
 * part of the same game turn.
 */
public class Frame {

    private final int firstBall;
    private final int secondBall;
    private Optional<Frame> followingFrame = Optional.empty();

    private Frame(int firstBall, int secondBall) {
        this.firstBall = firstBall;
        this.secondBall = secondBall;
    }

    public int score() {
        int score = firstBall + secondBall;
        if (score == 10 && followingFrame.isPresent()) {
            Frame followingFrame = this.followingFrame.get();
            score += followingFrame.firstBall;
            if (this.isStrike()) {
                if (followingFrame.isStrike()) {
                    score += followingFrame.followingFrame.map(Frame::getFirstBall).orElse(0);
                } else {
                    score += followingFrame.secondBall;
                }
            }
        }
        return score;
    }

    public boolean isStrike() {
        return firstBall == 10;
    }

    public boolean isSpare() {
        return firstBall + secondBall == 10;
    }

    public int getFirstBall() {
        return this.firstBall;
    }

    public void followingFrame(Frame followingFrame) {
        this.followingFrame = Optional.of(followingFrame);
    }

    public static Frame withThrows(int firsThrow, int secondThrow) {
        return new Frame(firsThrow, secondThrow);
    }

}
