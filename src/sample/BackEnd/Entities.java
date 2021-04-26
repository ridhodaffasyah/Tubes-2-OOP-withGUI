package sample.BackEnd;
import java.util.Vector;
import javafx.scene.canvas.GraphicsContext;

public class Entities {
    protected static Player P = new Player();
    protected static Vector<Engimon> listOfEngimon = new Vector<>();
    protected static GraphicsContext gd;

    public Entities() {}

    public Player getPlayer() {
        return P;
    }
    public Vector<Engimon> getListOfEngimon() {
        return listOfEngimon;
    }

    public void setGD(GraphicsContext _gd) {
        gd = _gd;
    }
}
