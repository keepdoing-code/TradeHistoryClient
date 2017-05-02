package view;

import java.awt.*;

/**
 * Created by apple on 02.05.17.
 */
public class Drawings {

    private static final int FORM_HEIGHT = 600;
    private static final int FORM_WIDTH = 800;
    private static final int CANDLE_WIDTH = 5;
    private static final int CANDLE_SPACE = 4;
    private static final int LINE_SPACE = 4;
    private static final int PANEL_WIDTH = 0;
    private static final int PANEL_HEIGHT = 100;
    private static final int GRID_STEP_SIZE = 20;

    private static final Color CANDLE_COLOR = Color.BLACK;
    private static final Color GRID_COLOR = Color.DARK_GRAY;
    private static final Color CANDLE_SHADOW_COLOR = Color.GRAY;
    private static final Color LINES_BACKGROUND = Color.BLACK;
    private static final Color CANDLE_UP = Color.WHITE;
    private static final Color CANDLE_DOWN = Color.GRAY;
    private static final Color LINE_COLOR = Color.GRAY;
    private static final Color LINE_DOTS = Color.WHITE;

    private int scale = 2;
    private int currentFormHeight = FORM_HEIGHT;
    private int currentFormWidth = FORM_WIDTH;

    public Drawings(int formHeight, int formWidth) {
        this.currentFormHeight = formHeight;
        this.currentFormWidth = formWidth;
    }

    public void drawGrid(Graphics g, int stepSize){
        g.setColor(GRID_COLOR);
        for (int i = 0; i < Math.round(FORM_WIDTH/stepSize); i++) {
            g.drawLine(i * stepSize, 0 , i * stepSize , FORM_HEIGHT);
        }
        for (int j = 0; j < Math.round(FORM_HEIGHT/stepSize); j++) {
            g.drawLine(0, j * stepSize, FORM_WIDTH, j * stepSize);
        }
    }
}
