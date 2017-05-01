package controller;

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
    private static final String STRINGS_RECEIVED = " strings received";

    private ArrayList<String> dataArray = new ArrayList<String>();
    private int dataCounter = 0;
    private int dataLength = 0;


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

            receiveHeader(in);
            receiveData(in);

            Log.i(CONNECTION_CLOSED);
            socket.close();

            Log.i(DATA_LENGTH + dataLength + BYTES);
            Log.i(dataCounter + STRINGS_RECEIVED);

        } catch (UnknownHostException e) {
            Log.i(UNKNOWN_HOST);
        } catch (IOException e) {
            Log.i(IO_ERROR);
        }
    }

    private void receiveHeader(BufferedReader in) {
        ArrayList<String> header = new ArrayList<String>();
        String str;
        Log.i(RECEIVE_HEADER);

        try {
            do {
                str = in.readLine();
                header.add(str);
            } while (!str.isEmpty());

            dataLength = Integer.parseInt(in.readLine(), 16);
        } catch (Exception e) {
            Log.i(e.toString());
        }
    }

    private void receiveData(BufferedReader in) {
        String str;

        try {
            Log.i(RECEIVE_DATA);

            do {
                str = in.readLine();
                if (!str.isEmpty()) {
                    dataCounter++;
                    dataArray.add(str);
                }
            } while (!str.isEmpty());

        } catch (Exception e) {
            Log.i(e.toString());
        }
    }

    public ArrayList<String> getDataList() {
        return dataArray;
    }

    public String[] getData() {
        return dataArray.toArray(new String[dataArray.size()]);
    }

    public int getDataLength() {
        return dataLength;
    }

    public int getDataCounter() {
        return dataCounter;
    }
}
