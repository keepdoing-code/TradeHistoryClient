package controller;

import model.Board;
import model.Ticker;
import view.Form;

/**
 * Created by apple on 01.05.17.
 */
public class ReadDumpRun {

    private static final Board board = Board.MICEX;
    private static final Ticker ticker = Ticker.SBER;
    private static final int period = 60;       // 1, 5, 10, 15, 20, 30, 60, 1440;
    private static final String from = "";
    private static final String to = "";
    private static final int bars = 5;

    private static final CandlesController candles = new CandlesController(FileController.readDump());
    private static final Form form = new Form(candles);

    public static void run() {
        form.setTitle(ticker.name());
        form.setVisible(true);
    }

}

