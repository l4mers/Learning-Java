//package world;
//
//import main.GamePanel;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WorldLoader {
//
//    private List<String> mapList;
//
//    String mapFolder = "src/resource/maps";
//
//
//    public WorldLoader() {
//
//        getMapNames();
//    }
//    public void getMapNames(){
//        try {
//            File mapFolder = new File(this.mapFolder);
//            mapList = new ArrayList<>();
//            for (File file : mapFolder.listFiles()) {
//                mapList.add(file.getName());
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    public String nextWorld(int index){
//        return mapList.get(index);
//    }
//}
