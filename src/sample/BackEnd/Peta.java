package sample.BackEnd;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Peta implements Runnable {
    public Vector<Vector<Cell>> peta = new Vector<Vector<Cell>>();
    public Vector<String> defaultMap = new Vector<String>();

    public Peta() {
        try {
//            Path currentRelativePath = Paths.get("");
//            String s = currentRelativePath.toAbsolutePath().toString();
//            System.out.println("Current relative path is: " + s);
            Scanner scanFile = new Scanner(new File("src/sample/BackEnd/map.txt"));
            String line;
            int i=0;

            // masukkan hasil scan ke peta
            while(scanFile.hasNextLine()){
                Vector<Cell> tempLine = new Vector<Cell>();
                line = scanFile.nextLine();
                defaultMap.add(line);
                
                for (int j=0; j<line.length(); j++){
                    Cell c = new Cell(i,j,line.charAt(j));
                    tempLine.add(c);
                }
                i++;
                peta.add(tempLine);
            }

            scanFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            //
            try { Thread.sleep(2000); }
            catch (Exception e) { }
        }
    }

    public static void movingWildEngimon(Vector<Engimon> listOfWildEngimon, Player P){
        Random randMove = new Random();
        int xDiff = randMove.nextInt(3) - 1;   
        int yDiff =  randMove.nextInt(3) - 1; 

        for (int i=0;i<listOfWildEngimon.size();i++){
            listOfWildEngimon.get(i).incExp(10);
            int xCurrent = listOfWildEngimon.get(i).get_posisi().getX();
            int yCurrent = listOfWildEngimon.get(i).get_posisi().getY(); 

            int xTotal = xCurrent+xDiff;
            int yTotal = yCurrent+yDiff;

            if (xTotal<0){
                xTotal=0;
            }else if(xTotal>41){
                xTotal=41;
            }

            if (yTotal<0){
                yTotal=0;
            }else if (yTotal>23){
                yTotal = 23;
            }

            boolean foundGoodPosition = false;
            while(!foundGoodPosition){
                Point fixPosition = new Point(xTotal,yTotal);
                if (!Peta.constraintEngimon(listOfWildEngimon.get(i).get_elements(), xTotal, yTotal) || !Peta.checkAvailability(fixPosition,listOfWildEngimon,P)){
                    xDiff = randMove.nextInt(3)-1;
                    yDiff = randMove.nextInt(3)-1; 
                    xTotal = xCurrent+xDiff;
                    yTotal = yCurrent+yDiff; 
                    if (xTotal<0){
                        xTotal=0;
                    }else if(xTotal>41){
                        xTotal=41;
                    }
        
                    if (yTotal<0){
                        yTotal=0;
                    }else if (yTotal>23){
                        yTotal = 23;
                    }
                    fixPosition = new Point(xTotal,yTotal);
                }else{
                    foundGoodPosition=true;
                    listOfWildEngimon.get(i).set_posisi(fixPosition);
                }
            }
        }
    }
    
    public static boolean constraintEngimon(String elements, int x, int y){
        if (elements.contains("Fire") && x<21 && y<12){
            return true;
        }else if (elements.contains("Water") && y<12 && x>20){
            return true;
        }else if ((elements.contains("Electric") || elements.contains("Ground")) && x<21 && y>11){
            return true;
        }else if (elements.contains("Ice") && x>20 && y>11){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkAvailability(Point wantToCheck, List<Engimon> listOfWildEngimon, Player P) {
        boolean retVal = true;
        int i = 0;
        while (i < listOfWildEngimon.size() && retVal) {
            if (wantToCheck.compareTo(listOfWildEngimon.get(i).get_posisi())==0) {
                retVal = false;
            }
            i++;
        }
        if (retVal) {
            if (wantToCheck.compareTo(P.getPosisiPlayer())==0 || wantToCheck.compareTo(P.getActiveEngimon().get_posisi())==0) {
                retVal = false;
            }
        }
        return retVal;
    }
    
    private void resetMap(){
        for (int i=0;i<peta.size();i++){
            for (int j=0;j<peta.get(i).size();j++){
                peta.get(i).get(j).setCellType(defaultMap.get(i).charAt(j));
            }
        }
    }

    public void printMap(Vector<Engimon> listOfWildEngimon, Player P){
        
        for (Engimon engimon : listOfWildEngimon) {
            char simbol = '\0';
            switch (engimon.get_elements()) {
                case "Fire":
                    if (engimon.get_level() == 35) {
                        simbol = 'F';
                    } else {
                        simbol = 'f';
                    }
                    break;
                case "Ground":
                    if (engimon.get_level() == 35) {
                        simbol = 'G';
                    } else {
                        simbol = 'g';
                    }
                    break;
                case "Electric":
                    if (engimon.get_level() == 35) {
                        simbol = 'E';
                    } else {
                        simbol = 'e';
                    }
                    break;
                case "Water":
                    if (engimon.get_level() == 35) {
                        simbol = 'W';
                    } else {
                        simbol = 'w';
                    }
                    break;
                case "Ice":
                    if (engimon.get_level() == 35) {
                        simbol = 'I';
                    } else {
                        simbol = 'i';
                    }
                    break;
                case "Fire/Electric":
                    if (engimon.get_level() == 35) {
                        simbol = 'L';
                    } else {
                        simbol = 'l';
                    }
                    break;
                case "Water/Ground":
                    if (engimon.get_level() == 35) {
                        simbol = 'S';
                    } else {
                        simbol = 's';
                    }
                    break;
                case "Water/Ice":
                    if (engimon.get_level() == 35) {
                        simbol = 'C';
                    } else {
                        simbol = 'c';
                    }
                    break;
            }
            peta.get(engimon.get_posisi().getX()).get(engimon.get_posisi().getY()).setCellType(simbol);

        }
        peta.get(P.getPosisiPlayer().getX()).get(P.getPosisiPlayer().getY()).setCellType('P');
        peta.get(P.getActiveEngimon().get_posisi().getX()).get(P.getActiveEngimon().get_posisi().getY()).setCellType('A');

        for (int i=0;i<peta.size();i++){
            for (int j=0;j<peta.get(i).size();j++){
                System.out.print(peta.get(i).get(j).getCellType());
            }
            System.out.println();
        }
        resetMap();
    }

    public static void main(String[] args) {
        Peta P = new Peta();
        Player player = new Player();
        Engimon E1 = new Engimon();
        player.setActiveEngimon(E1);
        Vector<Engimon> LE = new Vector<>();
        Engimon E = new Engimon();
        LE.add(E);
        P.printMap(LE, player);
        P.movingWildEngimon(LE, player);
        P.printMap(LE, player);
    }
}