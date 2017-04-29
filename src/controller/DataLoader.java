package controller;

import model.Candle;
import model.Request;

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
public class DataLoader {

    private static final String TRYING_CONNECT = "Trying to connect ";
    private static final String CONNECTED = "Connected";
    private static final String SEND_REQUEST = "Sending HTTP Request";
    private static final String CONNECTION_CLOSED = "Connection closed...";
    private static final String UNKNOWN_HOST = "Unknown host";
    private static final String IO_ERROR = "I/O error";
    private static final String RECEIVE_DATA = "Receive data";
    private static final String RECEIVE_HEADER = "Receive header";

    private ArrayList<String> dataArray = new ArrayList<String>(100);

    public DataLoader(Request request) {
        try {
            String host = request.getHost();
            log(TRYING_CONNECT + host);
            Socket socket = new Socket(host, 80);
            log(CONNECTED);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            log(SEND_REQUEST);
            out.println(request.getRequest());

            receiveHeader(in);
            dataArray = receiveData(in);

            log(CONNECTION_CLOSED);
            socket.close();

        } catch (UnknownHostException e) {
            log(UNKNOWN_HOST);
        } catch (IOException e) {
            log(IO_ERROR);
        }
    }

    private int receiveHeader(BufferedReader in) {
        ArrayList<String> header = new ArrayList<String>();
        String str;
        int length = 0;

        try {
            log(RECEIVE_HEADER);

            do {
                str = in.readLine();
                header.add(str);
            } while (!str.isEmpty());

            length = Integer.parseInt(in.readLine(), 16);
            header.add("Data length: " + length + " bytes");

        } catch (Exception e) {
            log(e.toString());
        }
        return length;
    }

    private ArrayList<String> receiveData(BufferedReader in){
        ArrayList<String> data = new ArrayList<String>();
        String str;

        try {
            log(RECEIVE_DATA);

            do {
                str = in.readLine();
                if (!str.isEmpty()) data.add(str);
            } while (!str.isEmpty());

        } catch (Exception e) {
            log(e.toString());
        }

        return data;
    }

    private ArrayList<String> receiveAll(BufferedReader in) {
        ArrayList<String> data = new ArrayList<String>();
        String str;

        try {
            log(RECEIVE_DATA);

            do {
                str = in.readLine();
                log(str);
            } while (!str.isEmpty());

            int length = Integer.parseInt(in.readLine(), 16);
            log("Data length: " + length + " bytes");


            //TODO processing many chunks
            do {
                str = in.readLine();
                if (!str.isEmpty()) data.add(str);
            } while (!str.isEmpty());

        } catch (Exception e) {
            log(e.toString());
        }

        return data;
    }

    public String[] getData() {
        return dataArray.toArray(new String[dataArray.size()]);
    }

    public Candle[] getCandles() {
        ArrayList<Candle> candlesArray = new ArrayList<Candle>(100);
        for (String s : dataArray) {
            candlesArray.add(new Candle(s));
        }

        return candlesArray.toArray(new Candle[candlesArray.size()]);
        //TODO exception handling
    }

    private void log(String msg){
        System.out.println(msg);
    }


}
