package MainFiles;

import FileExceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//create the log in dialog
public class LogIn extends JFrame implements KeyListener {
    private int size[] = {400,250};
    private JTextField field[] = new JTextField[2];
    private JButton click = new JButton("Log In");
    JLabel label[] = new JLabel[2];
    private String file;
    private char as;
    private MyFrame frame;
    LogIn(String title, String file, char as){

        super(title);
        this.file = file;
        File f = new File(file);
        /// here we implement Custom exception to handle if the file exist



        setTitle(title);
        setSize(size[0], size[1]);
        setResizable(false);
        ButtonEvents.steady(click);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logIn();
        center();

    }
    public void setFrame(MyFrame frame){
        this.frame = frame;
    }
    public void logIn(){
        arrangeItems();



    }

    private void arrangeItems() {


        setLayout(new BorderLayout());

        String label_string[] = {"User Name", "Password"};
        JPanel allHolder = new JPanel();
        JPanel textAndField = new JPanel();
        JPanel buttonHolder = new JPanel();
        for (int i=0; i < label_string.length; i++){
            label[i] = new JLabel(label_string[i]);
            field[i] = new JTextField();
            textAndField.add(label[i]);
            textAndField.add(field[i]);
        }
        buttonHolder.add(click);
        allHolder.add(textAndField);
        allHolder.add(buttonHolder);
        textAndField.setLayout(new GridLayout(label_string.length,2));
        buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        allHolder.setLayout(new BoxLayout(allHolder, BoxLayout.Y_AXIS));
        allHolder.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        click.setBorder(BorderFactory.createEmptyBorder(10,20, 10,20));
        add(allHolder, BorderLayout.CENTER);
        click.setEnabled(false);
    }



    public void showFrame(){
        setVisible(true);
    }
    public void center(){
        setLocationRelativeTo(null);
    }
    public void checkAvailable(){


    }

    @Override
    public void keyTyped(KeyEvent e) {
        checkAvailable();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        checkAvailable();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        checkAvailable();
    }


}
