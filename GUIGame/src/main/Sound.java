package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/main1.wav");
        soundURL[1] = getClass().getResource("/sound/main2.wav");
        soundURL[2] = getClass().getResource("/sound/lootKey.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/foodSound.wav");
        soundURL[5] = getClass().getResource("/sound/treasure.wav");
        soundURL[6] = getClass().getResource("/sound/swing_sword.wav");
        soundURL[7] = getClass().getResource("/sound/deal_damage.wav");
        soundURL[8] = getClass().getResource("/sound/take_damage.wav");
        soundURL[9] = getClass().getResource("/sound/kill_slime.wav");
        soundURL[10] = getClass().getResource("/sound/level_up.wav");
    }

    public void setFile(int index){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){

        clip.start();
        repeat();
        clip.stop();
    }
    public void repeat(){

        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
