package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
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
    private Vector<Engimon> ListOfGeneratedEngimon = new Vector<>();
    private Player P = new Player();
    private Command cmd = new Command();

    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    Scene first,second,third,scene,battle,invecntorySkill;
    Pane root,rootStart,rootBattle,rootStarter,rootStory,rootSkill;

    private void inventorySkill(Stage primaryStage){

    }

    private void battle(Engimon engi1, Engimon engi2, Stage primaryStage){
        //Interactive Battle
        ToolBar containerButton = new ToolBar();
        containerButton.setLayoutX(197);containerButton.setLayoutY(299);
        Button switchEngi = new Button("Switch Engimon");
        Button goBattle = new Button("Battle");
        Button run = new Button("Run");
        containerButton.getItems().addAll(switchEngi,goBattle,run);
        rootBattle.getChildren().add(containerButton);

        //UI
        ImageView containerPicEngi1 = new ImageView();containerPicEngi1.setLayoutX(84);containerPicEngi1.setLayoutY(71);
        containerPicEngi1.setFitHeight(150);containerPicEngi1.setFitWidth(111);
        ImageView containerPicEngi2 = new ImageView();containerPicEngi2.setLayoutX(418);containerPicEngi2.setLayoutY(71);
        containerPicEngi2.setFitHeight(150);containerPicEngi2.setFitWidth(111);

        Image picEngi1 = P.getActiveEngimon().getImage();containerPicEngi1.setImage(picEngi1);
        Image picEngi2 = engi2.getImage();containerPicEngi2.setImage(picEngi2);

        Text namaEngi1 = new Text(82,276,engi1.get_name() +" Lvl: "+ engi1.get_level());namaEngi1.setFont(Font.font("Arial",20));
        Text namaEngi2 = new Text(413,276,engi2.get_species() +" Lvl: "+ engi2.get_level());namaEngi2.setFont(Font.font("Arial",20));

        Text powerEngi1 = new Text(85,50,"DEFAULT");powerEngi1.setFill(Color.WHITE);powerEngi1.setFont(Font.font("Arial",18));
        Text powerEngi2 = new Text(416,50,"DEFAULT");powerEngi2.setFill(Color.WHITE);powerEngi2.setFont(Font.font("Arial",18));

        Text interactiveMsg = new Text(221,32,"DEFAULT");
        interactiveMsg.setFont(Font.font("Arial",12));

        rootBattle.getChildren().addAll(containerPicEngi1,containerPicEngi2,namaEngi1,namaEngi2);

        goBattle.setOnAction(actionEvent -> {
            cmd.battleBetween(engi2,P,ListOfGeneratedEngimon,interactiveMsg,powerEngi1,powerEngi2);
            rootBattle.getChildren().addAll(interactiveMsg,powerEngi1,powerEngi2);
        });

        run.setOnAction(actionEvent -> {
            primaryStage.setScene(scene);
            rootBattle.getChildren().removeAll(containerPicEngi1,containerPicEngi2,namaEngi1,namaEngi2,interactiveMsg);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //ADD BACKSOUND START MENU
        AudioClip soundMyNoise = new AudioClip(new File("src/sound/together.mp3").toURI().toString());
        soundMyNoise.play();

        //PANE FOR START MENU
        rootStart = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/startmenu.fxml")));

        primaryStage.setTitle("Engimon's World!");
        first = new Scene(rootStart, 1360, 768);
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

        //PANE FOR BATTLE
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Template/battle.fxml"));
        rootBattle = loader.load();

        battle = new Scene(rootBattle,600,340);

        //PANE FOR MAP
        Engimon starter = new Engimon();
        P.addEngimonPlayer(starter);
        P.setActiveEngimon(starter);
        P.setImage("/assets/chara.png");
        P.getActiveEngimon().setImage("/assets/pikachusprite.png");

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/map.fxml")));

        Canvas canvas = new Canvas(1360,768);
        root.getChildren().add(canvas);

        scene = new Scene(root,1360,768);

        GraphicsContext gd = canvas.getGraphicsContext2D();
        P.render(gd);
        starter.render(gd);

            //MOVEMENT PLAYER and Wild Engimon
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // Reset gambar setiap waktu
                gd.clearRect(0,0,1360,768);

                // Generate Wild Engimon
                try {
                    GenerateEngimon.generateEngimon(ListOfGeneratedEngimon,P);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

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
                }else if (keyEvent.getCode()==KeyCode.I){
                    primaryStage.setScene(invecntorySkill);
                }else if (keyEvent.getCode()==KeyCode.B){
                    try{
                        Engimon engi2 = cmd.findWildEngi(ListOfGeneratedEngimon,P);
                        battle(P.getActiveEngimon(),engi2,primaryStage);
                        primaryStage.setScene(battle);
                    }catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                Peta.movingWildEngimon(ListOfGeneratedEngimon,P);

                P.render(gd);
                P.getActiveEngimon().render(gd);
                for (Engimon E: ListOfGeneratedEngimon){
                    E.render(gd);
                }
            }
        });

        //PANE FOR STARTER PAGE
        rootStarter = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/starterengi.fxml")));

        second = new Scene(rootStarter, 1360, 768);

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
        rootStory = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/story.fxml")));

        third = new Scene(rootStory, 1360, 768);

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

        //INVENTORY SKILL PANE
        rootSkill =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/inventoryskill.fxml")));
        invecntorySkill = new Scene(rootSkill,1360,768);

        ScrollPane containerSkill = new ScrollPane();
        containerSkill.setLayoutX(228);containerSkill.setLayoutY(159);
        containerSkill.setPrefSize(917,489);

        rootSkill.getChildren().add(containerSkill);

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
