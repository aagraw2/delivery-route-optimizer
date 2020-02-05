package test.java.com.codingcon.util;

import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.util.PathFinderUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class PathFinderUtilTester {

    //TODO: Write Proper tests

    @InjectMocks
    PathFinderUtil pathFinderUtil;

    @Mock
    AreaMap areaMap;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        testDestinationList.add("9_58");
        testDestinationList.add("28_1");
        testDestinationList.add("24_58");
    }

    static char[][] testMapMatrix =
            {
                    {'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','x','x','x','x','x','s','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','x','x','x','x','o','o','x','o','o','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','o','o','o','o','x','x','x','o','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','o','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','o','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','o','o','o','o','o','o','x','x','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','x','x','o','x','x','o','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','d','x'},
                    {'x','o','x','x','x','x','x','o','x','o','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','o','x','o','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','x','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','o','o','o','x','o','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','o','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','x','x','o','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','x','x','o','x','x','x','o','x'},
                    {'x','o','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x'},
                    {'x','o','x','x','x','x','o','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','o','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','o','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','o','x'},
                    {'x','o','o','o','o','o','o','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','o','x','x','o','x','x','x','x','o','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x'},
                    {'x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','d','x'},
                    {'x','o','x','x','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','o','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','o','x','x','x','x','x','x','x','x'},
                    {'x','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','o','x','x','x','x','x','x','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x'},
                    {'x','d','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                    {'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'}
            };

    List<String> testDestinationList = new ArrayList<>();

    @Test
    public void findPathTest() throws Exception {
        when(areaMap.getMapMatrix()).thenReturn(testMapMatrix);
        when(areaMap.getSource()).thenReturn("1_6");
        when(areaMap.getDestinations()).thenReturn(testDestinationList);
        doAnswer(i->{
            return testMapMatrix[(int) i.getArgument(0)][(int) i.getArgument(1)];
        }).when(areaMap).getValue(anyInt(),anyInt());
        when(areaMap.getRows()).thenReturn(30);
        when(areaMap.getColumns()).thenReturn(60);
        doAnswer(i->{
            testMapMatrix[(int) i.getArgument(1)][(int) i.getArgument(2)]=i.getArgument(0);
            return true;
        }).when(areaMap).setValue(anyChar(),anyInt(),anyInt());

        pathFinderUtil.findTourPath();

        for (int i = 0; i < testMapMatrix.length; i++) {
            for (int j = 0; j < testMapMatrix[0].length; j++) {
                System.out.print(testMapMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void findShortestPairPathTest() throws Exception {
        when(areaMap.getMapMatrix()).thenReturn(testMapMatrix);
        when(areaMap.getSource()).thenReturn("1_6");
        when(areaMap.getDestinations()).thenReturn(testDestinationList);
        doAnswer(i->{
            return testMapMatrix[(int) i.getArgument(0)][(int) i.getArgument(1)];
        }).when(areaMap).getValue(anyInt(),anyInt());
        when(areaMap.getRows()).thenReturn(30);
        when(areaMap.getColumns()).thenReturn(60);
        doAnswer(i->{
            testMapMatrix[(int) i.getArgument(1)][(int) i.getArgument(2)]=i.getArgument(0);
            return true;
        }).when(areaMap).setValue(anyChar(),anyInt(),anyInt());

        pathFinderUtil.findShortestPairPath("1_6","28_1");

        for (int i = 0; i < testMapMatrix.length; i++) {
            for (int j = 0; j < testMapMatrix[0].length; j++) {
                System.out.print(testMapMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }



}
