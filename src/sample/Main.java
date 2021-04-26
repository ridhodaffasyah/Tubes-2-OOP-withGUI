package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class Main extends Application {
    static private Vector<Engimon> ListOfGeneratedEngimon = new Vector<>();
    static Player P = new Player();
    static Command cmd = new Command();

    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    public static void walk(String direction){
        cmd.inputCommand(direction);
        cmd.executeCommand(ListOfGeneratedEngimon,P);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //ADD BACKSOUND START MENU
        AudioClip soundMyNoise = new AudioClip(new File("src/sound/together.mp3").toURI().toString());
        soundMyNoise.play();

        //PANE FOR START MENU
        Pane rootStart = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startmenu.fxml")));

        primaryStage.setTitle("Engimon's World!");
        Scene first = new Scene(rootStart, 1360, 768);
        primaryStage.setScene(first);

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

        //EXIT PROGRAM WHEN CLICKED EXIT
        button2.setOnAction(actionEvent -> {
            Object node = actionEvent.getSource();
            System.out.println(node instanceof Button);
            assert node instanceof Button;
            System.exit(1);
        });

        rootStart.getChildren().addAll(button, button2);

        //PANE FOR MAP
        Engimon starter = new Engimon();
        P.addEngimonPlayer(starter);
        P.setActiveEngimon(starter);
        P.setImage("/assets/chara.png");
        P.getActiveEngimon().setImage("/assets/pikachusprite.png");

        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Engimon's World");

        Canvas canvas = new Canvas(1360,768);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root,1360,768);

        GraphicsContext gd = canvas.getGraphicsContext2D();
        P.render(gd);
        starter.render(gd);

        //MOVEMENT PLAYER
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

        //PANE FOR STARTER PAGE
        Pane rootStarter = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("starterengi.fxml")));

        primaryStage.setTitle("Engimon's World!");
        Scene second = new Scene(rootStarter, 1360, 768);
        primaryStage.setScene(second);

        Image image3 = new Image("/assets/back1.png");
        ImageView imageView3 = new ImageView(image3);

        button3 = new Button("",imageView3);
        button3.setLayoutX(0);
        button3.setLayoutY(0);

        Image image4 = new Image("/assets/next1.png");
        ImageView imageView4 = new ImageView(image4);

        button4 = new Button("",imageView4);
        button4.setLayoutX(1055);
        button4.setLayoutY(0);

        //SWITCH TO STARTER PAGE
        button.setOnAction(actionEvent -> primaryStage.setScene(second));

        //SWITCH TO START MENU PAGE
        button3.setOnAction(actionEvent -> primaryStage.setScene(first));

        rootStarter.getChildren().addAll(button3, button4);

        //PANE FOR STORY PAGE
        Pane rootStory = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("story.fxml")));

        primaryStage.setTitle("Engimon's World!");
        Scene third = new Scene(rootStory, 1360, 768);
        primaryStage.setScene(third);

        Image image5 = new Image("/assets/next1.png");
        ImageView imageView5 = new ImageView(image5);

        button5 = new Button("",imageView5);
        button5.setLayoutX(1055);
        button5.setLayoutY(650);

        Text t = new Text(50, 500,"Welcome to Engimon's World! The World where Engimon is Scattered!\n\n" +
                "Pika  : Ash! I want to save my friends! meow~ \n" +
                "Ash   : Yes pika! Let's save your friend and make this world safe!\n" +
                "Pika  : Pikaaaachu!");

        t.setFill(Color.WHITE);
        t.setFont(Font.font("Arial", 25));

        //SWITCH TO STORY PAGE
        button4.setOnAction(actionEvent -> {
            AudioClip soundMyNoise2 = new AudioClip(new File("src/sound/pikasound.wav").toURI().toString());
            soundMyNoise2.play();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(third);
        });

        //SWITCH TO MAP PAGE
        button5.setOnAction(actionEvent -> {
            primaryStage.setScene(scene);
            soundMyNoise.stop();
            AudioClip soundMyNoise3 = new AudioClip(new File("src/sound/skyarrow.mp3").toURI().toString());
            soundMyNoise3.setVolume(20);
            soundMyNoise3.play();
        });

        rootStory.getChildren().addAll(t, button5);

        //RUNNING STAGE
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setScene(first);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
