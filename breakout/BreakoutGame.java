package breakout;

import Graphics.CanvasWindow;
import Graphics.GraphicsGroup;

/**
 * Sets up and runs Breakout until player loses or all the bricks are destroyed.
 */
public class BreakoutGame {

    // For the canvas
    private static final int CANVAS_WIDTH = Constants.CANVAS_WIDTH;
    private static final int CANVAS_HEIGHT = Constants.CANVAS_HEIGHT;

    private int total_lives = 3;

    private GraphicsGroup bricks, border, hearts;

    private Ball ball;

    private Paddle paddle;

    private CanvasWindow canvas;

        
    /**
     * Creates canvas, graphics groups, and calls setup() function. Also creates instance of Runnable to help with animating ball and paddle.
     */
    public BreakoutGame() {
        // Creates new grahpicsgroups
        bricks = new GraphicsGroup();
        border = new GraphicsGroup();
        hearts = new GraphicsGroup();

        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        setup();

        // Creates new runnable object to help with animation
        Runnable runnable = new Runnable() {
            public void run() {
                paddle.move();
                ball.move();
                gameends();
            }
        };

        canvas.onMouseMove(event -> {
            paddle.setfinalX(event.getPosition().getX());
        });

        canvas.animate(runnable);
    }

    public static void main(String[] args){
        new BreakoutGame();
    }

    /**
     * Creates 100 bricks, temporary paddle and ball (removed in the reset function), and calls function createBorders() to create borders.
     * Adds all objects that don't add themselves to canvas.
     */
    private void setup() {

        // Creates 100 bricks
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Bricks brick = new Bricks(x, y);
                Constants.total_bricks += 1;
                bricks.add(brick);
            }
        }
        
        canvas.add(bricks);

        // Creates borders
        createBorders();

        // Creates temporary new ball and paddle so no errors show up during the reset function
        paddle = new Paddle(canvas);
        ball = new Ball(paddle, bricks, border);
        canvas.add(ball);

        // Add hearts to canvas
        canvas.add(hearts);

        reset();
    }

    /**
     * Creates borders, adds them to canvas.
     */
    private void createBorders() {
        final int BORDER = Constants.BORDER;
        new Border(0, 0, CANVAS_WIDTH, BORDER, border);
        new Border(0, CANVAS_HEIGHT - BORDER, CANVAS_WIDTH, BORDER, border);
        new Border(0, 0, BORDER, CANVAS_HEIGHT, border);
        new Border(CANVAS_WIDTH - BORDER, 0, BORDER, CANVAS_HEIGHT, border);
        canvas.add(border);
    }

    /**
     * Ends game if all lives are lost. If the player still has lives, calls the reset() function. If all the bricks are gone, game ends.
     * Based on which scenario, calls Countdown to create textbox with text for occasion (i.e. if they won or lost).
     */
    private void gameends() {
        // If ball is out of bounds, lose a life
        if (ball.getCenter().getY() + Constants.BALL_RADIUS > Constants.STARTING_PADDLE + Constants.PADDLE_HEIGHT) {
            // If no lives left, you lose
            if (total_lives == 0) {
                // Removes the last heart
                canvas.remove(hearts);

                // Uses the Countdown class to print text on the screen
                Countdown Endgame = new Countdown(canvas);
                Endgame.setText("You lose!");
                canvas.draw();
                canvas.pause(2000);
                canvas.closeWindow();
            }
            else {
                reset();
            }
        }

        // If all the bricks are gone, you win
        if (Constants.total_bricks == 0) {
            // Uses the Countdown class to print text on the screen
            Countdown Endgame = new Countdown(canvas);
            Endgame.setText("You win!");
            canvas.draw();
            canvas.pause(2000);
            canvas.closeWindow();
        }
    }

    /**
     * Creates countdown on screen, starting from 3.
     */
    private void beginCountdown() {
        // Countdown adds itself to the canvas
        Countdown countdown = new Countdown(canvas);
        for (int count = 3; count > 0; count--) {
            countdown.setText(Integer.toString(count));
            canvas.draw();
            canvas.pause(1000);
        }

        canvas.remove(countdown);
    }

    /**
     * Creates hearts on screen based on player lives.
     */
    private void createLives() {
        hearts.removeAll();
        for (int lives = 0; lives < total_lives; lives++) {
            new Hearts(lives, hearts);
        }
        total_lives--;
    }

    /**
     * Resets ball and paddle, then calls function createLives() which removes a heart from the screen. Begins countdown at the end.
     */
    private void reset() {
        // Removes current ball and paddle from canvas
        canvas.remove(paddle);
        canvas.remove(ball);
        
        // Paddle adds itself to canvas
        paddle = new Paddle(canvas);
        ball = new Ball(paddle, bricks, border);

        // Adds ball back to canvas
        canvas.add(ball);

        createLives();
        beginCountdown();
    }
}
