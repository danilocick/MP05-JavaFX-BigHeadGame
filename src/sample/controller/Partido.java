package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Data.Pilota;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Partido implements Initializable {

    private Scene scene;
    private Stage stage;

    private GraphicsContext gc;
    private Pilota pilota;
    private Image fons;

    /**
     * Opció 2: TimeLine
     * Controlem la velocitat de refresc amb KeyFrame.
     * Aquesta opció és molt més flexible que l'AnimationTimer
     */
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0057), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {

            pilota.clear(gc);
            gc.drawImage(fons, 0,0,600,400);
            pilota.move();
            pilota.render(gc);

        }
    })
    );

    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key2"));

        pilota = new Pilota(new Image("images/pelota_futbol.png"));
        fons = new Image("images/campo_futbol.jpeg");
        gc = mainCanvas.getGraphicsContext2D();
        //gc.drawImage(fons, 600,400);

        // Opció 1
        //animationTimer.start();

        // Opció 2
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                if(pilota.isClicked(point)) pilota.changeDir();
                System.out.println("click");
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                pilota.setDirection(keyEvent.getCode().toString());

            }
        });
    }

    public void setStage(Stage primaryStage) {
        this.stage =primaryStage;
    }

}
