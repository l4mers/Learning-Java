package main;

import tiles.GameTile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameMaster {
    GamePanel gp;

    //RECORD TIME AND CLICKS
    private int clicks = 0;
    private long start;

    public GameMaster(GamePanel gp){
        this.gp = gp;
    }
    public void shuffleGameTiles(){
        final ArrayList<Integer> intList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        Collections.shuffle(intList);
        for (int i = 0; i < intList.size(); i++)
        {
            gp.tm.tileList.get(i).setCurrentPos(intList.get(i));
        }
        if(!checkIfSolvable()){shuffleGameTiles();}
        else{start = System.nanoTime();}
    }

    public void dealGameTiles() {
        String currentPos;
        for (GameTile gt : gp.tm.tileList) {
            currentPos = String.valueOf(gt.getCurrentPos());
            for (JButton bt : gp.btList) {
                if (bt.getName().equals(currentPos)) {
                    bt.setIcon(new ImageIcon(gt.getImage()));
                }
            }
        }
    }

    public void checkWin(){
        int countTilesInRightPos = 0;
        for (GameTile gt : gp.tm.tileList) {
            if (gt.getID() == gt.getCurrentPos()){
                countTilesInRightPos++;
            }
        }
        if(countTilesInRightPos == 16){
            long finished = System.nanoTime() - start;
            gp.sound.playSoundEffect(1);
            gp.gameState(gp.victoryScreenState);
            gp.scoreboard.setCurrentHighScore(stringToDouble(convertLongToMin(finished)), clicks);
            gp.currentLeader.setText(gp.scoreboard.getCurrentLeader());
        }
    }

    private boolean checkIfSolvable(){
        //Check if solvable
        //Om blank är på rad 2 eller 4 måste inversionen vara udda
        //Om blank är på rad 1 eller 3 måste inversionen vara jämn
        final ArrayList<Integer> evenRow = new ArrayList<>(List.of(5, 6, 7, 8, 13, 14, 15, 16));
        int inversionCount = inversionCount();
        if (evenRow.contains(gp.tm.tileList.get(15).getCurrentPos())){
            return (inversionCount & 1) != 0;
        } else {
            return (inversionCount & 1) == 0;
        }
    }

    //Hur många inversions finns det
    private int inversionCount(){
        int inversionCount = 0;
        for (int i = 0; i < gp.tm.tileList.size(); i++) {
            for (int j = 0; j < gp.tm.tileList.size(); j++) {
                if (i < j){
                    if (gp.tm.tileList.get(i).getCurrentPos() > gp.tm.tileList.get(j).getCurrentPos()){
                        inversionCount++;
                    }
                }
            }
        }
        return inversionCount;
    }

    public void moveTiles(int tileNr, int currentPos, int currentPos2){
        for (GameTile gt : gp.tm.tileList){
            if(gt.isBlank){
                checkThreeTiles(tileNr, currentPos, currentPos2, gt);
            }
        }
        gp.gm.dealGameTiles();
        checkWin();
    }

    public void moveTiles(int tileNr,int currentPos, int currentPos2, int currentPos3){
        for (GameTile gt : gp.tm.tileList){
            if(gt.isBlank){
                if(gt.getCurrentPos() == currentPos){
                    checkFourTiles(tileNr, currentPos, gt);
                } else checkThreeTiles(tileNr, currentPos2, currentPos3, gt);
            }
        }
        gp.gm.dealGameTiles();
        checkWin();
    }

    public void moveTiles(int tileNr,int currentPos, int currentPos2, int currentPos3, int currentPos4){
        for (GameTile gt : gp.tm.tileList){
            if(gt.isBlank){
                if(gt.getCurrentPos() == currentPos){
                    checkFourTiles(tileNr, currentPos, gt);
                } else if (gt.getCurrentPos() == currentPos2) {
                    checkFourTiles(tileNr, currentPos2, gt);
                } else checkThreeTiles(tileNr, currentPos3, currentPos4, gt);
            }
        }
        gp.gm.dealGameTiles();
        checkWin();
    }

    private void checkThreeTiles(int tileNr, int currentPos, int currentPos2, GameTile gt) {
        if(gt.getCurrentPos() == currentPos){
            checkFourTiles(tileNr, currentPos, gt);
        } else if (gt.getCurrentPos() == currentPos2) {
            checkFourTiles(tileNr, currentPos2, gt);
        }
    }

    private void checkFourTiles(int tileNr, int currentPos, GameTile gt) {
        gt.setCurrentPos(tileNr);
        for (GameTile gt2 : gp.tm.tileList){
            if(gt2.getCurrentPos() == tileNr){
                if(!gt2.isBlank){
                    gp.sound.playSoundEffect(0);
                    gt2.setCurrentPos(currentPos);
                    clicks++;
                }
            }
        }
    }

    private String convertLongToMin(long time){
        return String.format("%d.%d", TimeUnit.NANOSECONDS.toMinutes(time),
                TimeUnit.NANOSECONDS.toSeconds(time) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(time)));
    }

    private double stringToDouble(String time){
        return Double.parseDouble(time);
    }

    public void cheat(){
        for (int i = 0; i < gp.tm.tileList.size(); i++) {
            if(i == 11){
                gp.tm.tileList.get(i).setCurrentPos(16);
            }else if(i == 15){
                gp.tm.tileList.get(i).setCurrentPos(12);
            }else{
                gp.tm.tileList.get(i).setCurrentPos(i + 1);
            }
        }
        gp.gm.dealGameTiles();
    }
}
