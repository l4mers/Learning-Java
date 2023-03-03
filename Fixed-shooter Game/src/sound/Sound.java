package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[3];
    long timeStamp = 0;

    public Sound(){
        soundURL[0] = getClass().getResource("/music.wav");
        soundURL[1] = getClass().getResource("/laser_sound.wav");
        soundURL[2] = getClass().getResource("/crash_sound.wav");
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
    }
    public void pause(){
        timeStamp = clip.getMicrosecondPosition();
        clip.stop();
    }
    public void resume(){
        clip.setMicrosecondPosition(timeStamp);
        clip.start();
    }
    public void repeat(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void reset(){
        timeStamp = 0;
    }
    public void stop(){
        clip.stop();
    }
}
