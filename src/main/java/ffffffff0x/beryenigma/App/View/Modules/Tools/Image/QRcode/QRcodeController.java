package ffffffff0x.beryenigma.App.View.Modules.Tools.Image.QRcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.App.Implement.Tools.Image.QRcode.Image_QRcode;
import ffffffff0x.beryenigma.App.Implement.Tools.Image.QRcode.QRcodeParameters;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingDoubleColumnView;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.TextFormatter.IntegerFilter;
import ffffffff0x.beryenigma.Kit.Utils.Util;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-11 13:17
 **/

public class QRcodeController extends ViewController {
    private ImageView IMG_loadImg;
    private ImageView IMG_outImg;
    private File ImgFile = null;
    private BufferedImage inBufferedImage;
    private BufferedImage outBufferedImage;
    private final double margins = 10.0;
    private VBox imageInputLittleButtonVBox;
    private VBox textOutputLittleButtonVBox;
    private JFXButton JBT_LoadClipboardImage = new JFXButton();
    private JFXButton JBT_OpenUrlWithBrowser = new JFXButton();

    private JFXColorPicker JCP_QRColor = new JFXColorPicker(Color.BLACK);
    private JFXColorPicker JCP_BKColor = new JFXColorPicker(Color.WHITE);
    private JFXTextField JTF_outImgWidth = new JFXTextField();
    private JFXTextField JTF_outImgHeight = new JFXTextField();
    private JFXComboBox<Integer> JCB_imgMargin = new JFXComboBox();
    private QRcodeParameters qRcodeParameters = new QRcodeParameters();
    //弹出式控件框
    private PopupSettingDoubleColumnView popupSettingView;

    @FXML
    private JFXToggleButton JTB_modeSelect;
    @FXML
    private JFXComboBox JCB_charset;
    @FXML
    private JFXComboBox<String> JCB_barcodeFormat;
    @FXML
    private JFXSpinner JSP_running;
    @FXML
    private JFXButton JBT_loadImg;
    @FXML
    private JFXButton JBT_outImg;

    public QRcodeController() {
    }

    @Override
    protected void initialize() {
        super.initialize();
        initViews();
        DragClickLoadImg();
        System.out.println();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        if (JTB_modeSelect.isSelected()) {
            encode();
        } else {
            decode();
        }
    }

    @FXML
    public void ONClickLoadImg() {
        ImgFile = ViewUtils.getFile();
        try {
            if (ImgFile != null) {
                if (FileUtils.isImage(ImgFile.getPath())) {
                    IMG_loadImg.setImage(new Image(new FileInputStream(ImgFile)));
                    JBT_loadImg.setGraphic(IMG_loadImg);
                    inBufferedImage = null;
                }  else {
                    ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_isImage"));
                    ImgFile = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClickOutImg() {
        try {
            if (outBufferedImage != null) {
                File outFile = ViewUtils.fileChooser(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
                if (outFile != null) {
                    ImageIO.write(outBufferedImage,"png",outFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), e.getMessage());
        }
    }

    @FXML
    public void ONClickModeSelect() {
        if (JTB_modeSelect.isSelected()) {
            JTB_modeSelect.setText(Init.getLanguage("EnCodeMode"));
            JBT_confirm.setText(Init.getLanguage("EnCode"));
            JTA_src.setVisible(true);
            JBT_loadImg.setVisible(false);
            JTA_dst.setVisible(false);
            JBT_outImg.setVisible(true);
            popupSettingView.setVisible(true);
            imageInputLittleButtonVBox.setVisible(false);
            textOutputLittleButtonVBox.setVisible(false);
        }else {
            JTB_modeSelect.setText(Init.getLanguage("DeCodeMode"));
            JBT_confirm.setText(Init.getLanguage("DeCode"));
            JTA_src.setVisible(false);
            JBT_loadImg.setVisible(true);
            JTA_dst.setVisible(true);
            JBT_outImg.setVisible(false);
            popupSettingView.setVisible(false);
            imageInputLittleButtonVBox.setVisible(true);
            textOutputLittleButtonVBox.setVisible(true);
        }
    }

    @Override
    protected void LoadPopupSettingNode() {
        //弹出式控件框
        popupSettingView = new PopupSettingDoubleColumnView(ACP_controllerAnchorPane);

        //初始化控件
        initControl();

        //弹出式控件框中添加初始化的控件
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("QRIMGColor"),JCP_QRColor,true));
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("QRIMGBKColor"),JCP_BKColor,true));
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("QRIMGWidth"),JTF_outImgWidth));
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("QRIMGHeight"),JTF_outImgHeight));
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("QRIMGMargin"),JCB_imgMargin));

    }

    private void DragClickLoadImg() {
        JBT_loadImg.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != JBT_loadImg) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        JBT_loadImg.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            List<File> files = dragboard.getFiles();
            if(files.size() > 0){
                try {
                    if (FileUtils.isImage(files.get(0).getPath())) {
                        ImgFile = files.get(0);
                        IMG_loadImg.setImage(new Image(new FileInputStream(ImgFile)));
                        JBT_loadImg.setGraphic(IMG_loadImg);
                    } else {
                        ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_isImage"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
        // 加载图像输入框图像
        IMG_loadImg = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_LOADQRIMG));
        IMG_loadImg.setFitHeight(165);
        IMG_loadImg.setFitWidth(500);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);
        // 加载图像输出框图像
        IMG_outImg = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_SAVEIMG));
        IMG_outImg.setFitHeight(165);
        IMG_outImg.setFitWidth(500);
        IMG_outImg.setPreserveRatio(true);
        JBT_outImg.setGraphic(IMG_outImg);

        ViewInit.comboBoxCharset(JCB_charset);

        JCB_barcodeFormat.getItems().addAll("QR Code",
                "Aztec",
                "PDF 417");
        JCB_barcodeFormat.setValue("QR Code");

        JTF_outImgHeight.setTextFormatter(new TextFormatter<>(
                new IntegerStringConverter(), // Standard converter form JavaFX
                400,
                new IntegerFilter()));
        JTF_outImgWidth.setTextFormatter(new TextFormatter<>(
                new IntegerStringConverter(), // Standard converter form JavaFX
                400,
                new IntegerFilter()));

        imageInputLittleButtonVBox = ViewUtils.getLittleButtonVBoxSrc(ACP_controllerAnchorPane);
        textOutputLittleButtonVBox = ViewUtils.getLittleButtonVBoxDst(ACP_controllerAnchorPane);

        popupSettingView.setVisible(false);

        ViewUtils.setLittleButtonToVBox(imageInputLittleButtonVBox, JBT_LoadClipboardImage, ImageListInit.ICON_LJBT_CLIPBOARD);
        ViewUtils.setLittleButtonToVBox(textOutputLittleButtonVBox, JBT_OpenUrlWithBrowser, ImageListInit.ICON_LJBT_IE);

        JBT_LoadClipboardImage.setTooltip(new Tooltip(Init.getLanguage("LoadImageFromClipboard")));
        JBT_LoadClipboardImage.setOnAction(actionEvent -> {
            try {
                inBufferedImage = ViewUtils.getBufferedImageFromClipboard();
                if (inBufferedImage != null) {
                    ImgFile = null;
                    IMG_loadImg.setImage(ViewUtils.convertToFxImage(inBufferedImage));
                    JBT_loadImg.setGraphic(IMG_loadImg);
                } else {
                    ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_isImage"));
                }
            } catch (Exception e) {
                ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), e.getMessage());
                e.printStackTrace();
            }
        });

        JBT_OpenUrlWithBrowser.setTooltip(new Tooltip(Init.getLanguage("OpenInBrowser")));
        JBT_OpenUrlWithBrowser.setOnAction(actionEvent -> {
            if (JTA_dst.getText().length() > 1) {
                ViewUtils.openURLWithBrowser(JBT_OpenUrlWithBrowser, JTA_dst.getText());
            }
        });


    }

    private void initControl() {
        JTF_outImgWidth.setText("400");
        JTF_outImgHeight.setText("400");

        JCB_imgMargin.getItems().addAll(0, 1, 2, 3, 4, 5);
        JCB_imgMargin.setValue(1);
    }

    private BarcodeFormat getBarcodeFormat(String type) {
        return switch (type) {
            case "QR Code" -> BarcodeFormat.QR_CODE;
            case "Aztec" -> BarcodeFormat.AZTEC;
            case "PDF 417" -> BarcodeFormat.PDF_417;
            default -> null;
        };
    }

    private void decode() {
        if (ImgFile != null) {
            JSP_running.setVisible(true);
            new Thread(() -> {
                try {
                    JTA_dst.setText(Image_QRcode.decode(ImgFile.getPath(), JCB_charset.getValue().toString()));
                    Platform.runLater(() -> JSP_running.setVisible(false));
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), e.getMessage());
                        JSP_running.setVisible(false);
                    });
                } catch (NotFoundException e) {
                    try {
                        JTA_dst.setText(Image_QRcode.decodeReverseColor(ImgFile.getPath(), JCB_charset.getValue().toString()));
                        JSP_running.setVisible(false);
                    } catch (IOException ex) {
                        Platform.runLater(() -> {
                            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), ex.getMessage());
                            JSP_running.setVisible(false);
                        });
                    } catch (NotFoundException ex) {
                        Platform.runLater(() -> {
                            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_noQRcode"));
                            JSP_running.setVisible(false);
                        });
                    }
                }
            }).start();
        } else if(inBufferedImage != null) {
            JSP_running.setVisible(true);
            new Thread(() -> {
                try {
                    JTA_dst.setText(Image_QRcode.decode(inBufferedImage, JCB_charset.getValue().toString()));
                    Platform.runLater(() -> JSP_running.setVisible(false));
                } catch (NotFoundException e) {
                    try {
                        JTA_dst.setText(Image_QRcode.decodeReverseColor(inBufferedImage, JCB_charset.getValue().toString()));
                        JSP_running.setVisible(false);
                    } catch (NotFoundException ex) {
                        Platform.runLater(() -> {
                            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_noQRcode"));
                            JSP_running.setVisible(false);
                        });
                    }
                }
            }).start();
        } else {
            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_notNull"));
            JSP_running.setVisible(false);
        }
    }

    private void encode() {
        if (JTA_src.getText().length() > 0) {
            JSP_running.setVisible(true);
            try {
                qRcodeParameters.setInputContent(Util.stringCharsetConvert(JTA_src.getText(),JCB_charset.getValue().toString()));
            } catch (UnsupportedEncodingException e) {
                ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), e.getMessage());
                JSP_running.setVisible(false);
            }
            qRcodeParameters.setBackgroundColor(colorStringConvert(JCP_BKColor.getValue().toString()));
            qRcodeParameters.setQrCodeColor(colorStringConvert(JCP_QRColor.getValue().toString()));
            qRcodeParameters.setCharacterSet(JCB_charset.getValue().toString());
            qRcodeParameters.setBarcodeFormat(getBarcodeFormat(JCB_barcodeFormat.getValue()));
            qRcodeParameters.setImgWidth(!JTF_outImgWidth.getText().equals("") ? Integer.parseInt(JTF_outImgWidth.getText()) : 400);
            qRcodeParameters.setImgHeight(!JTF_outImgHeight.getText().equals("") ? Integer.parseInt(JTF_outImgHeight.getText()) : 400);
            qRcodeParameters.setMargin(JCB_imgMargin.getValue());
            new Thread(() -> {
                try {
                    outBufferedImage = Image_QRcode.encode(qRcodeParameters);
                    Platform.runLater(() -> {
                        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
                        IMG_outImg.setImage(ViewUtils.convertToFxImage(outBufferedImage));
                        JBT_outImg.setGraphic(IMG_outImg);
                        JSP_running.setVisible(false);
                    });
                } catch (WriterException | IOException e) {
                    Platform.runLater(() -> {
                        ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), e.getMessage());
                        JSP_running.setVisible(false);
                    });
                    throw new RuntimeException(e);
                }
            }).start();
        } else {
            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrorMessage_notNull"));
            JSP_running.setVisible(false);
        }
    }

    private int colorStringConvert(String colorString) {
        colorString = colorString.replace("0x","");
        String[] colorList = colorString.split("");
        StringBuilder sb = new StringBuilder();
        sb.append(colorList[colorList.length - 2]);
        sb.append(colorList[colorList.length - 1]);

        for (int i = 0; i < colorList.length - 2; i++) {
            sb.append(colorList[i]);
        }
        return new BigInteger(sb.toString(), 16).intValue();
    }
}
