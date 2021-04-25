package sample;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

import java.io.File;

public abstract class Sprite
{
    protected Image image;

    public Sprite() { }

    public void setImage(String filename)
    {
        image = new Image(filename);
    }

    public abstract void render(GraphicsContext gc);

}