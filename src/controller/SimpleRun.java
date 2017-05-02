package controller;

import model.Board;
import model.Request;
import model.Ticker;
import view.Form;
import view.Log;

/**
 * Created by apple on 29.04.17.
 */
public class SimpleRun {

    private static final Board board = Board.MICEX;
    private static final Ticker ticker = Ticker.SBER;
    private static final int period = 60;       // 1, 5, 10, 15, 20, 30, 60, 1440;
    private static final String from = "";
    private static final String to = "";
    private static final int bars = 200;

    private static final Request request = new Request(board, ticker, period, from, to, bars);
    private static final LoadData load = new LoadData(request);
    private static final CandlesController candles = new CandlesController(load.getData());
    private static final Form form = new Form(candles);

    public static void run() {
        form.setTitle(ticker.name());
        form.setVisible(true);
        Log.a(load.getData());
    }

}
