package game;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;

public class playsound {
    private File file;
    private AudioInputStream stream;
    private AudioFormat format;
    private Info info;
    private Clip clip;
    static boolean[] b = new boolean[]{true, true, true, true};

    public playsound() {
    }

    void open(String s) {
        this.file = new File(s);

        try {
            this.stream = AudioSystem.getAudioInputStream(this.file);
            this.format = this.stream.getFormat();
        } catch (UnsupportedAudioFileException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    void play() {
        this.info = new Info(Clip.class, this.format);

        try {
            this.clip = (Clip)AudioSystem.getLine(this.info);
            this.clip.open(this.stream);
        } catch (LineUnavailableException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    void stop() {
        this.clip.stop();
    }

    void start() {
        this.clip.start();
    }

    void loop() {
        this.clip.loop(20);
    }
}
