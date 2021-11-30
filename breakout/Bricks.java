package breakout;

import Graphics.Rectangle;
import java.awt.Color;

/**
 * Creates rectangular bricks.
 */
public class Bricks extends Rectangle {

    // Spacing
    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 20;
    private static final int SPACING = 10;
    private static final int SPACING_FROM_TOP = 150;

    // Colors
    private static final Color BLUE = new Color(0, 0, 150);
    private static final Color GREEN = new Color(0, 150, 0);
    private static final Color RED = new Color(150, 0, 0);
    private static final Color VIOLET = new Color(150, 0, 150);
    private static final Color ORANGE = new Color(150, 150, 0);
    
    /**
     * Creates rectangular bricks. Based on which row their in, assigns color. 
     */
    public Bricks(int x, int y) {
        super((SPACING + BRICK_WIDTH) * x + SPACING / 2 , (BRICK_HEIGHT + SPACING) * y + SPACING_FROM_TOP, BRICK_WIDTH, BRICK_HEIGHT);
        switch (y / 2) {
            case 0:
                setFillColor(BLUE);
                break;
            case 1:
                setFillColor(GREEN);
                break;
            case 2:
                setFillColor(RED);
                break;
            case 3:
                setFillColor(VIOLET);
                break;
            case 4:
                setFillColor(ORANGE);
        }
    }
}
