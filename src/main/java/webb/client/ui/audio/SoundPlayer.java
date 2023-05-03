package webb.client.ui.audio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import webb.client.ui.constants.WebbAudio;

public class SoundPlayer extends Thread {

    private final AudioInputStream audioInputStream;
    private final ThreadHanger hanger = new ThreadHanger();
    private Clip clip;
    private float masterVolume = 1f;
    private boolean playing;
    private boolean loop = false;

    public SoundPlayer(String name) {
        final String path = "/webb/audio/" + name + ".wav";
        try {
            audioInputStream = AudioSystem.getAudioInputStream(WebbAudio.class.getResourceAsStream(path));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            this.play();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMasterVolume(float masterVolume) {
        if(masterVolume < 0 || masterVolume > 1) throw new IllegalArgumentException("Master volume must be between 0 and 1");
        this.masterVolume = masterVolume;
    }

    public float getMasterVolume() {

        if(clip == null) {return -1;}

        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            return (float) Math.pow(10f, gainControl.getValue() / 20f);
        }
        catch(IllegalArgumentException e) {
            return -1;
        }
    }

    public void stopPlaying() {
        playing = false;
        clip.stop();
        clip.close();

    }

    public void setLoop() {
        loop = true;
    }


    private void play() {

        try {
            clip = AudioSystem.getClip();
            clip.addLineListener(hanger);
            clip.open(audioInputStream);
            try {

                if(loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else {
                    clip.loop(0);
                }

                clip.setFramePosition(0);
                clip.start();
                playing = true;

                internalSetVolume(masterVolume);

                hanger.waitUntilDone();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                clip.close();
                playing = false;
            }
            audioInputStream.close();
        }
        catch(LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // set the volume
    private void internalSetVolume(float vol) {
        if(clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Math.max(-80, 20f * (float) Math.log10(vol)));
        }
    }

    public long getTotalTimeMS() {
        if(clip == null) {
            return -1;
        }
        return clip.getMicrosecondLength();
    }

    public long getCurrentTimeMS() {
        if(clip == null) {
            return -1;
        }
        return clip.getMicrosecondPosition();
    }

    public boolean isPlaying() {
        return playing;
    }

    public void fadeVolumeOverXSeconds(float to, int period, Runnable callback) {
        float startingVolume = getMasterVolume();
        if (startingVolume < 0)  {
            startingVolume = 1;
        }

        final int interval = 50;
        final float amount = (to - startingVolume) * interval / period;
        final boolean up = (to > startingVolume);

        AtomicReference<Float> tmpVolume = new AtomicReference<Float>(startingVolume);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                tmpVolume.set(tmpVolume.get() + amount);

                if(up && tmpVolume.get() >= to) {
                    internalSetVolume(to);
                    timer.cancel();
                    if(callback != null) {
                        callback.run();
                    }
                }
                else if (!up && tmpVolume.get() <= to) {
                    internalSetVolume(to);
                    timer.cancel();
                    if(callback != null) {
                        callback.run();
                    }
                }
                else {
                    internalSetVolume(tmpVolume.get());
                }


            }
        }, 0, interval);

    }

    //Basically used to hang the thread while the sound plays.
    //Bit hacky but it works
    static class ThreadHanger implements LineListener {
        private boolean done = false;

        @Override
        public synchronized void update(LineEvent event) {
            final Type eventType = event.getType();
            if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }

        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) {
                wait();
            }
        }
    }

}
