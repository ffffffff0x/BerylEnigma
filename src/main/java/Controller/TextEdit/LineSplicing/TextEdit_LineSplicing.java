package Controller.TextEdit.LineSplicing;

import java.util.ArrayList;

public class TextEdit_LineSplicing {
    public static String LineSplicing(ArrayList<String> file1, ArrayList<String> file2, String delimiter){
        StringBuilder result = new StringBuilder();
        int lineNum = 0;
        if(file1.size()>file2.size()){
            for (String line:file1) {
                if(lineNum < file2.size()){
                    result.append(line + delimiter + file2.get(lineNum) + "\n");
                    lineNum++;
                }else {
                    result.append(line + delimiter);
                    lineNum++;
                }
            }
        }else{
            for (String line:file2) {
                if(lineNum < file1.size()){
                    result.append(file1.get(lineNum) + delimiter + line + "\n");
                    lineNum++;
                }else {
                    result.append(delimiter + line);
                    lineNum++;
                }
            }
        }
        return result.toString();
    }
}
