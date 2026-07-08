package org.hit.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {

    public static Scene loadScene(String file) throws IOException {

        Parent root = FXMLLoader.load(
                SceneManager.class.getResource("/org/hit/ui/" + file)
        );

        Scene scene = new Scene(root, 600, 450);

        scene.getStylesheets().add(
                SceneManager.class
                        .getResource("/org/hit/ui/style.css")
                        .toExternalForm()
        );

        return scene;
    }

    public static Scene createScene(Parent root) {

        Scene scene = new Scene(root, 600, 450);

        scene.getStylesheets().add(
                SceneManager.class
                        .getResource("/org/hit/ui/style.css")
                        .toExternalForm()
        );

        return scene;
    }
}