package MainFiles;

import javax.swing.*;

public class ShowDialog {
    // renaming the messages type so that it will be easy for us to
    // call this class's methods
    public static final int ERROR = JOptionPane.ERROR_MESSAGE;
    public static final int INFO = JOptionPane.INFORMATION_MESSAGE;
    public static final int CANCEL = JOptionPane.CANCEL_OPTION;
    public static final int NOTHING = JOptionPane.PLAIN_MESSAGE;
    public static void showMessage(JFrame frame, String title, String message, int type){
        JOptionPane.showMessageDialog(frame, message, title, type);
    }
    //Show a dialog with message
    public static void showMessage(String title, String message, int type){
        JOptionPane.showMessageDialog(null, message, title, type);
    }
    //Show dialog that accepts input and return the value
    public static String acceptInput(){
        return JOptionPane.showInputDialog(null);
    }
    //Show dialog that accepts input and return the value Has label whit text what
    public static String acceptInput(String what){
        return JOptionPane.showInputDialog(null, what);
    }

    public static String acceptInput(JFrame frame, String what){
        return JOptionPane.showInputDialog(frame, what);
    }
}
