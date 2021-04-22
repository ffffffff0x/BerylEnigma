package ffffffff0x.beryenigma.App.View.Viewobj;

import java.io.File;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 13:51
 **/

public class ViewDataObject {
    public String sourceMessage;
    public String[] sourceMessageList;
    public File sourceFile;
    public String delimiter;

    public String getSourceMessage() {
        return sourceMessage;
    }

    public void setSourceMessage(String sourceMessage) {
        this.sourceMessage = sourceMessage;
    }

    public String[] getSourceMessageList() {
        return sourceMessageList;
    }

    public void setSourceMessageList(String[] sourceMessageList) {
        this.sourceMessageList = sourceMessageList;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
