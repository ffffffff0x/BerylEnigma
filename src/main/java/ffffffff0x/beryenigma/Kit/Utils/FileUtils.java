package ffffffff0x.beryenigma.Kit.Utils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class FileUtils {
    /**
     * 获取文件的byte数组格式
     * @param file
     * @return
     */
    public static byte[] getFilebyte(File file){
        FileInputStream fileInputStream;
        byte[] result = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = new byte[fileInputStream.available()];
            fileInputStream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按行获取文本
     * @param file
     * @return
     */
    public static ArrayList<String> getFileLines(File file){
        ArrayList<String> result = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str;
            while((str = bufferedReader.readLine()) != null)
            {
               result.add(str);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存文本格式文件至存储
     * @param out
     * @param charset
     */
    public static void outPutFile(String out,String charset){
        File file = ViewUtils.fileChooser();
        if(file!=null) {
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

    /**
     * 保存多个文件至一个文件夹
     * @param map
     * @param charset
     */
    public static void outPutFile(Map<String,String> map,String charset){
        File dir = ViewUtils.directoryChooser();
        OutputStreamWriter OSW;
        if(dir!=null){
            for (Map.Entry<String,String> enrty : map.entrySet()) {
                File file = new File(dir.getPath()+"/"+enrty.getKey());
                try {
                    OSW = new OutputStreamWriter(new FileOutputStream(file), charset);
                    OSW.write(enrty.getValue());
                    OSW.flush();
                    OSW.close();
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存byte数组格式文件至存储
     * @param out
     */
    public static void outPutFile(byte[] out){
        File file = ViewUtils.fileChooser();
        if(file!=null) {
            BufferedOutputStream bos = null;
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 返回载入文件/字符串的按行分割后的ArrayList<String>
     * @param object
     * @return
     */
    public static ArrayList<String> readLine(Object object){
        if(object instanceof File){
            return FileUtils.getFileLines((File)object);
        }else {
            String text = (String)object;
            ArrayList<String> list = new ArrayList<>();
            //把数组转成集合，也就是把数组里面的数据存进集合；
            Collections.addAll(list, text.split("\n"));
            return list;
        }
    }
}
