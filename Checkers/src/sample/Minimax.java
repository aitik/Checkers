package sample;

import java.util.ArrayList;


public class Minimax {
    int d;
    private int turn;
    private String history="History of the best moves: \r";
    private Move best=new Move(0,0,0,0,true);
//    Controller c = new Controller();
    public Move getBest(){
        return this.best;
    }
    public Minimax(int turn, int d){
        this.turn=turn;
        this.d=d;
    }
    public double miniMax(Board board, int depth, int turn, double alpha, double beta){//-8;+8;
        if(depth==0 || board.numberOfBlackPieces()==0 || board.numberOfRedPieces()==0){
            if(board.numberOfRedPieces()==0){
                return Double.NEGATIVE_INFINITY;
            }
            if(board.numberOfBlackPieces()==0){
                return Double.POSITIVE_INFINITY;
            }
            return board.evaluate();
        }
        ArrayList<Move> possibleMoves = board.getPossibleMoves();
        if(turn==1){
            Double maxEval = Double.NEGATIVE_INFINITY;
            for(int i=0; i<possibleMoves.size();i++){
                Board b = applyMove(board, possibleMoves.get(i));
//                if(possibleMoves.size()==1 && depth==d){
//                    best = possibleMoves.get(i);
//                    return board.evaluate();
//                }
                System.out.println("doing move "+i+ " out of" + possibleMoves.size());
                double newEval= miniMax(new Board(b.getTable(), turn*-1), depth - 1, turn*(-1), alpha, beta);
                maxEval=Math.max(maxEval, newEval);
                alpha=Math.max(alpha,newEval);
                if(newEval==alpha && depth==d  && this.turn==1){
                    best = possibleMoves.get(i);
                    history+=String.valueOf(best.getStartX())+best.getStartY()+" "+String.valueOf(best.getEndX())+best.getEndY()+"\r";
                }
                if(depth==d){
                    System.out.println("done "+i+ " move out of"+possibleMoves.size());
                }
                if(beta <= alpha) {
                    System.out.println("BREAK beta alpha");
                    break;
                }
            }
            System.out.println("returning max"+maxEval);
            if(depth==d){
                System.out.println(history);
                System.out.println("THE BEST MOVE "+best.getStartX()+best.getStartY()+" "+best.getEndX()+best.getEndY());
            }
            return maxEval;
        }
        else{
            Double minEval = Double.POSITIVE_INFINITY;
            for(int i=0; i<possibleMoves.size();i++){
                Board b = applyMove(board, possibleMoves.get(i));
//                if(possibleMoves.size()==1 && depth==d){
//                    best = possibleMoves.get(i);
//                    return board.evaluate();
//                }
                System.out.println("doing "+i+ " move out of" + possibleMoves.size());
                double newEval= miniMax(new Board(b.getTable(), turn*-1), depth - 1, turn*(-1), alpha, beta);
                minEval=Math.min(minEval, newEval);
                beta=Math.min(beta,newEval);
                if(newEval==beta && depth==d  && this.turn==-1){
                    best = possibleMoves.get(i);
                    history+=String.valueOf(best.getStartX())+best.getStartY()+" "+String.valueOf(best.getEndX())+best.getEndY()+"\r";
                }
                if(depth==d){
                    System.out.println("done "+i+ " move out of" + possibleMoves.size());
                }
                if(beta <= alpha) {
                    System.out.println("BREAK BETA ALPHA -1");
                    break;
                }
            }
            System.out.println("returning min" + minEval);
            if(depth==d){
                System.out.println(history);
                System.out.println("THE BEST MOVE "+best.getStartX()+best.getStartY()+" "+best.getEndX()+best.getEndY());
            }
            return minEval;
        }
    }
    public Board applyMove(Board board, Move move){
        Table t = new Table(board.getTable());
        Tile[][] tiles2 = new Tile[8][8];
        for(int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                tiles2[i][j]=new Tile(t.getTable()[i][j].getColor(),t.getTable()[i][j].getX(), t.getTable()[i][j].getY());
                if(t.getTable()[i][j].hasPiece()){
                    tiles2[i][j].setPiece(t.getTable()[i][j].getPiece());
                }
            }
        }
        for(int i1=0; i1<8;i1++){
            for(int j=0;j<8;j++){
                if(tiles2[i1][j].hasPiece()){
                    if(tiles2[i1][j].getPiece().getColor()){
                        System.out.print("1");
                    }
                    else{
                        System.out.print("0");
                    }
                }
                else{
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println("Changed to ---->");
        int startX = move.getStartX();
        int startY =move.getStartY();
        int endX=move.getEndX();
        int endY=move.getEndY();
            if(!move.getMoveType()){

                tiles2[endX][endY].setPiece(new Piece(tiles2[startX][startY].getPiece().getColor(), tiles2[startX][startY].getPiece().getQueen(), tiles2[startX][startY].getPiece().getRow(), tiles2[startX][startY].getPiece().getColumn()));
                tiles2[endX][endY].getPiece().setRow(endX);
                tiles2[endX][endY].getPiece().setColumn(endY);
                System.out.println("Piece "+startX+startY+tiles2[startX][startY].getPiece().getRow()+tiles2[startX][startY].getPiece().getColumn()+" to "
                +endX+endY+move.getMoveType());
                tiles2[startX][startY].setPiece(null);
            }
            else{
                int tx, ty;
                if(endX>startX){
                    if(endY>startY){
                        tx=endX-1;
                        ty=endY-1;
                    }
                    else{
                        tx=endX-1;
                        ty=endY+1;
                    }
                }
                else{
                    if(endY>startY){
                        tx=endX+1;
                        ty=endY-1;
                    }
                    else{
                        tx=endX+1;
                        ty=endY+1;
                    }
                }
                tiles2[endX][endY].setPiece(new Piece(tiles2[startX][startY].getPiece().getColor(), tiles2[startX][startY].getPiece().getQueen(), tiles2[startX][startY].getPiece().getRow(), tiles2[startX][startY].getPiece().getColumn()));
                tiles2[endX][endY].getPiece().setRow(endX);
                tiles2[endX][endY].getPiece().setColumn(endY);
                System.out.println("Piece "+startX+startY+tiles2[startX][startY].getPiece().getRow()+tiles2[startX][startY].getPiece().getColumn()+" to "
                        +endX+endY+move.getMoveType());
                tiles2[startX][startY].setPiece(null);
                tiles2[tx][ty].setPiece(null);
            }
            if(tiles2[endX][endY].getPiece().getColor() && endX==0){
                tiles2[endX][endY].getPiece().setQueen();
            }
        if(!tiles2[endX][endY].getPiece().getColor() && endX==7){
            tiles2[endX][endY].getPiece().setQueen();
        }
        for(int i1=0; i1<8;i1++){
            for(int j=0;j<8;j++){
                if(tiles2[i1][j].hasPiece()){
                    if(tiles2[i1][j].getPiece().getColor()){
                        System.out.print("1");
                    }
                    else{
                        System.out.print("0");
                    }
                }
                else{
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println("END MOVE" + new Board(tiles2,1).evaluate());
        if(!tiles2[endX][endY].hasPiece()){
            System.out.println("ERROR "+startX+startY+" "+endX+endY+move.getMoveType());
            for(int i1=0; i1<8;i1++){
                for(int j=0;j<8;j++){
                    if(tiles2[i1][j].hasPiece()){
                        if(tiles2[i1][j].getPiece().getColor()){
                            System.out.print("1");
                        }
                        else{
                            System.out.print("0");
                        }
                    }
                    else{
                        System.out.print(" ");
                    }

                }
                System.out.println();
            }
            System.out.println("ERROR ENDS");
        }
        if(tiles2[endX][endY].getPiece().getColor()){
            Board b = new Board(tiles2, -1);
            return b;
        }
        else{
            Board b = new Board(tiles2, 1);
            return b;
        }
    }
}
