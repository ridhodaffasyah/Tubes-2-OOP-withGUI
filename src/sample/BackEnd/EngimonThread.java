package sample.BackEnd;

import java.util.Random;

public class EngimonThread extends Entities implements  Runnable {
    private Engimon thisEngimon;
    private boolean killed;
    Thread t;

    public EngimonThread(Engimon e) {
        thisEngimon = e;
        killed = false;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (!killed) {
            //
            moveThisEngimon();
            gd.clearRect(0, 0, 1360, 768);
            thisEngimon.render(gd);
            P.render(gd);
            P.getActiveEngimon().render(gd);
            for (Engimon E: listOfEngimon){
                E.render(gd);
            }
            try {
                Thread.sleep(2500);
            }
            catch (Exception e) {}
            finally {
                if (thisEngimon.get_life() == 0) {
                    killed = true;
                }
            }
        }
        // killed
        t.interrupt();
    }

    public void moveThisEngimon() {
        Random randMove = new Random();
        int xDiff = randMove.nextInt(3) - 1;
        int yDiff =  randMove.nextInt(3) - 1;

        thisEngimon.incExp(40);
        int xCurrent = thisEngimon.get_posisi().getX();
        int yCurrent = thisEngimon.get_posisi().getY();

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
            if (!Peta.constraintEngimon(thisEngimon.get_elements(), xTotal, yTotal) || !Peta.checkAvailability(fixPosition,listOfEngimon,P)){
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
            }else{
                foundGoodPosition=true;
                thisEngimon.set_posisi(fixPosition);
            }
        }
    }
}
