package win.simple;

public class Location extends World{
    private int X = 0;
    private int Y = 0;

    public Location() {

    }

    public Location(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setLocation(int X, int Y) {
        WorldGrid[this.X][this.Y] = null;
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String toString() {
        return "{X:" + X + "Y:" + Y + "}";
    }
}
