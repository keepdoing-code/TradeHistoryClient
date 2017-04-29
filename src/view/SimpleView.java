package view;

import controller.DataLoader;
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
    private static final int bars = 1000;

    private static final Request request = new Request(board,ticker,period,from,to,bars);
    private static final DataLoader loader = new DataLoader(request);
    private static final Candle[] candles = loader.getCandles();


    public static void run() {
        for (Candle c:candles){
            System.out.println(c.toString());
        }
        System.out.println("Array size - "+candles.length);
    }
}
