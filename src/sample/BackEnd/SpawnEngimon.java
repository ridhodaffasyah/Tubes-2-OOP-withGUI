package sample.BackEnd;

public class SpawnEngimon extends Entities implements Runnable {
    Thread t;
    public SpawnEngimon(){
        t = new Thread(this);
        t.start();
    }
    public void run() {
        while (true) {
            // Generate Wild Engimon
            try {
                GenerateEngimon.generateEngimon(listOfEngimon,P);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            gd.clearRect(0, 0, 1360, 768);
            P.render(gd);
            P.getActiveEngimon().render(gd);
            for (Engimon E: listOfEngimon){
                E.render(gd);
            }
            try {
                Thread.sleep(3000);
            }
            catch (Exception e) {}
        }
    }
}
