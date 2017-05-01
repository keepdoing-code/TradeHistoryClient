package controller;

import model.Board;
import model.Request;
import model.Ticker;
import view.Form;

/**
 * Created by apple on 01.05.17.
 */
public class WriteDumpRun {

    private static final Board board = Board.MICEX;
    private static final Ticker ticker = Ticker.SBER;
    private static final int period = 60;       // 1, 5, 10, 15, 20, 30, 60, 1440;
    private static final String from = "2017-04-12";
    private static final String to = "";
    private static final int bars = 0;

    private static final Request request = new Request(board, ticker, period, from, to, bars);
    private static final LoadData load = new LoadData(request);

    public static void run() {
        FileController.writeDump(load.getData());
    }

}
