package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Controller {
    @FXML
    ProgressIndicator pi;
    @FXML
    GridPane gPane;
    @FXML
    Label turnlbl;
    @FXML
            Button startbtn;
    @FXML
            Label val;
    @FXML
            CheckBox auto;
    @FXML
            Button make;
    ImageView img[][] = new ImageView[8][8];
    int table[][]=new int[8][8];
    Button btn[][]=new Button[8][8];
    Tile tiles[][]=new Tile[8][8];
    ArrayList<Piece> redp = new ArrayList<Piece>();
    ArrayList<Piece> blackp = new ArrayList<Piece>();
    Image red = new javafx.scene.image.Image("newred.png");
    Image redq = new javafx.scene.image.Image("redq.png");
    Image black = new javafx.scene.image.Image("newblack.png");
    Image blackq = new javafx.scene.image.Image("blackq.png");
    Image blank = new Image("blank.png");
    Image square = new Image("square.png");
    Image squaret = new Image("squaretake.png");
    Board board;
    Move bestm;
    @FXML
    Label best;
    @FXML
    Spinner spinner;
    public void setbest(Move m){
        bestm=m;
        best.setText(String.valueOf(m.getStartX())+m.getStartY()+" "+m.getEndX()+m.getEndY());
    }
    @FXML
    private void start(){
        startbtn.setDisable(true);
        SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10, 4);
        spinner.setValueFactory(vf);
        bestm=new Move(5,6,4,7,false);
        for(int i=0; i<8; i++){
            for(int j=0; j<8;j++){
                table[i][j]=0;
                img[i][j] = new ImageView();
                img[i][j].maxHeight(60);
                img[i][j].maxWidth(60);
                img[i][j].setImage(blank);
                gPane.add(img[i][j], j, i);
            }
        }
        int counter=1;
        for(int i=0;i<8; i++){
            for(int j=0; j<8; j++){
                if(counter>0){
                    tiles[i][j]=new Tile(true,i,j);
                    counter*=-1;
                }
                else{
                    tiles[i][j]=new Tile(false,i,j);
                    counter*=-1;
                }
            }
            counter*=-1;
        }
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if(tiles[i][j].getColor()){
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        img[0][1].setImage(black);
        table[0][1]=-1;
        blackp.add(new Piece(false, false,0,1));
        tiles[0][1].setPiece(blackp.get(0));
        img[0][3].setImage(black);
        table[0][3]=-1;
        blackp.add(new Piece(false,false,0,3));
        tiles[0][3].setPiece(blackp.get(1));
        img[0][5].setImage(black);
        table[0][5]=-1;
        blackp.add(new Piece(false,false,0,5));
        tiles[0][5].setPiece(blackp.get(2));
        img[0][7].setImage(black);
        table[0][7]=-1;
        blackp.add(new Piece(false,false,0,7));
        tiles[0][7].setPiece(blackp.get(3));
        img[1][0].setImage(black);
        table[1][0]=-1;
        blackp.add(new Piece(false,false,1,0));
        tiles[1][0].setPiece(blackp.get(4));
        img[1][2].setImage(black);
        table[1][2]=-1;
        blackp.add(new Piece(false,false,1,2));
        tiles[1][2].setPiece(blackp.get(5));
        img[1][4].setImage(black);
        table[1][4]=-1;
        blackp.add(new Piece(false,false,1,4));
        tiles[1][4].setPiece(blackp.get(6));
        img[1][6].setImage(black);
        table[1][6]=-1;
        blackp.add(new Piece(false,false,1,6));
        tiles[1][6].setPiece(blackp.get(7));
        img[2][1].setImage(black);
        table[2][1]=-1;
        blackp.add(new Piece(false,false,2,1));
        tiles[2][1].setPiece(blackp.get(8));
        img[2][3].setImage(black);
        table[2][3]=-1;
        blackp.add(new Piece(false,false,2,3));
        tiles[2][3].setPiece(blackp.get(9));
        img[2][5].setImage(black);
        table[2][5]=-1;
        blackp.add(new Piece(false,false,2,5));
        tiles[2][5].setPiece(blackp.get(10));
        img[2][7].setImage(black);
        table[2][7]=-1;
        blackp.add(new Piece(false,false,2,7));
        tiles[2][7].setPiece(blackp.get(11));
        img[7][0].setImage(red);
        table[7][0]=1;
        redp.add(new Piece(true, false,7,0));
        tiles[7][0].setPiece(redp.get(0));
        img[7][2].setImage(red);
        table[7][2]=1;
        redp.add(new Piece(true,false,7,2));
        tiles[7][2].setPiece(redp.get(1));
        img[7][4].setImage(red);
        table[7][4]=1;
        redp.add(new Piece(true,false,7,4));
        tiles[7][4].setPiece(redp.get(2));
        img[7][6].setImage(red);
        table[7][6]=1;
        redp.add(new Piece(true,false,7,6));
        tiles[7][6].setPiece(redp.get(3));
        img[6][7].setImage(red);
        table[6][7]=1;
        redp.add(new Piece(true,false,6,7));
        tiles[6][7].setPiece(redp.get(4));
        img[6][5].setImage(red);
        table[6][5]=1;
        redp.add(new Piece(true,false,6,5));
        tiles[6][5].setPiece(redp.get(5));
        img[6][3].setImage(red);
        table[6][3]=1;
        redp.add(new Piece(true,false,6,3));
        tiles[6][3].setPiece(redp.get(6));
        img[6][1].setImage(red);
        table[6][1]=1;
        redp.add(new Piece(true,false,6,1));
        tiles[6][1].setPiece(redp.get(7));
        img[5][6].setImage(red);
        table[5][6]=1;
        redp.add(new Piece(true,false,5,6));
        tiles[5][6].setPiece(redp.get(8));
        img[5][4].setImage(red);
        table[5][4]=1;
        redp.add(new Piece(true,false,5,4));
        tiles[5][4].setPiece(redp.get(9));
        img[5][2].setImage(red);
        table[5][2]=1;
        redp.add(new Piece(true,false,5,2));
        tiles[5][2].setPiece(redp.get(10));
        img[5][0].setImage(red);
        table[5][0]=1;
        redp.add(new Piece(true,false,5,0));
        tiles[5][0].setPiece(redp.get(11));

        for(int i=0;i<8;i++){
            for(int j =0; j<8;j++){
                img[i][j].setOnMouseClicked(this::move);
                img[i][j].setOnDragDetected(this::movedrag);
            }
        }
        board = new Board(tiles, 1);
        disable();
        undisable();
    }

    ImageView imgClicked;
    int oldX, oldY;
    @FXML
    private void move(MouseEvent event){
        if(imgClicked!=null){
            redsquares();
        }
        oldX=GridPane.getRowIndex((ImageView) event.getSource());
        oldY=GridPane.getColumnIndex((ImageView) event.getSource());
        havetotake();
        movebackground(oldX, oldY);
        havetotake2();
        takemoves=false;
    }
    public void movedrag(MouseEvent event){
        if(imgClicked!=null){
            redsquares();
        }
        oldX=GridPane.getRowIndex((ImageView) event.getSource());
        oldY=GridPane.getColumnIndex((ImageView) event.getSource());
        if(turn==1&& tiles[oldX][oldY].hasPiece() && !tiles[oldX][oldY].getPiece().getColor()){
            return;
        }
        if(turn==-1 && tiles[oldX][oldY].hasPiece() && tiles[oldX][oldY].getPiece().getColor()){
            return;
        }
        Dragboard db = img[oldX][oldY].startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(img[oldX][oldY].getImage());
        db.setContent(content);
        havetotake();
        movebackground(oldX, oldY);
        havetotake2();
        takemoves=false;
        for(int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                if(img[i][j].getImage().equals(square)){
                    img[i][j].setOnDragOver(event1 -> {
                        if (event1.getDragboard().hasImage()){
                            event1.acceptTransferModes(TransferMode.ANY);
                        }
                        event1.consume();
                    });
                    img[i][j].setOnDragDropped(this::move2drag);
                }
                else if(img[i][j].getImage().equals(squaret)){
                    img[i][j].setOnDragOver(event1 -> {
                        if (event1.getDragboard().hasImage()){
                            event1.acceptTransferModes(TransferMode.ANY);
                        }
                        event1.consume();
                    });
                    img[i][j].setOnDragDropped(this::take2drag);
                }
                else{
                    img[i][j].setOnDragDropped(null);
                    img[i][j].setOnDragOver(null);
                }
            }
        }
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if(tiles[i][j].hasPiece()){
                    if(tiles[i][j].getPiece().getColor()){
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
    }
    boolean takemoves=false;
    int turn = 1;
    public void havetotake(){
        if(turn==1){
            for(int o=0;o<8;o++){
                for(int p=0;p<8;p++){
                    if(tiles[o][p].hasPiece() && tiles[o][p].getPiece().getColor()){
                        movebackground(o,p);
                        for(int i=0;i<8;i++){
                            for(int j=0;j<8;j++){
                                if(img[i][j].getImage().equals(squaret)){
                                    takemoves=true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            for(int o=0;o<8;o++){
                for(int p=0;p<8;p++){
                    if(tiles[o][p].hasPiece() && !tiles[o][p].getPiece().getColor()){
                        movebackground(o,p);
                        for(int i=0;i<8;i++){
                            for(int j=0;j<8;j++){
                                if(img[i][j].getImage().equals(squaret)){
                                    takemoves=true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                if(img[i][j].getImage().equals(square) || img[i][j].getImage().equals(squaret)){
                    img[i][j].setImage(blank);
                }
            }
        }
    }
    public void havetotake2(){
        if(takemoves){
            for(int k=0;k<8;k++){
                for(int l=0;l<8;l++){
                    if (img[k][l].getImage().equals(square)) {
                        img[k][l].setImage(blank);
                    }
                }
            }
        }
    }
    boolean takemoves2=false;
    public boolean iftakingavailable(int x, int y){

        return false;
    }
    public void movebackground(int oldX, int oldY){
        imgClicked = img[oldX][oldY];
        if(tiles[oldX][oldY].hasPiece()){
            if(tiles[oldX][oldY].getPiece().getColor()){
                currentColor=true;
                if(!tiles[oldX][oldY].getPiece().getQueen()){
                    if(oldX>0 && oldY>0 && oldX<=7 && oldY<7){
                        if(!tiles[oldX-1][oldY-1].hasPiece()){
                            img[oldX-1][oldY-1].setImage(square);
                            img[oldX-1][oldY-1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if(!tiles[oldX-1][oldY-1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX-2][oldY-2].hasPiece()){
                                        img[oldX-2][oldY-2].setImage(squaret);
                                        img[oldX-2][oldY-2].setOnMouseClicked(event1 -> take2(event1, oldX-1, oldY-1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                        if(!tiles[oldX-1][oldY+1].hasPiece()){
                            img[oldX-1][oldY+1].setImage(square);
                            img[oldX-1][oldY+1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if(!tiles[oldX-1][oldY+1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX-2][oldY+2].hasPiece()){
                                        img[oldX-2][oldY+2].setImage(squaret);
                                        img[oldX-2][oldY+2].setOnMouseClicked(event1 -> take2(event1, oldX-1, oldY+1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }

                        }
                    }
                    else if(oldY==0) {
                        if(!tiles[oldX-1][oldY+1].hasPiece()){
                            img[oldX-1][oldY+1].setImage(square);
                            img[oldX-1][oldY+1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if(!tiles[oldX-1][oldY+1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX-2][oldY+2].hasPiece()){
                                        img[oldX-2][oldY+2].setImage(squaret);
                                        img[oldX-2][oldY+2].setOnMouseClicked(event1 -> take2(event1, oldX-1, oldY+1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                    }
                    else if(oldY==7){
                        if(!tiles[oldX-1][oldY-1].hasPiece()){
                            img[oldX-1][oldY-1].setImage(square);
                            img[oldX-1][oldY-1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if(!tiles[oldX-1][oldY-1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX-2][oldY-2].hasPiece()){
                                        img[oldX-2][oldY-2].setImage(squaret);
                                        img[oldX-2][oldY-2].setOnMouseClicked(event1 -> take2(event1, oldX-1, oldY-1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                    }
                }
                else{

                    if(oldX==0){
                        for(int i=1;i<oldY+1;i++){
                            cx=oldX+i;
                            cy=oldY-i;
                            try {
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx + 1][cy - 1].hasPiece()) {
                                                img[cx + 1][cy - 1].setImage(squaret);
                                                img[cx + 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx + 1][cy + 1].hasPiece()) {
                                                img[cx + 1][cy + 1].setImage(squaret);
                                                img[cx + 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx + 1][cy - 1].hasPiece()) {
                                                img[cx + 1][cy - 1].setImage(squaret);
                                                img[cx + 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx - 1][cy - 1].hasPiece()) {
                                                img[cx - 1][cy - 1].setImage(squaret);
                                                img[cx - 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx - 1][cy + 1].hasPiece()) {
                                                img[cx - 1][cy + 1].setImage(squaret);
                                                img[cx - 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx + 1][cy + 1].hasPiece()) {
                                                img[cx + 1][cy + 1].setImage(squaret);
                                                img[cx + 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx - 1][cy + 1].hasPiece()) {
                                                img[cx - 1][cy + 1].setImage(squaret);
                                                img[cx - 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                if (!tiles[cx][cy].hasPiece()) {
                                    img[cx][cy].setImage(square);

                                    img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                } else {

                                    if (tiles[cx][cy].getPiece().getColor()) {
                                        break;
                                    }
                                    if (!tiles[cx][cy].getPiece().getColor()) {
                                        try {
                                            if (!tiles[cx - 1][cy - 1].hasPiece()) {
                                                img[cx - 1][cy - 1].setImage(squaret);
                                                img[cx - 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
            else{
                currentColor=false;
                if(!tiles[oldX][oldY].getPiece().getQueen()){
                    if(oldX>=0 && oldY>0 && oldX<7 && oldY<7){
                        if(!tiles[oldX+1][oldY-1].hasPiece()){
                            img[oldX+1][oldY-1].setImage(square);
                            img[oldX+1][oldY-1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if(tiles[oldX+1][oldY-1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX+2][oldY-2].hasPiece()){
                                        img[oldX+2][oldY-2].setImage(squaret);
                                        img[oldX+2][oldY-2].setOnMouseClicked(event1 -> take2(event1, oldX+1, oldY-1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                        if(!tiles[oldX+1][oldY+1].hasPiece()){
                            img[oldX+1][oldY+1].setImage(square);
                            img[oldX+1][oldY+1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if( tiles[oldX+1][oldY+1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX+2][oldY+2].hasPiece()){
                                        img[oldX+2][oldY+2].setImage(squaret);
                                        img[oldX+2][oldY+2].setOnMouseClicked(event1 -> take2(event1, oldX+1, oldY+1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                    }
                    else if(oldY==0) {
                        try{
                            if(!tiles[oldX+1][oldY+1].hasPiece()){
                                img[oldX+1][oldY+1].setImage(square);
                                img[oldX+1][oldY+1].setOnMouseClicked(event1 -> move2(event1));
                            }
                            else{
                                if( tiles[oldX+1][oldY+1].getPiece().getColor()){
                                    try {
                                        if(!tiles[oldX+2][oldY+2].hasPiece()){
                                            img[oldX+2][oldY+2].setImage(squaret);
                                            img[oldX+2][oldY+2].setOnMouseClicked(event1 -> take2(event1, oldX+1, oldY+1));
                                        }
                                    }catch (ArrayIndexOutOfBoundsException e){

                                    }
                                }
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){

                        }

                    }
                    else if(oldY==7){
                        if(!tiles[oldX+1][oldY-1].hasPiece()){
                            img[oldX+1][oldY-1].setImage(square);
                            img[oldX+1][oldY-1].setOnMouseClicked(event1 -> move2(event1));
                        }
                        else{
                            if( tiles[oldX+1][oldY-1].getPiece().getColor()){
                                try {
                                    if(!tiles[oldX+2][oldY-2].hasPiece()){
                                        img[oldX+2][oldY-2].setImage(squaret);
                                        img[oldX+2][oldY-2].setOnMouseClicked(event1 -> take2(event1, oldX+1, oldY-1));
                                    }
                                }catch (ArrayIndexOutOfBoundsException e){

                                }
                            }
                        }
                    }
                }
                else{


                        if(oldX==0){
                            for(int i=1;i<oldY+1;i++){
                                cx=oldX+i;
                                cy=oldY-i;
                                try {
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx + 1][cy - 1].hasPiece()) {
                                                    img[cx + 1][cy - 1].setImage(squaret);
                                                    img[cx + 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx + 1][cy + 1].hasPiece()) {
                                                    img[cx + 1][cy + 1].setImage(squaret);
                                                    img[cx + 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx + 1][cy - 1].hasPiece()) {
                                                    img[cx + 1][cy - 1].setImage(squaret);
                                                    img[cx + 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx - 1][cy - 1].hasPiece()) {
                                                    img[cx - 1][cy - 1].setImage(squaret);
                                                    img[cx - 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx - 1][cy + 1].hasPiece()) {
                                                    img[cx - 1][cy + 1].setImage(squaret);
                                                    img[cx - 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);
                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {
                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx + 1][cy + 1].hasPiece()) {
                                                    img[cx + 1][cy + 1].setImage(squaret);
                                                    img[cx + 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);
                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {
                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx - 1][cy + 1].hasPiece()) {
                                                    img[cx - 1][cy + 1].setImage(squaret);
                                                    img[cx - 1][cy + 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
                                    if (!tiles[cx][cy].hasPiece()) {
                                        img[cx][cy].setImage(square);

                                        img[cx][cy].setOnMouseClicked(event1 -> move2(event1));
                                    } else {

                                        if (!tiles[cx][cy].getPiece().getColor()) {
                                            break;
                                        }
                                        if (tiles[cx][cy].getPiece().getColor()) {
                                            try {
                                                if (!tiles[cx - 1][cy - 1].hasPiece()) {
                                                    img[cx - 1][cy - 1].setImage(squaret);
                                                    img[cx - 1][cy - 1].setOnMouseClicked(event1 -> take2(event1, cx, cy));
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
        else{
            System.out.println("Empty");
        }
    }
    int newX, newY, cx, cy;
    boolean currentColor;
    public void move2(MouseEvent e){
        newX=GridPane.getRowIndex((ImageView) e.getSource());
        newY=GridPane.getColumnIndex((ImageView) e.getSource());
        img[oldX][oldY].setImage(blank);
        tiles[newX][newY].setPiece(tiles[oldX][oldY].getPiece());
        tiles[newX][newY].getPiece().setRow(newX);
        tiles[newX][newY].getPiece().setColumn(newY);
        tiles[oldX][oldY].setPiece(null);
        if(currentColor){
            img[newX][newY].setImage(red);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(redq);
            }turn=-1;
            turnchange();
        }
        else{
            img[newX][newY].setImage(black);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(blackq);
            }
            turn=1;
        }
        turnchange();
        imgClicked=null;
        img[newX][newY].setOnMouseClicked(null);
        redsquares();
        if(tiles[newX][newY].getPiece().getColor() && newX==0){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(redq);
        }
        if(!tiles[newX][newY].getPiece().getColor() && newX==7){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(blackq);
        }
        turnchange();
        eval(oldX, oldY, newX, newY, false);
        ifwin();
        if(auto.isSelected()&& !ended){
            makeAI();
        }
    }
    int t2x, t2y;
    //BLACK PIECE = FALSE
    //RED PIECE = TRUE
    public void move2drag(DragEvent e){
        System.out.println("DRAG IS HAPPENING");
        newX=GridPane.getRowIndex((ImageView) e.getSource());
        newY=GridPane.getColumnIndex((ImageView) e.getSource());
        img[oldX][oldY].setImage(blank);
        if (
                e.getDragboard().hasImage()) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
        tiles[newX][newY].setPiece(tiles[oldX][oldY].getPiece());
        tiles[newX][newY].getPiece().setRow(newX);
        tiles[newX][newY].getPiece().setColumn(newY);
        tiles[oldX][oldY].setPiece(null);
        if(currentColor){
            img[newX][newY].setImage(red);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(redq);
            }
            turn=-1;
        }
        else{
            img[newX][newY].setImage(black);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(blackq);
            }turn=1;
            turnchange();
        }
        turnchange();
        imgClicked=null;
        img[newX][newY].setOnMouseClicked(null);
        redsquares();
        if(tiles[newX][newY].getPiece().getColor() && newX==0){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(redq);
        }
        if(!tiles[newX][newY].getPiece().getColor() && newX==7){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(blackq);
        }
        turnchange();
        eval(oldX, oldY, newX, newY, false);
        ifwin();
        if(auto.isSelected()&& !ended){
            makeAI();
        }
    }
    public void take2(MouseEvent e, int x, int y){
        newX=GridPane.getRowIndex((ImageView) e.getSource());
        newY=GridPane.getColumnIndex((ImageView) e.getSource());
        img[oldX][oldY].setImage(blank);
        if(newX>oldX){
            if(newY>oldY){
                tx=newX-1;
                ty=newY-1;
            }
            else{
                tx=newX-1;
                ty=newY+1;
            }
        }
        else{
            if(newY>oldY){
                tx=newX+1;
                ty=newY-1;
            }
            else{
                tx=newX+1;
                ty=newY+1;
            }
        }
        img[tx][ty].setImage(blank);
        tiles[newX][newY].setPiece(tiles[oldX][oldY].getPiece());
        tiles[newX][newY].getPiece().setRow(newX);
        tiles[newX][newY].getPiece().setColumn(newY);
        tiles[oldX][oldY].setPiece(null);
        tiles[tx][ty].setPiece(null);
        if(currentColor){
            img[newX][newY].setImage(red);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(redq);
            }
//            if(!iftakingavailable(newX,newY)){
//                turn=-1;
//            }
            turn=-1;
        }
        else{
            img[newX][newY].setImage(black);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(blackq);
            }
//            if(!iftakingavailable(newX,newY)){
//                turn=1;
//            }
            turn=1;
        }
        turnchange();
        imgClicked=null;
        img[newX][newY].setOnMouseClicked(null);
        redsquares();
        if( takemoves2){
            movebackground(newX, newY);
            for(int i=0;i<8;i++){
                for(int j=0; j<8;j++){
                    if(img[i][j].getImage().equals(square)){
                        img[i][j].setImage(blank);
                    }
                }
            }
        }
        if(tiles[newX][newY].getPiece().getColor() && newX==0){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(redq);
        }
        if(!tiles[newX][newY].getPiece().getColor() && newX==7){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(blackq);
        }
        takemoves2=false;
        eval(oldX, oldY, newX, newY, true);
        ifwin();
        if(auto.isSelected() && !ended){
            makeAI();
        }
    }
    public void turnchange(){
        if(turn==1){
            turnlbl.setText("Red's turn");
        }
        else{
            turnlbl.setText("Black's turn");
        }
    }
    int tx,ty;
    public void take2drag(DragEvent e){
        System.out.println("DRAG IS HAPPENING");
        newX=GridPane.getRowIndex((ImageView) e.getSource());
        newY=GridPane.getColumnIndex((ImageView) e.getSource());
//        tx=(oldX+newX)/2;
//        ty=(oldY+newY)/2;
        if(newX>oldX){
            if(newY>oldY){
                tx=newX-1;
                ty=newY-1;
            }
            else{
                tx=newX-1;
                ty=newY+1;
            }
        }
        else{
            if(newY>oldY){
                tx=newX+1;
                ty=newY-1;
            }
            else{
                tx=newX+1;
                ty=newY+1;
            }
        }
        img[oldX][oldY].setImage(blank);
        img[tx][ty].setImage(blank);
        if (
                e.getDragboard().hasImage()) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
        tiles[newX][newY].setPiece(tiles[oldX][oldY].getPiece());
        tiles[newX][newY].getPiece().setRow(newX);
        tiles[newX][newY].getPiece().setColumn(newY);
        tiles[oldX][oldY].setPiece(null);
        tiles[tx][ty].setPiece(null);
        if(currentColor){
            img[newX][newY].setImage(red);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(redq);
            }
//            if(!iftakingavailable(newX,newY)){
//                turn=-1;
//            }
            turn=-1;
        }
        else{
            img[newX][newY].setImage(black);
            if(tiles[newX][newY].getPiece().getQueen()){
                img[newX][newY].setImage(blackq);
            }
//            if(!iftakingavailable(newX,newY)){
//                turn=1;
//            }
            turn =1;
        }
        turnchange();
        imgClicked=null;
        img[newX][newY].setOnMouseClicked(null);
        redsquares();
        if(takemoves2){
            movebackground(newX, newY);
            for(int i=0;i<8;i++){
                for(int j=0; j<8;j++){
                    if(img[i][j].getImage().equals(square)){
                        img[i][j].setImage(blank);
                    }
                }
            }
        }
        if(tiles[newX][newY].getPiece().getColor() && newX==0){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(redq);
        }
        if(!tiles[newX][newY].getPiece().getColor() && newX==7){
            tiles[newX][newY].getPiece().setQueen();
            img[newX][newY].setImage(blackq);
        }
        takemoves2=false;
        eval(oldX, oldY, newX, newY, true);
        ifwin();
        if(auto.isSelected() && !ended){
            makeAI();
        }
    }
    public void eval(int oldX, int oldY, int newX, int newY, boolean type){
        pi.setProgress(0.1);
        int depth = (int)spinner.getValue();
        Minimax m = new Minimax(turn, depth);
        Board newB = new Board(tiles, turn);
//        newB=m.applyMove(board, new Move(oldX, oldY, newX, newY, type));//??????????????????????????????????
        Double evaluation =m.miniMax(newB,depth,turn,Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        val.setText(String.valueOf(evaluation));
        System.out.println(evaluation);
        setbest(m.getBest());
        pi.setProgress(1.0);
    }
    public void makeAI(){
        int startX = bestm.getStartX();
        int startY = bestm.getStartY();
        int endX = bestm.getEndX();
        int endY = bestm.getEndY();
        tiles[endX][endY].setPiece(tiles[startX][startY].getPiece());
        tiles[startX][startY].setPiece(null);
        tiles[endX][endY].getPiece().setRow(endX);
        tiles[endX][endY].getPiece().setColumn(endY);
        img[startX][startY].setImage(blank);
        if(!tiles[endX][endY].getPiece().getQueen()){
            if(tiles[endX][endY].getPiece().getColor()){
                img[endX][endY].setImage(red);
            }
            else{
                img[endX][endY].setImage(black);
            }
        }
        else{
            if(tiles[endX][endY].getPiece().getColor()){
                img[endX][endY].setImage(redq);
            }
            else{
                img[endX][endY].setImage(blackq);
            }
        }
        if(bestm.getMoveType()){
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
            img[tx][ty].setImage(blank);
            tiles[tx][ty].setPiece(null);
        }
        if(tiles[endX][endY].getPiece().getColor()){
            turn=-1;
            if(endX==0){
                tiles[endX][endY].getPiece().setQueen();
                img[endX][endY].setImage(redq);
            }
        }
        else{
            turn=1;
            if(endX==7){
                tiles[endX][endY].getPiece().setQueen();
                img[endX][endY].setImage(blackq);
            }
        }
        turnchange();
        redsquares();
        eval(startX,startY,endX,endY,bestm.getMoveType());
        ifwin();
    }
    public void redsquares(){
        for(int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                if(img[i][j].getImage().equals(squaret) || img[i][j].getImage().equals(square)){
                    img[i][j].setImage(blank);
                }
            }
        }
        if(turn==1){
            for(int i=0;i<8;i++){
                for(int j=0; j<8;j++){
                    if(img[i][j].getImage().equals(red) || img[i][j].getImage().equals(redq)){
                        img[i][j].setOnMouseClicked(this::move);
                    }
                    if(img[i][j].getImage().equals(black) || img[i][j].getImage().equals(blackq)){
                        img[i][j].setOnMouseClicked(null);
                        img[i][j].setOnDragOver(null);
                        img[i][j].setOnDragDropped(null);
                    }
                }
            }
        }
        else{
            for(int i=0;i<8;i++){
                for(int j=0; j<8;j++){
                    if(img[i][j].getImage().equals(black) || img[i][j].getImage().equals(blackq)){
                        img[i][j].setOnMouseClicked(this::move);
                    }
                    if(img[i][j].getImage().equals(red) || img[i][j].getImage().equals(redq)){
                        img[i][j].setOnMouseClicked(null);
                        img[i][j].setOnDragOver(null);
                        img[i][j].setOnDragDropped(null);
                    }
                }
            }
        }
    }
    public void disable(){
        for(int i=0; i <8;i++){
            for( int j=0; j <8;j++){
                img[i][j].setDisable(true);
            }
        }
        make.setDisable(true);
    }
    public void undisable(){
        for(int i=0; i <8;i++){
            for( int j=0; j <8;j++){
                img[i][j].setDisable(false);
            }
        }
        make.setDisable(false);
    }boolean ended = false;
    public void ifwin(){
        Board cb = new Board(tiles,turn);
        if(cb.getRedPieces().size()==0){
            val.setText("Black win!");
            disable();
            ended=true;
        }
        if(cb.getBlackPieces().size()==0){
            val.setText("Red win!");
            disable();
            ended=true;
        }
        if(cb.getPossibleMoves().size()==0 && turn==1){
            val.setText("Black win!");
            disable();
            ended=true;
        }
        if(cb.getPossibleMoves().size()==0 && turn==-1){
            val.setText("Red win!");
            disable();
            ended=true;
        }
    }
}
