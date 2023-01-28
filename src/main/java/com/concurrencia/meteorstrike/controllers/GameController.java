package com.concurrencia.meteorstrike.controllers;

import com.concurrencia.meteorstrike.HelloApplication;
import com.concurrencia.meteorstrike.models.Meteoro;
import com.concurrencia.meteorstrike.models.Nave;
import com.concurrencia.meteorstrike.models.Score;
import com.concurrencia.meteorstrike.models.Vector;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GameController implements Observer {

    @FXML
    private AnchorPane bgScene;

    @FXML
    private ImageView imgSpaceship;

    @FXML
    private ImageView imgMeteoro;

    @FXML
    public Label lblScore;

    @FXML
    private Label lbl_Continue;


    private Random random;

    private Nave nave;
    private Meteoro meteoro;
    private Score score;

    private String punt = "";

    private int puntuacion;
    private boolean meteoro_flag = true;
    private boolean game_flag = true;

    private String path = "D:\\Documentos\\Universidad\\7mo Cuatrimestre\\Programacion Concurrente\\Programs\\meteorStrike\\src\\main\\resources\\com\\concurrencia\\meteorStrike\\bgm\\bgMusicGame.mp3";
    private String pathExplosion = "D:\\Documentos\\Universidad\\7mo Cuatrimestre\\Programacion Concurrente\\Programs\\meteorStrike\\src\\main\\resources\\com\\concurrencia\\meteorStrike\\images\\explosion.gif";

    private MediaPlayer mediaPlayer;

    public void playSoundMenu(){
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void changeScene() throws IOException {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("credits-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene creditScene = new Scene(root);
        creditScene.getRoot().requestFocus();
        HelloApplication.primaryStage.setScene(creditScene);
    }

    @FXML
    public void initialize() {
        playSoundMenu();

        nave = new Nave();
        nave.setPosicion(new Vector(1, 14,135));
        nave.addObserver(this);
        new Thread(nave).start();

        meteoro = new Meteoro(0);
        meteoro.setPosicion(new Vector(2, 550, 150));
        meteoro.addObserver(this);
        new Thread(meteoro).start();


        score = new Score();
        score.addObserver(this);
        new Thread(score).start();

    }

    @FXML
    void spaceShipOnKeyPressed(KeyEvent event) {
        if (game_flag) {
            if(event.getCode() == KeyCode.A) {
                if (imgSpaceship.getLayoutX()  < 0) {
                    imgSpaceship.setLayoutX(imgSpaceship.getLayoutX());
                } else {
                    imgSpaceship.setLayoutX(imgSpaceship.getLayoutX() - 15);
                }
            }

            if(event.getCode() == KeyCode.D) {
                if (imgSpaceship.getLayoutX()  > 600) {
                    imgSpaceship.setLayoutX(imgSpaceship.getLayoutX());
                } else {
                    imgSpaceship.setLayoutX(imgSpaceship.getLayoutX() + 15);
                }
            }

            if(event.getCode() == KeyCode.W) {
                if (imgSpaceship.getLayoutY()  < 10) {
                    imgSpaceship.setLayoutY(imgSpaceship.getLayoutY());
                } else {
                    imgSpaceship.setLayoutY(imgSpaceship.getLayoutY() - 15);
                }
            }

            if(event.getCode() == KeyCode.S) {
                if (imgSpaceship.getLayoutY()  > 263) {
                    imgSpaceship.setLayoutY(imgSpaceship.getLayoutY());
                } else {
                    imgSpaceship.setLayoutY(imgSpaceship.getLayoutY() + 15);
                }
            }
        }
        else {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    changeScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Score) {
            puntuacion = score.getScore();
            punt = (String.valueOf(puntuacion));
            System.out.println("Puntuacion: " + punt);
            Platform.runLater(() -> lblScore.setText("Score: " + punt));

        } else {
            random = new Random(System.currentTimeMillis());
                Vector pos = (Vector)arg;
                switch (pos.getId()) {
                    case 2:
                        Platform.runLater(() -> imgMeteoro.setLayoutX(pos.getPosX()));
                        break;
                }

                if(imgSpaceship.getBoundsInParent().intersects(imgMeteoro.getBoundsInParent())) {
                    lbl_Continue.setVisible(true);
                    meteoro.setStatus(false);
                    score.setStatus(false);
                    Platform.runLater(() -> {
                        lblScore.setText("Fin");
                        imgSpaceship.setImage(new Image(pathExplosion));
                        FadeTransition explosion = new FadeTransition(
                                Duration.millis(1000), imgSpaceship);
                        explosion.setToValue(1);
                        explosion.play();
                        game_flag = false;
                    });
                }

                if (imgMeteoro.getLayoutX() <= -130){
                    int aleatorio = random.nextInt((220));
                    this.meteoro.setStatus(false);
                    this.imgMeteoro.setVisible(false);
                    this.meteoro_flag = false;

                    if (!meteoro.getStatus()) {
                        this.meteoro.setStatus(true);
                        meteoro.setPosicion(new Vector(2, 675, aleatorio));
                        this.imgMeteoro.setVisible(true);
                        imgMeteoro.setLayoutY(aleatorio);
                        imgMeteoro.setLayoutX(675);
                        this.meteoro_flag = true;
                    }
                }

                if (puntuacion == 100) {
                    meteoro.setVelocidad(-10);
                } else if (puntuacion == 250) {
                    meteoro.setVelocidad(-25);
                } else if (puntuacion == 500) {
                    meteoro.setVelocidad(-50);
                } else if (puntuacion == 1000) {
                    meteoro.setVelocidad(-75);
                }

        }

    }
}
