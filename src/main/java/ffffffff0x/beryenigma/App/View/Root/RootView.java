package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.ConfigListInit;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeView;
import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;

import static javafx.geometry.NodeOrientation.INHERIT;

public class RootView extends AnchorPane {
    private BorderPane borderPane;
    private AnchorPane indexpane;
    private AnchorPane treePane;
    private JFXTreeView<String> RootTree;
    private JFXButton bt_option;
    private ImageView IV_Logo;
    private ImageView IV_Github;
    private JFXButton JBT_GithubLink;
    private JFXButton JBT_StyleChange;
    private int styleMode;
    private AutoRootTreeNode autoRootTreeNode;
    private AnchorPane selectedAnchorPane = null;
    private int testnum;

    public RootView() {
        initializeUI();
        initialize();
    }

    private void initializeUI() {
        // Set AnchorPane properties
        setPrefHeight(625.0);
        setPrefWidth(900.0);

        // Create BorderPane
        borderPane = new BorderPane();
        borderPane.setLayoutY(7.0);
        borderPane.setId("borderPane");
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);

        // Create TreePane (Left)
        treePane = new AnchorPane();
        treePane.setPrefHeight(600.0);
        treePane.setPrefWidth(270.0);
        RootTree = new JFXTreeView<>();
        RootTree.setOnMousePressed(e -> checkView());
        AnchorPane.setBottomAnchor(RootTree, 0.0);
        AnchorPane.setLeftAnchor(RootTree, 0.0);
        AnchorPane.setRightAnchor(RootTree, 0.0);
        AnchorPane.setTopAnchor(RootTree, 0.0);
        treePane.getChildren().add(RootTree);
        borderPane.setLeft(treePane);

        // Create MenuBar (Top)
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        fileMenu.setVisible(false);
        MenuItem closeItem = new MenuItem("Close");
        fileMenu.getItems().add(closeItem);

        Menu editMenu = new Menu("Edit");
        editMenu.setVisible(false);
        MenuItem deleteItem = new MenuItem("Delete");
        editMenu.getItems().add(deleteItem);

        Menu helpMenu = new Menu("Help");
        helpMenu.setVisible(false);
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        borderPane.setTop(menuBar);

        // Create Center Pane
        indexpane = new AnchorPane();
        
        // Create Logo ImageView
        IV_Logo = new ImageView();
        IV_Logo.setFitHeight(600.0);
        IV_Logo.setFitWidth(630.0);
        IV_Logo.setNodeOrientation(INHERIT);
        IV_Logo.setPickOnBounds(true);
        IV_Logo.setPreserveRatio(true);
        IV_Logo.setOnMouseClicked(e -> test());
        IV_Logo.setViewport(new Rectangle2D(0, 0, 0, 0));
        AnchorPane.setBottomAnchor(IV_Logo, 0.0);
        AnchorPane.setLeftAnchor(IV_Logo, 0.0);
        AnchorPane.setRightAnchor(IV_Logo, 0.0);
        AnchorPane.setTopAnchor(IV_Logo, 0.0);

        // Create Bottom Buttons
        GridPane bottomLeftGrid = new GridPane();
        bottomLeftGrid.setPrefHeight(40.0);
        bottomLeftGrid.setPrefWidth(200.0);
        AnchorPane.setBottomAnchor(bottomLeftGrid, 10.0);
        AnchorPane.setLeftAnchor(bottomLeftGrid, 10.0);

        JBT_StyleChange = new JFXButton();
        JBT_StyleChange.setPrefHeight(40.0);
        JBT_StyleChange.setPrefWidth(100.0);
        JBT_StyleChange.setText(Init.getLanguage("DarkMode"));
        bottomLeftGrid.add(JBT_StyleChange, 0, 0);

        GridPane bottomRightGrid = new GridPane();
        bottomRightGrid.setPrefHeight(40.0);
        bottomRightGrid.setPrefWidth(200.0);
        AnchorPane.setBottomAnchor(bottomRightGrid, 10.0);
        AnchorPane.setRightAnchor(bottomRightGrid, 10.0);

        IV_Github = new ImageView();
        IV_Github.setFitHeight(36.0);
        IV_Github.setFitWidth(36.0);
        IV_Github.setNodeOrientation(null);
        IV_Github.setPickOnBounds(true);
        IV_Github.setPreserveRatio(true);
        IV_Github.setViewport(new Rectangle2D(0, 0, 0, 0));

        JBT_GithubLink = new JFXButton("By ffffffff0x/RyuZU");
        JBT_GithubLink.setPrefHeight(40.0);
        JBT_GithubLink.setPrefWidth(165.0);
        JBT_GithubLink.setRipplerFill(javafx.scene.paint.Color.CADETBLUE);
        JBT_GithubLink.setStyle("-fx-font-size: 15.0");
        JBT_GithubLink.setOnAction(e -> openURL());
        JBT_GithubLink.setId("JBT_GithubLink");

        bottomRightGrid.add(IV_Github, 0, 0);
        bottomRightGrid.add(JBT_GithubLink, 1, 0);

        indexpane.getChildren().addAll(IV_Logo, bottomLeftGrid, bottomRightGrid);
        borderPane.setCenter(indexpane);

        getChildren().add(borderPane);
    }

    private void initialize() {
        autoRootTreeNode = new AutoRootTreeNode();
        RootTree.setRoot(autoRootTreeNode.rootItem);
        setImage();
        changeStyle();
    }

    private void checkView() {
        try {
            if ((RootTree.getSelectionModel().getSelectedItem()) != null && RootTree.getSelectionModel().getSelectedItem().getValue().equals(Init.getLanguage("Root"))) {
                borderPane.setCenter(indexpane);
            }
            if ((RootTree.getSelectionModel().getSelectedItem()) != null && (RootTree.getSelectionModel().getSelectedItem()).isLeaf()) {
                if(!"".equals(autoRootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem()))) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(RootViewController.class.getResource(autoRootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem())));
                    loader.setResources(Init.getLanguageResourceBundle());
                    try {
                        selectedAnchorPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    borderPane.setCenter(selectedAnchorPane);
                    try {
                        RootTree.getSelectionModel().select(RootTree.getSelectionModel().getSelectedItem().getParent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void openURL() {
        ViewUtils.openURLWithBrowser(JBT_GithubLink, "https://github.com/ffffffff0x/BerylEnigma");
    }

    private void setImage() {
        IV_Logo.setImage(ViewUtils.getImage(ImageListInit.LOGO));
        IV_Github.setImage(ViewUtils.getImage(ImageListInit.ICON_GITHUB));
    }

    private void test() {
        testnum++;
        if (testnum > 10) {
            testnum = 0;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootViewController.class.getResource("/ffffffff0x/beryenigma/Kit/Mock/TestView.fxml"));
            loader.setResources(Init.getLanguageResourceBundle());
            try {
                selectedAnchorPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            borderPane.setCenter(selectedAnchorPane);
        }
    }

    private void changeStyle() {
        if (Init.getConfig(ConfigListInit.AppStyle).equals("dark")) {
            styleMode = 2;
        } else {
            styleMode = 3;
        }
        
        JBT_StyleChange.setOnAction((ActionEvent actionEvent) -> {
            if (styleMode == 100) {
                styleMode = 2;
            }
            if (styleMode % 2 == 0) {
                borderPane.getScene().getStylesheets().clear();
                borderPane.getScene().getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_light.css")).toExternalForm());
                ConfigUtils.editConfigFile(ConfigListInit.AppStyle, "light");
                setImage();
                JBT_StyleChange.setText(Init.getLanguage("LightMode"));
                autoRootTreeNode = new AutoRootTreeNode();
                RootTree.setRoot(autoRootTreeNode.rootItem);
                styleMode++;
            } else {
                borderPane.getScene().getStylesheets().clear();
                borderPane.getScene().getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_dark.css")).toExternalForm());
                ConfigUtils.editConfigFile(ConfigListInit.AppStyle, "dark");
                if (styleMode % 11 == 0) {
                    IV_Logo.setImage(ViewUtils.getImage(ImageListInit.LOGO_REDEYE));
                    IV_Github.setImage(ViewUtils.getImage(ImageListInit.ICON_GITHUB));
                    JBT_StyleChange.setText("???Mode");
                } else {
                    setImage();
                    JBT_StyleChange.setText(Init.getLanguage("DarkMode"));
                }
                autoRootTreeNode = new AutoRootTreeNode();
                RootTree.setRoot(autoRootTreeNode.rootItem);
                styleMode++;
            }
        });
    }
}
