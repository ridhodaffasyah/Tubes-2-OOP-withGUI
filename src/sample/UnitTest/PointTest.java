package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    private Point testPoint = new Point();

    @Test
    public void PointTestSuccess() {
        assertEquals(0, testPoint.getX());
        assertEquals(0, testPoint.getY());
    }

    @Test
    public void PointTestFail() {
        assertEquals(-1, testPoint.getX());
        assertEquals(-1, testPoint.getY());
    }

    @Test
    public void PointTestWithParamTestSuccess() {
        int x, y;
        x = 5;
        y = 7;
        Point testPoint2 = new Point(x, y);
        assertEquals(5, testPoint2.getX());
        assertEquals(7, testPoint2.getY());
    }

    @Test
    public void PointTestWithParamTestFail() {
        int x, y;
        x = 5;
        y = 7;
        Point testPoint2 = new Point(x, y);
        assertEquals(7, testPoint2.getX());
        assertEquals(5, testPoint2.getY());
    }

    @Test
    public void getXTestSuccess() {
        assertEquals(0, testPoint.getX());
    }

    @Test
    public void getXTestFail() {
        assertNotEquals(0, testPoint.getX());
    }

    @Test
    public void getYTestSuccess() {
        assertEquals(0, testPoint.getY());
    }

    @Test
    public void getYTestFail() {
        assertNotEquals(0, testPoint.getY());
    }

    @Test
    public void setXTestSuccess() {
        testPoint.setX(5);
        assertEquals(5, testPoint.getX());
    }

    @Test
    public void setXTestFail() {
        testPoint.setX(5);
        assertNotEquals(5, testPoint.getX());
    }

    @Test
    public void setYTestSuccess() {
        testPoint.setY(5);
        assertEquals(5, testPoint.getY());
    }

    @Test
    public void setYTestFail() {
        testPoint.setY(5);
        assertNotEquals(5, testPoint.getY());
    }

    @Test
    public void setTestSuccess() {
        testPoint.set(5,7);
        assertEquals(5, testPoint.getX());
        assertEquals(7, testPoint.getY());
    }

    @Test
    public void setTestFail() {
        testPoint.set(5,7);
        assertNotEquals(7, testPoint.getX());
        assertNotEquals(5, testPoint.getY());
    }

    @Test
    public void compareToTestSuccess() {
        Point tempPoint = new Point(0,0);
        assertNotEquals(0, testPoint.compareTo(tempPoint));
    }

    @Test
    public void compareToTestFail() {
        Point tempPoint = new Point(10,10);
        assertNotEquals(1, testPoint.compareTo(tempPoint));
    }
}