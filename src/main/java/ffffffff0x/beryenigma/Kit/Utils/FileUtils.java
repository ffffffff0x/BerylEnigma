package ffffffff0x.beryenigma.Kit.Utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
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
     * 获取文本内容
     * @param file
     * @return
     */
    public static String getFileString(File file){
        StringBuilder result = new StringBuilder();
        try {
            if (file != null) {
                FileInputStream inputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String str;
                while((str = bufferedReader.readLine()) != null)
                {
                    result.append(str);
                }
                inputStream.close();
                bufferedReader.close();
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
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

    /**
     * 将FX的Image对象转换为文件
     * @param image JAVAFX 的Image对象
     */
    public static void outPutImgFile(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();
        byte[] buffer = new byte[width * height * 4];
        WritablePixelFormat<ByteBuffer> format = PixelFormat.getByteBgraInstance();
        reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ViewUtils.fileChooser()));
            for (int count = 0; count < buffer.length; count += 4) {
                out.write(buffer[count + 2]);
                out.write(buffer[count + 1]);
                out.write(buffer[count]);
                out.write(buffer[count + 3]);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件扩展名/后缀名
     * @param file 传入文件对象
     * @return 文件后缀名
     */
    public static String getFileFormatName(File file) {
        if (file != null) {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return null;
    }

    /**
     * 检查文件是否是图片
     *
     * @param filePath 文件路径
     * @return boolean
     * @throws IOException 文件异常
     */
    public static boolean isImage(String filePath) throws IOException {
        BufferedImage image;
        image = ImageIO.read(new File(filePath));
        return image != null && image.getWidth() > 0 && image.getHeight() > 0;
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath 文件路径
     * @return boolean
     */
    public static boolean checkFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 检查文件是否存在
     *
     * @param file 文件对象
     * @return boolean
     */
    public static boolean checkFileExist(File file) {
        return file.exists();
    }

    /**
     * 检查文件夹是否存在，如果不存在就创建文件夹
     *
     * @param folderPath 文件夹路径
     * @return boolean
     */
    public static boolean checkFolderExist(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            return folder.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 使用默认程序打开特定类型的文件
     *
     * @param filePath 文件路径
     */
    public static void openFile(String filePath) {
        // 创建文件对象
        File fileToOpen = new File(filePath);

        // 检查平台是否支持 Desktop 类
        OpenFileWithDefaultProgram(fileToOpen);
    }

    /**
     * 使用默认程序打开特定类型的文件
     *
     * @param fileToOpen 文件对象
     */
    public static void openFile(File fileToOpen) {
        // 检查平台是否支持 Desktop 类
        OpenFileWithDefaultProgram(fileToOpen);
    }

    private static void OpenFileWithDefaultProgram(File fileToOpen) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            // 判断文件是否存在
            if (fileToOpen.exists()) {
                try {
                    // 打开文件
                    desktop.open(fileToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File does not exist.");
            }
        } else {
            System.out.println("Desktop is not supported on this platform.");
        }
    }

    /**
     * 重命名文件
     *
     * @param oldFilePath 旧文件路径
     * @param newFileName 新文件名称
     * @return boolean
     */
    public static boolean renameFile(String oldFilePath, String newFileName) {
        File oldFile = new File(oldFilePath);
        File newFile = new File(oldFile.getParent(), newFileName);

        return oldFile.renameTo(newFile);
    }

    /**
     * 保存 InputStream 置文件系统
     *
     * @param inputStream InputStream
     * @param targetPath 保存目标路径
     * @throws IOException IOException
     */
    public static void saveInputStream(InputStream inputStream, String targetPath) throws IOException {
        File targetFile = new File(targetPath);

        // 检查目标文件所在的目录是否存在，如果不存在则创建
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        // 将 InputStream 写入目标文件
        try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
