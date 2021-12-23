package ffffffff0x.beryenigma.Kit.Mock;

import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;

public class TestViewController extends ViewController {
    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        setTextareaOnDrag();
        JTA_src.setText(System.getProperty("user.dir"));
    }

    protected void setTextareaOnDrag() {
//        JTA_src.setOnDragOver(dragEvent -> {
//            if (dragEvent.getGestureSource() != JTA_src) {
//                dragEvent.acceptTransferModes(TransferMode.ANY);
//            }
//        });

//        JTA_src.setOnDragDropped(dragEvent -> {
//            Dragboard dragboard = dragEvent.getDragboard();
//            List<File> files = dragboard.getFiles();
//            if(files.size() > 0){
//                JTA_dst.setText(files.get(0).getPath());
//            }
//        });
    }
}
