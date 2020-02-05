package main.java.com.codingcon.controller;

import main.java.com.codingcon.constants.AppConstants;
import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.model.Destination;
import main.java.com.codingcon.model.SetSourceResponse;
import main.java.com.codingcon.service.OptimizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = AppConstants.BASE_PATH)
public class OptimizerApiRestController {

    @Autowired
    OptimizerService optimizerService;

//    @RequestMapping(path = AppConstants.INIT_PATH)
//    public String initializeMap(){
//        optimizerService.initializeMap();
//        return "Map Initialized";
//    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(path = AppConstants.GET_AREAMAP_PATH)
    public @ResponseBody AreaMap displayMap(){
        return optimizerService.getAreaMap();
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(path = AppConstants.SET_SOURCE_PATH)
    public @ResponseBody SetSourceResponse setSource(
            @RequestParam(value="r", required = true) int r,
            @RequestParam(value="c", required = true) int c) {
        return optimizerService.setSource(r,c);
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(path = AppConstants.TOGGLE_DEST_PATH)
    public @ResponseBody Destination toggleDestination(
            @RequestParam(value="r", required = true) int r,
            @RequestParam(value="c", required = true) int c) {
        return optimizerService.toggleDestination(r,c);
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(path = AppConstants.FIND_PATH)
    public @ResponseBody AreaMap findPath() throws Exception {
        return optimizerService.findPath();

    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(path = AppConstants.RESET_PATH)
    public @ResponseBody AreaMap reset() {
        return optimizerService.resetMap();
    }

}
