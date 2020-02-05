package main.java.com.codingcon.util;

public class IdConvertUtil {
    public static int getR(String id){
        String[] coordinates = id.split("_");
        return Integer.parseInt(coordinates[0]);
    }

    public static int getC(String id){
        String[] coordinates = id.split("_");
        return Integer.parseInt(coordinates[1]);
    }

    public static  String convertToId(int r,int c){
        return r + "_" + c;
    }
}
