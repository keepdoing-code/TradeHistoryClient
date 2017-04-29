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

    public static void run() {
        Request request = new Request(Board.MICEX, Ticker.SBER, 60, "2017-04-01");
        DataLoader loader = new DataLoader(request);

        Candle[] candles = loader.getCandles();

        for (Candle c:candles){
            System.out.println(c.toString());
        }
    }
}
