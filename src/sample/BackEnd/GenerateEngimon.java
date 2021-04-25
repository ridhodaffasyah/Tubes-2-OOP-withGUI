
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GenerateEngimon extends Generate{
    public static void generateEngimon(List<Engimon> listOfWildEngimon, Player P) throws IOException {
        Vector<Vector<String>> vectorOfEngimon = readFromFile("Engimon.txt");
        Random randEngi = new Random();
        int randIdx = randEngi.nextInt(24);
        
        if (listOfWildEngimon.size()<8){
            Point defaultPoint = new Point(0,0);
            int defaultLevel = randEngi.nextInt(11) + P.getHighestLevel();  //tambahin level engi wild
            Engimon generatedEngimon = new Engimon(vectorOfEngimon.get(randIdx).get(0),defaultLevel, defaultPoint);

            int xRandom = randEngi.nextInt(10);
            int yRandom =  randEngi.nextInt(12);  

            boolean foundGoodPosition = false;
            while(!foundGoodPosition){
                Point fixPosition = new Point(xRandom,yRandom);
                if (!Peta.constraintEngimon(generatedEngimon.get_elements(), xRandom, yRandom) || !Peta.checkAvailability(fixPosition,listOfWildEngimon,P)){
                    xRandom = randEngi.nextInt(10);
                    yRandom = randEngi.nextInt(12);  
                    fixPosition = new Point(xRandom,yRandom);
                }else{
                    foundGoodPosition=true;
                    generatedEngimon.set_posisi(fixPosition);
                }
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
