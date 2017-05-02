package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by apple on 02.05.17.
 */
public class Actions {
    ActionListener alButtonCandles = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    ActionListener alButtonLines = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    public static final ActionListener alButtonExit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
}
