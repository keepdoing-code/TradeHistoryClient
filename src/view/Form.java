package view;


import model.Candle;
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
    private static final int CANDLE_SPACE = 2;

    JPanel panel = new JPanel();
    JPanel panelHistory = new JPanel();
    JButton btnShow = new JButton(SHOW_HISTORY);
    JButton btnExit = new JButton(EXIT);
    private Graphics g;
    private Candle[] candles;
    private ScreenCandle[] scrCandles;
    private float multiplier;
    private float min;

    public Form(Candle[] sc, float range, float min) throws HeadlessException {

        this.candles = sc;
        this.multiplier = FORM_HEIGHT / range;
        this.min = min;
        scrCandles = new ScreenCandle[sc.length];


        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(btnShow);
        panel.add(btnExit);

        add(panel, BorderLayout.PAGE_END);
        add(panelHistory, BorderLayout.CENTER);

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    private void paintPanel(Graphics graphics) {
        g = graphics;
        convertCandles();
        for (int i = 0; i < scrCandles.length; i++) {
            drawCandle(scrCandles[i], i * CANDLE_WIDTH + CANDLE_SPACE);
        }
    }

    private void convertCandles() {
        ScreenCandle.zeroPoint = FORM_HEIGHT;
        ScreenCandle.multiplier = multiplier;
        ScreenCandle.min = min;

        for (int i = 0; i < candles.length; i++) {
            scrCandles[i] = new ScreenCandle(candles[i]);
            System.out.println(scrCandles[i].toString());
        }
    }

    private void drawCandle(ScreenCandle sc, int x) {
        g.setColor(CANDLE_SHADOW_COLOR);
        g.drawLine(x+2,sc.high,x+2,sc.low);

        g.setColor(CANDLE_COLOR);
        g.drawRect(x,sc.open,CANDLE_WIDTH,sc.close);
    }
}
