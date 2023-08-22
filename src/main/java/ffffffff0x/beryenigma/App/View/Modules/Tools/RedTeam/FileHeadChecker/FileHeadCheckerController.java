package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker.Beans.FileHeadCheckerResultBean;
import ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker.Beans.FileHeaderBean;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ConfigListInit;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.TextFormatter.IntegerFilter;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: RyuZUSUNC
 * @create: 2023/8/16 20:11
 **/
@ViewNode(name = "FileHeadChecker",folderPath = "Root/Tools/RedTeam/",fxmlName = "FileHeadCheckerView.fxml")
public class FileHeadCheckerController extends ViewController {
    // 切换/加载 文件头信息JSON控件
    @FXML
    JFXToggleButton JTB_ModeSwitch;
    // 显示已经加载的文件头信息JSON的名称 默认为 UseDefaultHeaderJSON
    @FXML
    Label JLB_JSONName;
    // 显示加载的JSON文件的名称
    @FXML
    Label JLB_JSONNum;
    // 显示已经加载JSON内条目数量
    @FXML
    Label JLB_FileNum;
    // 检测文件头十六进制数的数量 默认为4
    @FXML
    JFXTextField JTF_CheckHexNum;
    // 检测按钮 按下开始检测
    @FXML
    JFXButton JBT_Check;
    // 文件加载按钮 使用多选文件 不可选择文件夹
    @FXML
    JFXButton JBT_LoadFiles;
    // 显示所有已经加载文件的全路径
    @FXML
    JFXTextArea JTA_FileList;
    // 检查进度条
    @FXML
    JFXSpinner JSP_CheckFile;

    // 加载要检测文件头的文件列表
    List<File> files;

    // 缓存文件头信息-文件头信息
    HashMap<String, FileHeaderBean> fileTypes;

    // 默认使用的头文件对照表路径
    final String DefaultFileTypeJsonPath = "/json/redTeam/FileHead.json";

    // JSON序列化工具
    Gson gson = new Gson();

    // 加载文件按钮的图片
    final ImageView IMG_FileAdd = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_FILEADD));
    final ImageView IMG_FileAdd_M = new ImageView(ViewUtils.getImage(ImageListInit.ICON_MJBT_FILEADD));

    // 默认的检测文件头HEX数量
    Integer hexnum = 4;

    // 用来弹出结果窗口的stage
    Stage resultStage = new Stage();

    @Override
    protected void initialize() {
        // 初始化本地文件头信息
        String jsonData = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(FileHeadCheckerController.class.getResourceAsStream(DefaultFileTypeJsonPath))))
                .lines().collect(Collectors.joining(System.lineSeparator()));

        // 保留类型信息使用的TypeToken
        Type type = new TypeToken<HashMap<String, FileHeaderBean>>(){}.getType();
        fileTypes = gson.fromJson(jsonData, type);

        // 显示默认文件头JSON文件中 文件头信息的数量
        JLB_JSONNum.setText(Init.getLanguage("JSONHeaderNum") + ": " + fileTypes.size());

        // HexNum输入加载仅数字的过滤器
        JTF_CheckHexNum.setTextFormatter(new TextFormatter<>(
                new IntegerStringConverter(), // Standard converter form JavaFX
                null,
                new IntegerFilter()));

        // 加载全尺寸的按钮图片
        fileLoadButtonLoadImage(JBT_LoadFiles);

        // 设置图标
        resultStage.getIcons().add(ViewUtils.getImage(ImageListInit.ICON));

        // 设置结果stage的最小大小
        resultStage.setMinHeight(Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinheight)));
        resultStage.setMinWidth(Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinwidth)));

        JLB_JSONNum.setText(Init.getLanguage("JSONHeaderNum") + " : " + fileTypes.size());
        JLB_FileNum.setText(Init.getLanguage("FileNum") + " : 0");
        JTF_CheckHexNum.setPromptText(Init.getLanguage("Check_Hex_Num_Default"));
    }

    @FXML
    public void ONClickLoadFiles() {
        // 缩小按钮为小尺寸
        zoomButton(JBT_LoadFiles);
        // 加载小尺寸的按钮图片
        fileLoadButtonLoadSImage(JBT_LoadFiles);
        // 设置按钮不透明度
        JBT_LoadFiles.setOpacity(0.8);
        // 加载文件与展示加载到的文件路径
        LoadFilePaths();
    }

    @FXML
    public void ONClickCheck() {
        if (files != null) {
            JSP_CheckFile.setVisible(true);
            new Thread(() -> {
                if (JTF_CheckHexNum.getText().length() != 0) {
                    hexnum = Integer.parseInt(JTF_CheckHexNum.getText());
                }

                Scene scene = new Scene(initResultPane(initResultTableView(files, fileTypes, hexnum)));
                // 设置CSS样式
                ViewUtils.setCSSStyle(scene);

                Platform.runLater(() -> {
                    resultStage.setScene(scene);
                    resultStage.show();
                    JSP_CheckFile.setVisible(false);
                });
            }).start();
        }
    }

    @FXML
    public void ONClickModeSelect() {
        if (JTB_ModeSwitch.isSelected()) {
            JTB_ModeSwitch.setText(Init.getLanguage("CustomMode"));
            File file = ViewUtils.getFile(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
            String json = FileUtils.getFileString(file);

            if (json != null && json.length() > 0) {
                // 保留类型信息使用的TypeToken
                Type type = new TypeToken<HashMap<String, FileHeaderBean>>(){}.getType();
                fileTypes = gson.fromJson(json, type);
                JLB_JSONName.setText(file.getName());
                JLB_JSONNum.setText(Init.getLanguage("JSONHeaderNum") + " : " + fileTypes.size());
            } else {
                JTB_ModeSwitch.selectedProperty().setValue(false);
                JTB_ModeSwitch.setText(Init.getLanguage("DeafultMode"));
            }
        }else {
            // 初始化本地文件头信息
            String jsonData = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(FileHeadCheckerController.class.getResourceAsStream(DefaultFileTypeJsonPath))))
                    .lines().collect(Collectors.joining(System.lineSeparator()));

            // 保留类型信息使用的TypeToken
            Type type = new TypeToken<HashMap<String, FileHeaderBean>>(){}.getType();
            fileTypes = gson.fromJson(jsonData, type);
            JTB_ModeSwitch.setText(Init.getLanguage("DeafultMode"));
            JLB_JSONName.setText(Init.getLanguage("UseDefaultHeaderJSON"));
            JLB_JSONNum.setText(Init.getLanguage("JSONHeaderNum") + " : " + fileTypes.size());
        }
    }

    /**
     * 加载文件与文件路径
     */
    public void LoadFilePaths() {
        files = ViewUtils.getFiles();
        StringJoiner stringJoiner = new StringJoiner("\n");
        if (files != null) {
            for (File file : files) {
                stringJoiner.add(file.getPath());
            }
            JTA_FileList.setText(stringJoiner.toString());
            JLB_FileNum.setText(Init.getLanguage("FileNum") + " : " + files.size());
        }
    }

    /**
     * 缩小按钮
     *
     * @param jfxButton jfxButton
     */
    public void zoomButton(JFXButton jfxButton) {
        if (AnchorPane.getBottomAnchor(jfxButton) == 5.0) {
//            ViewUtils.setAnchor(jfxButton, 0.0, 0.0, 0.0, 0.0);
        } else {
            AnchorPane.clearConstraints(jfxButton);

            jfxButton.setPrefSize(40.0,40.0);

            AnchorPane.setRightAnchor(jfxButton, 20.0);
            AnchorPane.setBottomAnchor(jfxButton, 5.0);
        }
    }

    /**
     * 加载文件按钮的图片 （大号
     *
     * @param jfxButton jfxButton
     */
    public void fileLoadButtonLoadImage(JFXButton jfxButton) {
        IMG_FileAdd.setFitHeight(290);
        IMG_FileAdd.setFitWidth(220);
        IMG_FileAdd.setPreserveRatio(true);
        jfxButton.setGraphic(IMG_FileAdd);
    }

    /**
     * 加载文件按钮的图片 （小号
     *
     * @param jfxButton jfxButton
     */
    public void fileLoadButtonLoadSImage(JFXButton jfxButton) {
        IMG_FileAdd_M.setFitHeight(32);
        IMG_FileAdd_M.setFitWidth(32);
        IMG_FileAdd_M.setPreserveRatio(true);
        jfxButton.setGraphic(IMG_FileAdd_M);
    }

    public void buttonAnimate() {
        JBT_LoadFiles.setOnAction(actionEvent -> {
            // Create KeyValues for scaleX and scaleY properties
            KeyValue scaleXValue = new KeyValue(JBT_LoadFiles.scaleXProperty(), 0.3);
            KeyValue scaleYValue = new KeyValue(JBT_LoadFiles.scaleYProperty(), 0.3);

            // Create KeyFrame with KeyValues and duration
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.8), scaleXValue, scaleYValue);

            // Create a Timeline with the KeyFrame
            Timeline timeline = new Timeline(keyFrame);

            // Play the animation
            timeline.play();
        });
    }

    /**
     * 初始化一个用来显示结果与操作控件的AnchorPane
     *
     * @param beanWrapperJFXTreeTableView 检查结果列表
     * @return AnchorPane
     */
    private AnchorPane initResultPane(JFXTreeTableView<BeanWrapper> beanWrapperJFXTreeTableView) {
        // 背景Pane
        AnchorPane resultBlackground = new AnchorPane();
        // 存放操作按钮的控件
        HBox hBox = new HBox();
        // 导出按钮
        JFXButton buttonExport = new JFXButton(Init.getLanguage("Export"));
        // 重命名按钮
        JFXButton buttonRenameAll = new JFXButton(Init.getLanguage("RenameAll"));

        // 设置ID CSS样式用
        resultBlackground.setId("ACP_backgroundAnchorPane");
        // 设置最佳大小
        resultBlackground.setPrefSize(Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinwidth)), Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinheight)));

        // 设置锚点样式
        ViewUtils.setAnchor(resultBlackground, 0.0, 0.0, 0.0, 0.0);
        ViewUtils.setAnchor(beanWrapperJFXTreeTableView, 5.0, 10.0, 10.0, 80.0);
        ViewUtils.setAnchor(hBox,null,20.0,20.0,0.0);

        // 设置按钮最佳大小
        buttonExport.setPrefSize(101,50);
        buttonRenameAll.setPrefSize(101,50);

        // 设置导出按钮点击事件
        buttonExport.setOnAction(actionEvent -> {
            String filePath = ViewUtils.fileChooser(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")).getPath();
            new Thread(() -> {
                try {
                    exportToCSV(beanWrapperJFXTreeTableView, filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    ViewUtils.alertPane(resultStage,Init.getLanguage("INFO") ,Init.getLanguage("Export_Complete"));
                });
            }).start();
        });

        // 设置重命名按钮点击事件
        buttonRenameAll.setOnAction(actionEvent -> {
            new Thread(() -> {
                renameAll(beanWrapperJFXTreeTableView);
                Platform.runLater(() -> {
                    ViewUtils.alertPane(resultStage,Init.getLanguage("INFO") ,Init.getLanguage("Rename_Complete"));
                });
            }).start();
        });

        // 控件加入背景板
        resultBlackground.getChildren().add(beanWrapperJFXTreeTableView);
        resultBlackground.getChildren().add(hBox);

        // 设置hbox样式
        hBox.setPrefHeight(80.0);
        hBox.setSpacing(20.0);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        // 按钮加入hbox
        hBox.getChildren().addAll(buttonExport,buttonRenameAll);

        return resultBlackground;
    }

    /**
     * 初始化一个用来显示检查结果的列表控件
     *
     * @param files 选择中的文件
     * @param fileTypes 加载的文件头对照JSON
     * @return JFXTreeTableView<BeanWrapper>
     */
    private JFXTreeTableView<BeanWrapper> initResultTableView(List<File> files, HashMap<String, FileHeaderBean> fileTypes, Integer hexnum) {
        // 创建根节点，使用 RecursiveTreeItem 封装数据列表，通过 RecursiveTreeObject::getChildren 设置子项提供器
        TreeItem<BeanWrapper> root = new RecursiveTreeItem<>(FileHeadCheckerImpl.getFileTypes(files, fileTypes, hexnum),
                RecursiveTreeObject::getChildren);

        // 创建 JFXTreeTableView，并将根节点设置为树状表格的根，通过 setShowRoot(false) 隐藏根节点的显示
        JFXTreeTableView<BeanWrapper> resultTreeTableView = new JFXTreeTableView<>(root);
        resultTreeTableView.setShowRoot(false);

        // 初始化列
        JFXTreeTableColumn<BeanWrapper, String> fileName = new JFXTreeTableColumn<>(Init.getLanguage("fileName"));
        JFXTreeTableColumn<BeanWrapper, String> filePath = new JFXTreeTableColumn<>(Init.getLanguage("filePath"));
        JFXTreeTableColumn<BeanWrapper, String> extName = new JFXTreeTableColumn<>(Init.getLanguage("extName"));
        JFXTreeTableColumn<BeanWrapper, String> fileHeaderHEX = new JFXTreeTableColumn<>(Init.getLanguage("fileHeaderHEX"));
        JFXTreeTableColumn<BeanWrapper, String> fileDescription = new JFXTreeTableColumn<>(Init.getLanguage("fileDescription"));

        // 将列添加到树状表格的列列表中
        resultTreeTableView.getColumns().setAll(fileName, extName, fileHeaderHEX, fileDescription, filePath);

        // 添加点击事件监听器
        resultTreeTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // 检查是否双击
                TreeItem<BeanWrapper> selectedItem = resultTreeTableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String resultfilePath = selectedItem.getValue().getFilePathProperty();
                    openFileWithSystemDefault(resultfilePath);
//                    System.out.println("Clicked file path: " + resultfilePath);
                }
            }
        });

        // 为 fileName 列设置数据源
        fileName.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileHeadCheckerController.BeanWrapper, String> param) -> {
            if (fileName.validateValue(param)) {
                return param.getValue().getValue().fileNameProperty;
            } else {
                return fileName.getComputedValue(param);
            }
        });

        // 为 filePath 列设置数据源
        filePath.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileHeadCheckerController.BeanWrapper, String> param) -> {
            if (filePath.validateValue(param)) {
                return param.getValue().getValue().filePathProperty;
            } else {
                return filePath.getComputedValue(param);
            }
        });

        // 为 extName 列设置数据源
        extName.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileHeadCheckerController.BeanWrapper, String> param) -> {
            if (extName.validateValue(param)) {
                return param.getValue().getValue().extensionNameProperty;
            } else {
                return extName.getComputedValue(param);
            }
        });

        // 为 fileHeaderHEX 列设置数据源
        fileHeaderHEX.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileHeadCheckerController.BeanWrapper, String> param) -> {
            if (fileHeaderHEX.validateValue(param)) {
                return param.getValue().getValue().fileHeaderHEXProperty;
            } else {
                return fileHeaderHEX.getComputedValue(param);
            }
        });

        // 为 fileDescription 列设置数据源
        fileDescription.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileHeadCheckerController.BeanWrapper, String> param) -> {
            if (fileDescription.validateValue(param)) {
                return param.getValue().getValue().fileDescriptionProperty;
            } else {
                return fileDescription.getComputedValue(param);
            }
        });

        // 返回构建完成的列表
        return resultTreeTableView;
    }

    /**
     * 导出列表为CSV文件
     *
     * @param treeTableView JFXTreeTableView<BeanWrapper>
     * @param filename 保存路径
     */
    private void exportToCSV(JFXTreeTableView<BeanWrapper> treeTableView, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        JFXTreeTableColumn<BeanWrapper, ?>[] columns = treeTableView.getColumns().toArray(new JFXTreeTableColumn[0]);

        // Write header
        for (int i = 0; i < columns.length; i++) {
            fileWriter.append(columns[i].getText());
            if (i < columns.length - 1) {
                fileWriter.append(",");
            } else {
                fileWriter.append("\n");
            }
        }

        // Write data
        for (TreeItem<BeanWrapper> item : treeTableView.getRoot().getChildren()) {
            fileWriter.append(item.getValue().getFileNameProperty()); // Modify this to match the appropriate property
            fileWriter.append(",");
            fileWriter.append(item.getValue().getFilePathProperty()); // Modify this to match the appropriate property
            fileWriter.append(",");
            fileWriter.append(item.getValue().getExtensionNameProperty()); // Modify this to match the appropriate property
            fileWriter.append(",");
            fileWriter.append(item.getValue().getFileHeaderHEXProperty()); // Modify this to match the appropriate property
            fileWriter.append(",");
            fileWriter.append(item.getValue().getFileDescriptionProperty()); // Modify this to match the appropriate property
            fileWriter.append("\n");
        }

        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * 按照匹配出的 扩展名 重命名所有匹配到的文件
     *
     * @param treeTableView JFXTreeTableView<BeanWrapper>
     */
    private void renameAll(JFXTreeTableView<BeanWrapper> treeTableView) {
        for (TreeItem<BeanWrapper> item : treeTableView.getRoot().getChildren()) {
            if (!item.getValue().getExtensionNameProperty().equals("UNKNOWN")) {
                FileUtils.renameFile(item.getValue().getFilePathProperty(),
                        item.getValue().getFileNameProperty()
                                + "." + item.getValue().getExtensionNameProperty());
            }
        }
    }

    /**
     * 使用系统默认程序打开文件
     *
     * @param filePath 文件路径
     */
    private void openFileWithSystemDefault(String filePath) {
        FileUtils.openFile(filePath);
    }

    // 最终结果显示用bean类
    static class BeanWrapper extends RecursiveTreeObject<BeanWrapper> {
        private final SimpleStringProperty filePathProperty = new SimpleStringProperty();
        private final SimpleStringProperty fileNameProperty = new SimpleStringProperty();
        private final SimpleStringProperty extensionNameProperty = new SimpleStringProperty();
        private final SimpleStringProperty fileHeaderHEXProperty = new SimpleStringProperty();
        private final SimpleStringProperty fileDescriptionProperty = new SimpleStringProperty();

        public BeanWrapper(FileHeadCheckerResultBean fileHeadCheckerResultBean) {
            filePathProperty.set(fileHeadCheckerResultBean.getFilePath());
            fileNameProperty.set(fileHeadCheckerResultBean.getFileName());
            extensionNameProperty.set(fileHeadCheckerResultBean.getExtensionName());
            fileHeaderHEXProperty.set(fileHeadCheckerResultBean.getFileHeaderHEX());
            fileDescriptionProperty.set(fileHeadCheckerResultBean.getFileDescription());
        }

        public String getFilePathProperty() {
            return filePathProperty.get();
        }

        public SimpleStringProperty filePathPropertyProperty() {
            return filePathProperty;
        }

        public String getFileNameProperty() {
            return fileNameProperty.get();
        }

        public SimpleStringProperty fileNamePropertyProperty() {
            return fileNameProperty;
        }

        public String getExtensionNameProperty() {
            return extensionNameProperty.get();
        }

        public SimpleStringProperty extensionNamePropertyProperty() {
            return extensionNameProperty;
        }

        public String getFileHeaderHEXProperty() {
            return fileHeaderHEXProperty.get();
        }

        public SimpleStringProperty fileHeaderHEXPropertyProperty() {
            return fileHeaderHEXProperty;
        }

        public String getFileDescriptionProperty() {
            return fileDescriptionProperty.get();
        }

        public SimpleStringProperty fileDescriptionPropertyProperty() {
            return fileDescriptionProperty;
        }
    }
}
