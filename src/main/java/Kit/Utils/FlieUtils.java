package Kit.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FlieUtils {
    public static byte[] getFile(File file){
        FileInputStream fileInputStream;
        byte[] result = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = new byte[fileInputStream.available()];
            fileInputStream.read(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }//返回文件的byte[]格式
}
