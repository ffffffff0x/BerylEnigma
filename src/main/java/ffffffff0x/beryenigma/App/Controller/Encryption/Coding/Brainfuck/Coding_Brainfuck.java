package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl.OokEngine;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl.TrollScriptEngine;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl.shortOokEngine;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-17 15:18
 **/

public class Coding_Brainfuck {
    public static String BrainfuckDeCode(String message,String charset) throws Exception {
        BrainfuckEngine brainfuckEngine = new BrainfuckEngine(1024);
        return new String(brainfuckEngine.interpret(message),charset);
    }

    public static String OokDeCode(String message,String charset) throws Exception {
        message = message.replace("\n"," ");
        OokEngine ookEngine = new OokEngine(1024);
        return new String(ookEngine.interpret(message),charset);
    }

    public static String shortOokDeCode(String message,String charset) throws Exception {
        message = message.replace("\n"," ").replace(" ","");
        message = message.replaceAll("(.{2})","$1 ");
        shortOokEngine shortOokEngine = new shortOokEngine(1024);
        return new String(shortOokEngine.interpret(message),charset);
    }

    public static String TrollscriptDeCode(String message,String charset) throws Exception {
        TrollScriptEngine trollScriptEngine = new TrollScriptEngine(1024);
        return new String(trollScriptEngine.interpret(message),charset);
    }
}
