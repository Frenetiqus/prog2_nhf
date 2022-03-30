package hu.ppke.itk.java2020.maldo.hf03.chessgame.misc;

public class Coord {
    public int x;
    public int y;
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean equals(Coord b){
        return (this.x == b.x && this.y == b.y);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
