package View.Root;

import Init.Init;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class RootTreeNode {
    public final TreeItem<String> rootItem = new TreeItem<>(Init.languageResourceBundle.getString("Root"),folderIcon("open"));
    public Map<TreeItem<String>,String> nodeMap = new HashMap<>();

    public RootTreeNode(){
        final TreeItem<String> Tools = new TreeItem<>(Init.languageResourceBundle.getString("Tools"),folderIcon("open"));
            final TreeItem<String> TextModify = new TreeItem<>(Init.languageResourceBundle.getString("TextModify"),folderIcon("open"));
        final TreeItem<String> Encryption = new TreeItem<>(Init.languageResourceBundle.getString("Encryption"),folderIcon("open"));
            final TreeItem<String> Modern = new TreeItem<>(Init.languageResourceBundle.getString("Modern"),folderIcon("open"));
                final TreeItem<String> Authentication = new TreeItem<>(Init.languageResourceBundle.getString("Authentication"),folderIcon("open"));
            final TreeItem<String> Classical = new TreeItem<>(Init.languageResourceBundle.getString("Classical"),folderIcon("open"));
            final TreeItem<String> Coding = new TreeItem<>(Init.languageResourceBundle.getString("Coding"),folderIcon("open"));

        //rootitem
        rootItem.setExpanded(true);
        rootItem.getChildren().add(Tools);
        rootItem.getChildren().add(Encryption);

        //Tools
        Tools.getChildren().add(TextModify);

        //TextModify
        // ItemAdd(TextModify,"CaseConvert");
        // ItemAdd(TextModify,"TextReplace");
        // ItemAdd(TextModify,"TextSeparate");

        //Encryption
        Encryption.setExpanded(true);
        Encryption.getChildren().add(Modern);
        Encryption.getChildren().add(Classical);
        Encryption.getChildren().add(Coding);

        //Modern
        Modern.getChildren().add(Authentication);
        // ItemAdd(Modern,"AES");
        // ItemAdd(Modern,"DES");
        // ItemAdd(Modern,"HASH");
        // ItemAdd(Modern,"SM3");
        // ItemAdd(Modern,"SM4");

        //Authentication
        // ItemAdd(Authentication,"NTLM_Hash");
        // ItemAdd(Authentication,"JWT");

        //Classical
        // ItemAdd(Classical,"ROT13");
        // ItemAdd(Classical,"Rail_fence");
        // ItemAdd(Classical,"Atbash");
        // ItemAdd(Classical,"Vigenere");

        //Coding
        // ItemAdd(Coding,"URL");
        ItemAdd(Coding,"Base64","/View/Encryption/Coding/Base64/Base64View.fxml");
        ItemAdd(Coding,"ASCII","/View/Encryption/Coding/ASCII/ASCIIView.fxml");
        ItemAdd(Coding,"HEX","/View/Encryption/Coding/HEXCoder/HEXCoderView.fxml");
        // ItemAdd(Coding,"Unicode");
        // ItemAdd(Coding,"Morse_Code");
        // ItemAdd(Coding,"HTML_Character_Entity");
        // ItemAdd(Coding,"Base_Conversion");
        // ItemAdd(Coding,"Base_Conversion_Split");
    }

    private void ItemAdd(TreeItem<String> treeItem,String itemName){
        treeItem.getChildren().add(new TreeItem<>(Init.languageResourceBundle.getString(itemName)));
    }

    public void ItemAdd(TreeItem<String> treeItem, String itemName, String viewPath){
        TreeItem<String> Temp = new TreeItem<>(Init.languageResourceBundle.getString(itemName),folderIcon("settings"));
        treeItem.getChildren().add(Temp);
        nodeMap.put(Temp,viewPath);
    }

    private Node folderIcon(String check){
        switch(check){
            case "open" : return new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_folder-open-fill.png")));
            case "close" : return new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_folder-3-fill.png")));
            case "settings" : return new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_settings-3-line.png")));
            default:return null;
        }
    }
}
