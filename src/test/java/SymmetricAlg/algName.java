package SymmetricAlg;

import java.util.ArrayList;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-06 16:09
 **/

public class algName {
    String algName;
    ArrayList<algWorkMode> algWorkModes = new ArrayList<>();

    public String getAlgName() {
        return algName;
    }

    public void setAlgName(String algName) {
        this.algName = algName;
    }

    public ArrayList<algWorkMode> getAlgWorkModes() {
        return algWorkModes;
    }

    public void setAlgWorkModes(ArrayList<algWorkMode> algWorkModes) {
        this.algWorkModes = algWorkModes;
    }

    @Override
    public String toString() {
        return "algName{" +
                "algName='" + algName + '\'' +
                ", algWorkModes=" + algWorkModes +
                '}';
    }
}
