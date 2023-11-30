package FileExceptions;

import java.io.File;

public class FileExceptions{
    public static void fileExist(String url) throws MyFileException{
        File file = new File(url);
        if (!file.exists()){
            throw new MyFileException("The File Doesn't Exist");
        }
    }
}
