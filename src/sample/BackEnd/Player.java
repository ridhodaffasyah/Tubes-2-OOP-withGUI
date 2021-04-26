package sample.BackEnd;

import javafx.scene.canvas.GraphicsContext;
import sample.Sprite;

import java.util.*;

public class Player extends Sprite {

    // public String inputCommandMove;
    protected Point posisi;
    protected Engimon activeEngimon;
    public Inventory<Engimon> engiInventory = new Inventory<Engimon>();
    public Inventory<Skill> skillInventory = new Inventory<Skill>();

    /* Constructor */
    public Player() {
        posisi = new Point(0,0);
    }

    /* Getter and Setter */
    public Engimon getActiveEngimon(){
        return activeEngimon;
    }

    public void setActiveEngimon(Engimon EP){
        activeEngimon = EP;
    }

    public Point getPosisiPlayer(){
        return this.posisi;
    }

    public void setPosisiPlayer(Point P){
        this.posisi = P;
    }

    public Inventory<Engimon> getEngiInventory(){
        return engiInventory;
    }

    public Inventory<Skill> getSkillInventory(){
        return skillInventory;
    }
    
    /* Main Method */

    //i. Menampilkan list dari commands yang tersedia, KAYAKNYA DI KELAS COMMAND AJA
    
    //ii. Bergerak ke satu petak ke kiri, kanan, atas, atau bawah, KAYAKNYA DI KELAS COMMAND AJA
    
    //iii. Menampilkan list engimon yang dimiliki, TODO CHECK INVENTORY
    public void displayAllEngimon(){
        engiInventory.getThings().forEach((k,v) -> {
            
        });

        for (Engimon i : engiInventory.getThings().keySet()){
            System.out.println("\n======================================");
            System.out.println("ID: " + i.get_id());
            System.out.println("Nama: " +  i.get_name());
            System.out.println("Elemen: " +  i.get_elements());
            System.out.println("Level: " + i.get_level());
            System.out.println("======================================\n");
        }
    }
    
    /*
    iv. Menampilkan data lengkap suatu engimon di inventory (setiap atribut kelas dan setiap atribut skillnya) 
        1. Juga harus menampilkan nama parent beserta spesies mereka
    */
    public void displaySpecificEngimon(){
        System.out.print("Masukkan ID Engimon yang ingin dilihat : ");
        int id;
        Scanner o = new Scanner(System.in);
        id = o.nextInt();
        Engimon tempEngi;
        try {
            tempEngi = findEngimon(id);
            tempEngi.displayEngiInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        
    }
        
    //v. Mengecek dan mengganti active engimon
    public void switchActiveEngimon(){
        System.out.println("Masukkan ID engimon : ");
        int idEngi;
        Scanner input = new Scanner(System.in);
        idEngi = input.nextInt();

        Engimon newActive;
        try {
            newActive = findEngimon(idEngi);
            newActive.set_posisi(getActiveEngimon().get_posisi());
            getActiveEngimon().set_posisi(new Point(-1,-1));

            setActiveEngimon(newActive);
            System.out.println(newActive.get_name() + " berhasil menjadi aktif!\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
    
    //vi. Menampilkan list skill item yang dimiliki beserta informasi mengenai atribut skill tersebut (base power dan elemen yang bisa learn skill tersebut)
    public void displayAllSkillItem(){
        SortedMap<Skill, Integer> Test = skillInventory.getThings();
        int num=1;
        for (Skill i : Test.keySet()){
            System.out.println("\n"+num + ". Item : " + i.getNamaSkill());
            System.out.println("   Base Power : " + i.getBasePower());
            System.out.println("   Elemen dibutuhkan : " + i.getUnique());
            System.out.println("   Jumlah saat ini : " + Test.get(i));
            num++;
        }
    }
    
    //vii. Menggunakan skill item pada suatu engimon

    public void learnSkillTo(Engimon E, Skill S){
        if (checkCompatibility(E, S)){
            E.displayEngiInfo();
            boolean sama = false;
            while(!sama){
                System.out.print("\nMasukkan nama skill Engimon yang ingin anda replace: ");
                Scanner remove = new Scanner(System.in);
                String skillRemoved = remove.nextLine();
                //replace skill
                for (int i=0;i<4;i++){
                    if (E.getEngiSkill().get(i).getNamaSkill().equals(skillRemoved)){
                        E.getEngiSkill().remove(i);
                        E.getEngiSkill().add(new EngimonSkill(S));
                        sama= true;
                        try {       //hapus skill yang telah dipakai
                            getSkillInventory().deleteThing(S);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                if (!sama){
                    System.out.println("Oops, masukan anda salah. Ulangi.\n");
                }
            }
            System.out.println("Skill berhasil di-replace!\n\n");
        }else{
            System.out.println("Element engimon dengan element skill tidak compatible!");
        }
    }

    public void learnSkill(){
        displayAllSkillItem();
        boolean sama = false;
        while(!sama){
            // Pilih skill yang ingin diajarkan
            System.out.print("Masukkan nama skill yang ingin anda pakai: ");
            Scanner scan = new Scanner(System.in);
            String namaSkill = scan.nextLine();
            for (Skill value : getSkillInventory().getThings().keySet()) {
                if (value.getNamaSkill().equals(namaSkill)) {
                    
                    // Pilih Engimon yang ingin diajarkan
                    displayAllEngimon();
                    System.out.println("Masukkan id Engimon yang ingin anda beri skill: ");
                    int id;
                    Scanner d = new Scanner(System.in);
                    id = d.nextInt();
                    Engimon currentEngimon;

                    try {
                        currentEngimon = findEngimon(id);
                        learnSkillTo(currentEngimon, value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    // Ketemu
                    sama = true;
                }
            }
            if (!sama){
                System.out.println("Oops, masukan anda salah. Ulangi.\n");
            }
        }
    }
    
    //viii. Melaksanakan breeding antara 2 engimon, KAYAKNYA DI KELAS COMMAND AJA
    
    /*
    ix. Melakukan battle dengan suatu engimon yang berada didekatnya (adjacent tiles) yaitu satu petak di sebelah kiri, kanan, atas, dan bawah. KAYAKNYA DI KELAS COMMAND AJA
        1. Ketika ingin melakukan battle, tampilkan detail engimon lawan ke layar
    Untuk flow UI dan tampilan UI dibebaskan asalkan dapat mendukung delapan kebutuhan diatas. Beberapa command akan dijelaskan lebih detail di poin-poin selanjutnya
    */

    //x. Membuang X amount dari suatu skill item atau melepaskan engimon inventory, TODO
    public void deleteSkill(){
        displayAllSkillItem();
        System.out.print("\nMasukkan nama skill yang ingin dihapus: ");
        Scanner scan = new Scanner(System.in);
        String namaSkill = scan.nextLine();
        try {
            Skill wantToBeDelete = findSkillItem(namaSkill);
            System.out.print("\nMasukkan kuantitas yang ingin dihapus: ");
            int qty = scan.nextInt();
            while (qty>getSkillInventory().getThings().get(wantToBeDelete)){
                System.out.println("\nKuantitas yang ingin dihapus tidak sesuai! Ulangi.");
                System.out.print("\nMasukkan kuantitas yang ingin dihapus: ");
                qty = scan.nextInt();
            }
            for (int i=0;i<qty;i++){
                getSkillInventory().deleteThing(wantToBeDelete);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    public void deleteEngimonInteractive(){
        displayAllEngimon();
        System.out.print("\nMasukkan ID Engimon yang ingin dilepas: ");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        try {
            Engimon engiDeleted = findEngimon(id);
            deleteEngimonPlayer(engiDeleted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEngimonPlayer(Engimon o){
        try {
            if (o.equals(getActiveEngimon())){
                System.out.println("Engimon yang ingin dihapus merupakan active engimon!");
                System.out.println("Ganti active engimon anda terlebih dahulu!\n");
                switchActiveEngimon();
            }
            boolean berhasil = engiInventory.deleteThing(o);
            if (berhasil){
                System.out.println(o.get_name() + "berhasil dilepaskan!");
            }else{
                System.out.println("Engimon tidak ditemukan!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //xi. Mengganti nama dari suatu engimon yang ada di inventory, TODO
    public void changeName(){
        displayAllEngimon();
        System.out.print("\nMasukkan ID engimon yang ingin diubah namanya: ");
        Scanner scan = new Scanner(System.in);
        int idEngi = scan.nextInt();
        try {
            Engimon dstEngi = findEngimon(idEngi);
            System.out.println("\nNama sekarang adalah " + dstEngi.get_name());
            System.out.print("\nMasukkan nama yang baru!\n");
            dstEngi.set_name();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
    //xii. Save game, KAYAKNYA DI KELAS TERPISAH AJA

    /* Method Bantuan */
    public Skill findSkillItem(String namaSkill) throws PlayerException{
        for (Skill S: getSkillInventory().getThings().keySet()){
            if (S.getNamaSkill().equals(namaSkill)){
                return S;
            }
        }
        throw new PlayerException("Skill item " + namaSkill + " tidak ada!");
    }

    public Engimon findEngimon(int id) throws PlayerException{
        if (engiInventory.getThings().size()==0){
            throw new PlayerException("Inventory Engimon kosong!");
        } else {
            SortedMap<Engimon, Integer> tempMap = engiInventory.getThings();
            for (Engimon i : tempMap.keySet()) {
                if (i.get_id() == id) {
                    return i;
                }
            }
        }
        throw new PlayerException("Engimon dengan ID: "+ id +" Tidak ditemukan!");
    }

    public void interactActiveEngimon(){
        switch (activeEngimon.get_species()) {
            case "Dragon" -> System.out.println("[" + activeEngimon.get_name() + "]: Hhhuuaaaa....");  // ganti aja gaes gatau isi slogan apa
            case "Cat" -> System.out.println("[" + activeEngimon.get_name() + "]: Meoowww.. meoww....");  // ganti aja gaes gatau isi slogan apa
            case "Bird" -> System.out.println("[" + activeEngimon.get_name() + "]: Ciiuuiitt...");  // ganti aja gaes gatau isi slogan apa
            case "Fish" -> System.out.println("[" + activeEngimon.get_name() + "]: Mblututk...  blututk...");  // ganti aja gaes gatau isi slogan apa
            case "Octopus" -> System.out.println("[" + activeEngimon.get_name() + "]: Gatau ini suaranya...");  // ganti aja gaes gatau isi slogan apa
            case "Plant" -> System.out.println("[" + activeEngimon.get_name() + "]: ini ga bersuara:(");  // ganti aja gaes gatau isi slogan apa
            case "Worm" -> System.out.println("[" + activeEngimon.get_name() + "]: ini gatau juga");  // ganti aja gaes gatau isi slogan apa
            case "Tiger" -> System.out.println("[" + activeEngimon.get_name() + "]: Hrrgghhhh... Hauunngggg....");  // ganti aja gaes gatau isi slogan apa
        }
    }
    
    public void addSkillItem(Skill skill){
        try {
            skillInventory.addThing(skill);
            System.out.println(skill.getNamaSkill() + " berhasil ditambahkan!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEngimonPlayer(Engimon engi){
        try {
            engiInventory.addThing(engi);
            engi.set_isOwnedByPlayer(true);
            System.out.println(engi.get_name() + " berhasil ditambahkan!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private boolean checkCompatibility(Engimon E,Skill S){
        String[] engiType = E.get_elements().split("/");
        System.out.println(engiType[0]);
        if (engiType[0].equals(S.getUnique())){
            return true;
        }else{
            if (engiType.length==2 && engiType[1].equals(S.getUnique())){
                return true;
            }
            return false;
        }
    }

    public Skill checkOwned(Skill S){
        for (Skill skillOwned: skillInventory.getThings().keySet()){
            if (skillOwned.getNamaSkill().equals(S.getNamaSkill())){
                return skillOwned;
            }
        }
        return new Skill(S);
    }

    public int getHighestLevel(){
        int max=0;
        for (Engimon engi: engiInventory.getThings().keySet()){
            if (engi.get_level()>max){
                max = engi.get_level();
            }
        }
        return max;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image,32*getPosisiPlayer().getX()+2, 32*getPosisiPlayer().getY()-5);
    }

    // public static void main(String[] args){
    //     Player P = new Player();
    //     Engimon EP1 = new Engimon();
    //     // Skill S1 = new Skill("Fireball", "Fire", 50);
    //     // try {
    //     //     P.getSkillInventory().deleteThing(S1);
    //     // } catch (Exception e) {
    //     //     // TODO Auto-generated catch block
    //     //     System.out.println(e.getMessage());
    //     // }
    //     Skill S1 = new Skill("Fireball", "Fire", 50);
    //     Skill S2 = new Skill("Water Gun", "Water", 100);
    //     Skill S3 = new Skill("Water Ice", "Ice", 75);
    //     Skill S4 = new Skill("Electric Punch", "Electric", 75);
    //     P.addSkillItem(S1);
    //     P.addSkillItem(S1);
    //     P.deleteSkill();
    //     P.displayAllSkillItem();
    //     // P.addEngimonPlayer(EP1);
    //     // // P.displaySpecificEngimon();
    //     // // P.addEngimonPlayer(EP1);
    //     // P.displayAllEngimon();
    //     // P.addSkillItem(S1);
    //     // P.addSkillItem(S2);
    //     // P.addSkillItem(S3);
    //     // // P.deleteEngimonPlayer(EP1);
    //     // // P.displayAllEngimon();
    //     // P.displayAllSkillItem();
    //     // // P.learnSkillTo(EP1, S4);
    //     // P.learnSkill();
    // }

}
