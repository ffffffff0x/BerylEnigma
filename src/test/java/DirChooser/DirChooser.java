package DirChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2024-02-23 14:04
 **/

public class DirChooser {
    public static void main(String[] args) {
        File file = new File("D:\\SteamLibrary\\steamapps");
        List<File> fileo = new ArrayList<>();
        dirChooser(fileo,file);

        for (File f:fileo) {
            System.out.println(f);
        }
    }

    public static void dirChooser(List<File> Fileo,File file) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                dirChooser(Fileo,file1);
            }
            if (file1.isFile()) {
                Fileo.add(file1);
//                System.out.println(file1);
            }
        }
    }
}
