package sample.BackEnd;

public class Cell extends Point{
    protected char cellType;
    
    public Cell(){
        super();
        this.cellType = 'X';
    }
    public Cell(int x, int y, char cellType){
        super(x, y);
        this.cellType = cellType;
    }
    public char getCellType(){
        return cellType;
    }
    public void setCellType(char c){
        cellType = c;
    }

}
