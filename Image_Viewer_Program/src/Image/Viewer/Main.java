package Image.Viewer;
//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import DataStorage.popupData;
import DataStorage.writePopupData;
import DataStorage.readPopupData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UiLayout.fxml"));
        primaryStage.setTitle("Image Viewer");
        primaryStage.setScene(new Scene(root, 1920, 1020));
        primaryStage.show();
        //resets the popup window variable stored in popupdata
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {

                try {
                    readPopupData read = new readPopupData("./src/PopupData.csv");
                    popupData temp = new popupData("0:0");
                    read.add(temp);
                    writePopupData entered = new writePopupData();
                    try {
                        read.get(0).setIsActive("0");
                        entered.writePopupDataToFile(read.get(0).getIsActive(),read.get(0).getPlacement(),read.get(0).getSearchActive());
                    } catch (Exception ex) {
                        System.out.println("Could not write to Popup file.");
                    }
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
                Platform.exit();
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) { launch(args); }
}
