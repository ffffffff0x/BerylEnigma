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
        final TreeItem<String> tools = new TreeItem<>(Init.languageResourceBundle.getString("Tools"),folderIcon("open"));
            final TreeItem<String> textModify = new TreeItem<>(Init.languageResourceBundle.getString("TextModify"),folderIcon("open"));
        final TreeItem<String> encryption = new TreeItem<>(Init.languageResourceBundle.getString("Encryption"),folderIcon("open"));
            final TreeItem<String> modern = new TreeItem<>(Init.languageResourceBundle.getString("Modern"),folderIcon("open"));
                final TreeItem<String> authentication = new TreeItem<>(Init.languageResourceBundle.getString("Authentication"),folderIcon("open"));
            final TreeItem<String> classical = new TreeItem<>(Init.languageResourceBundle.getString("Classical"),folderIcon("open"));
            final TreeItem<String> coding = new TreeItem<>(Init.languageResourceBundle.getString("Coding"),folderIcon("open"));

        //rootitem
        rootItem.setExpanded(true);
        rootItem.getChildren().add(tools);
        rootItem.getChildren().add(encryption);

        //Tools
        tools.getChildren().add(textModify);

        //TextModify
        // ItemAdd(textModify,"CaseConvert");
        // ItemAdd(textModify,"TextReplace");
        // ItemAdd(textModify,"TextSeparate");

        //Encryption
        encryption.setExpanded(true);
        encryption.getChildren().add(modern);
        encryption.getChildren().add(classical);
        encryption.getChildren().add(coding);

        //Modern
        modern.getChildren().add(authentication);
        // ItemAdd(modern,"AES");
        // ItemAdd(modern,"DES");
        // ItemAdd(modern,"HASH");
        // ItemAdd(modern,"SM3");
        // ItemAdd(modern,"SM4");

        //Authentication
        // ItemAdd(authentication,"NTLM_Hash");
        // ItemAdd(authentication,"JWT");

        //Classical
        ItemAdd(classical,"ROT13","/View/Encryption/Classical/ROT/ROTView.fxml");
        // ItemAdd(classical,"Rail_fence");
        // ItemAdd(classical,"Atbash");
        // ItemAdd(classical,"Vigenere");

        //Coding
        ItemAdd(coding,"ASCII","/View/Encryption/Coding/ASCII/ASCIIView.fxml");
        ItemAdd(coding,"URL","/View/Encryption/Coding/URL/URLView.fxml");
        ItemAdd(coding,"Base64","/View/Encryption/Coding/Base64/Base64View.fxml");
        ItemAdd(coding,"HEX","/View/Encryption/Coding/HEXCoder/HEXCoderView.fxml");
        ItemAdd(coding,"Unicode","/View/Encryption/Coding/Unicode/UnicodeView.fxml");
        ItemAdd(coding,"HTMLCharacterEntity","/View/Encryption/Coding/HTMLCharEntity/HTMLCharEntityView.fxml");
        // ItemAdd(coding,"Morse_Code");
        // ItemAdd(coding,"Base_Conversion");
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
