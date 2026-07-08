package org.hit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL url = getClass().getResource("/org/hit/ui/Main.fxml");
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 600, 450);

        scene.getStylesheets().add(
                getClass().getResource("/org/hit/ui/style.css").toExternalForm()
        );

        stage.setTitle("📚 Book Store System");
        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}