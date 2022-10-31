package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;

    URL[] soundURL = new URL[5];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/music.wav");
        soundURL[1] = getClass().getResource("/sound/move.wav");
        soundURL[2] = getClass().getResource("/sound/victory.wav");
        soundURL[3] = getClass().getResource("/sound/cheat.wav");
        soundURL[4] = getClass().getResource("/sound/click.wav");

    }

    public void setFile(int index){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void playMusic(){
        setFile(0);
        clip.start();
        repeat();
    }
    public void playSoundEffect(int index){
        setFile(index);
        clip.start();
    }
    public void repeat(){

        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
