package Kit.Utils;

import javax.swing.text.View;
import java.awt.*;
import java.io.*;

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

    public static void outPutFile(String out,String charset){
        File file = ViewUtils.saveTextFile();
        if(file==null) {
            return;
        }else{
            try {
                OutputStreamWriter OSW = new OutputStreamWriter(new FileOutputStream(file), charset);
                OSW.write(out);
                OSW.flush();
                OSW.close();
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
