package listItems;
import javax.swing.*;
import java.net.URL;

public class ControllerItems {
    private String ControllerType;
    private String desc;
    private URL icon;

    public ControllerItems(String controllerType, String desc, URL icon) {
        ControllerType = controllerType;
        this.desc = desc;
        this.icon = icon;
    }

    public String getControllerType() {
        return ControllerType;
    }

    public void setControllerType(String controllerType) {
        ControllerType = controllerType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }
}
