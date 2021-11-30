package breakout;

import Graphics.CanvasWindow;
import Graphics.GraphicsGroup;
import Graphics.Rectangle;

import java.awt.Color;

/**
 * Creates paddle.
 */
public class Paddle extends GraphicsGroup {

    private static final Color GREY = new Color(200, 200, 200);
    private static final int PADDLE_WIDTH = Constants.PADDLE_WIDTH;

    private static final int PADDLE_HEIGHT = Constants.PADDLE_HEIGHT;

    private static final int STARTING = Constants.STARTING_PADDLE;
    private static final int PADDLE_SPEED = Constants.BALL_SPEED;

    private int dx = 0;
    private double X = Constants.CANVAS_WIDTH / 2;

    
    /**
     * Creates rectangle that acts like a paddle, then adds it to the canvas that was inputted as a parameter.
     * @param canvas
     */
    public Paddle(CanvasWindow canvas) {
        super();

        // Creates paddle
        Rectangle rectangle = new Rectangle(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
        rectangle.setFillColor(GREY);
        add(rectangle);

        // Sets the starting point
        setCenter(300, STARTING);

        // Gets canvas window to access key presses
        canvas.add(this);
    }

    /**
     * Sets the X coordinate so that the paddle begins moving towards the X coordinate.
     */
    public void setfinalX(double x) {
        X = x;
    }

    /**
     * Gets the last saved X coordinate, moves the paddle towards that X coordinate.
     */
    public void move() {
        if ((getCenter().getX() - X > PADDLE_SPEED) && 
        (getCenter().getX() - PADDLE_WIDTH / 2  - PADDLE_SPEED > Constants.BORDER)) {
            dx = -PADDLE_SPEED;
        }
        else if ((X - getCenter().getX() > PADDLE_SPEED) && 
        (getCenter().getX() + PADDLE_WIDTH / 2 + PADDLE_SPEED < Constants.CANVAS_WIDTH - Constants.BORDER)) {
            dx = PADDLE_SPEED;
        }
        else {
            dx = 0;
        }
        super.moveBy(dx, 0);
    }
    
}
