package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Vector;

public class Main extends Application {
    static private Vector<Engimon> ListOfGeneratedEngimon = new Vector<>();
    static Player P = new Player();
    static Command cmd = new Command();

    Button button;
    Button button2;

    public static void walk(String direction){
        cmd.inputCommand(direction);
        cmd.executeCommand(ListOfGeneratedEngimon,P);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane rootStart = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startmenu.fxml")));

        primaryStage.setTitle("Engimon's World!");
        primaryStage.setScene(new Scene(rootStart, 1360, 768));

        Image image = new Image("/assets/mulai.png");
        ImageView imageView = new ImageView(image);

        button = new Button("",imageView);
        button.setLayoutX(520);
        button.setLayoutY(500);

        Image image2 = new Image("/assets/exit1.png");
        ImageView imageView2 = new ImageView(image2);

        button2 = new Button("",imageView2);
        button2.setLayoutX(520);
        button2.setLayoutY(620);

        //button.setOnAction(actionEvent -> primaryStage.setScene());

        button2.setOnAction(actionEvent -> {
            Object node = actionEvent.getSource();
            System.out.println(node instanceof Button);
            assert node instanceof Button;
            System.exit(1);
        });

        rootStart.getChildren().addAll(button, button2);

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

        button.setOnAction(actionEvent -> primaryStage.setScene(scene));

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
