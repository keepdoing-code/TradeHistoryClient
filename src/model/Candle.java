package model;

/**
 * Created by apple on 01.05.17.
 */
public class Candle {
    public String date;
    public float open;
    public float high;
    public float low;
    public float close;
    public float volume;

    public Candle(String data) {
        this.parse(data);
    }

    //TODO parse exception
    private boolean parse(String data) {
        String[] arr = data.split("\t");
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
        return "Candle{date='" + date +
                "| open=" + open +
                "| high=" + high +
                "| low=" + low +
                "| close=" + close +
                "| volume=" + volume +
                "}";
    }
}
