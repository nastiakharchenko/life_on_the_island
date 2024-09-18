package org.module_two;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import org.module_two.controller.IslandFxController;

public class AppIsland extends Application {
    @Getter
    private static Stage primaryStage;
    private AnchorPane root;
    @Getter
    private static IslandFxController controller;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(AppIsland.class.getResource("/fxml/island.fxml"));
        root = fxmlLoader.load();

        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Island");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
