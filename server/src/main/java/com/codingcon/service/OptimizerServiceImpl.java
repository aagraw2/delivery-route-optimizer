package main.java.com.codingcon.service;

import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.model.Destination;
import main.java.com.codingcon.model.SetSourceResponse;
import main.java.com.codingcon.model.Source;
import main.java.com.codingcon.util.PathFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class OptimizerServiceImpl implements OptimizerService {

    @Autowired
    AreaMap areaMap;

    @Autowired
    PathFinderUtil pathFinder;

    @Override
    public void initializeMap() {
        AreaMap.initializeMap();
    }

    @Override
    public AreaMap getAreaMap() {
        return areaMap;
    }

    @Override
    public SetSourceResponse setSource(int r, int c) {
        Source source = new Source(r,c);
        return areaMap.setSource(source);
    }

    @Override
    public Destination toggleDestination(int r, int c) {
        Destination destination = new Destination(r,c);
        if(!areaMap.getDestinations().contains(destination.getDestinationId())){
            areaMap.addDestination(destination);
        }else{
            areaMap.removeDestination(destination);
        }
        return destination;
    }

    @Override
    public AreaMap findPath() throws Exception {
        pathFinder.findTourPath();
        return areaMap;
    }


    @Override
    public AreaMap resetMap() {
        areaMap.reset();
        return areaMap;
    }
}
