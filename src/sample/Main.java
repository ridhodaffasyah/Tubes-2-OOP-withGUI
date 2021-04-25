package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    static private Vector<Engimon> ListOfGeneratedEngimon = new Vector<Engimon>();
    static Player P = new Player();
    static Command cmd = new Command();

    public static void walk(String direction){
        cmd.inputCommand(direction);
        cmd.executeCommand(ListOfGeneratedEngimon,P);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Engimon starter = new Engimon();
        P.addEngimonPlayer(starter);
        P.setActiveEngimon(starter);
        P.setImage("/assets/chara.png");
        P.getActiveEngimon().setImage("/assets/engi1-kanan.png");

        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Engimon's World");

        Canvas canvas = new Canvas(1360,768);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root,1360,768);

        GraphicsContext gd = canvas.getGraphicsContext2D();
        P.render(gd);
        starter.render(gd);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                gd.clearRect(0,0,1360,768);
                if (keyEvent.getCode()==KeyCode.W){
                    cmd.inputCommand("left");
                    cmd.executeCommand(ListOfGeneratedEngimon,P);
                }else if (keyEvent.getCode()==KeyCode.A){
                    cmd.inputCommand("up");
                    cmd.executeCommand(ListOfGeneratedEngimon,P);
                }else if (keyEvent.getCode()==KeyCode.S){
                    cmd.inputCommand("right");
                    cmd.executeCommand(ListOfGeneratedEngimon,P);
                }else if (keyEvent.getCode()==KeyCode.D){
                    cmd.inputCommand("down");
                    cmd.executeCommand(ListOfGeneratedEngimon,P);
                }
                P.render(gd);
                P.getActiveEngimon().render(gd);
            }
        });



        primaryStage.setScene(scene);

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

//    private void handleKeyPress(KeyEvent e){
//        if (e.getCode()==KeyCode.W){
//            cmd.inputCommand("up");
//            cmd.executeCommand(ListOfGeneratedEngimon,P);
//        }else if (e.getCode()==KeyCode.A){
//            cmd.inputCommand("left");
//            cmd.executeCommand(ListOfGeneratedEngimon,P);
//        }else if (e.getCode()==KeyCode.S){
//            cmd.inputCommand("down");
//            cmd.executeCommand(ListOfGeneratedEngimon,P);
//        }else if (e.getCode()==KeyCode.D) {
//            cmd.inputCommand("right");
//            cmd.executeCommand(ListOfGeneratedEngimon, P);
//        }
//
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
