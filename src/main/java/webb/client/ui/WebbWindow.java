package webb.client.ui;

import javax.swing.JFrame;
public class WebbWindow extends JFrame {

    private static WebbWindow instance;

    private WebbWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Webb Window");

        this.setSize(600, 600);
    }

    public static WebbWindow getInstance() {
        if (instance == null) {
            instance = new WebbWindow();
        }
        return instance;
    }

}
