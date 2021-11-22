package FacherTest;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-12 13:49
 **/

public class RealView extends ViewObject {

    @Override
    public void ONClick_JBT_enCode() {
        super.ONClick_JBT_enCode();
        JTA_dst.setText("encode");
    }

    @Override
    public void ONClick_JBT_deCode() {
        super.ONClick_JBT_deCode();
        JTA_dst.setText("encode");
    }

    @Override
    public void ONClick_JBT_confirm() {
        super.ONClick_JBT_confirm();
        JTA_dst.setText("confirm");
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
}
