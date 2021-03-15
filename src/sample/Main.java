package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Partido;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        //SCENE TO WORK
        Scene scene = new Scene(root);

        //Injectar Scene i Stage
        Partido partido = loader.getController();
        partido.setScenePrincipal(scene);
        partido.setStagePrincipal(primaryStage);

        //CSS
//        http://fxexperience.com/2011/12/styling-fx-buttons-with-css/
        scene.getStylesheets().add("sample/CSS/button-styles.css");

        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}