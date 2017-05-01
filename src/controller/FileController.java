package controller;

import view.Log;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by apple on 01.05.17.
 */
public class FileController {

    public static final String FILE_NAME = "history.txt";

    public static void writeDump(String[] data) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, false);
            BufferedWriter bufferWriter = new BufferedWriter(writer);

            for (String s : data) {
                bufferWriter.write(s + "\n");
            }
            bufferWriter.close();
        } catch (IOException e1) {
            Log.i(e1.toString());
        }
    }

    public static String[] readDump(){
        try {
            FileReader reader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<String> data = new ArrayList<String>();

            while (bufferedReader.ready()){
                data.add(bufferedReader.readLine());
            }

            bufferedReader.close();
            return data.toArray(new String[data.size()]);

        } catch (IOException e1) {
            Log.i(e1.getMessage());
            return new String[0];
        }

    }
}
