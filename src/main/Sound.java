package main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;


public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    private FloatControl volumeControl;

    public Sound(){
        soundURL[0]=getClass().getResource("/sound/game.wav");
        soundURL[1]=getClass().getResource("/sound/menu.wav");
        soundURL[2]=getClass().getResource("/sound/chest.wav");
        soundURL[3]=getClass().getResource("/sound/key.wav");
        soundURL[4]=getClass().getResource("/sound/coin.wav");
        soundURL[5]=getClass().getResource("/sound/damage.wav");
        soundURL[6]=getClass().getResource("/sound/door.wav");
        soundURL[7]=getClass().getResource("/sound/lever.wav");

    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        }catch(Exception e)
        {

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void setVolume(float volume) {
        // volume e între -80.0 (mut) și 6.0 (maxim) de regulă
        if(volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}
