package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-08-14 15:30
 **/

public class FileHeadCheckerImpl {
    // 获取文件类型
    public static FileHeaderBean getFileType(String filePath,HashMap<String,FileHeaderBean> fileTypes) {
        FileHeaderBean fileHeaderBean = fileTypes.get(getFileHeader(filePath));
        if (fileHeaderBean != null) {
            return fileHeaderBean;
        }else {
            return new FileHeaderBean("UNKNOWN",getFileHeader(filePath),"NULL");
        }
    }

    // 根据文件路径获取文件头信息
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    // 根据文件流获取文件头信息
    public static String getFileHeaderByFileInputStream(FileInputStream is) {
        String value = null;
        try {
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    // 将要读取文件头信息的文件的byte数组转换成string类型表示
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        //System.out.println(builder.toString());
        return builder.toString();
    }


}
