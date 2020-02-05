package main.java.com.codingcon.model;

import com.google.gson.Gson;

public class MapCell extends MapObject {

    private MapCell previousMapCell;

    public MapCell(int r, int c){
        super.setPosition(r,c);
        this.previousMapCell = null;
    }

    public MapCell(int r, int c, MapCell previous){
        super.setPosition(r,c);
        this.previousMapCell = previous;
    }

    public MapCell getPreviousMapCell() {
        return previousMapCell;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
