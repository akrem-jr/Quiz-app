package MainFiles;

import javax.swing.*;
import java.awt.*;

public class ButtonEvents {
    private static Color btnSteady = new Color(64, 104, 224);

    private static Color btnFocus = new Color(74, 121, 255);
    private static Color btnClicked = new Color(33, 69, 175);
    //private static Color btnClickedBorder = new Color(108, 183, 255);

    public static void clicked(JButton btn){
        btn.setBackground(btnClicked);
        btn.setBorder(BorderFactory.createLineBorder(btnClicked, 1));
        btn.setForeground(Color.WHITE);
    }
    public static void clicked(JPanel btn, JLabel label){
        btn.setBackground(btnClicked);
        btn.setBorder(BorderFactory.createLineBorder(btnClicked, 1));
        label.setForeground(Color.WHITE);
    }
    public static void steady(JButton btn){
        btn.setBackground(btnSteady);
        btn.setBorder(BorderFactory.createLineBorder(btnSteady, 1));
        btn.setForeground(Color.WHITE);
    }
    public static void steady(JPanel btn, JLabel label){
        btn.setBackground(btnSteady);
        btn.setBorder(BorderFactory.createLineBorder(btnSteady, 1));
        label.setForeground(Color.WHITE);
    }
    public static void focused(JButton btn){
        btn.setBackground(btnFocus);
        btn.setBorder(BorderFactory.createLineBorder(btnFocus, 1));
        btn.setForeground(Color.WHITE);
    }
    public static void focused(JPanel btn, JLabel label){
        btn.setBackground(btnFocus);
        btn.setBorder(BorderFactory.createLineBorder(btnFocus, 1));
        label.setForeground(Color.WHITE);
    }
}
