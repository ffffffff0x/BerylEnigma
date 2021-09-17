package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl.OokEngine;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl.TrollScriptEngine;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-17 15:18
 **/

public class Coding_Brainfuck {
    public static String BrainfuckDeCode(String message) throws Exception {
        BrainfuckEngine brainfuckEngine = new BrainfuckEngine(1024);
        return brainfuckEngine.interpret(message);
    }

    public static String OokDeCode(String message) throws Exception {
        OokEngine ookEngine = new OokEngine(1024);
        return ookEngine.interpret(message);
    }

    public static String TrollscriptDeCode(String message) throws Exception {
        TrollScriptEngine trollScriptEngine = new TrollScriptEngine(1024);
        return trollScriptEngine.interpret(message);
    }
}
