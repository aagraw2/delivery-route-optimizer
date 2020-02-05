package main.java.com.codingcon.service;

import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.model.Destination;
import main.java.com.codingcon.model.SetSourceResponse;
import main.java.com.codingcon.model.Source;

public interface OptimizerService {

    //InitializeMap
    public void initializeMap();
    //DisplayMap
    public AreaMap getAreaMap();
    //SetSource
    public SetSourceResponse setSource(int r, int c);
    //ToggleDestination
    public Destination toggleDestination(int r, int c);
    //FindPath
    public AreaMap findPath() throws Exception;
    //ResetMap
    public AreaMap resetMap();
}
