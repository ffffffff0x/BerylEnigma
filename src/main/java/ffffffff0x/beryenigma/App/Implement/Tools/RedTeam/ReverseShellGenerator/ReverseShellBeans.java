package ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.ReverseShellGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-24 08:37
 **/

public class ReverseShellBeans {
    List<ReverseShellBean> data = new ArrayList<>();

    public List<ReverseShellBean> getData() {
        return data;
    }

    public void setData(List<ReverseShellBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReverseShellBeans{" +
                "data=" + data +
                '}';
    }

    public static class ReverseShellBean {
        private String name;
        private String command;
        private String[] meta;

        @Override
        public String toString() {
            return "ReverseShellBean{" +
                    "name='" + name + '\'' +
                    ", command='" + command + '\'' +
                    ", meta=" + Arrays.toString(meta) +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public String[] getMeta() {
            return meta;
        }

        public void setMeta(String[] meta) {
            this.meta = meta;
        }
    }
}

