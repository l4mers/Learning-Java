package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class HighScore {

    public record Score(String name, int score, LocalDate date){
        @Override
        public String toString() {
            return name + "/" + score + "/" + date;
        }
    }
    public void addScore(String name, int score){
        List<Score> scoreList = getScoresAsList();
        if(scoreList.size() < 10){
            scoreList.add(new Score(name, score, LocalDate.now()));
            toFile(scoreList);
        }
        else if (score > scoreList.get(0).score){
            scoreList.remove(0);
            scoreList.add(new Score(name, score, LocalDate.now()));
            toFile(scoreList);
        }
    }
    public List<Score> getScoresAsList() {
        List<String> contentList = Collections.emptyList();
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/scoreboard.txt"))) {
            contentList = stream.filter(line -> !line.isBlank()).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Score> scoreList = new ArrayList<>();
        for (String s :
                contentList) {
            String[] record = s.split("/");
            scoreList.add(new Score(record[0], Integer.parseInt(record[1]), LocalDate.parse(record[2])));
        }
        scoreList.sort(Comparator.comparingInt(e -> e.score));
        return scoreList;
    }
    private void toFile(List<Score> scoreList) {
        try (FileWriter fw = new FileWriter(Paths.get("src/resources/scoreboard.txt").toFile());
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Score score:
                    scoreList) {
                bw.write("\n" + score.toString());
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getScoreToStringList(){
        List<Score> scoreList = getScoresAsList();
        ArrayList<String> scoreAsString = new ArrayList<>();
        for (Score score:
                scoreList) {
            scoreAsString.add(score.toString());
        }
        return scoreAsString;
    }
}
