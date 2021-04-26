package sample.BackEnd;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GenerateEngimon extends Generate{
    public static void generateEngimon(List<Engimon> listOfWildEngimon, Player P) throws IOException {
        Vector<Vector<String>> vectorOfEngimon = readFromFile("Engimon.txt");
        Random randEngi = new Random();
        int randIdx = randEngi.nextInt(24);
        
        if (listOfWildEngimon.size()<20){
            Point defaultPoint = new Point(0,0);
            int defaultLevel = randEngi.nextInt(11) + P.getHighestLevel();  //tambahin level engi wild
            Engimon generatedEngimon = new Engimon(vectorOfEngimon.get(randIdx).get(0),defaultLevel, defaultPoint);

            int xRandom = randEngi.nextInt(42);
            int yRandom =  randEngi.nextInt(24);

            boolean foundGoodPosition = false;
            while(!foundGoodPosition){
                Point fixPosition = new Point(xRandom,yRandom);
                if (!Peta.constraintEngimon(generatedEngimon.get_elements(), xRandom, yRandom) || !Peta.checkAvailability(fixPosition,listOfWildEngimon,P)){
                    xRandom = randEngi.nextInt(42);
                    yRandom = randEngi.nextInt(24);
                    fixPosition = new Point(xRandom,yRandom);
                }else{
                    foundGoodPosition=true;
                    generatedEngimon.set_posisi(fixPosition);
                }
            }
            if (generatedEngimon.get_species().equals("Dragon")){
                generatedEngimon.setImage("/assets/dragon.png");
            }else if (generatedEngimon.get_species().equals("Cat")){
                generatedEngimon.setImage("/assets/cat.png");
            }else if (generatedEngimon.get_species().equals("Bird")){
                generatedEngimon.setImage("/assets/engi5-depan.png");
            }else if (generatedEngimon.get_species().equals("Fish")){
                generatedEngimon.setImage("/assets/fish.png");
            }else if (generatedEngimon.get_species().equals("Octopus")){
                generatedEngimon.setImage("/assets/octopus.png");
            }else if (generatedEngimon.get_species().equals("Plant")){
                generatedEngimon.setImage("/assets/engi6-depan.png");
            }else if (generatedEngimon.get_species().equals("Worm")){
                generatedEngimon.setImage("/assets/worm.png");
            }else if (generatedEngimon.get_species().equals("Tiger")){
                generatedEngimon.setImage("/assets/tiger.png");
            }
            listOfWildEngimon.add(generatedEngimon);
        }
    }

    public static void main(String[] args) {
        try {
            GenerateEngimon.generateEngimon(new Vector<Engimon>(), new Player());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
