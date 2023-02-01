package ffffffff0x.beryenigma.App.View.Root;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-01 14:26
 **/

public class ViewNodeInfo {
    String name;
    String folderPath;
    String fxmlPath;

    public ViewNodeInfo(String name, String folderPath, String fxmlPath) {
        this.name = name;
        this.folderPath = folderPath;
        this.fxmlPath = fxmlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    @Override
    public String toString() {
        return "ViewNodeInfo{" +
                "name='" + name + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", fxmlPath='" + fxmlPath + '\'' +
                '}';
    }
}
