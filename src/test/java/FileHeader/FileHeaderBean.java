package FileHeader;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-08-14 16:00
 **/

public class FileHeaderBean {
    // 扩展名
    String ExtensionName;
    // 文件头HEX
    String FileHeaderHEX;
    // 文件详情
    String FileDescription;

    public FileHeaderBean(String extensionName, String fileHeaderHEX, String fileDescription) {
        ExtensionName = extensionName;
        FileHeaderHEX = fileHeaderHEX;
        FileDescription = fileDescription;
    }

    public FileHeaderBean() {
    }

    public String getExtensionName() {
        return ExtensionName;
    }

    public void setExtensionName(String extensionName) {
        ExtensionName = extensionName;
    }

    public String getFileHeaderHEX() {
        return FileHeaderHEX;
    }

    public void setFileHeaderHEX(String fileHeaderHEX) {
        FileHeaderHEX = fileHeaderHEX;
    }

    public String getFileDescription() {
        return FileDescription;
    }

    public void setFileDescription(String fileDescription) {
        FileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "FileHeaderBean{" +
                "ExtensionName='" + ExtensionName + '\'' +
                ", FileHeaderHEX='" + FileHeaderHEX + '\'' +
                ", FileDescription='" + FileDescription + '\'' +
                '}';
    }
}
