import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip musicClip;
    private Clip shootingClip;
    private Clip popClip;
    private Clip powerUpClip;
    private Clip gameOverClip;
    private AudioInputStream emptyMusicStream;
    private AudioInputStream emptyShootingStream;
    private AudioInputStream emptyPopStream;
    private AudioInputStream emptyPowerUpStream;
    private AudioInputStream gameOverStream;

    public Sound() {
        try {
            this.musicClip = AudioSystem.getClip();
            this.shootingClip = AudioSystem.getClip();
            this.popClip = AudioSystem.getClip();
            this.powerUpClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMusicClip(int i){
        try {
            switch(i){
                case 1 -> {
                    this.emptyMusicStream = AudioSystem.getAudioInputStream(new File(Constants.INTRO));
                    this.musicClip.open(this.emptyMusicStream);
                }
                case 2 -> {
                    this.emptyMusicStream = AudioSystem.getAudioInputStream(new File(Constants.TRANSITION));
                    this.musicClip.open(this.emptyMusicStream);
                }
                case 3 -> {
                    this.emptyMusicStream = AudioSystem.getAudioInputStream(new File(Constants.DURING));
                    this.musicClip.open(this.emptyMusicStream);
                }

            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playMusic() {
        this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopMusic(){
        this.musicClip.stop();
        this.musicClip.close();
    }

    public void loadShootingSound(){
        try {
            this.emptyShootingStream = AudioSystem.getAudioInputStream(new File(Constants.SHOOTING));
            this.shootingClip.open(this.emptyShootingStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playShootingSound(){
        this.shootingClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopShootingSound(){
        this.shootingClip.stop();
        this.shootingClip.close();
    }

    public void loadPopSound(){
        try {
            if(!this.popClip.isOpen()){
                this.emptyPopStream = AudioSystem.getAudioInputStream(new File(Constants.POP));
                this.popClip.open(this.emptyPopStream);
            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playPopSound(){
        this.popClip.setMicrosecondPosition(0);
        this.popClip.start();
    }
    public void stopPopSound(){
        this.popClip.stop();
        this.popClip.close();
    }

    public void loadPowerUpSound(){
        try {
            this.emptyPowerUpStream = AudioSystem.getAudioInputStream(new File(Constants.POWER_UP));
            this.powerUpClip.open(this.emptyPowerUpStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void playPowerUpSound(){
        this.powerUpClip.setMicrosecondPosition(0);
        this.powerUpClip.start();
    }
    public void stopPowerUpSound(){
        this.powerUpClip.stop();
        this.powerUpClip.close();
    }

    public void loadGameOverSound(){
        try {
            this.gameOverStream = AudioSystem.getAudioInputStream(new File(Constants.POWER_UP));
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
        this.powerUpClip.stop();
        this.powerUpClip.close();
    }


}
