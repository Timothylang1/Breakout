package breakout;

import Graphics.Ellipse;
import Graphics.GraphicsGroup;
import Graphics.Point;
import Graphics.GraphicsObject;

import java.awt.Color;

import java.util.List;

import java.util.Random;

/**
 * Ball class that creates and controls the bouncing ball on the screen.
 */
public class Ball extends GraphicsGroup {

    private static final Color GREY = new Color(150, 150, 150);
    private static final int RADIUS = Constants.BALL_RADIUS;
    private static final int STARTING = 500;

    private final Paddle paddle;
    private final GraphicsGroup bricks, border;

    private static final int SPEED = Constants.BALL_SPEED;

    private double dx, dy;

    /**
     * Creates ball using Ellipse() function, then gives it a random direction. Also saves three GraphicsGroups inputted to check for
     * interactions with them.
     */
    public Ball(Paddle paddle, GraphicsGroup bricks, GraphicsGroup border) {
        super();

        // Allows ball access to all the graphics groups necessary to check for collision
        this.paddle = paddle;
        this.bricks = bricks;
        this.border = border;

        // Adds ball
        Ellipse ball = new Ellipse(0, 0, RADIUS * 2, RADIUS * 2);
        ball.setFillColor(GREY);
        add(ball);

        // Creates starting direction
        Random rand = new Random();
        dx = SPEED * Math.cos(Math.toRadians(60 + 60 * rand.nextDouble()));
        dy = SPEED * Math.sin(Math.toRadians(60 + 60 * rand.nextDouble()));

        // Sets the center of the group in the middle of the canvas
        setCenter(300, STARTING);
    }

    /**
     * Moves ball first. Gets 8 points spanning the rim of the ball, then calls the checkCollision function to chcek if any object is at one
     * of those eight points.
     */
    public void move() {
        super.moveBy(dx, dy);

        double x = getCenter().getX();
        double y = getCenter().getY();
        List<Point> checkPoints = List.of(
            new Point(x + RADIUS * Math.cos(5 * Math.PI/4), y + RADIUS * Math.sin(5 * Math.PI/4)),
            new Point(x + RADIUS * Math.cos(7 * Math.PI/4), y + RADIUS * Math.sin(7 * Math.PI/4)),
            new Point(x + RADIUS * Math.cos(1 * Math.PI/4), y + RADIUS * Math.sin(1 * Math.PI/4)),
            new Point(x + RADIUS * Math.cos(3 * Math.PI/4), y + RADIUS * Math.sin(3 * Math.PI/4)),
            new Point(x, y + RADIUS), 
            new Point(x, y - RADIUS),
            new Point(x + RADIUS, y),
            new Point(x - RADIUS, y)
            );
        
        checkCollision(bricks, checkPoints);
        checkCollision(border, checkPoints);
        checkCollision(paddle, checkPoints);
    }

    /**
     * Depending on the group, gets a subset of the inputted points, then checks for collision within that subset. If there is a collision,
     * changes dx and dy accordingly, depending on which side hit. If the group is bricks, remove collided brick from group. If the 
     * group is paddle, calls the changeDirection() method.
     */
    private void checkCollision(GraphicsGroup group, List<Point> checkPoints) {
        int counter = 0; // Used to check which point is being tested currently
        if (group == border) {
            checkPoints = checkPoints.subList(4, 8);
            counter += 4;
        }
        if (group == paddle) {
            checkPoints = checkPoints.subList(2, 5);
        }

        for (Point point: checkPoints) {
            GraphicsObject object_at_point = group.getElementAt(point);
            if (object_at_point != null) {
                switch (counter) {
                    // Reason for Math.abs is in case ball brushes past a rectangle, then the direction won't suddenly change in a weird way
                    case 0:
                        dx = Math.abs(dx);
                        dy = Math.abs(dy);
                        break;
                    case 1:
                        dx = -Math.abs(dx);
                        dy = Math.abs(dy);
                        break;
                    case 2:
                        dx = -Math.abs(dx);
                        dy = -Math.abs(dy);
                        break;
                    case 3:
                        dx = Math.abs(dx);
                        dy = -Math.abs(dy);
                        break;
                    case 4:
                        dy = -Math.abs(dy);
                        break;
                    case 5:
                        dy = Math.abs(dy);
                        break;
                    case 6:
                        dx = -Math.abs(dx);
                        break;
                    case 7:
                        dx = Math.abs(dx);
                        break;
                }
                // Special case: if we're using the brick GraphicsGroup
                if (group == bricks) {
                    group.remove(object_at_point);
                    Constants.total_bricks -= 1;
                }
                // Special case: only need to check collision with the bottom point between the paddle and the ball, so we break
                else if (group == paddle) {
                    changeDirection();
                    break;
                }
            }
            counter++;
        }
    }
    
    /**
     * Upon collision with paddle, this method calculates the trajectory the ball will bounce off based on where it hit the paddle.
     * If it hits the left, the ball will bounce off to the left, and vice versa.
     */
    private void changeDirection() {
        // Ball direction depends on where on the paddle it hit
        double difference = paddle.getCenter().getX() + Constants.PADDLE_WIDTH / 2 - getCenter().getX();
        double angle = 45 + difference * 90 / Constants.PADDLE_WIDTH;
        dx = SPEED * Math.cos(Math.toRadians(angle));
        dy = -SPEED * Math.sin(Math.toRadians(angle));
    }
}
