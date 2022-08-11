package ffffffff0x.beryenigma.App.Implement.Tools.Image.QRcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import ffffffff0x.beryenigma.App.Implement.Encryption.Coding.BaseConversion.Coding_BaseConversion;
import ffffffff0x.beryenigma.Init.Init;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-11 11:13
 **/

public class Image_QRcode {
    public static BufferedImage encode(QRcodeParameters parameters) throws WriterException, IOException {
        Map<EncodeHintType, Object> encodeHints = new HashMap<>();
        encodeHints.put(EncodeHintType.CHARACTER_SET, parameters.getCharacterSet());
        encodeHints.put(EncodeHintType.MARGIN, parameters.getMargin());
        BitMatrix bitMatrix = new MultiFormatWriter().encode(parameters.getInputContent(), parameters.getBarcodeFormat(), parameters.getImgWidth(), parameters.getImgWidth(), encodeHints);
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(parameters.getQrCodeColor(),parameters.getBackgroundColor());
        return MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
    }

    public static String decode(String filepath, String characterSet) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filepath));
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        HashMap<DecodeHintType, Object> decodeHints = new HashMap<>();
        decodeHints.put(DecodeHintType.CHARACTER_SET, characterSet);
        Result result = new MultiFormatReader().decode(bitmap, decodeHints);
        return result.getText();
    }
}
