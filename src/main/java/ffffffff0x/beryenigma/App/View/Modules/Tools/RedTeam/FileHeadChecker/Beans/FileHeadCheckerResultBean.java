package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker.Beans;

/**
 * @author: RyuZUSUNC
 * @create: 2023/8/19 15:21
 **/
public class FileHeadCheckerResultBean extends FileHeaderBean {
    // 文件路径
    public String filePath;
    // 文件名称
    public String fileName;

    public FileHeadCheckerResultBean(String extensionName, String fileHeaderHEX, String fileDescription, String filePath, String fileName) {
        super(extensionName, fileHeaderHEX, fileDescription);
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public FileHeadCheckerResultBean(FileHeaderBean fileHeaderBean) {
        super(fileHeaderBean.ExtensionName, fileHeaderBean.FileHeaderHEX, fileHeaderBean.FileDescription);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileHeadCheckerResultBean{" +
                "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", ExtensionName='" + ExtensionName + '\'' +
                ", FileHeaderHEX='" + FileHeaderHEX + '\'' +
                ", FileDescription='" + FileDescription + '\'' +
                '}';
    }
}
