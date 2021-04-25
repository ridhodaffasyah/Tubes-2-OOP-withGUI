package sample.BackEnd;

import java.util.Scanner;
import java.util.Vector;

public class Command {
    protected String currentCommand;
    //protected Peta map;

    public Command(){}

    public void inputCommand() {
        System.out.print(">>> ");
        Scanner command = new Scanner(System.in);
        currentCommand = command.nextLine().strip();
        System.out.println(currentCommand);
    }

    public void executeCommand(Vector<Engimon> listOfWildEngimon, Player P){
        try{
            switch (currentCommand) {
                case "up", "down", "left", "right" -> {
                    resolvePlayerNewPosition(currentCommand,P);
                }
    
                case "talk" -> P.interactActiveEngimon();
    
                case "battle" -> {
                    Engimon enemy = findWildEngi(listOfWildEngimon,P);
                    System.out.println("Ketemu Engimon Liar!\n");
                    enemy.displayEngiInfo();
                    System.out.print("\nLawan engimon? (y/n)");
                    Scanner scan = new Scanner(System.in);
                    String confirm = scan.nextLine();
                    while (!confirm.equals("y") && !confirm.equals("n")){
                        System.out.println("Input Error!");
                        System.out.print("\nLawan engimon? (y/n)");
                        confirm = scan.nextLine();
                    }
                    
                    if (confirm.equals("y")){
                        battleBetween(enemy, P, listOfWildEngimon);
                    }
                    break;
                }
                case "breeding" -> {
                    P.displayAllEngimon();
                    System.out.println("Pilih engimon yang ingin di-breeding!");
                    System.out.println("Masukkan ID engimon 1: ");
                    int id1;
                    Scanner id = new Scanner(System.in);
                    id1 = id.nextInt();
                    System.out.println("Masukkan ID engimon 2: ");
                    int id2;
                    Scanner idd = new Scanner(System.in);
                    id2 = idd.nextInt();
                    Engimon hasilBreeding = Breeding.makeBreeding(P.findEngimon(id1), P.findEngimon(id2));
                    P.addEngimonPlayer(hasilBreeding);
                    System.out.println("Yeay punya anak!\n");
                }
                case "showallengimon" -> P.displayAllEngimon();
    
                case "showallskillitem" -> P.displayAllSkillItem();
    
                case "switchactiveengimon" -> P.switchActiveEngimon();
    
                case "learnskill" -> P.learnSkill();
    
                case "showspecificengimon" -> P.displaySpecificEngimon();

                case "deleteengimon" -> P.deleteEngimonInteractive();

                case "deleteskill" -> P.deleteSkill();

                case "changeengimonname" -> P.changeName();
                
                default -> System.out.println("\nCOMMAND INVALID\n");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    private float elementAdvChart(String elemen1, String elemen2){
        return switch (elemen1) {
            case "Fire" -> switch (elemen2) {
                case "Fire", "Electric" -> 1.0F;
                case "Water" -> 0.0F;
                case "Ground" -> 0.5F;
                default -> 2.0F;
            };
            case "Water" -> switch (elemen2) {
                case "Fire" -> 2.0F;
                case "Water" -> 1.0F;
                case "Electric" -> 0.0F;
                case "Ground" -> 1.0F;
                default -> 1.0F;
            };
            case "Electric" -> switch (elemen2) {
                case "Fire", "Electric" -> 1.0F;
                case "Water" -> 2.0F;
                case "Ground" -> 0.0F;
                default -> 1.5F;
            };
            case "Ground" -> switch (elemen2) {
                case "Fire" -> 1.5F;
                case "Water", "Ground" -> 1.0F;
                case "Electric" -> 2.0F;
                default -> 0.0F;
            };
            default -> switch (elemen2) {
                case "Fire" -> 0.0F;
                case "Water" -> 1.0F;
                case "Electric" -> 0.5F;
                case "Ground" -> 2.0F;
                default -> 1.0F;
            };
        };
    }

    private Vector<String> elementsParser(String elements){
        Vector<String> retVal = new Vector<>();
        if (elements.contains("/")) {
            String[] parser = elements.split("/", 0);
            for (String a : parser) {
                assert false;
                retVal.add(a);
            }
            return retVal;
        }
        else{
            retVal.add(elements);
            return retVal;
        }
    }

    //engimon1 dibandingkan dengan engimon2, mereturn elementadv untuk engimon1
    private float getElementsAdvantage(Engimon engimon1, Engimon engimon2){
        Vector<String> elementsEngimon1 = elementsParser(engimon1.get_elements());
        Vector<String> elementsEngimon2 = elementsParser(engimon2.get_elements());
        float max = 0;

        for (String s : elementsEngimon1) {
            for (String value : elementsEngimon2) {
                if (elementAdvChart(s, value) > max) {
                    max = elementAdvChart(s, value);
                }
            }
        }
        return max;
    }

    private Engimon findWildEngi(Vector<Engimon> listOfWildEngimon, Player P) throws CommandException{
        for (Engimon engimon : listOfWildEngimon) {
            if (Point.isAdjacent(engimon.get_posisi().getX(), engimon.get_posisi().getY(), P.getPosisiPlayer().getX(), P.getPosisiPlayer().getY())) {
                return engimon;
            }
        }
        throw new CommandException("Tidak ada engimon liar di dekatmu!");
    }
    
    private void battleBetween(Engimon eng2, Player currentPlayer, Vector<Engimon> listOfWildEngimon){

        Engimon eng1 = currentPlayer.getActiveEngimon();

        //Mendapatkan nilai elements advantage
        float eng1ElemenAdv = getElementsAdvantage(eng1,eng2);
        float eng2ElemenAdv = getElementsAdvantage(eng2,eng1);

        //Mendapatkan SUM(Basepower * mastery level)
        float totalPower1 = (float) (eng1.get_level() * eng1ElemenAdv) + eng1.get_sum_power();
        float totalPower2 = (float) (eng2.get_level() * eng2ElemenAdv) + eng2.get_sum_power();

        System.out.println("Power Engi1: " + totalPower1);
        System.out.println("Power Engi2: " + totalPower2);

        if ( totalPower1 < totalPower2){
            //decrement Life
            eng1.decLife();
            System.out.println(eng1.get_name() + " Telah Kalah! Sisa life engimonmu: " +eng1.get_life());
            
            //pengecekan apakah life 0 atau tidak
            if (eng1.get_life()==0){
                currentPlayer.deleteEngimonPlayer(eng1);
            }else{
                System.out.print("\nIngin mengganti active engimon? (y/n)");
                Scanner scan = new Scanner(System.in);
                String confirm = scan.nextLine();
                while (!confirm.equals("y") && !confirm.equals("n")){
                    System.out.println("Error input!\n");
                    System.out.print("\nIngin mengganti active engimon? (y/n)");
                    confirm = scan.nextLine();
                }
                if (confirm.equals("y")){
                    currentPlayer.switchActiveEngimon();
                }
            }

            // pengecekan apakah ingin battle lagi atau tidak
            System.out.print("\nIngin melawan lagi? (y/n)");
            Scanner scan = new Scanner(System.in);
            String confirm = scan.nextLine();
            while (!confirm.equals("y") && !confirm.equals("n")){
                System.out.println("Error input!\n");
                System.out.print("\nIngin melawan lagi? (y/n)");
                confirm = scan.nextLine();
            }
            if (confirm.equals("y")){
                battleBetween(eng2, currentPlayer, listOfWildEngimon);
            }
        }
        else{
            System.out.println(eng1.get_name() + " Telah Menang! ");

            //xp nambah
            int alive = eng1.incExp((int) (totalPower1-totalPower2));
            if (alive==1){
                System.out.println("\nEngimon anda telah mencapai level 50 dan akan mati!");
                currentPlayer.deleteEngimonPlayer(eng1);
            }

            //drop item
            try {
                Skill dropSkill = currentPlayer.checkOwned(eng2.getEngiSkill().get(0));
                System.out.print("Item Drop!\n");
                System.out.println("Nama: " + dropSkill.getNamaSkill());
                currentPlayer.skillInventory.addThing(dropSkill);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            //mendapatkan eng2
            for (int i=0;i<listOfWildEngimon.size();i++){
                if (listOfWildEngimon.get(i).get_id()==eng2.get_id()){
                    Engimon dropEngimon = listOfWildEngimon.remove(i); //hapus ke-i
                    currentPlayer.addEngimonPlayer(dropEngimon);
                    System.out.println("Selamat, kamu mendapatkan " + dropEngimon.get_species());
                }
            }
        }
    }

    private boolean isPositionValid(Point P) {
        int x = P.getX();
        int y = P.getY();
        return (x >= 0) && (x < 10) && (y >= 0) && (y < 12);
    }

    private void resolvePlayerNewPosition(String command, Player P) {
        Point oldPosition = P.getPosisiPlayer();
        int xDif = 0;
        int yDif = 0;

        if (command.equals("up") || command.equals("UP")) {
            xDif = -1;
        }
        if (command.equals("down") || command.equals("DOWN")){
            xDif = 1;
        }
        if (command.equals("left") || command.equals("LEFT")) {
            yDif = -1;
        }
        if (command.equals("right") || command.equals("RIGHT")) {
            yDif = 1;
        }

        Point newPosition = new Point(oldPosition.getX()+xDif, oldPosition.getY()+yDif);
        if (isPositionValid(newPosition)) {
            P.setPosisiPlayer(newPosition);
            moveActiveEngimon(oldPosition,P);
        }
    }

    private void moveActiveEngimon(Point oldPlayerPosition,Player P) {  // Exception handling belom
        P.getActiveEngimon().set_posisi(oldPlayerPosition);
    }

    public static void main(String[] args) {
        Command C = new Command();
        Peta Pt = new Peta();
        Player Pl = new Player();
        Engimon E = new Engimon();
        Pl.addEngimonPlayer(E);
        Pl.setActiveEngimon(E);
        Vector<Engimon> VE = new Vector<Engimon>();

        Pt.printMap(VE, Pl);
        C.inputCommand();
        C.executeCommand(VE,Pl);
        Pt = new Peta();
        Pt.printMap(VE, Pl);
        System.out.println(C.elementsParser("Electric/Fire"));
    }
}
