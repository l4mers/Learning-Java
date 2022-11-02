package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip effectClip, musicClip;
    URL[] soundURL = new URL[4];
    URL musicURL;
    long timeStamp;

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/move.wav");
        soundURL[1] = getClass().getResource("/sound/victory.wav");
        soundURL[2] = getClass().getResource("/sound/cheat.wav");
        soundURL[3] = getClass().getResource("/sound/click.wav");

        musicURL = getClass().getResource("/sound/music.wav");
        setMusic();
    }
    
    public void setMusic(){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicURL);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setFile(int index){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            effectClip = AudioSystem.getClip();
            effectClip.open(audioInputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playMusic(){
        musicClip.start();
        repeat();
    }

    public void playSoundEffect(int index){
        setFile(index);
        effectClip.start();
    }

    public void repeat(){
        musicClip.loop(effectClip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        timeStamp = musicClip.getMicrosecondPosition();
        musicClip.stop();
    }

    public void restartMusic() {
        musicClip.setMicrosecondPosition(timeStamp);
        musicClip.start();
    }
}