package breakout;

/**
 * Creates shared constants. Except for total_balls, which isn't a constant, but not shared.
 */
public class Constants {

    // Shared by ball and paddle
    static final int STARTING_PADDLE = 700;

    static final int BALL_SPEED = 7;

    static final int PADDLE_WIDTH = BALL_SPEED * 10;

    // Shared by BreakoutGame and Paddle
    static final int CANVAS_WIDTH = 600;
    
    static final int CANVAS_HEIGHT = 800;

    // Need this to make sure the ball doesn't fly past the paddle if it's too thin
    static final int BORDER = BALL_SPEED + 1;

    static final int PADDLE_HEIGHT = BALL_SPEED + 1;

    // Shared by BreakoutGame and ball
    static final int BALL_RADIUS = 15;

    static int total_bricks = 0;
}
