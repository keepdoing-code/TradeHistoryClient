package model;

/**
 * Created by apple on 01.05.17.
 */
public class ScreenCandle {
    public String date;
    public int open;
    public int high;
    public int low;
    public int close;
    public int volume;

    private static int canvasHeight = 0;
    private static float minPrice = 0;
    private static float multiplier = 0;

    public static void Init(int canvasHeight, float minPrice, float multiplier){
        ScreenCandle.canvasHeight = canvasHeight;
        ScreenCandle.minPrice = minPrice;
        ScreenCandle.multiplier = multiplier;
    }


    public ScreenCandle(Candle candle) {
        this.date = candle.date;
        this.open = calcScreen(candle.open);
        this.high = calcScreen(candle.high);
        this.low = calcScreen(candle.low);
        this.close = calcScreen(candle.close);
        this.volume = (int)candle.volume;
    }

    private int calcScreen(float exp) {
        float f = canvasHeight - ((exp - minPrice) * multiplier);
        return Math.round(f);
    }

    @Override
    public String toString() {
        return "ScreenCandle{" +
                "open=" + open +
                " | high=" + high +
                " | low=" + low +
                " | close=" + close +
                " | date=" + date +
                " | volume=" + volume +
                '}';
    }
}
