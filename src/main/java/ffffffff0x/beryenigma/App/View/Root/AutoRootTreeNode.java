package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ClassScanner;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-01 14:11
 **/

public class AutoRootTreeNode {
//    public TreeItem<String> rootItem = new TreeItem<>(Init.getLanguage("Root"),folderIcon("open"));
    public Map<TreeItem<String>,String> nodeMap = new HashMap<>();

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

    public void NodeScan() {
        // 视图节点信息
        Map<String, ViewNodeInfo> beanContainer = new HashMap<>();
        // 视图路径级别
        HashSet<String> folderPaths = new HashSet<>();
        try {
            Set<Class<?>> set = new ClassScanner().getAnnotationClasses("ffffffff0x.beryenigma.App.View.Modules", ViewNode.class);
            for (Class<?> aClass : set) {
                ViewNode annotation = aClass.getAnnotation(ViewNode.class);
                beanContainer.put(annotation.name(), new ViewNodeInfo(annotation.name(),
                        annotation.folderPath(),
                        ("/" + aClass.getPackage().getName().replace(".","/") + "/" + annotation.fxmlName())));
                folderPaths.add(annotation.folderPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(folderPaths);
        System.out.println(beanContainer);
    }

    public void createFolder() {

    }

    public static void main(String[] args) {
        new AutoRootTreeNode().NodeScan();
    }
}
