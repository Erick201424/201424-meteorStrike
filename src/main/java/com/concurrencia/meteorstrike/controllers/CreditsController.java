package com.concurrencia.meteorstrike.controllers;

import com.concurrencia.meteorstrike.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Paths;

public class CreditsController {

    @FXML
    private AnchorPane bgCredits;

    @FXML
    private Button btnMenú;

    @FXML
    private Button btnSalir;

    private String path = "src/main/resources/com/concurrencia/meteorstrike/bgm/bgMusicFinale.mp3";

    MediaPlayer mediaPlayer;

    public void playSoundMenu(){
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @FXML
    public void initialize() {
        playSoundMenu();
    }

    @FXML
    void btnMenu_OnMouse(MouseEvent event) throws IOException {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene creditScene = new Scene(root);
        creditScene.getRoot().requestFocus();
        HelloApplication.primaryStage.setScene(creditScene);
    }

    @FXML
    void btnSalir_OnMouse(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void exitOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.exit(1);
        }
    }

}
