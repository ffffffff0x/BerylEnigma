package SymmetricAlg;

import java.util.ArrayList;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-06 15:59
 **/

public class algWorkMode {
    String modeName;
    ArrayList<String> modePaddingName = new ArrayList<>();

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public ArrayList<String> getModePaddingName() {
        return modePaddingName;
    }

    public void setModePaddingName(ArrayList<String> modePaddingName) {
        this.modePaddingName = modePaddingName;
    }

    @Override
    public String toString() {
        return "algWorkMode{" +
                "modeName='" + modeName + '\'' +
                ", modePaddingName=" + modePaddingName +
                '}';
    }
}
