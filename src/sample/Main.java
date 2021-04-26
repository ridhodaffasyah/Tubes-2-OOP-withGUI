package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import sample.BackEnd.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class Main extends Application {
    private Entities entities = new Entities();
    private Vector<Engimon> ListOfGeneratedEngimon = entities.getListOfEngimon();
    private Player P = entities.getPlayer();
    private Command cmd = new Command();

    AudioClip soundMyNoise;
    AudioClip soundMyNoise2;
    AudioClip soundMyNoise3;

    Button button,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16,button17;
    Scene first,second,third,scene,battle,menu,settings,engi,invecntorySkill;
    Pane root,rootStart,rootBattle,rootStarter,rootStory,rootMenu,rootSettings,rootEngi,rootSkill;

    private void InventoryEngi(Stage primaryStage) {
        GridPane gp = new GridPane();

        int count = 0;
        for (Engimon e : P.getEngiInventory().getThings().keySet()) {
            ImageView e1 = new ImageView(e.getImage());
            e1.setFitWidth(120);
            e1.setFitHeight(100);
            Popup popup = new Popup();
            popup.setX(200);
            popup.setY(200);

            GridPane gd2 = new GridPane();
            ScrollPane sp2 = new ScrollPane();

            Text engiID = new Text("Id : " + e.get_id() + "\n");
            Text engiName = new Text("Name : " + e.get_name() + "\n");
            Text engiParent = new Text("Parents Name : " + e.get_parentsName() + "\n");
            Text engiLife = new Text("Life : " + e.get_life() + "\n");
            Text engiSpecies = new Text("Species : " + e.get_species() + "\n");
            Text engiElements = new Text("Elements : " + e.get_elements() + "\n");
            Text engiLevel = new Text("Level : " + e.get_level() + "\n");
            Text engiPosisi = new Text("Posisi : " + "(" + e.get_posisi().getX() + ", " + e.get_posisi().getY() + ")" + "\n");
            Text engiSkill = new Text("Skill : " + "\n");

            gd2.add(engiID, 0, 0);
            gd2.add(engiName, 0, 1);
            gd2.add(engiParent, 0, 2);
            gd2.add(engiLife, 0, 3);
            gd2.add(engiSpecies, 0, 4);
            gd2.add(engiElements, 0, 5);
            gd2.add(engiLevel, 0, 6);
            gd2.add(engiPosisi, 0, 7);
            gd2.add(engiSkill, 0, 8);

            for (int i = 0; i < e.getEngiSkill().size(); i++) {
                Text iterateSkill = new Text(i + 1 + ". " + e.getEngiSkill().get(i).getNamaSkill() + ", Base Power : " + e.getEngiSkill().get(i).getBasePower() + "\n");
                gd2.add(iterateSkill, 0, 9 + i);
            }

            sp2.setContent(gd2);
            popup.getContent().addAll(sp2);

            button17 = new Button("", e1);
            button17.setOnAction(actionEvent -> {
                popup.show(primaryStage);
            });
            gp.add(button17, count, 0);
            count = count + 1;
        }

        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(1000, 500);
        sp.setLayoutX(120);
        sp.setLayoutY(150);
        sp.setContent(gp);

        rootEngi.getChildren().add(sp);
    }

    private void inventorySkill(Stage primaryStage){
        //container semua skill
        GridPane gp = new GridPane();
        gp.setPrefSize(884,455);
        gp.setLayoutX(410);gp.setLayoutY(160);
        gp.setGridLinesVisible(true);

        int i=0;
        for (Skill S: P.getSkillInventory().getThings().keySet()){
            String pathImage = "/assets/Skill/"+S.getNamaSkill()+".png";
            S.setImage(pathImage);

            ImageView iconSkill = new ImageView(S.getImage());
            iconSkill.setFitWidth(100);iconSkill.setFitHeight(100);

            gp.add(iconSkill,0,i);
            gp.add(new Text("       "+S.getNamaSkill()+"       "),1,i);
            gp.add(new Text("       Element: "+S.getUnique()+"       "),2,i);
            gp.add(new Text("       Base Power: "+S.getBasePower()+"       "),3,i);
            gp.add(new Text("       Qty: "+P.getSkillInventory().getThings().get(S).toString()+"       "),4,i);

            Button useSkill = new Button("use");
            gp.add(useSkill,5,i);

            TextField entryID = new TextField();
            Popup chooseEngi = new Popup();
            chooseEngi.getContent().add(entryID);

            EventHandler<ActionEvent> event =
                    new EventHandler<ActionEvent>() {

                        public void handle(ActionEvent e) {
                            if (!chooseEngi.isShowing()){
                                chooseEngi.show(primaryStage);
                            }else {
                                int idEngimon = Integer.parseInt(entryID.getText());
                                try{
                                    Engimon wantTobeLearned = P.findEngimon(idEngimon);
                                    Text succesOrNot = new Text("DEFAULT");

                                    if (P.learnSkillTo(wantTobeLearned,S)){
                                        succesOrNot.setText(("BERHASIL"));
                                        succesOrNot.setFill(Color.GREEN);succesOrNot.setFont(Font.font(20));
                                    }else{
                                        succesOrNot.setText("GAGAL");
                                        succesOrNot.setFill(Color.RED);succesOrNot.setFont(Font.font(20));
                                    }
                                    succesOrNot.setLayoutX(900);succesOrNot.setLayoutY(650);
                                    rootSkill.getChildren().add(succesOrNot);
                                    primaryStage.setScene(invecntorySkill);

                                }catch(Exception err){
                                    System.out.println(err.getMessage());
                                }
                                chooseEngi.hide();
                            }
                        }
                    };

            useSkill.setOnAction(event);
            i++;
        }
        rootSkill.getChildren().add(gp);

        invecntorySkill.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    rootSkill.getChildren().remove(gp);
                    primaryStage.setScene(scene);
                }
            }
        });
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
            rootBattle.getChildren().removeAll(containerPicEngi1,containerPicEngi2,namaEngi1,namaEngi2,interactiveMsg,powerEngi1,powerEngi2);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //ADD BACKSOUND START MENU
        soundMyNoise = new AudioClip(new File("src/sound/together.mp3").toURI().toString());
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
        this.entities.setGD(gd);
        P.render(gd);
        starter.render(gd);

        SpawnEngimon s = new SpawnEngimon();

            //MOVEMENT PLAYER and Wild Engimon
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // Reset gambar setiap waktu
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
                }else if (keyEvent.getCode()==KeyCode.B){
                    try{
                        Engimon engi2 = cmd.findWildEngi(ListOfGeneratedEngimon,P);
                        battle(P.getActiveEngimon(),engi2,primaryStage);
                        primaryStage.setScene(battle);
                    }catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }else if (keyEvent.getCode()==KeyCode.ESCAPE){
                    primaryStage.setScene(menu);
                }

                // Peta.movingWildEngimon(ListOfGeneratedEngimon,P);

                P.render(gd);
                P.getActiveEngimon().render(gd);
                for (Engimon E: ListOfGeneratedEngimon){
                    E.render(gd);
                }
            }
        });
        //PANE FOR MENU

        rootMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/showallengi.fxml")));

        menu = new Scene(rootMenu, 1360, 768);

        Image image6 = new Image("/assets/x.png");
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitHeight(50);
        imageView6.setFitWidth(50);

        button6 = new Button("", imageView6);
        button6.setMaxHeight(50);
        button6.setMaxWidth(50);

        button6.setOnAction(actionEvent -> primaryStage.setScene(scene));

        Text b7 = new Text("Inventory of Engimon");
        b7.setFont(Font.font("Arial Black", 12));
        button7 =  new Button("",b7);
        Insets i = new Insets(10,30,10,30);
        button7.setPadding(i);
        button7.setLayoutX(580);
        button7.setLayoutY(260);

        button7.setOnAction(actionEvent -> {
            primaryStage.setScene(engi);
            InventoryEngi(primaryStage);
        });

        Text b8 = new Text("Inventory of Skill");
        b8.setFont(Font.font("Arial Black", 12));
        button8 =  new Button("",b8);
        Insets i2 = new Insets(10,43,10,43);
        button8.setPadding(i2);
        button8.setLayoutX(580);
        button8.setLayoutY(340);

        button8.setOnAction(actionEvent -> {
            primaryStage.setScene(invecntorySkill);
            inventorySkill(primaryStage);
        });

        Text b9 = new Text("Settings");
        b9.setFont(Font.font("Arial Black", 12));
        button9 =  new Button("",b9);
        Insets i3 = new Insets(10,72,10,72);
        button9.setPadding(i3);
        button9.setLayoutX(580);
        button9.setLayoutY(420);

        button9.setOnAction(actionEvent -> primaryStage.setScene(settings));

        Text b10 = new Text("Back to Start Menu");
        b10.setFont(Font.font("Arial Black", 12));
        button10 =  new Button("",b10);
        Insets i4 = new Insets(10,37,10,37);
        button10.setPadding(i4);
        button10.setLayoutX(580);
        button10.setLayoutY(500);

        button10.setOnAction(actionEvent -> {
            primaryStage.setScene(first);
            soundMyNoise3.stop();
            soundMyNoise.play();
        });

        Text gameMenu = new Text(500,100, "Game Menu");
        gameMenu.setFont(Font.font("Polya", 60));

        rootMenu.getChildren().addAll(gameMenu,button6, button7, button8, button9, button10);

        //PANE FOR SETTINGS MENU

        rootSettings = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/settings.fxml")));

        settings = new Scene(rootSettings, 1360, 768);

        Text gameSettings = new Text(560,100, "Settings");
        gameSettings.setFont(Font.font("Polya", 60));

        Image image7 = new Image("/assets/back.png");
        ImageView imageView7 = new ImageView(image7);
        imageView7.setFitHeight(50);
        imageView7.setFitWidth(50);

        button11 = new Button("", imageView7);
        button11.setMaxHeight(50);
        button11.setMaxWidth(50);

        button11.setOnAction(actionEvent -> primaryStage.setScene(menu));

        Text b12 = new Text("Turn On Music");
        b12.setFont(Font.font("Arial Black", 12));
        button12 =  new Button("",b12);
        Insets i5 = new Insets(10,45,10,45);
        button12.setPadding(i5);
        button12.setLayoutX(580);
        button12.setLayoutY(260);

        button12.setOnAction(actionEvent -> {
            if (!soundMyNoise3.isPlaying()){
                soundMyNoise3.play();
            }
        });

        Text b13 = new Text("Turn Off Music");
        b13.setFont(Font.font("Arial Black", 12));
        button13 =  new Button("",b13);
        Insets i6 = new Insets(10,45,10,45);
        button13.setPadding(i5);
        button13.setLayoutX(580);
        button13.setLayoutY(340);

        button13.setOnAction(actionEvent -> {
            soundMyNoise3.stop();
        });

        Text b14 = new Text("Set to Fullscreen");
        b14.setFont(Font.font("Arial Black", 12));
        button14 =  new Button("",b14);
        Insets i7 = new Insets(10,38,10,38);
        button14.setPadding(i7);
        button14.setLayoutX(580);
        button14.setLayoutY(420);

        button14.setOnAction(actionEvent -> primaryStage.setFullScreen(true));

        rootSettings.getChildren().addAll(gameSettings, button11, button12, button13, button14);

        //PANE FOR INVENTORY ENGI
        rootEngi = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/inventoryengi.fxml")));

        engi = new Scene(rootEngi, 1360, 768);

        Text gameInvenEngi = new Text(100,100, "Inventory Engimon");
        gameInvenEngi.setFont(Font.font("Polya", 60));

        Image image8 = new Image("/assets/back.png");
        ImageView imageView8 = new ImageView(image8);
        imageView8.setFitHeight(50);
        imageView8.setFitWidth(50);

        button15 = new Button("", imageView8);
        button15.setMaxHeight(50);
        button15.setMaxWidth(50);

        button15.setOnAction(actionEvent -> primaryStage.setScene(menu));

        Text b16 = new Text("Breeding");
        b16.setFont(Font.font("Arial Black", 12));
        button16 =  new Button("",b16);
        Insets i8 = new Insets(10,38,10,38);
        button16.setPadding(i8);
        button16.setLayoutX(700);
        button16.setLayoutY(50);

        rootEngi.getChildren().addAll(gameInvenEngi, button15,button16);

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

        Text t = new Text(50, 500, """
                Welcome to Engimon's World! 
                The World where Engimon is Scattered!

                Pika  : Ash! I want to save my friends! meow~\s
                Ash   : Yes pika! Let's save your friend and make this world safe!
                Pika  : Pikaaaachu!""");

        t.setFill(Color.WHITE);
        t.setFont(Font.font("Arial", 25));

        //SWITCH TO STORY PAGE
        button4.setOnAction(actionEvent -> {
            soundMyNoise2 = new AudioClip(new File("src/sound/pikasound.wav").toURI().toString());
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
            soundMyNoise3 = new AudioClip(new File("src/sound/skyarrow.mp3").toURI().toString());
            soundMyNoise3.setVolume(20);
            soundMyNoise3.play();
        });

        rootStory.getChildren().addAll(t, button5);


        //INVENTORY SKILL PANE
        rootSkill =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Template/inventoryskill.fxml")));
        invecntorySkill = new Scene(rootSkill,1360,768);


        //RUNNING STAGE
        primaryStage.setFullScreen(false);
        primaryStage.setScene(first);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
