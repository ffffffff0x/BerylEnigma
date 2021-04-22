package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.AES;

public class AESControlList {
    public String ENCRYPT_MODE = "ECB";
    public String PADDING_MODE = "PKCS5";
    public String OUTPUT_FORMAT = "HEX";
    public String TEXT_ENCODING = "UTF-8";
    public String KEY_FORMAT = "Text";

    @Override
    public String toString() {
        return "AESControlList{" +
                "ENCRYPT_MODE='" + ENCRYPT_MODE + '\'' +
                ", PADDING_MODE='" + PADDING_MODE + '\'' +
                ", OUTPUT_FORMAT='" + OUTPUT_FORMAT + '\'' +
                ", TEXT_ENCODING='" + TEXT_ENCODING + '\'' +
                ", KEY_FORMAT='" + KEY_FORMAT + '\'' +
                '}';
    }

    public String getENCRYPT_MODE() {
        return ENCRYPT_MODE;
    }

    public void setENCRYPT_MODE(String ENCRYPT_MODE) {
        this.ENCRYPT_MODE = ENCRYPT_MODE;
    }

    public String getPADDING_MODE() {
        return PADDING_MODE;
    }

    public void setPADDING_MODE(String PADDING_MODE) {
        this.PADDING_MODE = PADDING_MODE;
    }

    public String getOUTPUT_FORMAT() {
        return OUTPUT_FORMAT;
    }

    public void setOUTPUT_FORMAT(String OUTPUT_FORMAT) {
        this.OUTPUT_FORMAT = OUTPUT_FORMAT;
    }

    public String getTEXT_ENCODING() {
        return TEXT_ENCODING;
    }

    public void setTEXT_ENCODING(String TEXT_ENCODING) {
        this.TEXT_ENCODING = TEXT_ENCODING;
    }

    public String getKEY_FORMAT() {
        return KEY_FORMAT;
    }

    public void setKEY_FORMAT(String KEY_FORMAT) {
        this.KEY_FORMAT = KEY_FORMAT;
    }
}
