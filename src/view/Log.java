package view;

/**
 * Created by apple on 01.05.17.
 */
public class Log {
    public static void i(String msg){
        System.out.println(msg);
    }

    public static void a(String[] msg){
        for(String s:msg){
            System.out.println(s);
        }
    }

    public static void d(int digit){
        System.out.println(digit);
    }
}
