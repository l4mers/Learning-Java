package scoreboard;

import main.GamePanel;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Scoreboard {

    private final String filePath = "src/resources/scoreboard/scoreboard.txt";

    private List<HighScore> highScoreList;
    private String player;

    private HighScore currentHighScore;
    private boolean sortOrder = true;

    private record HighScore(String name, double time, int clicks, LocalDate date){
        @Override
        public String toString() {
            return name +
                    "\nMinutes: " + time +
                    "\nClicks: " + clicks +
                    "\nDate: " + date;
        }
    }

    public Scoreboard(){

        highScoreList = new ArrayList<>();

        populateHighScoreList();
    }
    public void loadScoreboard(JTextArea textArea) {
        textArea.setText(null);
        highScoreList.forEach(e -> textArea.append(e.toString() + "\n\n"));
    }
    public String getCurrentLeader(){
        if (!sortOrder){
            setSortOrder();
        }
        sortList();
        return highScoreList.get(0).toString();
    }
    public void populateHighScoreList(){
        List<String> lines = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filePath))){
            lines = stream.filter(line -> !line.equals("*")).toList();
        }catch (IOException e){
            e.printStackTrace();
        }

        int count = 0;
        String name = null;
        double min = 0;
        int clicks = 0;
        for (String line:lines) {

            switch (count){
                case 0 -> name = line;
                case 1 -> min = Double.parseDouble(line.replace("Minutes: ", ""));
                case 2 -> clicks = Integer.parseInt(line.replace("Clicks: ", ""));
                case 3 -> highScoreList.add(new HighScore(name, min, clicks, LocalDate.parse(line.replace("Date: ", ""))));
            }

            count++;
            if (count == 4){
                count = 0;
            }
        }
    }
    public void setSortOrder(){
        sortOrder = !sortOrder;
    }
    public String sortList(){
        if (sortOrder){
            highScoreList.sort(Comparator.comparingDouble(HighScore::time));
            return "Sort by clicks";
        } else{
            highScoreList.sort(Comparator.comparingInt(HighScore::clicks));
            return "Sort by time";
        }
    }
    public void setPlayerName(){
        player = JOptionPane.showInputDialog("who is playing?");
    }
    public void setCurrentHighScore(double time, int clicks){
        currentHighScore = new HighScore(player, time, clicks, LocalDate.now());
        highScoreList.add(currentHighScore);
        saveHighScore();
    }
    private void saveHighScore(){
        try(FileWriter fw = new FileWriter(Paths.get(filePath).toFile(), true);
            BufferedWriter bw = new BufferedWriter(fw)){

            bw.write("\n" + currentHighScore.toString() + "\n*");
            bw.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
