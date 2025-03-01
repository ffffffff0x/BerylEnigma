package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ClassScanner;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-01 14:11
 **/

public class AutoRootTreeNode {
    public TreeItem<String> rootItem = new TreeItem<>(Init.getLanguage("Root"),folderIcon("open"));
    // 视图切换用对应关系
    public Map<TreeItem<String>,String> nodeMap = new HashMap<>();

    public Map<String,TreeItem<String>> folderMap = new HashMap<>();

    public AutoRootTreeNode() {
        NodeScan();
    }

    public void ItemAdd(TreeItem<String> folderItem, String itemName, String viewPath){
        TreeItem<String> Temp = new TreeItem<>(Init.getLanguage(itemName),folderIcon("settings"));
        folderItem.getChildren().add(Temp);
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
        Map<String, ViewNodeInfo> viewNodeContainer = new TreeMap<>();
        // 视图路径级别
        HashSet<String> folderPaths = new HashSet<>();
        try {
            // 扫描包路径，获取存在注解的类
            List<Class<?>> set = new ClassScanner().getAnnotationClasses("ffffffff0x.beryenigma.App.View.Modules", ViewNode.class);
            System.out.println("扫描获取功能节点数量: " + set.size());
            // 遍历获取到的所有类
            for (Class<?> aClass : set) {
                // 获取注解
                ViewNode annotation = aClass.getAnnotation(ViewNode.class);
                // 节点可显示参数为true时
                if (annotation.isVisible()) {
                    // 获取注解信息构建节点对象
                    viewNodeContainer.put(annotation.name(), new ViewNodeInfo(annotation.name(),
                            annotation.folderPath(),
                            ("/" + aClass.getPackageName().replace(".","/") + "/" + annotation.fxmlName())));
                    // System.out.println(aClass.getPackageName());
                    // 获取注解信息中的目录路径
                    folderPaths.add(annotation.folderPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据获取的路径信息创建路径
        createFolder(folderPaths);
        // 根据获取的节点信息创建节点
        createTreeItem(viewNodeContainer);
//        System.out.println(folderPaths);
//        System.out.println(viewNodeContainer.keySet());
    }

    public void createFolder(HashSet<String> folderPaths) {
        // 路径排序
        List<String> folderPathsList = new ArrayList<>(folderPaths);
        // 倒序
        folderPathsList.sort(Collections.reverseOrder());
        // 添加根目录
        folderMap.put("Root/",rootItem);
        // 根目录默认展开
        rootItem.setExpanded(true);
        // 临时节点存放
        TreeItem<String> tempFolderTreeItem;
        // 临时文件夹路径
        StringBuilder tempFolderPath = new StringBuilder();
        // 路径深度
        int folderDepth;
        // 遍历所有路径
        for (String folderPath : folderPathsList) {
            // 设置初始路径深度
            folderDepth = 0;
            // 设置初始路径字符串
            tempFolderPath.delete(0,tempFolderPath.length());
            // 设置初始起点路径，Root节点
            tempFolderTreeItem = folderMap.get("Root/");
            // 遍历路径下的每一个文件夹
            for (String folder : folderPath.split("/")) {
                tempFolderPath.append(folder).append("/");
                // 如果不存在这个文件夹
                if (!folderMap.containsKey(tempFolderPath.toString())) {
//                    System.out.println("创建" + Init.getLanguage(folder) + "目录");
                    // 创建文件夹
                    TreeItem<String> tempTreeItem = new TreeItem<>(Init.getLanguage(folder),folderIcon("open"));
                    // 将文件夹装载入folderMap
                    folderMap.put(tempFolderPath.toString(),tempTreeItem);
                    // 将文件夹添加到前一个文件夹中
                    tempFolderTreeItem.getChildren().add(tempTreeItem);
                    // 如果路径深度小于2
                    if (folderDepth < 2) {
                        // 默认展开文件夹
                        tempTreeItem.setExpanded(true);
                    }
                }
                // 将当前文件夹放入临时节点对象中用于下一节点的添加
                tempFolderTreeItem = folderMap.get(tempFolderPath.toString());
                // 路径深度自增
                folderDepth++;
            }
        }
//        System.out.println(folderMap.keySet());
    }

    public void createTreeItem(Map<String, ViewNodeInfo> viewNodeContainer) {
        for (String viewNodeName : viewNodeContainer.keySet()) {
//            System.out.println("加载" + viewNodeContainer.get(viewNodeName).name + "模块");
            ItemAdd(folderMap.get(viewNodeContainer.get(viewNodeName).folderPath)
                    ,viewNodeContainer.get(viewNodeName).name,
                    viewNodeContainer.get(viewNodeName).fxmlPath);
        }
    }
}
