package com.concurrencia.meteorstrike.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class CreditsController {

    @FXML
    private AnchorPane bgCredits;

    private String path = "D:\\Documentos\\Universidad\\7mo Cuatrimestre\\Programacion Concurrente\\Programs\\meteorStrike\\src\\main\\resources\\com\\concurrencia\\meteorStrike\\bgm\\bgMusicFinale.mp3";

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
    void exitOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("I press Enter to exit");
            System.exit(1);
        }
    }

}
