package webb.client.ui.audio;

import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import webb.client.ui.WebbWindow;

public class SFXPlayer extends Thread {

    private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
    //private SoundPlayer sp;

    public SFXPlayer() {
        super("Audio Player - SFX");
    }

    public void queue(String name) {
        if(!WebbWindow.getInstance().getGameOptions().isSfxEnabled()) {
            return;
        }
        queue.add(name);
    }

    @Override
    public void run() {
        while(true) {

            if(queue.peek() != null) {
                SoundPlayer sp = new SoundPlayer(queue.poll());
                sp.start();
//                try {
//                    sp.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        }
    }

}
