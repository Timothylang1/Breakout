package breakout;

import Graphics.GraphicsGroup;
import Graphics.Image;

/**
 * Creates image of heart.
 */
public class Hearts extends Image {
    private static final int STARTING_Y = 650;
    private static final int STARTING_X = 300;
    private static final double SCALE = 0.4;

    /**
     * Creates image of heart, then adds it to the group based on the x coordinate.
     * @param x
     * @param group
     */
    public Hearts(int x, GraphicsGroup group) {
        super(x * 50 + STARTING_X, STARTING_Y, "heart/heart.png");
        setScale(SCALE);
        group.add(this);
    }
}



