# 简述
软件基于JDK8U251开发，使用自带JAVAFX 框架以及JFoenixUI组件架构。

## 开发工具与资源
- IDEA:https://www.jetbrains.com/idea/

- JDK:https://www.oracle.com/java/technologies/javase-downloads.html

- Scene Builder：https://gluonhq.com/products/scene-builder/
  - JAVAFX可视化FXML编辑器

- JFoenix:https://github.com/jfoenixadmin/JFoenix
  - 第三方JAVAFX UI库

# 程序文件说明
```
CryptoMicroscope
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   ├── Controller
│       │   ├── Init
│       │   ├── Kit
│       │   ├── Languages
│       │   ├── View
│       │   └─ Main.java
│       └── resources
│           ├── css
│           └── img
└── target
```
## Controller
存放所有主要功能的具体实现类。

- 按照主页面树状图的文件结构创建包
- 全部为静态方法
- 返回值最好是可以直接显示的String
- 类命名规则为 `[上一级的包名]+[类名]` 例如: Coding_Base64

## Init
存放所有的视图与程序的初始化项目。

- Init.java 中存放主程序启动时需要初始化的全局变量与配置，如语言配置等。
- ViewInit.java 中存放视图调用时需要初始化的变量与配置。
- 全部为静态方法/静态变量
- 视图初始化类命名规则为`[控件名]+[操作描述]` 例如: comboBoxSplit
- 初始化方法如果有需要可以使用，如果有大量需要可以修改/添加方法

## Kit
存放所有的工具类与开发模板

- Mock 包中放置测试单独页面的类
- TempView 包中存放基本的样式模板
- Utils 包中存放工具类
  - ViewUtils 中存放所有与视图有关的工具类
  - 方法命名规则为`[控件名]+[操作描述]` 例如: comboBoxInputCheck

## Languages
存放语言配置文件

- 修改/添加时注意三个一起改

## View
存放所有的视图文件与视图控制器

- 按照主页面树状图的文件结构创建包
- 视图与视图控制器放置于同一个包内
  - 视图文件命名规则为`[页面名]+[View]`
  - 视图控制器文件命名规则为`[页面名]+"View"+"Controller"`
- Root 包是主页面框架的View与实现
  - RootTreeNode.java 是主页面框架左边的选择树的实现
  - 在每次添加页面的时候要注意往 RootTreeNode.java 中添加选择项

# 新增功能流程与规则
## 页面绘制规则
功能页面具体参数
- 主页面AnchorPane
  - width = 510
  - height = 70
- 主标题Lable
  - 上边距 = 15
  - 左边距 = 10
  - 右边距 = 10

其他的可以自由发挥或者使用模板(推荐)。

## 使用Temp模板注意事项
1. 注意修改视图控制器"fx:controller="Kit.TempView.TempViewController""为你所设置的视图控制器，可以在文本模式修改也可以在 ScenceBulider 中的 Document > Controller 中修改。
2. 注意控件的绑定是否正确。
3. ScenceBulider 有一些bug,部分项目只能在文本模式修改，以下列出常用的一些会导致 ScenceBulider 卡死的修改项。
- Properties > Style
  - 可以在FXML视图中手动修改 例:style="-fx-background-color: #ffffff;"

## 新增功能页面流程
1. 在Controller包中按照位置放置具体的方法实现类。
2. 绘制页面 fxml，页面由 Kit/TempView/TempView_00.fxml 衍生时，绘制页面时直接复制并改名即可，做好视图控制器与视图与主控制器(Controller包中的具体方法实现)的连接，中途可以使用 Mock 包中的单独页面测试类测试。
3. 编辑 View/Root/RootTreeNode.java 使用类内的 IteamAdd 方法在分类下添加iteam  
 示例 ： 
```java
//Coding
ItemAdd(Coding,"Base64","/View/Encryption/Coding/Base64Base64View.fxml");

// Coding：添加项目的放置分类，RootTreeNode()一开始有所有分类的iteam创建。
// "Base64"：添加项目的名字，要保证在Languages包的语言设置中有这个字符才能写入，否则不能正常多国语言转换。
// "/View/Encryption/Coding/Base64Base64View.fxml"：FXML文件的包内路径，在界面切换时会使用并读取。
```

目前的树状图功能分类预览
```
"..."为具体功能页面

root
├── Tools
│   └── TextModify
|       └── ...
└── Encryption
    ├── Moderm
    |   ├── Authentication
    |   |   └── ...
    |   └── ...
    ├── Classical
    |   └── ...
    └── Coding
        └── ...
```