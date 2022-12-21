package main;

public class DJ {

    GamePanel gp;

    public DJ (GamePanel gp){
        this.gp = gp;
    }
    public void playMusic(){
        gp.music.setFile(0);
        gp.music.play();
        gp.music.repeat();
    }
    public void stopMusic(){
        gp.music.stop();
    }
    public void playSoundEffect(int index){
        gp.soundEffect.setFile(index);
        gp.soundEffect.play();
    }
    public void pauseMusic(){
        gp.music.pause();
    }
    public void resetMusic(){
        gp.music.reset();
    }
    public void resumeMusic(){
        gp.music.resume();
        gp.music.repeat();
    }
}
