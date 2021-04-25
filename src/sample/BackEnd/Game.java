package sample.BackEnd;

import java.io.IOException;
import java.util.Vector;

public class Game {
    private Player P;
    private Peta Map;
    private Command cmd;
    private Vector<Engimon> ListOfWildEngimon;

    public Game(){
        ListOfWildEngimon = new Vector<Engimon>();

        P = new Player();
        Engimon starter = new Engimon();
        P.addEngimonPlayer(starter);
        P.setActiveEngimon(starter);

        Map = new Peta();
        cmd = new Command();
    }

    public void run(){
        Map.printMap(ListOfWildEngimon, P);
//        cmd.inputCommand();
        cmd.executeCommand(ListOfWildEngimon, P);
        Map.movingWildEngimon(ListOfWildEngimon, P);
        try {
            GenerateEngimon.generateEngimon(ListOfWildEngimon, P);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        while (true){
            game.run();
        }
    }
}
