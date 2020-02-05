package main.java.com.codingcon.util;

import main.java.com.codingcon.constants.AppConstants;
import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.model.MapCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathFinderUtil {
    @Autowired
    AreaMap areaMap;

    Map<String, Integer> nodes = new HashMap<>();
    Map<Integer, String> intNodes = new HashMap<>();
    //TODO: Finder a better way store this two-way mapping

    public String findTourPath() throws Exception {

    //Todo: Add custom exceptions, surround with try-catch block
    try{
        if(areaMap.getSource()==null){
            throw new Exception("Source not present");
        }
        if(areaMap.getDestinations().isEmpty()){
            throw new Exception("Destination not present");
        }
        nodes.put(areaMap.getSource(),0);
        intNodes.put(0,areaMap.getSource());
        areaMap.getDestinations().forEach((v)-> {
            nodes.put(v, nodes.size());
            intNodes.put(intNodes.size(),v);
        });

        int numOfDestinations = areaMap.getDestinations().size();

        int[][] weightMatrix = new int[numOfDestinations+1][numOfDestinations+1];

        String curr = areaMap.getSource();
        int index = 0;
        while(index < areaMap.getDestinations().size()){
            int length = 0;

            Set<String> unvisited = new HashSet<>(areaMap.getDestinations().subList(index,numOfDestinations));
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(curr);
            queue.add(null);

            while(!queue.isEmpty()){

                if(unvisited.isEmpty()) break;
                String qNode = queue.poll();

                if(qNode==null){
                    if (queue.peek()==null) break;
                    length++;
                    continue;
                }

                visited.add(qNode);

//                String[] coordinates = qNode.split("_");
//                int row = Integer.parseInt(coordinates[0]);
//                int col = Integer.parseInt(coordinates[1]);

                int row = IdConvertUtil.getR(qNode);
                int col = IdConvertUtil.getC(qNode);

                if(areaMap.getValue(row,col)==AppConstants.DESTINATION){
                    weightMatrix[index][nodes.get(qNode)] = length;
                    weightMatrix[nodes.get(qNode)][index] = length;
                    unvisited.remove(qNode);
                }

                //up
                if (row > 0 && areaMap.getValue(row-1,col)!= AppConstants.OBSTACLE){
                    String id = IdConvertUtil.convertToId(row-1,col);
                    if(!visited.contains(id)) queue.add(id);
                }
                //down
                if (row < areaMap.getRows()-1 && areaMap.getValue(row+1,col)!= AppConstants.OBSTACLE){
                    String id = IdConvertUtil.convertToId(row+1,col);
                    if(!visited.contains(id)) queue.add(id);
                }
                //left
                if (col > 0 && areaMap.getValue(row,col-1)!= AppConstants.OBSTACLE){
                    String id = IdConvertUtil.convertToId(row,col-1);
                    if(!visited.contains(id)) queue.add(id);
                }
                //right
                if (col < areaMap.getColumns()-1 && areaMap.getValue(row,col+1)!= AppConstants.OBSTACLE){
                    String id = IdConvertUtil.convertToId(row,col+1);
                    if(!visited.contains(id)) queue.add(id);
                }

                if(queue.peek()==null)
                    queue.add(null);
            }

            curr = areaMap.getDestinations().get(index);
            index++;
        }

        int startNode = nodes.get(areaMap.getSource());
        TourFinderUtil solver =
                new TourFinderUtil(startNode, weightMatrix);

        List<Integer> tour = solver.getTour();


        for(int i=0;i<tour.size()-1;i++){
            findShortestPairPath(intNodes.get(tour.get(i)),intNodes.get(tour.get(i+1)));
        }
    }
    catch (Exception e){
        throw e;
    }
        return "Path Found";
    }

    public void findShortestPairPath(String A, String B) throws Exception {

        int firstRow = IdConvertUtil.getR(A);
        int firstCol = IdConvertUtil.getC(A);
        int lastRow = IdConvertUtil.getR(B);
        int lastCol = IdConvertUtil.getC(B);

        Set<String> visited = new HashSet<>();
        Queue<MapCell> queue = new LinkedList<>();
        queue.add(new MapCell(firstRow, firstCol));
        MapCell current;

        while (true) {

            if (queue.isEmpty()) {
                throw new Exception();
            }

            current = queue.poll();
            int row = current.getRowNumber();
            int col = current.getColNumber();
            visited.add(IdConvertUtil.convertToId(row,col));

            if (col == lastCol && row == lastRow) {
                break;
            }

            //up
            if (row > 0 && areaMap.getValue(row-1,col)!= AppConstants.OBSTACLE){
                String id = IdConvertUtil.convertToId(row-1,col);
                if(!visited.contains(id)) queue.add(new MapCell( row-1,col, current));
            }
            //down
            if (row < areaMap.getRows()-1 && areaMap.getValue(row+1,col)!= AppConstants.OBSTACLE){
                String id = IdConvertUtil.convertToId(row+1,col);
                if(!visited.contains(id)) queue.add(new MapCell( row+1, col, current));
            }
            //left
            if (col > 0 && areaMap.getValue(row,col-1)!= AppConstants.OBSTACLE){
                String id = IdConvertUtil.convertToId(row,col-1);
                if(!visited.contains(id)) queue.add(new MapCell( row,col-1, current));
            }
            //right
            if (col < areaMap.getColumns()-1 && areaMap.getValue(row,col+1)!= AppConstants.OBSTACLE){
                String id = IdConvertUtil.convertToId(row,col+1);
                if(!visited.contains(id)) queue.add(new MapCell( row,col+1, current));
            }
        }

        //backtrack
        current=current.getPreviousMapCell();
        while (current.getPreviousMapCell() != null) {
            areaMap.setValue(AppConstants.OUTPUT_PATH,current.getRowNumber(),current.getColNumber());
            current = current.getPreviousMapCell();
        }
    }
}
