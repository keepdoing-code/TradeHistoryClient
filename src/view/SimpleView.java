package view;

import controller.LoadData;
import model.Board;
import model.Candle;
import model.Request;
import model.Ticker;

/**
 * Created by apple on 29.04.17.
 */
public class SimpleView {

    private static final Board board = Board.MICEX;
    private static final Ticker ticker = Ticker.SBER;
    private static final int period = 60;       // 1, 5, 10, 15, 20, 30, 60, 1440;
    private static final String from = "";
    private static final String to = "";
    private static final int bars = 100;

    private static final Request request = new Request(board, ticker, period, from, to, bars);

    public static void run() {
        final LoadData loader = new LoadData(request);
        final Candle[] candles = loader.getCandles();
        final Form form = new Form(candles, loader.getRange(), loader.getMin());
        form.setVisible(true);

        for (Candle c:candles){
            System.out.println(c.toString());
        }
        System.out.println("Array size - "+candles.length);
    }

}
