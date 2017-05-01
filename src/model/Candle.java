package model;

/**
 * Created by apple on 29.04.17.
 * <p/>
 * Возвращаются следующие поля свеч: DATE, OPEN, HIGH, LOW, CLOSE, VOLUME.
 * Формат поля DATE — Y-m-d H:i:s, разделителем для остальных полей является точка (.).
 * Поля записи разделены табуляцией (\t), записи разделены символом новой строки (\n).
 * После последней записи также присутствует символ новой строки (\n).
 */

public class Candle {

    private String date;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;

    public Candle(String date, float open, float high, float low, float close, float volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public Candle(String data) {
        this.parse(data);
    }

    private boolean parse(String data) {
        String[] arr;
        arr = data.split("\t");

        try {
            this.date = arr[0];
            this.open = Float.parseFloat(arr[1]);
            this.high = Float.parseFloat(arr[2]);
            this.low = Float.parseFloat(arr[3]);
            this.close = Float.parseFloat(arr[4]);
            this.volume = Float.parseFloat(arr[5]);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" +
                "date='" + date + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public float getOpen() {
        return open;
    }

    public float getVolume() {
        return volume;
    }

    public String getDate() {
        return date;
    }
    //    public ScreenCandle getScreenCandle(int canvasWidth,int canvasHeight){
//
//    }

}
