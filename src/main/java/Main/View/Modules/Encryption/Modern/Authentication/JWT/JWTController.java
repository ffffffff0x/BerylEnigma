package Main.View.Modules.Encryption.Modern.Authentication.JWT;

import Kit.Utils.ViewUtils;
import App.Controller.Encryption.Modern.Authentication.JWT.Authentication_JWT;
import App.View.Viewobj.ViewControllerObject;

public class JWTController extends ViewControllerObject {
    /**
     *  JTA_src1 :Header
     *  JTA_src2 :Payload
     *  JTA_src3 :VS
     *  JTA_dst :Token
     */

    @Override
    protected void initialize(){
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(Authentication_JWT.encode(JTA_src1.getText(), JTA_src2.getText(), JTA_src3.getText()));
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            String[] result = Authentication_JWT.decode(JTA_dst.getText());
            JTA_src1.setText(result[0]);
            JTA_src2.setText(result[1]);
            JTA_src3.setText(result[2]);
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
