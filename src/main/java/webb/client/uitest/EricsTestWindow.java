package webb.client.uitest;

import javax.swing.JFrame;
import webb.client.uitest.screens.CreditsScreen;


public class EricsTestWindow extends JFrame {

        public EricsTestWindow() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Example Window");

            //this.add(new MainMenuScreen());
            this.add(new CreditsScreen());


            this.setSize(600, 600);
           // this.pack();
        }
}
