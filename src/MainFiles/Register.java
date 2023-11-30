package MainFiles;


import FileExceptions.FileExceptions;
import FileExceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Register extends JFrame implements KeyListener {

    private JTextField fields[] = new JTextField[3];
    private JLabel label[] = new JLabel[3];
    private JButton registerButton = new JButton("Register");
    private String label_string[] = {"Email", "Username", "Password"};
    String file;
    char as;
    public Register(String title, String file, char as){
        super(title);
        this.as =as;
        this.file = file;
        File f = new File(file);
        if(as==Status.SCHOOL){
            label_string[0] = "School Name";
        }
        /// here we implement Custom exception to handle if the file exist
        try {
            FileExceptions.fileExist(file);
        } catch (MyFileException e) {
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(file));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        setSize(400,250);
        ButtonEvents.steady(registerButton);
        handleClick();


        arrangeItems();
        setLocationRelativeTo(null);
        setResizable(false);

    }
    // here we handle the click events
    private void handleClick() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = fields[0].getText();
                String username = fields[1].getText();
                char[] passwordChars = ((JPasswordField)fields[2]).getPassword();
                String password = new String(passwordChars);

                //if (!(email.endsWith(".com") || email.endsWith(".org") ) && ) {
                if (as != Status.SCHOOL)
                    if(!(email.contains("@") && ((email.endsWith(".com")&&!email.endsWith(".org"))
                    ||((!email.endsWith(".com")&&email.endsWith(".org")))))){
                        ShowDialog.showMessage(Register.this, "Register Error", "Invalid email address. Please enter\n a valid Gmail address.", ShowDialog.ERROR);
                        return;
                    }



                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String userInfo ="";
                    int j = 0;
                    while ((j=reader.read())!=-1){
                        userInfo += (char) j;
                    }
                    String array[] = userInfo.split("\n");

                    reader.close();
                    for(String s:array){
                        if(s.equals(""))
                            continue;
                        if (username.equals(s.split(",")[1])){
                            ShowDialog.showMessage(Register.this,  "Change User Name", "User Name already tooken",ShowDialog.ERROR);
                            return;
                        }

                        if (email.equals(s.split(",")[0])){
                            ShowDialog.showMessage(Register.this,  "Change Email", "Email already tooken",ShowDialog.ERROR);
                            return;
                        }

                    }

                    writeUser(email, username, password);


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    private void writeUser(String email, String username, String password) throws IOException{
        BufferedWriter writer= new BufferedWriter(new FileWriter(file, true));
        writer.write(email+","+username+","+password+"\n");
        writer.close();
        for (int i=0;i< fields.length; i++){
            fields[i].setText("");
        }
        ShowDialog.showMessage(this, "Success", "Registered SuccesFully", JOptionPane.YES_OPTION);
        setVisible(false);
    }

    public void checkAvailable(){

        if(fields[0].getText().length() >= 5 && fields[1].getText().length() >= 5 && fields[2].getText().length() > 5 )

            registerButton.setEnabled(true);
        else
            registerButton.setEnabled(false);
    }
    private void arrangeItems() {


        setLayout(new BorderLayout());


        JPanel allHolder = new JPanel();
        JPanel textAndField = new JPanel();
        JPanel buttonHolder = new JPanel();
        for (int i=0; i < label_string.length; i++){
            label[i] = new JLabel(label_string[i]);
            if(label_string[i].equals(label_string[2])){
                fields[i] = new JPasswordField();
            }else{
                fields[i] = new JTextField();
            }

            textAndField.add(label[i]);
            textAndField.add(fields[i]);
            fields[i].addKeyListener(this);

        }
        buttonHolder.add(registerButton);
        allHolder.add(textAndField);
        allHolder.add(buttonHolder);
        textAndField.setLayout(new GridLayout(label_string.length,2));
        buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        allHolder.setLayout(new BoxLayout(allHolder, BoxLayout.Y_AXIS));
        allHolder.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10,20, 10,20));
        add(allHolder, BorderLayout.CENTER);
        registerButton.setEnabled(false);
    }
    public void showFrame(){
        setVisible(true);
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