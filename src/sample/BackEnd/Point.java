
public class Point implements Comparable<Point> {
    protected int x;
    protected int y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point(Point P){
        this.x = P.x;
        this.y = P.y;
    }
    public static boolean isValidPosisi(int x, int y){
        return (x>=0 && x<10 && y>=0 && y<12);
    }
    public static boolean isAdjacent(int _x1, int _y1, int _x2, int _y2) {
        return (_x1 == _x2 + 1 && _y1 == _y2) || (_x1 == _x2 - 1 && _y1 == _y2) || (_x1 == _x2 && _y1 == _y2 + 1) || (_x1 == _x2 && _y1 == _y2 - 1);
    }

    @Override
    public int compareTo(Point o) {
        if (x == o.getX() && y == o.getY()){
            return 0;   //sama
        } 
        else{
            return 1;   //tidak sama
        }
    }
}
