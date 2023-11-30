package listItems.renders;

import listItems.ControllerItems;

import javax.swing.*;
import java.awt.*;
import MainFiles.Status;


public class ControllerRender implements ListCellRenderer<ControllerItems> {
    JPanel home[];
    JLabel  ctrlType[];
    JLabel ctrlDesc[];
    JPanel labelHolder[];
    JLabel icon[];
    JPanel both[];
    JPanel separate[];
    BorderLayout borderLayout[];
    Color selected = new Color(25, 31, 72, 255);
    Color notSelected = new Color(232, 232, 232, 145);
    Color textSelected = new Color(255,255,255);
    Color textNotSelected = new Color(0, 136, 255);
    Color descColor = new Color(115, 115, 115, 255);
    Color focus = new Color(255, 237, 237);
    public ControllerRender(int length){
        home = new JPanel[length];
        ctrlType = new JLabel[length];
        ctrlDesc = new JLabel[length];
        labelHolder = new JPanel[length];
        icon = new JLabel[length];
        both = new JPanel[length];
        separate = new JPanel[length];
        borderLayout = new BorderLayout[length];
        for (int i=0; i < home.length; i++){
            home[i] = new JPanel();
            ctrlType[i] = new JLabel();
            ctrlDesc[i] = new JLabel();
            labelHolder[i] = new JPanel();
            icon[i] = new JLabel();
            both[i] = new JPanel();
            separate[i] = new JPanel();
            borderLayout[i] = new BorderLayout(10, 10);
            setView(i);

        }
    }

    private void setView(int index) {

        ctrlDesc[index].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
        ctrlType[index].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        labelHolder[index].setLayout(new BoxLayout(labelHolder[index], BoxLayout.Y_AXIS));
        labelHolder[index].add(ctrlType[index]);labelHolder[index].add(ctrlDesc[index]);
        separate[index].setLayout(new BoxLayout(separate[index], BoxLayout.Y_AXIS));
        separate[index].add(both[index]);
        separate[index].add(new JSeparator(JSeparator.HORIZONTAL));
        both[index].setLayout(new FlowLayout(FlowLayout.LEADING));
        both[index].add(icon[index]);
        both[index].add(labelHolder[index]);

        home[index].setLayout(borderLayout[index]);

        home[index].add(separate[index], BorderLayout.CENTER);

        home[index].setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, ControllerItems value, int index, boolean isSelected, boolean cellHasFocus) {

        ControllerItems items =  value;
        ctrlType[index].setText(items.getControllerType());
        ctrlDesc[index].setText(items.getDesc());
        icon[index].setIcon(new ImageIcon(items.getIcon()));



        if(isSelected){
            both[index].setBackground(selected);
            labelHolder[index].setBackground(selected);
            ctrlType[index].setForeground(textSelected);
            ctrlDesc[index].setForeground(textSelected);

        }else{
            labelHolder[index].setBackground(notSelected);
            both[index].setBackground(notSelected);
            ctrlType[index].setForeground(textNotSelected);
            ctrlDesc[index].setForeground(descColor);
        }

        if (Status.loggedIn){
            if(Status.asWho == Status.USER){
                if(anArrayHas(Status.USER_FUN, items.getControllerType())){
                    home[index].setVisible(false);
                    return home[index];
                }
            } else if(Status.asWho == Status.SCHOOL) {
                if(anArrayHas(Status.SCHOOL_FUN, items.getControllerType())){
                    home[index].setVisible(false);
                    return home[index];
                }
            }
            if(Status.asWho == Status.ADMIN){
                if(anArrayHas(Status.ADMIN_FUN, items.getControllerType())){
                    home[index].setVisible(false);
                    return home[index];
                }
            }
        }else {
            if(anArrayHas(Status.NON_USER_FUN, items.getControllerType())){
                home[index].setVisible(false);
                return home[index];
            }
        }

        return new JPanel();

    }

    private boolean anArrayHas(String array[], String item) {
        for(String s:array){
            if(item == s){
                return true;
            }
        }
        return false;
    }
}
