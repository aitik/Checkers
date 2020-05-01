package sample;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {
    private Tile[][] table;
    private ArrayList<Piece> redPieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    private ArrayList<Move> possibleMoves = new ArrayList<Move>();
    private int turn;
    private double evaluation;

    public Tile[][] getTable(){
        return this.table;
    }
    public ArrayList<Piece> getRedPieces(){
        return redPieces;
    }
    public ArrayList<Piece> getBlackPieces(){
        return blackPieces;
    }
    public void setBoard(Tile[][] t, int turn){
        this.table = t;
        this.turn = turn;
        this.possibleMoves.clear();
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if(this.table[i][j].hasPiece()){
                    if(this.table[i][j].getPiece().getColor()){
                        this.redPieces.add(table[i][j].getPiece());
                    }
                    else if(!this.table[i][j].getPiece().getColor()){
                        this.blackPieces.add(this.table[i][j].getPiece());
                    }
                }
            }
        }
    }
    public ArrayList<Move> getPossibleMoves(){
        if(turn==1){
            for(Piece p:redPieces){
                int oldX = p.getRow();
                int oldY = p.getColumn();
                if(!p.getQueen()){
                    if(oldY<7){
                        if (!table[oldX - 1][oldY + 1].hasPiece()) {
                            possibleMoves.add(new Move(oldX, oldY, oldX - 1, oldY + 1, false));
                        }else {
                            if (table[oldX - 1][oldY + 1].hasPiece() && !table[oldX - 1][oldY + 1].getPiece().getColor() && oldY<6 && oldX>1) {
                                if(!table[oldX-2][oldY+2].hasPiece()){
                                    possibleMoves.add(new Move(oldX,oldY,oldX-2,oldY+2,true));
                                }
                            }
                        }
                    }
                    if(oldY>0){
                        if (!table[oldX - 1][oldY - 1].hasPiece()) {
                            possibleMoves.add(new Move(oldX, oldY, oldX - 1, oldY - 1, false));
                        }else {
                            if (table[oldX - 1][oldY - 1].hasPiece() && !table[oldX - 1][oldY - 1].getPiece().getColor() && oldY>1 && oldX>1) {
                                if(!table[oldX-2][oldY-2].hasPiece()){
                                    possibleMoves.add(new Move(oldX,oldY,oldX-2,oldY-2,true));
                                }
                            }
                        }
                    }
                }else{
                    int cx, cy;
                    if(oldX==0){
                        for(int i=1;i<oldY+1;i++){
                            cx=oldX+i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8-oldY;i++){
                            cx=oldX+i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                    if(oldX>0 && oldX<7){
                        for(int i=1;i<oldY+1;i++){
                            cx=oldX+i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX+i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                            System.out.println("arr2");
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                System.out.println("arr3");
                                break;
                            }
                        }
                    }
                    if(oldX==7){
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {

                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){

                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(turn==-1){
            for(Piece p:blackPieces){
                int oldX = p.getRow();
                int oldY = p.getColumn();
                if(!p.getQueen()){
                    if(oldY<7){
                        if (!table[oldX + 1][oldY + 1].hasPiece()) {
                            possibleMoves.add(new Move(oldX, oldY, oldX + 1, oldY + 1, false));
                        }else {
                            if (table[oldX + 1][oldY + 1].hasPiece() && table[oldX + 1][oldY + 1].getPiece().getColor() && oldY<6 && oldX<6) {
                                if(!table[oldX+2][oldY+2].hasPiece()){
                                    possibleMoves.add(new Move(oldX,oldY,oldX+2,oldY+2,true));
                                }
                            }
                        }
                    }
                    if(oldY>0){
                        if (!table[oldX + 1][oldY - 1].hasPiece()) {
                            possibleMoves.add(new Move(oldX, oldY, oldX + 1, oldY - 1, false));
                        }else {
                            if(oldY>1 && oldX<6){
                                if (table[oldX + 1][oldY - 1].hasPiece() && table[oldX + 1][oldY - 1].getPiece().getColor()) {
                                    if(!table[oldX+2][oldY-2].hasPiece()){
                                        possibleMoves.add(new Move(oldX,oldY,oldX+2,oldY-2,true));
                                    }
                                }
                            }
                        }
                    }
                }else{
                    int cx, cy;
                    if(oldX==0){
                        for(int i=1;i<oldY+1;i++){
                            cx=oldX+i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8-oldY;i++){
                            cx=oldX+i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                    if(oldX>0 && oldX<7){
                        for(int i=1;i<oldY+1;i++){
                            cx=oldX+i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX+i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx + 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx+1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                    if(oldX==7){
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY+i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy + 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy + 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {

                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){

                                break;
                            }
                        }
                        for(int i=1;i<8;i++){
                            cx=oldX-i;
                            cy=oldY-i;
                            try {
                                if (!table[cx][cy].hasPiece()) {
                                    possibleMoves.add(new Move(oldX, oldY, cx, cy, false));
                                } else {
                                    System.out.println("here");
                                    if (!table[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (table[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!table[cx - 1][cy - 1].hasPiece()) {
                                                possibleMoves.add(new Move(oldX, oldY, cx-1, cy - 1, true));
                                                break;
                                            } else {
                                                break;
                                            }
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                }
            }
        }
//        Iterator<Move> itr = possibleMoves.iterator();
//        while(itr.hasNext()){
//            Move loan = itr.next();
//            if(loan.getMoveType()){
//                Iterator<Move> itr1 = possibleMoves.iterator();
//                while(itr1.hasNext()){
//                    Move loan1 = itr1.next();
//                    if(!loan1.getMoveType()){
//                        itr1.remove();
//                    }
//                }
//                break;
//            }
//        }
        for(int i=0; i <possibleMoves.size();i++){
            if(possibleMoves.get(i).getMoveType()){
                Iterator<Move> it = possibleMoves.iterator();
                while(it.hasNext()){
                    Move loan = it.next();
                    if(!loan.getMoveType()){
                        it.remove();
                    }
                }
//                for(Move m:possibleMoves){
//                    if(!m.getMoveType()){
//                        possibleMoves.remove(m);
//                    }
//                }
                break;
            }
        }
        return possibleMoves;
    }
    public Board(Tile table[][], int turn){
        this.table=table;
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if(table[i][j].hasPiece()){
                    if(table[i][j].getPiece().getColor()){
                        redPieces.add(table[i][j].getPiece());
                    }
                    else if(!table[i][j].getPiece().getColor()){
                        blackPieces.add(table[i][j].getPiece());
                    }
                }
            }
        }
        this.turn=turn;
        successors();
    }
    public void successors(){
        if(turn==1){

        }
        else{

        }
    }
    public int numberOfRedPieces(){
        return redPieces.size();
    }
    public int numberOfBlackPieces(){
        return blackPieces.size();
    }
    public double evaluate(){
        double red = 0;
        double black = 0;
        for(Piece p:redPieces){
            if(p.getQueen()){
                red+=2.5;
            }
            else{
                red+=1;
            }
        }
        for(Piece p:blackPieces){
            if(p.getQueen()){
                black+=2.5;
            }
            else{
                black+=1;
            }
        }
        return red-black;
    }

}
