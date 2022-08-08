package test;

import java.io.File;
import java.io.IOException;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-05 11:30
 **/

public class configTest {
    public static void main(String[] args) {
        File file = new File("d:\\Temp\\test\\123.txt");
        File folder = new File("d:\\Temp\\test\\tt");

        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            System.out.println("创建文件夹");
        } else {
            System.out.println("文件夹已存在");
        }


    }

}
