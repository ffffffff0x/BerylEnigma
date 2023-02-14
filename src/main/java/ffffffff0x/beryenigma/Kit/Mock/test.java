package ffffffff0x.beryenigma.Kit.Mock;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-14 15:07
 **/

public class test extends RecursiveTreeObject<test> {
    private String t1;
    private String t2;

    public test() {}

    public test(String t1,String t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }
}
