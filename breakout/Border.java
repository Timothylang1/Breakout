package breakout;

import Graphics.Rectangle;
import Graphics.GraphicsGroup;
import java.awt.Color;

/**
 * Creates black rectangle that acts like a border around the edges of the canvas.
 */
public class Border extends Rectangle {
    private static final Color BLACK = new Color(0, 0, 0);
    
    /**
     * Creates rectangle.
     */
    public Border(int x, int y, int width, int height, GraphicsGroup group) {
        super(x, y, width, height);
        setFillColor(BLACK);
        group.add(this);
    }
}
