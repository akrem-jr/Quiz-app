package MainFiles;

import java.io.FileInputStream;
import java.io.InputStream;

class Main implements constants{
    Main(){
        ClassLoader cl = getClass().getClassLoader();
        System.out.println(cl.getResource(""));
    }
    public static void main(String[] args){
        MyFrame myFrame = new MyFrame(title, size, true);
        myFrame.showFrame();




    }

}