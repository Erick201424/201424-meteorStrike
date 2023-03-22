package com.concurrencia.meteorstrike;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        scene.getRoot().requestFocus();
        primaryStage.setTitle("Meteor-Strike!");
        primaryStage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("images/icon-game.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}