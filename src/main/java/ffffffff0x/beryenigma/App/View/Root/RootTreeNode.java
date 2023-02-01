package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class RootTreeNode {
    public TreeItem<String> rootItem = new TreeItem<>(Init.getLanguage("Root"),folderIcon("open"));
    public Map<TreeItem<String>,String> nodeMap = new HashMap<>();

    public RootTreeNode(){
        TreeItem<String> tools = new TreeItem<>(Init.getLanguage("Tools"),folderIcon("open"));
        TreeItem<String> textModify = new TreeItem<>(Init.getLanguage("TextModify"),folderIcon("open"));
        TreeItem<String> practical = new TreeItem<>(Init.getLanguage("Practical"),folderIcon("open"));
        TreeItem<String> redTeam = new TreeItem<>(Init.getLanguage("RedTeam"),folderIcon("open"));
        TreeItem<String> imageTools = new TreeItem<>(Init.getLanguage("ImageTools"),folderIcon("open"));
        TreeItem<String> encryption = new TreeItem<>(Init.getLanguage("Encryption"),folderIcon("open"));
        TreeItem<String> modern = new TreeItem<>(Init.getLanguage("Modern"),folderIcon("open"));
        TreeItem<String> symmetricEncryption = new TreeItem<>(Init.getLanguage("SymmetricEncryption"),folderIcon("open"));
        TreeItem<String> authentication = new TreeItem<>(Init.getLanguage("Authentication"),folderIcon("open"));
        TreeItem<String> classical = new TreeItem<>(Init.getLanguage("Classical"),folderIcon("open"));
        TreeItem<String> coding = new TreeItem<>(Init.getLanguage("Coding"),folderIcon("open"));
        TreeItem<String> baseEncoding = new TreeItem<>(Init.getLanguage("BaseEncoding"),folderIcon("open"));

        //rootitem
        rootItem.setExpanded(true);
        rootItem.getChildren().add(tools);
        rootItem.getChildren().add(encryption);

        //Tools
        tools.setExpanded(true);
        tools.getChildren().add(textModify);
        tools.getChildren().add(practical);
        tools.getChildren().add(redTeam);
        tools.getChildren().add(imageTools);

        //ImageTools
        ItemAdd(imageTools,"PixelReplacement","/ffffffff0x/beryenigma/App/View/Modules/Tools/Image/PixelReplacement/PixelReplacementView.fxml");
        ItemAdd(imageTools,"QRCode","/ffffffff0x/beryenigma/App/View/Modules/Tools/Image/QRcode/QRcodeView.fxml");

        //Practical
        ItemAdd(practical,"MoneyConvert", "/ffffffff0x/beryenigma/App/View/Modules/Tools/Practical/MoneyConvert/MoneyConvertView.fxml");
        ItemAdd(practical,"Timestamp", "/ffffffff0x/beryenigma/App/View/Modules/Tools/Practical/Timestamp/TimestampView.fxml");

        //TextModify
        ItemAdd(textModify,"CaseConvert", "/ffffffff0x/beryenigma/App/View/Modules/Tools/TextEdit/CaseConvert/CaseConvertView.fxml");
        ItemAdd(textModify,"TextReplace", "/ffffffff0x/beryenigma/App/View/Modules/Tools/TextEdit/TextReplace/TextReplaceView.fxml");
        ItemAdd(textModify,"TextSeparate", "/ffffffff0x/beryenigma/App/View/Modules/Tools/TextEdit/TextSeparate/TextSeparateView.fxml");
        ItemAdd(textModify,"LineSplicing", "/ffffffff0x/beryenigma/App/View/Modules/Tools/TextEdit/LineSplicing/LineSplicingView.fxml");

        //RedTeam
        ItemAdd(redTeam,"ReverseShellGenerator","/ffffffff0x/beryenigma/App/View/Modules/Tools/RedTeam/ReverseShellGenerator/ReverseShellGeneratorView.fxml");
        ItemAdd(redTeam,"PayloadConverter", "/ffffffff0x/beryenigma/App/View/Modules/Tools/RedTeam/PayloadConverter/PayloadConverterView.fxml");
        ItemAdd(redTeam,"TargetFinishing", "/ffffffff0x/beryenigma/App/View/Modules/Tools/RedTeam/TargetClassification/TargetFinishingView.fxml");
        ItemAdd(redTeam,"DomainSplit", "/ffffffff0x/beryenigma/App/View/Modules/Tools/RedTeam/DomainSplit/DomainSplitView.fxml");

        //Encryption
        encryption.setExpanded(true);
        encryption.getChildren().add(modern);
        encryption.getChildren().add(classical);
        encryption.getChildren().add(coding);

        //Modern
        modern.getChildren().add(authentication);
        modern.getChildren().add(symmetricEncryption);
        ItemAdd(symmetricEncryption,"BlockCipher","/ffffffff0x/beryenigma/App/View/Modules/Encryption/Modern/SymmetricEncryption/BlockCipher/BlockCipherView.fxml");
        ItemAdd(modern,"HASH", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Modern/Hash/HashView.fxml");
        ItemAdd(modern,"HMAC", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Modern/HMAC/HMACView.fxml");

        //Authentication
        ItemAdd(authentication,"NTLM_Hash", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Modern/Authentication/NTLM/NTLMView.fxml");
        ItemAdd(authentication,"JWT", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Modern/Authentication/JWT/JWTView.fxml");

        //Classical
        ItemAdd(classical,"ROT", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/ROT/ROTView.fxml");
        ItemAdd(classical,"RailFence", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/RailFence/RailFenceView.fxml");
        ItemAdd(classical,"Atbash", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/Atbash/AtbashView.fxml");
        ItemAdd(classical,"Vigenere", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/Vigenere/VigenereView.fxml");
        ItemAdd(classical,"CaesarCipher", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/CaesarCipher/CaesarCipherView.fxml");

        //Coding
        coding.getChildren().add(baseEncoding);
        baseEncoding.setExpanded(true);
        ItemAdd(baseEncoding,"Base64", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base64/Base64View.fxml");
        ItemAdd(baseEncoding,"Base16", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base16/Base16View.fxml");
        ItemAdd(baseEncoding,"Base32", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base32/Base32View.fxml");
        ItemAdd(baseEncoding,"Base58", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base58/Base58View.fxml");
        ItemAdd(baseEncoding,"Base62", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base62/Base62View.fxml");
        ItemAdd(baseEncoding,"Base85", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base85/Base85View.fxml");
        ItemAdd(baseEncoding,"Base91", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base91/Base91View.fxml");
        ItemAdd(baseEncoding,"Base92", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseEncoding/Base92/Base92View.fxml");
        ItemAdd(coding,"ASCII", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/ASCII/ASCIIView.fxml");
        ItemAdd(coding,"URL", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/URL/URLView.fxml");
        ItemAdd(coding,"Brainfuck", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/Brainfuck/BrainfuckView.fxml");
        ItemAdd(coding,"HEX", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/HEXCoder/HEXCoderView.fxml");
        ItemAdd(coding,"XOR","/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/XOR/XORView.fxml");
        ItemAdd(coding,"Unicode", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/Unicode/UnicodeView.fxml");
        ItemAdd(coding,"HTMLCharacterEntity", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/HTMLCharEntity/HTMLCharEntityView.fxml");
        ItemAdd(coding,"MorseCoder", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/MorseCoder/MorseCoderView.fxml");
        ItemAdd(coding,"BaseConversion", "/ffffffff0x/beryenigma/App/View/Modules/Encryption/Coding/BaseConversion/BaseConversionView.fxml");
    }

    private void ItemAdd(TreeItem<String> treeItem,String itemName){
        treeItem.getChildren().add(new TreeItem<>(Init.getLanguage(itemName)));
    }

    public void ItemAdd(TreeItem<String> treeItem, String itemName, String viewPath){
        TreeItem<String> Temp = new TreeItem<>(Init.getLanguage(itemName),folderIcon("settings"));
        treeItem.getChildren().add(Temp);
        nodeMap.put(Temp,viewPath);
    }

    private Node folderIcon(String check){
        return switch (check) {
            case "open" -> new ImageView(ViewUtils.getImage(ImageListInit.ICON_FOLDER_OPEN));
//            case "close" -> new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/icon_folder-3-fill.png"))));
            case "settings" -> new ImageView(ViewUtils.getImage(ImageListInit.ICON_SETTING));
            default -> null;
        };
    }
}
