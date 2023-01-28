package com.concurrencia.meteorstrike.controllers;

import com.concurrencia.meteorstrike.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Paths;

public class HelloController {
    @FXML
    private Button btnJugar;

    @FXML
    private Button btnSalir;

    private String path = "D:\\Documentos\\Universidad\\7mo Cuatrimestre\\Programacion Concurrente\\Programs\\meteorStrike\\src\\main\\resources\\com\\concurrencia\\meteorStrike\\bgm\\bgMusicUnder.mp3";
    private MediaPlayer mediaPlayer;

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
    void btnJugar_OnMouse(MouseEvent event) throws IOException {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene newScene = new Scene(root);
        newScene.getRoot().requestFocus();
        HelloApplication.primaryStage.setScene(newScene);
    }

    @FXML
    void btnSalir_OnMouse(MouseEvent event) {
        System.exit(1);
    }
}