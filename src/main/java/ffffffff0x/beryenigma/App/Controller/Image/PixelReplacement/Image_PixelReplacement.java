package ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement;

import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * from https://github.com/yuchenxi2000/PicEncrypt
 * @author: RyuZUSUNC
 * @create: 2021/8/12 22:17
 **/
public class Image_PixelReplacement {
    public static BufferedImage image_transform(double key, File fi, Boolean encrypt, String mode) {
        try {
            BufferedImage bi = ImageIO.read(fi);

            int h = bi.getHeight();
            int w = bi.getWidth();

            int[] buffer = new int[w*h];

            bi.getRGB(0, 0, w, h, buffer, 0, w);

            // 一维转二维
            int[][] buffer_2d = new int[h][w];
            ArrayFunctions af = new ArrayFunctions();
            af.change(buffer, buffer_2d, h, w);

            // 解密
            Algorithms ma = new Algorithms();
            if (encrypt) {
                switch (mode) {
                    case "rc" -> ma.encrypt(buffer_2d, key, h, w);
                    case "r" -> ma.allRowEncrypt(buffer_2d, key, h, w);
                    default -> {
                        System.out.println("未知的模式，可能的模式：r/rc");
                        return null;
                    }
                }
            } else {
                switch (mode) {
                    case "rc" -> ma.decrypt(buffer_2d, key, h, w);
                    case "r" -> ma.allRowDecrypt(buffer_2d, key, h, w);
                    default -> {
                        System.out.println("未知的模式，可能的模式：r/rc");
                        return null;
                    }
                }
            }

            // 二维转一维
            af.recovery(buffer_2d, buffer, h, w);

            bi.setRGB(0, 0, w, h, buffer, 0, w);

            return bi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void losslessImage(BufferedImage BUFFEREDIMAGE,String formatname){
        Iterator iter = ImageIO.getImageWritersByFormatName(formatname);
        ImageWriter writer = (ImageWriter)iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwp.setCompressionQuality(1);   // an integer between 0 and 1
        // 1 specifies minimum compression and maximum quality
        FileImageOutputStream output = null;
        try {
            output = new FileImageOutputStream(ViewUtils.fileChooser("Test."+formatname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.setOutput(output);
        IIOImage image = new IIOImage(BUFFEREDIMAGE, null, null);
        try {
            writer.write(null, image, iwp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.dispose();
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
}
