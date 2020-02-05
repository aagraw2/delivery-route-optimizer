package main.java.com.codingcon.model;

import com.google.gson.Gson;

public abstract class MapObject {
    private int rowNumber;
    private int colNumber;

    public int getColNumber(){
        return colNumber;
    }

    public int getRowNumber(){
        return rowNumber;
    }

    public void setPosition(int r, int c){
        this.rowNumber = r;
        this.colNumber = c;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}
