import java.util.Arrays;

/**
 * Created by apple on 29.04.17.
 */
public class Main {
    public static void main(String[] args) {
        Request request = new Request(Board.MICEX, Ticker.SBER, 60, "2017-04-01");
        Connection connection = new Connection(request);

        Candle[] candles = connection.getCandles();

        for (Candle c:candles){
            System.out.println(c.toString());
        }
    }
}
