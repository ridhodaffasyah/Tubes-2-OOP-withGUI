package sample;

import sample.BackEnd.*;
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
import sample.BackEnd.Point;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        //new Image(new File("chara.png").toURI().toURL().toExternalForm());
        Image engi1 = new Image("chara.png");
//        System.out.println("Current relative path is: " + s);
//        Engimon E = new Engimon();
//        E.setImage("engi1-kanan.png");
//        E.set_posisi(new Point(13,2));

        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Engimon's World");


        Canvas canvas = new Canvas(1360,768);
        root.getChildren().add(canvas);

        GraphicsContext gd = canvas.getGraphicsContext2D();

        //gd.setFill(Color.BLUE);

        gd.drawImage(engi1,10*32+2,12*32-5);
//        E.render(gd);

        primaryStage.setScene(new Scene(root));

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
