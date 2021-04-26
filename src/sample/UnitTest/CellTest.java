package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell testCellType = new Cell();
    private Cell testCellType2 = new Cell(3,3,'P');

    @Test
    public void CellTestSuccess() {
        Cell tempCellType = new Cell();
        assertEquals(tempCellType, testCellType);
    }

    @Test
    public void CellTestFail() {
        Cell tempCellType = new Cell();
        assertNotEquals(tempCellType, testCellType2);
    }

    @Test
    public void CellWithParamTestSuccess() {
        Cell tempCellType2 = new Cell(3,3,'P');
        assertEquals(tempCellType2, testCellType2);
    }

    @Test
    public void CellWithParamTestFail() {
        Cell tempCellType2 = new Cell(3,3,'P');
        assertNotEquals(tempCellType2, testCellType);
    }

    @Test
    public void getCellTypeTestSuccess() {
        assertEquals('X', testCellType.getCellType());
    }

    @Test
    public void getCellTypeTestFail() {
        assertNotEquals('X', testCellType2.getCellType());
    }

    @Test
    public void setCellTypeTestSuccess() {
        testCellType.setCellType('P');
        assertEquals('P', testCellType.getCellType());
    }

    @Test
    public void setCellTypeTestFail() {
        testCellType.setCellType('P');
        assertNotEquals('X', testCellType.getCellType());
    }
}