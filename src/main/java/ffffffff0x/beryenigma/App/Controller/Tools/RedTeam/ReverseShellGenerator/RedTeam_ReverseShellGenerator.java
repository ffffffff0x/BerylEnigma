package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.ReverseShellGenerator;

import com.google.gson.Gson;
import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;

import java.io.File;
import java.util.Objects;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-23 16:57
 **/

public class RedTeam_ReverseShellGenerator {
    private ReverseShellBeans reverseShellBeans;

    public RedTeam_ReverseShellGenerator() {
        String jsonData = FileUtils.getFileString(new File(Objects.requireNonNull(RedTeam_ReverseShellGenerator.class.getResource("/redTeam/ReverseShell.txt")).getFile()));
        Gson gson = new Gson();
        reverseShellBeans = gson.fromJson(jsonData, ReverseShellBeans.class);
    }

    public void getAllShellBeans() {
        ObservableList<shellName> shellNames = FXCollections.observableArrayList();
        for (ReverseShellBeans.ReverseShellBean shellBean : reverseShellBeans.getData()) {
            shellNames.add(new shellName(shellBean.getName()));
        }
    }

    private static final class shellName extends RecursiveTreeObject<shellName> {
        final StringProperty shellName;

        shellName(String name) {
            this.shellName = new SimpleStringProperty(name);
        }
    }

}
