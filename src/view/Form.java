package view;

import controller.CandlesController;
import model.ScreenCandle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by apple on 30.04.17.
 */
public class Form extends JFrame {

    private static final String EXIT = "Exit";
    private static final String SHOW_HISTORY = "Candles";
    private static final int FORM_HEIGHT = 600;
    private static final int FORM_WIDTH = 800;
    private static final int CANDLE_WIDTH = 5;
    private static final int CANDLE_SPACE = 4;
    private static final String PLUS_SCALE = "Lines";
    private static final String MINUS_SCALE = "-";
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

    JPanel panel = new JPanel();
    JPanel panelHistory = new JPanel();
    JButton btnShow = new JButton(SHOW_HISTORY);
    JButton btnExit = new JButton(EXIT);
    JButton btnPlusScale = new JButton(PLUS_SCALE);
    JButton btnMinusScale = new JButton(MINUS_SCALE);

    private int scale = 2;
    private int currentFormHeight = FORM_HEIGHT;
    private int CurrentFormWidth = FORM_WIDTH;
    private Graphics graphics;
    private CandlesController controller;
    private ScreenCandle[] screenCandles;


    public Form(CandlesController candlesController) throws HeadlessException {
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(btnShow);
        panel.add(btnPlusScale);
        panel.add(btnMinusScale);
        panel.add(btnExit);

        add(panel, BorderLayout.SOUTH);
        add(panelHistory, BorderLayout.CENTER);

        controller = candlesController;
        controller.convertToScreen(FORM_HEIGHT - PANEL_HEIGHT);
        screenCandles = controller.getScreenCandles();

        actions();
    }

    //==============================================================================
    private void drawCandles(Graphics g) {
        this.graphics = g;
        graphics.clearRect(0, 0, getWidth(), getHeight());
        graphics.setColor(LINES_BACKGROUND);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics,Math.round((FORM_WIDTH - PANEL_WIDTH) / controller.rangePrice));

        int space = Math.round((FORM_WIDTH - PANEL_WIDTH) / screenCandles.length);

        for (int i = 0; i < screenCandles.length; i++) {
            drawCandle(screenCandles[i], i * space);
        }
    }

    //==============================================================================
    private void drawCandle(ScreenCandle sc, int xOffset) {
        graphics.setColor(CANDLE_SHADOW_COLOR);
        graphics.drawLine(
                xOffset,
                sc.high,
                xOffset,
                sc.low);

        if(sc.open > sc.close) {
            graphics.setColor(CANDLE_DOWN);
            graphics.fillRect(
                    xOffset - CANDLE_WIDTH / 2,
                    sc.close,
                    CANDLE_WIDTH,
                    sc.open - sc.close);
        }else {
            graphics.setColor(CANDLE_UP);
            graphics.fillRect(
                    xOffset - CANDLE_WIDTH / 2,
                    sc.open,
                    CANDLE_WIDTH,
                    sc.close-sc.open);
        }
    }

    //==============================================================================
    private void drawLines(Graphics g) {
        this.graphics = g;

        graphics.clearRect(0, 0, getWidth(), getHeight());
        graphics.setColor(LINES_BACKGROUND);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        int tmp = Math.round((FORM_WIDTH - PANEL_WIDTH) / controller.rangePrice);
        drawGrid(graphics, tmp);

        int space = Math.round((FORM_WIDTH - PANEL_WIDTH) / screenCandles.length);

        for (int i = 1; i < screenCandles.length; i++) {
            graphics.setColor(LINE_COLOR);
            graphics.drawLine(
                    (i - 1) * space,
                    screenCandles[i - 1].open,
                    i * space,
                    screenCandles[i].open
            );
            graphics.setColor(LINE_DOTS);
            graphics.drawOval(i * space-1, screenCandles[i].open-1, 2, 2);
        }
    }
    //==============================================================================
    private void drawGrid(Graphics g, int stepSize){
        g.setColor(GRID_COLOR);
        for (int i = 0; i < Math.round(FORM_WIDTH/stepSize); i++) {
            g.drawLine(i * stepSize, 0 , i * stepSize , FORM_HEIGHT);
        }
        for (int j = 0; j < Math.round(FORM_HEIGHT/stepSize); j++) {
            g.drawLine(0, j * stepSize, FORM_WIDTH, j * stepSize);
        }
    }//TODO right grid draw direction

    //==============================================================================
    private void actions() {
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawCandles(panelHistory.getGraphics());
            }
        });

        btnPlusScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLines(panelHistory.getGraphics());
            }
        });

        btnMinusScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
