package ThreadExample;

/**
 * Created by zxm on 2017/3/14.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Mp3Finder extends Application {
    final Label status = new Label();
    final Label folderCount = new Label();
    final Label fileCount = new Label();
    final Label mp3Count = new Label();

    @Override public void start(Stage stage) {
        final GridPane finderResults = new GridPane();
        finderResults.setPrefWidth(400);
        finderResults.setVgap(10);
        finderResults.setHgap(10);
        finderResults.addRow(0, new Label("Status: "),    status);
        finderResults.addRow(1, new Label("# Folders: "), folderCount);
        finderResults.addRow(2, new Label("# Files: "),   fileCount);
        finderResults.addRow(3, new Label("# mp3s: "),    mp3Count);

        final Button finderStarter = new Button("Find mp3s");
        finderStarter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                startMp3Finder(finderStarter);
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10; -fx-font-size: 16;");
        layout.getChildren().setAll(finderStarter, finderResults);
        stage.setScene(new Scene(layout));
        stage.show();
    }

    private void startMp3Finder(final Node starterNode) {
        starterNode.setDisable(true);

        Mp3FinderTask task = new Mp3FinderTask(status, folderCount, mp3Count);
        task.runningProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> ov, Boolean wasRunning, Boolean isRunning) {
                if (!isRunning) {
                    starterNode.setDisable(false);
                }
            }
        });

        final Thread thread = new Thread(task , "mp3-finder");
        thread.setDaemon(true);
        thread.start();
    }

    private class Mp3FinderTask extends Task<List<String>> {
        private final Label status;
        private final Label folderCount;
        private final Label mp3Count;

        public Mp3FinderTask(Label status, Label folderCount, Label mp3Count) {
            this.status = status;
            this.folderCount = folderCount;
            this.mp3Count = mp3Count;
        }

        @Override protected List<String> call() throws Exception {
            initFinderResults();

            updateLabelLater(status, "Finding Folders");
            setProgressIndicator(folderCount);
            List folders = findFolders();
            updateLabelLater(folderCount, folders.size() + "");

            updateLabelLater(status, "Finding Files");
            setProgressIndicator(fileCount);
            List files = findFiles(folders);
            updateLabelLater(fileCount, files.size() + "");

            updateLabelLater(status, "Find mp3s");
            setProgressIndicator(mp3Count);
            List mp3s = findMp3s(files);
            updateLabelLater(mp3Count, mp3s.size() + "");

            updateLabelLater(status, "All mp3s Found");

            return mp3s;
        }

        void updateLabelLater(final Label label, final String text) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    label.setGraphic(null);
                    label.setText(text);
                }
            });
        }

        private List<String> findFolders() throws InterruptedException {
            // dummy implementation
            Thread.currentThread().sleep(1000);
            return Arrays.asList("folder1", "folder2", "folder3");
        }

        private List<String> findFiles(List<String> folders) throws InterruptedException {
            // dummy implementation
            Thread.currentThread().sleep(1000);
            return Arrays.asList("file1", "file2", "file3", "file4", "file5");
        }

        private List<String> findMp3s(List<String> files) throws InterruptedException {
            // dummy implementation
            Thread.currentThread().sleep(1000);
            return Arrays.asList("music1", "music2");
        }

        private void initFinderResults() {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    status.setText("");
                    folderCount.setText("");
                    fileCount.setText("");
                    mp3Count.setText("");
                }
            });
        }

        private void setProgressIndicator(final Label label) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    label.setGraphic(new ProgressIndicator());
                }
            });
        }
    }

    public static void main(String[] args) { launch(args); }
}