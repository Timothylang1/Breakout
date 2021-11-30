package breakout;

import Graphics.CanvasWindow;
import Graphics.GraphicsText;
import Graphics.GraphicsGroup;

/**
 * Countdown creates textbox, and sets it up towards the lower half of the screen.
 */
public class Countdown extends GraphicsGroup {

    private static final int TEXTSIZE = 100;
    private static final int Y_COOR = 600;

    private GraphicsText textbox;

    /**
     * Creates textbox, then adds it to itself.
     */
    public Countdown(CanvasWindow canvas) {
        super(0, 0);
        textbox = new GraphicsText();
        textbox.setFontSize(TEXTSIZE);
        this.add(textbox);
        canvas.add(this);
    }
    
    /**
     * Changes text of textbox.
     * @param string
     */
    public void setText(String string) {
        textbox.setText(string);
        setCenter(Constants.CANVAS_WIDTH / 2, Y_COOR);
    }
}
