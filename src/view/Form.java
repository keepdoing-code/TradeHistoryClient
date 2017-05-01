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
    private static final String SHOW_HISTORY = "Show history";
    private static final int FORM_HEIGHT = 600;
    private static final int FORM_WIDTH = 800;
    private static final Color CANDLE_COLOR = Color.BLACK;
    private static final Color CANDLE_SHADOW_COLOR = Color.blue;
    private static final int CANDLE_WIDTH = 5;
    private static final int CANDLE_SPACE = 7;
    private static final String PLUS_SCALE = "+";
    private static final String MINUS_SCALE = "-";

    JPanel panel = new JPanel();
    JPanel panelHistory = new JPanel();
    JButton btnShow = new JButton(SHOW_HISTORY);
    JButton btnExit = new JButton(EXIT);
    JButton btnPlusScale = new JButton(PLUS_SCALE);
    JButton btnMinusScale = new JButton(MINUS_SCALE);

    private int scale = 2;
    private Graphics graphics;
    private CandlesController controller;
    private ScreenCandle[] screenCandles;


    public Form(CandlesController candlesController) throws HeadlessException {

        controller = candlesController;
        controller.convertToScreen(FORM_HEIGHT);
        screenCandles = controller.getScreenCandles();

        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(btnShow);
        panel.add(btnPlusScale);
        panel.add(btnMinusScale);
        panel.add(btnExit);

        add(panel, BorderLayout.PAGE_END);
        add(panelHistory, BorderLayout.CENTER);

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel(panelHistory.getGraphics());
            }
        });

        btnPlusScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale++;
                Log.i("Scale:"+scale);
                paintPanel(panelHistory.getGraphics());
            }
        });

        btnMinusScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale--;
                Log.i("Scale:"+scale);
                paintPanel(panelHistory.getGraphics());
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void paintPanel(Graphics g) {
        graphics = g;
        graphics.clearRect(0,0,getWidth(),getHeight());

        for (int i = 0; i < screenCandles.length; i++) {
            drawCandle(screenCandles[i], i * CANDLE_SPACE + 1);
        }
    }

    private void drawCandle(ScreenCandle sc, int xOffset) {

        graphics.setColor(CANDLE_SHADOW_COLOR);
        graphics.drawLine(xOffset + 2, sc.high, xOffset + 2, sc.low);

        graphics.setColor(CANDLE_COLOR);
        graphics.drawRect(xOffset, sc.open, CANDLE_WIDTH, sc.close);
    }
}
