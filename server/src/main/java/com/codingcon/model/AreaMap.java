package main.java.com.codingcon.model;

import main.java.com.codingcon.Exception.MapAlreadyInitializedException;
import main.java.com.codingcon.Exception.MapObjectPositionException;
import main.java.com.codingcon.constants.AppConstants;
import main.java.com.codingcon.util.MapLoaderUtil;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;


public class AreaMap {
    private int columns;
    private int rows;
    private char[][] mapMatrix;

    private static AreaMap areaMap = null;

    private String source;
    private List<String> destinationList = new ArrayList<>();

    private AreaMap(){
        this.mapMatrix = MapLoaderUtil.convertMapToMatrix();
        this.columns = this.mapMatrix[0].length;
        this.rows = this.mapMatrix.length;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public char[][] getMapMatrix() {
        return mapMatrix;
    }

    public char getValue(int r, int c){
        return mapMatrix[r][c];
    }

    public boolean setValue(char v, int r, int c){
        mapMatrix[r][c]=v;
        return true;
    }

    public String getSource(){
        return this.source;
    }

    public SetSourceResponse setSource(Source source) throws MapObjectPositionException{
        if(this.mapMatrix[source.getRowNumber()][source.getColNumber()]!= AppConstants.PATH){
            throw new MapObjectPositionException();
        }
        Source oldSource = null;
        if(areaMap.getSource()!=null){
            oldSource = new Source(areaMap.getSource());
            this.mapMatrix[oldSource.getRowNumber()][oldSource.getColNumber()] = AppConstants.PATH;
        }
        this.source = source.getSourceId();
        this.mapMatrix[source.getRowNumber()][source.getColNumber()] = AppConstants.SOURCE;
        return new SetSourceResponse(oldSource,source);
    }

    public List<String> getDestinations(){
        return this.destinationList;
    }

    public void addDestination(Destination dest) throws MapObjectPositionException{
        if(this.mapMatrix[dest.getRowNumber()][dest.getColNumber()]!= AppConstants.PATH){
            throw new MapObjectPositionException();
        }
        this.destinationList.add(dest.getDestinationId());
        this.mapMatrix[dest.getRowNumber()][dest.getColNumber()]=AppConstants.DESTINATION;
    }

    public void removeDestination(Destination dest){
        //Todo: Add custom exception, surround with try-catch block
//        if(!this.destinationList.contains(dest.getDestinationId())){
//            throw new Exception("Already not in destinations list");
//        }
        this.destinationList.remove(dest.getDestinationId());
        this.mapMatrix[dest.getRowNumber()][dest.getColNumber()]=AppConstants.PATH;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    public static AreaMap initializeMap(){
        if(areaMap==null){
            areaMap = new AreaMap();
        } else {
            throw new MapAlreadyInitializedException();
        }
        return areaMap;
    }

    public void reset() {
        this.mapMatrix = MapLoaderUtil.convertMapToMatrix();
        this.columns = this.mapMatrix[0].length;
        this.rows = this.mapMatrix.length;
        this.source=null;
        this.destinationList.clear();
    }
}
