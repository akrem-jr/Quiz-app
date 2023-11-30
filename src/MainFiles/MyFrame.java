package MainFiles;
import FileExceptions.FileExceptions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import FileExceptions.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;


public class MyFrame extends JFrame implements constants{
    private int size[];
    private static int viewType = -1;

    private Register registerAsUser = new Register("Register", USER_FILE, USER);
    private Register registerAsSchool = new Register("Register", SCHOOL_FILE_REQ, SCHOOL);
    private CardLayout layout;
    private JPanel rightHolder;
    private JPanel leftHolder;

    MyFrame(String title,int size[]){
        super(title);
        this.size = size;
        setTitle(title);
        setSize(size[0], size[1]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
    }
    MyFrame(String title,int size[], boolean resizable){
        super(title);
        setTitle(title);
        this.size = size;
        setSize(size[0], size[1]);
        createSplitter();
        setResizable(resizable);
        center();
        setAllFiles();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAllFiles() {

        try {
            FileExceptions.fileExist(ADMIN_FILE);
        } catch (MyFileException e) {
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(ADMIN_FILE));
                write.write("solution"+","+"solution"+","+"solution");
                write.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void createSplitter() {
        JPanel navBar = new JPanel();
        //add nav bar conent here
        handleNav(navBar);
        // wee split the window into two we also may need to rearrange it
        JSplitPane homeSplitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //Left part of the splitter
        leftHolder = new JPanel();
        homeSplitter.setDividerSize(5);
        homeSplitter.setEnabled(true);
        //riht part of the splitter
        rightHolder = new JPanel();
        // we create all the content that goes in the left panel inside the following function
        handleLeft();
        //add the elements on left to sclor pane
        JScrollPane leftScroller = new JScrollPane();
        leftScroller.add(leftHolder);
        homeSplitter.add(leftHolder, JSplitPane.LEFT);

        // we create all the content that goes in the right panel inside the following function
        handleRight();
        homeSplitter.add(rightHolder, JSplitPane.RIGHT);
        setLayout(new BorderLayout());
        add(homeSplitter, BorderLayout.CENTER);
        add(navBar, BorderLayout.NORTH);
    }

    private void handleNav(JPanel navBar) {
        // this create a single item to be added to the nav
        int itemsNumber = NAV_ITEMS.length;
        JPanel items[] = new JPanel[itemsNumber];
        for(int i=0; i < itemsNumber; i++){
            items[i] = createSingleItem(NAV_ITEMS[i],NAV_ICONS[i]);
            navBar.add(items[i]);
        }
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));

    }

    /// create a single nav bar item containing the in a single panel
    private JPanel createSingleItem(String itemText, String itemUrl) {
        JPanel item = new JPanel();
        JLabel iText = new JLabel(itemText);
        JLabel iIcon = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource(itemUrl)));
        item.add(iIcon);
        item.add(iText);
        item.setLayout(new BoxLayout(item,BoxLayout.Y_AXIS));
        item.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (registerAsUser.isShowing() || registerAsSchool.isShowing())
                    return;
                if(Status.loggedIn)
                    return;
                item.setBackground(clickedBtn);
                iText.setForeground(Color.WHITE);
                if(itemText == Status.NAV_ITEMS[0]){
                    layout.show(rightHolder, SCHOOL_LOG_IN);
                }
                if(itemText == Status.NAV_ITEMS[1]){
                    registerAsSchool.showFrame();
                }
                if(itemText == Status.NAV_ITEMS[2]){
                    layout.show(rightHolder, ADMIN_LOG_IN);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                item.setBackground(backgroundColor);
                iText.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(selectedBtn);
                iText.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(backgroundColor);
                iText.setForeground(Color.BLACK);

            }
        });
        item.setBorder(new EmptyBorder(5,5,5,5));
        return  item;
    }


    private void handleRight() {
        //The Card Layout that changes throught out the program is set here
        layout = new CardLayout();
        rightHolder.setLayout(layout);
        //The first panel to be show after any user registration and login
        JPanel empty = createEmptyPanel();
        JPanel logIn = createLogIn(USER_FILE, USER);
        JPanel adminLogIn = createLogIn(ADMIN_FILE, ADMIN);
        JPanel schoolLogin = createLogIn(SCHOOL_FILE, SCHOOL);
        rightHolder.add(schoolLogin, SCHOOL_LOG_IN);
        JPanel quiz = createQuizPanel(QUIZ_FILE);
        JPanel viewRequest = createAdminRequest();
        JPanel panel = createPanel();
        JPanel addQuestions = createAddQuestion(QUIZ_FILE);
        rightHolder.add(addQuestions, ADD_QUESTION);
        rightHolder.add(quiz, QUIZ);
        rightHolder.add(panel, READY_FOR_QUIZ);
        rightHolder.add(viewRequest, ADMIN_REQ);
        rightHolder.add(logIn, LOOG_IN);
        rightHolder.add(adminLogIn, ADMIN_LOG_IN);
        rightHolder.add(empty, NONE_PAGE);
        rightHolder.setLayout(layout);
        windowUpdate();
    }

    private JPanel createAddQuestion(String quizFile) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        String label_string[] = {"Question", "Choose TWo", "Choose Three", "Choose Four", "Aswer"};
        JTextField field[] = new JTextField[label_string.length];
        JLabel label[] = new JLabel[label_string.length];
        JButton click = new JButton("Log In");
        JPanel allHolder = new JPanel();
        JPanel textAndField = new JPanel();
        JPanel buttonHolder = new JPanel();
        ButtonEvents.steady(click);
        for (int i=0; i < label_string.length; i++){
            label[i] = new JLabel(label_string[i]);
            field[i] = new JTextField();
            textAndField.add(label[i]);
            textAndField.add(field[i]);
                field[i].setPreferredSize(new Dimension(150,30));

        }



        buttonHolder.add(click);
        allHolder.add(textAndField);
        allHolder.add(buttonHolder);
        textAndField.setLayout(new GridLayout(label_string.length,2, 10,10));
        buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        allHolder.setLayout(new BoxLayout(allHolder, BoxLayout.Y_AXIS));
        allHolder.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        click.setBorder(BorderFactory.createEmptyBorder(10,20, 10,20));
        panel.add(allHolder);
        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<label_string.length;i++){
                    if(field[i].getText().equals(""))
                        ShowDialog.showMessage(MyFrame.this, "Blank place Error", "You Cant leave the input field\nbank", ShowDialog.ERROR);
                        return;
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(QUIZ_FILE, true) );
                    String writeSomeHow = "";
                    for (int i=0;i<label_string.length;i++){
                        if(i == label_string.length -1){
                            writeSomeHow += field[i].getText().charAt(0);
                        }else
                            writeSomeHow += field[i].getText();
                            writeSomeHow += ",";

                    }
                    writer.write(writeSomeHow);
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        return panel;
    }

    private JPanel createQuizPanel(String file) {

        JPanel allHolder = new JPanel();
        allHolder.setLayout(new GridLayout(1,1));
        // Create the radio button with the label
        // Create a panel for the options
        Quizes quizes[] = Quizes.readFromFile(file);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        for(Quizes q:quizes){
            JPanel panel = addSingleQuestion(q);
            panel2.add(panel);
        }

        JButton finish = new JButton("Finish");
        ButtonEvents.steady(finish);
        finish.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        panel2.add(finish);


        JScrollPane scrollPane = new JScrollPane(panel2);
        allHolder.add(scrollPane);
        return allHolder;
    }

    private JPanel addSingleQuestion(Quizes q) {
        JLabel label = new JLabel(q.getQuestion());

        // Create the radio button with the label
        // Create a panel for the options
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        // Create the radio button options A, B, C, D
        JRadioButton option[] = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        // Add the radio buttons to the options panel
        panel2.add(label);
        for (int i=0;i < 4; i++){
            switch (i){
                case 0:
                    option[i] = new JRadioButton(q.getChooseA());
                    break;

                    case 1:
                        option[i] = new JRadioButton(q.getChooseB());
                    break;
                case 2:
                    option[i] = new JRadioButton(q.getChooseC());
                    break;
                case 3:
                    option[i] = new JRadioButton(q.getChooseD());
                    break;
            }

            buttonGroup.add(option[i]);
            panel2.add(option[i]);
            panel2.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        }
        System.out.println(buttonGroup.getSelection());
        panel2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        return panel2;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        JButton btn = new JButton("Start Quiz");
        ButtonEvents.steady(btn);
        panel.add(btn);
        btn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(rightHolder, QUIZ);
            }
        });
        return  panel;
    }

    public void windowUpdate(){
        if(!Status.loggedIn){
            layout.show(rightHolder, NONE_PAGE);
        }else {
            if(Status.asWho == ADMIN){
                layout.show(rightHolder, ADMIN_REQ);
            }
            if(Status.asWho == USER){
                layout.show(rightHolder, READY_FOR_QUIZ);
            }
            if(Status.asWho==SCHOOL){
                layout.show(rightHolder, ADD_QUESTION);
            }
        }
    }
    private JPanel createLogIn(String file, char as) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        String label_string[] = {"User Name", "Password"};
        JTextField field[] = new JTextField[label_string.length];
        JLabel label[] = new JLabel[label_string.length];
        JButton click = new JButton("Log In");
        JPanel allHolder = new JPanel();
        JPanel textAndField = new JPanel();
        JPanel buttonHolder = new JPanel();
        ButtonEvents.steady(click);
        for (int i=0; i < label_string.length; i++){
            label[i] = new JLabel(label_string[i]);
            field[i] = new JTextField();
            textAndField.add(label[i]);
            textAndField.add(field[i]);
            field[i].setPreferredSize(new Dimension(150,30));
            field[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if(field[0].getText().length() >= 5 && field[1].getText().length() >= 5)
                        click.setEnabled(true);
                    else
                        click.setEnabled(false);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(field[0].getText().length() >= 5 && field[1].getText().length() >= 5)
                        click.setEnabled(true);
                    else
                        click.setEnabled(false);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if(field[0].getText().length() >= 5 && field[1].getText().length() >= 5)
                        click.setEnabled(true);
                    else
                        click.setEnabled(false);
                }
            });
        }
        JLabel iconT = new JLabel(as == USER ? "User" : as == SCHOOL ? "School":"Admin");
        iconT.setIcon(as == USER ? new ImageIcon(this.getClass().getClassLoader().getResource(ICON_PATH[0])):
                as == SCHOOL ? new ImageIcon(this.getClass().getClassLoader().getResource(NAV_ICONS[0])):new ImageIcon(this.getClass().getClassLoader().getResource(NAV_ICONS[2])));
        iconT.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        allHolder.add(iconT);

        buttonHolder.add(click);
        allHolder.add(textAndField);
        allHolder.add(buttonHolder);
        textAndField.setLayout(new GridLayout(label_string.length,2, 10,10));
        buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        allHolder.setLayout(new BoxLayout(allHolder, BoxLayout.Y_AXIS));
        allHolder.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        click.setBorder(BorderFactory.createEmptyBorder(10,20, 10,20));
        panel.add(allHolder);
        click.setEnabled(false);


        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    checkDatabase(file, field[0].getText(), field[1].getText(), as);
                    field[0].setText("");
                    field[1].setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return panel;
    }
    private void checkDatabase(String file, String username, String password, char as) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader(file));
        String adminInfo = "";
        int j = 0;
        while ((j=read.read())!=-1){
            adminInfo += (char)j;
        }
        String array[] = adminInfo.split("\n");
        for(String s: array){
            if(username.equals(s.split(",")[1])){
                if(password.equals(s.split(",")[2])){
                    Status.loggedIn = true;
                    Status.asWho = as;
                    handleLeft();
                    windowUpdate();
                    Status.username = username;
                    return;
                }
            }
        }
        ShowDialog.showMessage(this, "No Such User", "Please check Your inputs again", ShowDialog.ERROR);

    }
    private JPanel createEmptyPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please Log in Or Create Account");
        panel.add(label);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        label.setForeground(Color.red);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        return panel;
    }

    private JPanel createAdminRequest() {
        JPanel panel = new JPanel();
        String all = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SCHOOL_FILE_REQ));
            int j = 0;
            while ((j=reader.read())!=-1){
                all += (char) j;
            }
            reader.close();
            String lines[] = all.split("\n");
            String array_separate[][] = new String[lines.length][3];
            for(int i=0; i < lines.length; i++){
                if (lines.length < 2)
                    continue;
                array_separate[i] = lines[i].split(",");
                JPanel row = rowItem( array_separate[i]);
                panel.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JPanel rowItem( String[] strings) {
        if(strings.length < 1)
            return new JPanel();
        JPanel holder = new JPanel();
        JLabel label = new JLabel(strings[1]);
        JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(ICON_PATH[0])));
        holder.add(label1);
        holder.add(label);
        label.setBorder(BorderFactory.createEmptyBorder(5,30,5, 30));
        holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));
        JButton btn = new JButton("Approve");
        ButtonEvents.steady(btn);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        holder.add(btn);
        holder.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        holder.setBackground(backgroundColor);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(SCHOOL_FILE, true));
                    writer.write(strings[0]+","+strings[1]+","+strings[2]+"\n");
                    writer.close();

                    BufferedReader file = new BufferedReader(new FileReader(SCHOOL_FILE_REQ));
                    int i =0;
                    String allTogether = "";
                    while ((i=file.read())!=-1){
                        allTogether += (char) i;
                    }
                    String lines[] = allTogether.split("\n");
                    file.close();
                    BufferedWriter writeAgain = new BufferedWriter(new FileWriter(SCHOOL_FILE_REQ));
                    holder.removeAll();
                    for(String s:lines){
                        if (!s.equals(strings[0]+","+strings[1]+","+strings[2])){
                            writeAgain.write(s+"\n");
                            rowItem(s.split(","));
                        }
                    }
                    writeAgain.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        return holder;
    }

    public void handleLeft() {
        leftHolder.removeAll();
        for(int i =0; i < CONTROLLER_TYPE.length; i++){
            JPanel panel = createControlItem(CONTROLLER_TYPE[i], CONTROLLER_DESC[i],
                    this.getClass().getClassLoader().getResource(ICON_PATH[i]));
            if(panel == null)
                continue;
            leftHolder.add(panel);
        }
        leftHolder.setLayout(new BoxLayout(leftHolder, BoxLayout.Y_AXIS));
    }

    private JPanel createControlItem(String controllerType, String controllerDesc, URL icon) {
        JPanel panel = new JPanel();
        JPanel horizntal = new JPanel();
        JPanel vertical = new JPanel();
        vertical.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel itemIcon = new JLabel();
        itemIcon.setIcon(new ImageIcon(icon));
        JLabel type = new JLabel(controllerType);
        JLabel desc = new JLabel(controllerDesc);
        desc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        vertical.add(type);
        vertical.add(desc);
        horizntal.add(itemIcon);
        horizntal.add(vertical);
        horizntal.setAlignmentX(Component.LEFT_ALIGNMENT);
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        horizntal.setLayout(new BoxLayout(horizntal, BoxLayout.X_AXIS));
        horizntal.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setLayout(new BorderLayout());
        panel.add(horizntal);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowClicked(controllerType);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(clickedBtn);
                vertical.setBackground(clickedBtn);
                horizntal.setBackground(clickedBtn);
                type.setForeground(Color.white);
                desc.setForeground(Color.white);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setBackground(backgroundColor);
                vertical.setBackground(backgroundColor);
                horizntal.setBackground(backgroundColor);
                type.setForeground(Color.BLACK);
                desc.setForeground(Color.BLACK);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(selectedBtn);
                vertical.setBackground(selectedBtn);
                horizntal.setBackground(selectedBtn);
                type.setForeground(Color.WHITE);
                desc.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(backgroundColor);
                vertical.setBackground(backgroundColor);
                horizntal.setBackground(backgroundColor);
                type.setForeground(Color.BLACK);
                desc.setForeground(Color.BLACK);
            }
        });

        if (Status.loggedIn){
            if(Status.asWho == Status.USER){
                if(anArrayHas(Status.USER_FUN, controllerType)){
                    return panel;
                }
            } else if(Status.asWho == Status.SCHOOL) {
                if(anArrayHas(Status.SCHOOL_FUN, controllerType)){
                    return panel;
                }
            }
            if(Status.asWho == Status.ADMIN){
                if(anArrayHas(Status.ADMIN_FUN, controllerType)){
                    return panel;
                }
            }
        }else {
            if(anArrayHas(Status.NON_USER_FUN, controllerType)){
                return panel;
            }
        }

        return null;
    }
    private boolean anArrayHas(String array[], String item) {
        for(String s:array){
            if(item == s){
                return true;
            }
        }
        return false;
    }
    private void rowClicked(String controllerType) {
        if(Status.loggedIn){
            if(controllerType == CONTROLLER_TYPE[7]){
                Status.asWho = NONE;
                Status.loggedIn = false;
                handleLeft();
                windowUpdate();
                return;
            }
            if(Status.asWho == USER){

            }



        }else{
            if (!registerAsUser.isShowing()) {
                if (controllerType == CONTROLLER_TYPE[0]) {
                    layout.show(rightHolder, LOOG_IN);
                }
                if (controllerType == CONTROLLER_TYPE[1]) {
                    registerAsUser.showFrame();
                }
            }
        }
    }

    public void showFrame(){
        setVisible(true);

    }
    public void center(){
        setLocationRelativeTo(null);
    }
}
