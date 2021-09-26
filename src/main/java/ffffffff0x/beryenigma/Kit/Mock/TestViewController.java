package ffffffff0x.beryenigma.Kit.Mock;

import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;

public class TestViewController extends ViewController {
    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        JTA_src.setText(System.getProperty("user.dir"));
    }
}
