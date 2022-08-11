package ffffffff0x.beryenigma.App.Implement.Tools.Image.QRcode;

import com.google.zxing.BarcodeFormat;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-11 11:20
 **/

public class QRcodeParameters {
    String inputContent;
    int imgWidth;
    int imgHeight;
    int margin;
    int backgroundColor;
    int qrCodeColor;
    String characterSet;
    BarcodeFormat barcodeFormat;

    public QRcodeParameters() {
        this.imgWidth = 400;
        this.imgHeight = 400;
        this.margin = 1;
        this.backgroundColor = 0xFFFFFFFF;
        this.qrCodeColor = 0xFF000001;
        this.characterSet = "UTF-8";
        this.barcodeFormat = BarcodeFormat.QR_CODE;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    public BarcodeFormat getBarcodeFormat() {
        return barcodeFormat;
    }

    public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
        this.barcodeFormat = barcodeFormat;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getQrCodeColor() {
        return qrCodeColor;
    }

    public void setQrCodeColor(int qrCodeColor) {
        this.qrCodeColor = qrCodeColor;
    }

    @Override
    public String toString() {
        return "QRcodeParameters{" +
                "inputContent='" + inputContent + '\'' +
                ", imgWidth=" + imgWidth +
                ", imgHeight=" + imgHeight +
                ", margin=" + margin +
                ", backgroundColor=" + backgroundColor +
                ", qrCodeColor=" + qrCodeColor +
                ", characterSet='" + characterSet + '\'' +
                ", barcodeFormat=" + barcodeFormat +
                '}';
    }
}
