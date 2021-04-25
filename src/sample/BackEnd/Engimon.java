
import sample.Peta.Point;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Engimon implements Comparable<Engimon>{
    //protected atribut
    /*1*/ protected String name;
    /*2*/ protected int life = 3;
    /*3.1*/ protected String parentsName;
    /*3.2*/ protected String species;
    /*4*/ protected List<EngimonSkill> engiSkill = new Vector<>();
    /*5*/ protected String elements;
    /*6*/ protected int level;
    /*7*/ protected int exp;
    /*8*/ protected int cumExp;
    
    protected final int id = numOfEngimon+1;
    protected Point posisi;

    //static atribut
    protected static int numOfEngimon = 0;
    
    // public Method
    /* Constructor */
    public Engimon(){
        //ini bakal buat engimon starter aja
        name = "Pikachu";
        this.species = "Cat";
        this.level = 1;
        this.posisi = new Point(1,0);
        elements = "Electric";
        parentsName = "Pika";
        numOfEngimon = numOfEngimon+1;
        exp = 0;
        cumExp = level*100;
        String[] list_skill = new String[4];
        list_skill[0] = "Electric Ball";
        list_skill[1] = "Electric Claw";
        list_skill[2] = "Electric Slash";
        list_skill[3] = "Giga Volt";
        int[] list_basePower = new int[4];
        list_basePower[0] = 65;
        list_basePower[1] = 105;
        list_basePower[2] = 140;
        list_basePower[3] = 185;
        for (int i = 0; i < 4; i++) {
            EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
            engiSkill.add(skill);
        }
    }
    public Engimon(Engimon engi){
        this.name = engi.name;
        this.parentsName = engi.parentsName;
        this.species = engi.species;
        this.level = engi.level;
        exp = engi.exp;
        cumExp = level*100;
        this.posisi = engi.posisi;
        this.elements = engi.elements;
        this.engiSkill = engi.engiSkill;
        this.life = engi.life;
        numOfEngimon = numOfEngimon+1;
    }

    public Engimon(String species, int level, Point posisi){
        name = "NONE";
        parentsName = "NONE";
        this.species = species;
        this.level = level;
        exp = 0;
        cumExp = level*100;
        this.posisi = posisi;

        numOfEngimon = numOfEngimon+1;

        switch (species) {
            case "Dragon", "dragon" -> set_elements("Fire");
            case "Cat", "cat" -> set_elements("Electric");
            case "Bird", "bird" -> set_elements("Water/Ice");
            case "Fish", "fish" -> set_elements("Water");
            case "Octopus", "octopus" -> set_elements("Ice");
            case "Plant", "plant" -> set_elements("Ground");
            case "Worm", "worm" -> set_elements("Water/Ground");
            case "Tiger", "tiger" -> set_elements("Fire/Electric");
        }

        switch (species) {
            case "Dragon", "dragon" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Fire Ball";
                list_skill[1] = "Fire Claw";
                list_skill[2] = "Fire Breathe";
                list_skill[3] = "Fire Punch";
                int[] list_basePower = new int[4];
                list_basePower[0] = 50;
                list_basePower[1] = 100;
                list_basePower[2] = 150;
                list_basePower[3] = 180;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Cat", "cat" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Electric Ball";
                list_skill[1] = "Electric Claw";
                list_skill[2] = "Electric Slash";
                list_skill[3] = "Giga Volt";
                int[] list_basePower = new int[4];
                list_basePower[0] = 65;
                list_basePower[1] = 105;
                list_basePower[2] = 140;
                list_basePower[3] = 185;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Bird", "bird" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Ice Ball";
                list_skill[1] = "Ice Smash";
                list_skill[2] = "Water Spread";
                list_skill[3] = "Water Bomb";
                int[] list_basePower = new int[4];
                list_basePower[0] = 45;
                list_basePower[1] = 60;
                list_basePower[2] = 130;
                list_basePower[3] = 190;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Fish", "fish" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Water Ball";
                list_skill[1] = "Water Float";
                list_skill[2] = "Water Spread";
                list_skill[3] = "Water Bomb";
                int[] list_basePower = new int[4];
                list_basePower[0] = 48;
                list_basePower[1] = 65;
                list_basePower[2] = 130;
                list_basePower[3] = 190;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Octopus", "octopus" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Ice Ball";
                list_skill[1] = "Ice Smash";
                list_skill[2] = "Ice Punch";
                list_skill[3] = "Ice Bomb";
                int[] list_basePower = new int[4];
                list_basePower[0] = 55;
                list_basePower[1] = 75;
                list_basePower[2] = 145;
                list_basePower[3] = 180;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Plant", "plant" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Ground Ball";
                list_skill[1] = "Ground Punch";
                list_skill[2] = "Ground Smash";
                list_skill[3] = "Ground Bomb";
                int[] list_basePower = new int[4];
                list_basePower[0] = 60;
                list_basePower[1] = 80;
                list_basePower[2] = 145;
                list_basePower[3] = 180;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Worm", "worm" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Water Ball";
                list_skill[1] = "Ground Punch";
                list_skill[2] = "Water Spread";
                list_skill[3] = "Ground Bomb";
                int[] list_basePower = new int[4];
                list_basePower[0] = 48;
                list_basePower[1] = 80;
                list_basePower[2] = 130;
                list_basePower[3] = 180;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
            case "Tiger", "tiger" -> {
                String[] list_skill = new String[4];
                list_skill[0] = "Fire Ball";
                list_skill[1] = "Electric Claw";
                list_skill[2] = "Fire Breathe";
                list_skill[3] = "Giga Volt";
                int[] list_basePower = new int[4];
                list_basePower[0] = 50;
                list_basePower[1] = 105;
                list_basePower[2] = 150;
                list_basePower[3] = 185;
                for (int i = 0; i < 4; i++) {
                    EngimonSkill skill = new EngimonSkill(list_skill[i], list_basePower[i], this.elements);
                    engiSkill.add(skill);
                }
            }
        }
    }
    /* Getter */
    public String get_name(){
        return this.name;
    }
    public int get_life(){
        return life;
    }
    public String get_parentsName(){
        return this.parentsName;
    }
    public String get_species(){
        return this.species;
    }
    public List<EngimonSkill> getEngiSkill() {
        return engiSkill;
    }
    public String get_elements(){
        return this.elements;
    }
    public int get_level(){
        return this.level;
    }
    public int get_exp(){
        return exp;
    }
    public int get_cumExp(){
        return cumExp;
    }
    public final int get_id(){
        return this.id;
    }
    public Point get_posisi(){
        return this.posisi;
    }//get_posisiX dan get_posisiY diapus biar lewat getX sama getY dari Point aja
    public static int get_numOfEngimon(){
        return numOfEngimon;
    }
    public float get_sum_power(){
        float sum=0;
        for (EngimonSkill S:engiSkill){
            sum+=S.getBasePower()*S.getMasteryLevel();
        }
        return sum;
    }
    
    

    /* Setter */
    public void set_name(){
        String name;
        System.out.println("Masukkan nama engimon : ");
        Scanner nama = new Scanner(System.in);
        name = nama.nextLine();
        this.name = name;
    }
    public void set_parentsName(String pN){
        this.parentsName = pN;
    }
    public void set_species(String species){
        this.species = species;
    }
    public void set_elements(String elements){
        this.elements = elements;
    }
    public void set_level(int level){
        this.level = level;
    }
    public void set_exp(int exp){
        this.exp = exp;
    }
    public void set_cumExp(int cE){
        this.cumExp = cE;
    }
    public void set_posisi(Point P){
        posisi = P;     //bisa gini aja ngga sih?
    }

    //Dapet Exp, return 1 jika setelah mendapatkan exp levelnya melebihi max level (50). Return 0 jika sebaliknya.
    public int incExp(int expGet){
        exp += expGet;
        if (exp>=100){
            set_level(level + (exp/100) );
            exp = exp%100;
            if (level>=50){
                return 1;
            }
        }
        return 0;
    }

    public void decLife(){
        life--;
    }
    
    public void displayEngiInfo(){
        System.out.println("~~ Profile Engimon ~~");
        System.out.println("Id : " + get_id());
        System.out.println("Name : " + get_name());
        System.out.println("Parents Name : " + get_parentsName());
        System.out.println("Life : " + get_life());
        System.out.println("Species : " + get_species());
        System.out.println("Elements : " + get_elements());
        System.out.println("Level : " + get_level());
        System.out.println("Posisi : " + "(" + get_posisi().getX() + ", " + get_posisi().getY() + ")");
        System.out.println("Skill : ");
        for (int i = 0 ; i < engiSkill.size(); i++) {
            System.out.println(i+1 + ". " + engiSkill.get(i).getNamaSkill() + ", Base Power : " + engiSkill.get(i).getBasePower());
        }
    }
    
    @Override
    public int compareTo(Engimon o) {
        int currentEngiElement;
        int comparerEngiElement;
        switch (this.get_elements()) {
            case "Fire":
                currentEngiElement=8;
                break;
        
            case "Electric":
                currentEngiElement = 7;
                break;

            case "Water/Ice":
                currentEngiElement = 6;
                break;
                
            case "Water":
                currentEngiElement = 5;
                break;
                
            case "Fire/Electic":
                currentEngiElement = 4;
                break;
                
            case "ice":
                currentEngiElement = 3;
                break;
                
            case "Ground":
                currentEngiElement = 2;
                break;
                
            case "Water/Ground":
                currentEngiElement = 1;
                break;
                
            default:
                currentEngiElement = 0;
                break;
        }

        switch (o.get_elements()) {
            case "Fire":
                comparerEngiElement=8;
                break;
        
            case "Electric":
                comparerEngiElement = 7;
                break;

            case "Water/Ice":
                comparerEngiElement = 6;
                break;
                
            case "Water":
                comparerEngiElement = 5;
                break;
                
            case "Fire/Electic":
                comparerEngiElement = 4;
                break;
                
            case "ice":
                comparerEngiElement = 3;
                break;
                
            case "Ground":
                comparerEngiElement = 2;
                break;
                
            case "Water/Ground":
                comparerEngiElement = 1;
                break;
                
            default:
                comparerEngiElement =0;
                break;
        }

        if (currentEngiElement>comparerEngiElement){
            return 1;
        }else if (currentEngiElement==comparerEngiElement){
            if (this.get_level()>o.get_level()){
                return 1;
            }else if (this.get_level()==o.get_level()){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

}
