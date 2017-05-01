package controller;

import model.Candle;
import model.Request;
import view.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;


/**
 * Created by apple on 29.04.17.
 */
public class LoadData {

    private static final String TRYING_CONNECT = "Trying to connect ";
    private static final String CONNECTED = "Connected";
    private static final String SEND_REQUEST = "Sending HTTP Request";
    private static final String CONNECTION_CLOSED = "Connection closed...";
    private static final String UNKNOWN_HOST = "Unknown host";
    private static final String IO_ERROR = "I/O error";
    private static final String RECEIVE_DATA = "Receive data";
    private static final String RECEIVE_HEADER = "Receive header";
    private static final String DATA_LENGTH = "Data length: ";
    private static final String BYTES = " byte(s)";

    private static ArrayList<String> dataArray = new ArrayList<String>();
    private static int dataCounter = 0;
    private static float max;
    private static float min;


    public LoadData(Request request) {
        try {
            String host = request.getHost();
            Log.i(TRYING_CONNECT + host);
            Socket socket = new Socket(host, 80);
            Log.i(CONNECTED);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Log.i(SEND_REQUEST);
            out.println(request.getRequest());

            Integer length = receiveHeader(in);
            Log.i(DATA_LENGTH + length.toString() + BYTES);
            dataArray = receiveData(in);

            Log.i(CONNECTION_CLOSED);
            socket.close();

        } catch (UnknownHostException e) {
            Log.i(UNKNOWN_HOST);
        } catch (IOException e) {
            Log.i(IO_ERROR);
        }
    }

    private int receiveHeader(BufferedReader in) {
        ArrayList<String> header = new ArrayList<String>();
        String str;
        int length = 0;

        try {
            Log.i(RECEIVE_HEADER);

            do {
                str = in.readLine();
                header.add(str);
            } while (!str.isEmpty());

            length = Integer.parseInt(in.readLine(), 16);

        } catch (Exception e) {
            Log.i(e.toString());
        }
        return length;
    }

    private ArrayList<String> receiveData(BufferedReader in){
        ArrayList<String> data = new ArrayList<String>();
        String str;

        try {
            Log.i(RECEIVE_DATA);

            do {
                str = in.readLine();
                if (!str.isEmpty()) {
                    dataCounter++;
                    data.add(str);
                }
            } while (!str.isEmpty());

        } catch (Exception e) {
            Log.i(e.toString());
        }

        return data;
    }

    public Candle[] getCandles() {
        ArrayList<Candle> candlesArray = new ArrayList<Candle>(100);

        if(!dataArray.isEmpty()) {
            Candle firstCandle = new Candle(dataArray.get(0));
            max = firstCandle.getHigh();
            min = firstCandle.getLow();
        } else {
            return null; //TODO throw exception
        }


        for (String s : dataArray) {
            Candle c = new Candle(s);
            if(c.getHigh() > max) max = c.getHigh();
            if(c.getLow() < min) min = c.getLow();
            candlesArray.add(c);
        }

        return candlesArray.toArray(new Candle[candlesArray.size()]);
        //TODO exception handling
    }

    public float getMin() {
        return min;
    }

    public float getRange(){
        return max - min;
    }
}
