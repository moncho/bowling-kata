package bowling;

import java.util.Stack;

/**
 * A representation of a 10-pin bowling game as described here: https://en.wikipedia.org/wiki/Ten-pin_bowling.
 */
public class BowlingGame {
    private Stack<Frame> frames = new Stack<>();
    //This holds information about the extra throw that might happen
    //depending on the result of the last regular frame.
    private Frame extraThrow = null;

    public int gameScore() {
        return frames.stream().mapToInt(Frame::score).sum();
    }

    public BowlingGame strike() {
        return this.frame(10, 0);
    }

    public BowlingGame frame(int firstBall, int secondBall) {
        Frame frame = Frame.withThrows(firstBall, secondBall);
        if (extraThrow == null) {
            if (!this.frames.isEmpty()) {
                this.frames.peek().followingFrame(frame);
            }
            if (this.frames.size() < 10) {
                this.frames.push(frame);
            } else {
                extraThrow = frame;
            }
        } else {
            extraThrow.followingFrame(frame);
        }
        return this;
    }
}
