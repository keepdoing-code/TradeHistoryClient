package controller;

import model.Candle;
import model.ScreenCandle;
import view.Log;

/**
 * Created by apple on 01.05.17.
 */
public class CandlesController {

    private static final String EMPTY_RAW_DATA = "E - no data to load in Candles";
    public float maxPrice;
    public float minPrice;
    public float rangePrice;
    public int candlesCount = 0;
    public float multiplier = 0;

    private ScreenCandle[] screenCandles;
    private Candle[] candles;


    public CandlesController(String[] rawData) {
        if (rawData.length > 0) {
            candles = new Candle[rawData.length];
            Candle first = new Candle(rawData[0]);
            maxPrice = first.high;
            minPrice = first.low;

            //Reverse array
            for (int i = 0; i < rawData.length; i++) {
                candles[rawData.length - i - 1] = StrToCandle(rawData[i]);
            }

            rangePrice = maxPrice - minPrice;
            candlesCount = candles.length;
        } else {
            Log.i(EMPTY_RAW_DATA);
        }
    }

    private Candle StrToCandle(String str) {
        Candle candle = new Candle(str);
        if (candle.high > maxPrice) maxPrice = candle.high;
        if (candle.low < minPrice) minPrice = candle.low;
        return candle;
    }

    public boolean convertToScreen(int canvasHeight) {

        if (candlesCount == 0)
            return false;

        multiplier = canvasHeight / rangePrice;
        ScreenCandle.Init(canvasHeight,minPrice,multiplier);
        screenCandles = new ScreenCandle[candles.length];

        for (int i = 0; i < candlesCount; i++) {
            screenCandles[i] = new ScreenCandle(candles[i]);
        }
        return true;
    }

    public Candle[] getCandles() {
        return candles;
    }

    public ScreenCandle[] getScreenCandles() {
        return screenCandles;
    }

    public void printCandles() {
        for (Candle c : candles) {
            Log.i(c.toString());
        }
    }

    public void printScreenCandles() {
        for (ScreenCandle sc : screenCandles) {
            Log.i(sc.toString());
        }
    }
}


