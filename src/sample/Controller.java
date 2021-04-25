package sample;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Controller {

        // <== perhaps you had this missing??
        @FXML
    void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                Main.walk("up");
                break;
            case A:
                Main.walk("left");
                break;
            case S:
                Main.walk("down");
                break;
            case D:
                Main.walk("right");
                break;
            default:
                break;
        }
    }
}
