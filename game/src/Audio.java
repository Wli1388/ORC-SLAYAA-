import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Audio {
   private Clip clip;

    public void play(URL name){
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(name);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch(Exception e){
        }
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(URL name){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(URL name){
        clip.stop();
    }
}
