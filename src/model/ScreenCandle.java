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
