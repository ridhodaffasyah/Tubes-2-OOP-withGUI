package sample;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

import java.io.File;

public abstract class Sprite
{
    protected Image image;
    private double width;
    private double height;

    public Sprite() { }

    public void setImage(Image i)
    {
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        image = new Image(new File(filename).toURI().toString());

    }

//    public void setPosition(int x, int y)
//    {
//        positionX = x;
//        positionY = y;
//    }

//    public void update(int xDiff, int yDiff)
//    {
//        positionX += xDiff;
//        positionY += yDiff;
//    }

    public abstract void render(GraphicsContext gc);

//    public Rectangle2D getBoundary()
//    {
//        return new Rectangle2D(positionX,positionY,width,height);
//    }
//
//    public boolean intersects(Sprite s)
//    {
//        return s.getBoundary().intersects( this.getBoundary() );
//    }
//
//    public String toString()
//    {
//        return " Position: [" + positionX + "," + positionY + "]"
//                + " Velocity: [" + velocityX + "," + velocityY + "]";
//    }
}