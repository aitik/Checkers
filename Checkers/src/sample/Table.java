package sample;

public class Table {
    private Tile[][] table;
    public Table(Tile[][] tiles){
        this.table = tiles;
    }
    public Tile[][] getTable(){
        return this.table;
    }
    public void setTable(Tile[][] t){
        this.table = t;
    }
}
