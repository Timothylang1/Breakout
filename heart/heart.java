package heart;
import Graphics.*;

import java.awt.Color;

/**
 * Creates heart.
 */
public class heart {

    /**
     * Creates group and canvas, two ellipses, and a triangle, then screenshots the whole thing, saves it as an image.
     */
    public heart() {
        CanvasWindow canvas = new CanvasWindow("Creating Heart", 50, 90);
        GraphicsGroup group = new GraphicsGroup();
        canvas.add(group);

        // Create first heart
        Color color = new Color(232, 3, 82);

        Ellipse top = new Ellipse(0, 0, 30, 30);
        top.setFillColor(color);
        top.setStrokeColor(color);
        group.add(top);
        
        Ellipse top1 = new Ellipse(20, 0, 30, 30);
        top1.setFillColor(color);
        top1.setStrokeColor(color);
        group.add(top1);

        double angle = 43;

        Path base = Path.makeTriangle(15 + 15 * Math.cos(Math.toRadians(270 - angle)), 15 - 15 * Math.sin(Math.toRadians(270 - angle)), 
        35 + 15 * Math.cos(Math.toRadians(270 + angle)), 15 - 15 * Math.sin(Math.toRadians(270 + angle)),
        25, 50);
        base.setFillColor(color);
        base.setStrokeColor(color);
        group.add(base);

        group.setCenter(30, 45);

        canvas.screenShot("src/heart/heart.png");


    }

    public static void main(String[] args) {
        new heart();
    }
}
