package sample;

public class Piece {
    private boolean queen;
    private int row;
    private int column;
    private boolean color;
    public boolean getColor(){return color;}
    public boolean getQueen(){
        return queen;
    }
    public void setQueen(){
        this.queen=true;
    }
    public void setRow(int x){this.row=x;}
    public void setColumn(int y){this.column=y;}
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

    public Piece(boolean color, boolean queen, int row, int column){
        this.color=color;
        this.queen=queen;
        this.row=row;
        this.column=column;
    }
}
