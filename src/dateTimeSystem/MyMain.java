package dateTimeSystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void init() throws Exception {
        System.out.println("init");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Start");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
        HBox rootNode = loader.load();

        MenuBar menuBar = createMenu();
        rootNode.getChildren().add(0,menuBar);

        Scene scene = new Scene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Time Converter");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenu() {
        //Creating File Menu
        Menu fileMenu = new Menu("Menu");

        MenuItem quitMenuItem = new MenuItem("Quit");

        quitMenuItem.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
        fileMenu.getItems().addAll(quitMenuItem);

        //Creating Help Menu
        Menu  helpMenu = new Menu("Help");
        MenuItem aboutApp = new MenuItem("About");
        aboutApp.setOnAction(event -> aboutApp());
        helpMenu.getItems().addAll(aboutApp);

        //Creating Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);

        return menuBar;
    }

    private void aboutApp() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("Time and Date Converter Tool");
        alertDialog.setHeaderText("Change IST to PST");
        alertDialog.setContentText("This app simply converts the entered date and time from IST to PST. " +
                "This version of app is only for 2021.\r\n \r\n \r\n Contact - devsharma291199@gmail.com");

        alertDialog.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop");
        super.stop();
    }
}
