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
        tools.setExpanded(true);
        tools.getChildren().add(textModify);

        //TextModify
        // ItemAdd(textModify,"CaseConvert");
        ItemAdd(textModify,"TextReplace","/View/TextEdit/TextReplace/TextReplaceView.fxml");
        ItemAdd(textModify,"TextSeparate","/View/TextEdit/TextSeparate/TextSeparateView.fxml");

        //Encryption
        encryption.setExpanded(true);
        encryption.getChildren().add(modern);
        encryption.getChildren().add(classical);
        encryption.getChildren().add(coding);

        //Modern
        modern.getChildren().add(authentication);
        // ItemAdd(modern,"AES");
        // ItemAdd(modern,"DES");
        ItemAdd(modern,"HASH","/View/Encryption/Modern/Hash/HashView.fxml");
        // ItemAdd(modern,"SM3");
        // ItemAdd(modern,"SM4");

        //Authentication
        ItemAdd(authentication,"NTLM_Hash","/View/Encryption/Modern/Authentication/NTLM/NTLMView.fxml");
        // ItemAdd(authentication,"JWT");

        //Classical
        ItemAdd(classical,"ROT","/View/Encryption/Classical/ROT/ROTView.fxml");
        ItemAdd(classical,"RailFence","/View/Encryption/Classical/RailFence/RailFenceView.fxml");
        ItemAdd(classical,"Atbash","/View/Encryption/Classical/Atbash/AtbashView.fxml");
        ItemAdd(classical,"Vigenere","/View/Encryption/Classical/Vigenere/VigenereView.fxml");

        //Coding
        ItemAdd(coding,"ASCII","/View/Encryption/Coding/ASCII/ASCIIView.fxml");
        ItemAdd(coding,"URL","/View/Encryption/Coding/URL/URLView.fxml");
        ItemAdd(coding,"Base64","/View/Encryption/Coding/Base64/Base64View.fxml");
        ItemAdd(coding,"HEX","/View/Encryption/Coding/HEXCoder/HEXCoderView.fxml");
        ItemAdd(coding,"Unicode","/View/Encryption/Coding/Unicode/UnicodeView.fxml");
        ItemAdd(coding,"HTMLCharacterEntity","/View/Encryption/Coding/HTMLCharEntity/HTMLCharEntityView.fxml");
        ItemAdd(coding,"MorseCoder","/View/Encryption/Coding/MorseCoder/MorseCoderView.fxml");
        ItemAdd(coding,"BaseConversion","/View/Encryption/Coding/BaseConversion/BaseConversionView.fxml");
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
        return switch (check) {
            case "open" -> new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_folder-open-fill.png")));
            case "close" -> new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_folder-3-fill.png")));
            case "settings" -> new ImageView(new Image(this.getClass().getResourceAsStream("/img/icon_settings-3-line.png")));
            default -> null;
        };
    }
}
