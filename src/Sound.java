import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private int currentMusicPlaying;
    private Clip musicClip;
    private Clip hitClip;
    private Clip popClip;
    private Clip gameOverClip;
    private AudioInputStream musicStream;
    private AudioInputStream shootingStream;
    private AudioInputStream popStream;
    private AudioInputStream gameOverStream;

    public Sound() {
        try {
            this.musicClip = AudioSystem.getClip();
            this.hitClip = AudioSystem.getClip();
            this.popClip = AudioSystem.getClip();
            this.gameOverClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMusicClip(int i){
        try {
            switch(i){
                case Constants.INTRO_NUM -> {
                    this.musicClip.close();
                    this.musicStream = AudioSystem.getAudioInputStream(new File(Constants.INTRO));
                    this.musicClip.open(this.musicStream);
                    this.currentMusicPlaying = Constants.INTRO_NUM;
                }
                case Constants.DURING_NUM -> {
                    this.musicClip.close();
                    this.musicStream = AudioSystem.getAudioInputStream(new File(Constants.DURING));
                    this.musicClip.open(this.musicStream);
                    this.currentMusicPlaying = Constants.DURING_NUM;

                }

            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public int lastPlayedMusic(){
        return this.currentMusicPlaying;
    }
    public void playMusic() {
        this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopMusic(){
        this.musicClip.stop();
    }
    public void closeMusic(){
        this.musicClip.close();
    }

    public void loadHitSound(){
        try {
            this.shootingStream = AudioSystem.getAudioInputStream(new File(Constants.SHOOTING));
            this.hitClip.open(this.shootingStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playHitSound(){
        this.hitClip.start();
    }

    public void loadPopSound(){
        try {
            if(!this.popClip.isOpen()){
                this.popStream = AudioSystem.getAudioInputStream(new File(Constants.POP));
                this.popClip.open(this.popStream);
            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playPopSound(){
        this.popClip.setMicrosecondPosition(0);
        this.popClip.start();
    }


    public void loadGameOverSound(){
        try {
            this.gameOverStream = AudioSystem.getAudioInputStream(new File(Constants.GAME_OVER));
            this.gameOverClip.open(this.gameOverStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playGameOverSound(){
        this.gameOverClip.setMicrosecondPosition(0);
        this.gameOverClip.start();
    }
    public void stopGameOverSound(){
        this.gameOverClip.stop();
        this.gameOverClip.close();
    }


}
