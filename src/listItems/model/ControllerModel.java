package listItems.model;
import listItems.ControllerItems;

import javax.swing.*;

public class ControllerModel extends DefaultListModel {

    public void addItem(ControllerItems items){
        addElement(items);

    }
}
