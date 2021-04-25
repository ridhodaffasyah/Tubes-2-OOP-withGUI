package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Image chara = new Image("chara.png");

        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Engimon's World");


        Canvas canvas = new Canvas(1360,768);
        root.getChildren().add(canvas);

        GraphicsContext gd = canvas.getGraphicsContext2D();

        //gd.setFill(Color.BLUE);
        gd.drawImage(chara, 32*10+2, 32*12-5);

        primaryStage.setScene(new Scene(root));

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
