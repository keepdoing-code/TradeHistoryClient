import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by apple on 29.04.17.
 */
public class Connection {

    private static final String TRYING_CONNECT = "Trying to connect ";
    private static final String CONNECTED = "Connected!";
    private static final String SEND_REQUEST = "Sending HTTP Request";
    private static final String CONNECTION_CLOSED = "Connection closed...";
    private static final String UNKNOWN_HOST = "Unknown host";
    private static final String IO_ERROR = "I/O error";
    private static final String RECEIVE_DATA = "Receive data";

    private ArrayList<String> receivedData = new ArrayList<String>(100);

    public Connection(Request request) {
        try {
            String host = request.getHost();
            System.out.println(TRYING_CONNECT + host);
            Socket socket = new Socket(host, 80);
            System.out.println(CONNECTED);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(SEND_REQUEST);
            out.println(request.getRequest());

            receivedData = receive(in);
//            receiveData(in, new LinkedList<String>());
//            receiveData(in, receivedData);

            System.out.println(CONNECTION_CLOSED);
            socket.close();

        } catch (UnknownHostException e) {
            System.out.println(UNKNOWN_HOST);
        } catch (IOException e) {
            System.out.println(IO_ERROR);
        }
    }

    private ArrayList<String> receive(BufferedReader in){
        ArrayList<String> data = new ArrayList<String>();
        String str;

        try{
            System.out.println(RECEIVE_DATA);
//            while (true){
//                headerData = in.readLine();
//                if(!headerData.isEmpty()) break;
//            }
            do{
                str = in.readLine();
            }while (!str.isEmpty());

            str = in.readLine();
            int length = Integer.parseInt(str,16);
            System.out.println("Data length:  hex - " + str+"  dec - "+length);

//            while (true){
//                candleData = in.readLine();
//                if(candleData.isEmpty()) break;
//                    data.add(candleData);
//            }

            do{
                str = in.readLine();
                if(!str.isEmpty()) data.add(str);
            }while (!str.isEmpty());

        }catch (Exception e){
            System.out.println(e.toString());
        }

        return data;
    }

//    private void receiveData(BufferedReader in, LinkedList<String> data) {
//        try {
//            System.out.println(RECEIVE_DATA);
//
//            do {
//                data.add(in.readLine());
//            } while (!data.getLast().isEmpty());
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//    }
//
//    public String[] getData() {
//        String[] data = new String[receivedData.size()];
//
//        for (int i = 0; i < receivedData.size(); i++) {
//            data[i] = receivedData.get(i);
//        }
//        return data;
//    }

    public String[] getData(){
        return receivedData.toArray(new String[receivedData.size()]);
    }

    public Candle[] getCandles(){
        ArrayList<Candle> candlesArray = new ArrayList<Candle>(100);
        for(String s:receivedData){
            candlesArray.add(new Candle(s));
        }

        return candlesArray.toArray(new Candle[candlesArray.size()]);
    }

}
