package sample;

public class Tile {
    private Piece piece = null;
    private boolean color;
    private int x;
    private int y;


    public boolean getColor() {
        return color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean hasPiece() {
        return piece != null;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Tile(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
    }
}
