package Model;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

// Sources: https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/
public class AudioPlayer {

    private volatile static boolean isCancelled;
    private Thread currentThread = null;

    public static AudioPlayer playBgm(String path) {
        AudioPlayer player = new AudioPlayer();
        player.currentThread = new Thread(() -> {
            while (!AudioPlayer.isCancelled) {
                player.play(path);
            } // Continuously
        });
        player.currentThread.start();
        return player;
    }

    public static AudioPlayer playSoundEffect(String path) {
        AudioPlayer player = new AudioPlayer();
        player.currentThread = new Thread(() -> player.play(path));
        player.currentThread.start();
        return player;
    }

    public static void stopPlaying() {
        isCancelled = true;
    }

    public static void continuePlaying() {
        isCancelled = false;
    }

    public Thread getCurrentThread() {
        return currentThread;
    }

    public void play(String path) {
        final File file = new File(path);
        try (final AudioInputStream in = getAudioInputStream(file)) {
            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);
            try (final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int channel = inFormat.getChannels();
        final float sampleRate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, sampleRate, 16, channel, channel * 2, sampleRate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        final byte[] buffer = new byte[4096];
        for (int i = 0; i != -1 && !AudioPlayer.isCancelled; i = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, i);
        }
    }
}