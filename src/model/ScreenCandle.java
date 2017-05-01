package model;

/**
 * Created by apple on 01.05.17.
 */
public class ScreenCandle {
    public int date;
    public int open;
    public int high;
    public int low;
    public int close;
    public int volume;

    public static float multiplier;
    public static float min;
    public static int zeroPoint;

    public ScreenCandle(Candle c) {
        open = calc(c.getOpen());
        close = calc(c.getClose());
        high = calc(c.getHigh());
        low = calc(c.getLow());
    }

    private int calc(float exp){
        return (int) ( zeroPoint - (exp - min)* multiplier);
    }

    @Override
    public String toString() {
        return "ScreenCandle{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                " || multiplier=" + multiplier+
                " || min="+min+
                " || zero="+zeroPoint+
                '}';
    }
}
