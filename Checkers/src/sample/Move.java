package sample;

public class Move {
    private int startX, startY, endX, endY;
    private boolean moveType;
    public Move(int startX, int startY, int endX, int endY, boolean moveType){
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
        this.moveType=moveType;
    }
    public int getStartX(){return startX;}
    public int getStartY(){return startY;}
    public int getEndX(){return endX;}
    public int getEndY(){return endY;}
    public boolean getMoveType(){return moveType;}
}
