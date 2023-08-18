package ffffffff0x.beryenigma.App.View.Modules.Tools.Image.PixelReplacement.Engine;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ComponentSampleModel;
import java.util.Arrays;

/**
 * https://blog.csdn.net/10km/article/details/65444680
 * @author: RyuZUSUNC
 * @create: 2021/8/17 20:34
 **/
public class BufferedImageModeCheck {
    /**
     * @param image
     * @param bandOffset 用于判断通道顺序
     * @return
     */
    private static boolean equalBandOffsetWith3Byte(BufferedImage image, int[] bandOffset){
        if(image.getType()==BufferedImage.TYPE_3BYTE_BGR){
            if(image.getData().getSampleModel() instanceof ComponentSampleModel){
                ComponentSampleModel sampleModel = (ComponentSampleModel)image.getData().getSampleModel();
                if(Arrays.equals(sampleModel.getBandOffsets(), bandOffset)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断图像是否为BGR格式
     * @return
     */
    public static boolean isBGR3Byte(BufferedImage image){
        return equalBandOffsetWith3Byte(image,new int[]{0, 1, 2});
    }
    /**
     * 判断图像是否为RGB格式
     * @return
     */
    public static boolean isRGB3Byte(BufferedImage image){
        return equalBandOffsetWith3Byte(image,new int[]{2, 1, 0});
    }
    /**
     * 对图像解码返回RGB格式矩阵数据
     * @param image
     * @return
     */
    public static byte[] getMatrixRGB(BufferedImage image) {
        if(null==image)
            throw new NullPointerException();
        byte[] matrixRGB;
        if(isRGB3Byte(image)){
            matrixRGB= (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        }else{
            // 转RGB格式
            BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null).filter(image, rgbImage);
            matrixRGB= (byte[]) rgbImage.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        }
        return matrixRGB;
    }
    /**
     * 对图像解码返回BGR格式矩阵数据
     * @param image
     * @return
     */
    public static byte[] getMatrixBGR(BufferedImage image){
        if(null==image)
            throw new NullPointerException();
        byte[] matrixBGR;
        if(isBGR3Byte(image)){
            matrixBGR= (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        }else{
            // ARGB格式图像数据
            int intrgb[]=image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            matrixBGR=new byte[image.getWidth() * image.getHeight()*3];
            // ARGB转BGR格式
            for(int i=0,j=0;i<intrgb.length;++i,j+=3){
                matrixBGR[j]=(byte) (intrgb[i]&0xff);
                matrixBGR[j+1]=(byte) ((intrgb[i]>>8)&0xff);
                matrixBGR[j+2]=(byte) ((intrgb[i]>>16)&0xff);
            }
        }
        return matrixBGR;
    }

    /**
     * 将BGR格式的BufferedImage转换为RGB格式
     * @param image BufferedImage
     * @return BufferedImage
     */
    public static BufferedImage BGR2RGB(BufferedImage image) {
        // 转RGB格式
        BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null).filter(image, rgbImage);
        return image;
    }

    /**
     * 将RGB格式的BufferedImage转换为BGR格式
     * @param image BufferedImage
     * @return BufferedImage
     */
    public static BufferedImage RGB2BGR(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        // 获取图片中每个rgb像素的int类型表示
        int[] rgbPixels = image.getRGB(0, 0, width, height, null, 0, width);
        int[] bgrPixels = new int[rgbPixels.length];
        for (int i = 0; i < rgbPixels.length; i++) {
            int color = rgbPixels[i];
            int red = ((color & 0x00FF0000) >> 16);
            int green = ((color & 0x0000FF00) >> 8);
            int blue = color & 0x000000FF;
            // 将rgb三个通道的数值合并为一个int数值，同时调换b通道和r通道
            bgrPixels[i] = (red & 0x000000FF) | (green << 8 & 0x0000FF00) | (blue << 16 & 0x00FF0000);
        }
        image.setRGB(0, 0, width, height, bgrPixels, 0, width);
        return image;
    }
}
