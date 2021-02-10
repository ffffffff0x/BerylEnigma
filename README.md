<p align="center">
    <img src="./assets/img/BE_Banner.png" width="70%">
</p>

## 简介

一个CTF+渗透测试工具包，主要实现功能在加解密和编码这块，在软件使用过程中发现问题或建议欢迎提交 issue,也欢迎提交新功能需求。

前身为[CryptionTool](https://github.com/ffffffff0x/CryptionTool)，为更方便的开发更换框架重构。

软件基于JDK15开发，使用JAVAFX UI框架以及JFoenixUI组件架构。详见开发文档。

使用 jpackage 打包，java16 更新后会同步更进并使用更加完善的打包机制。

---

# 界面展示

![](./assets/img/01.png)


## 下载
访问 [releases](https://github.com/ffffffff0x/BerylEnigma/releases) 下载

>如果机器使用 java15 只需下载 .jar 文件，双击即可使用。
>
>不使用 java 或 java 版本不是 15 的情况下，请下载 7z 文件，其中打包好了 java15 的运行时环境，双击 exe 使用。
---

# 目前支持的功能

**加解密/编解码**
- 现代
  - 认证
    - HTLM-hash
    - JWT
  - hash
  - SM3
- 古典
  - ROT13
  - Atbash
  - Vigenre
- 编码
  - URL
  - ASCII
  - Base64
  - HEX
  - HTML实体编码
  - Unicode

**文本操作工具**
- 文本替换
- 文本分隔
- 英文大小写转换
---

# 免责声明

本人编写的工具,仅供学习和研究使用,请勿使用文中的技术源码用于非法用途,任何人造成的任何负面影响,与本人无关.
