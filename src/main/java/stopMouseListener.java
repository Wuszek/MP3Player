import jaco.mp3.player.MP3Player;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class stopMouseListener extends MouseAdapter {

    private JLabel playButton;
    private boolean play;
    private boolean pause;
    private JLabel stopButton;
    private JLabel pauseButton;
    private JLabel songNameDisplay;
    MP3Player player;


    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath + Configuration.imagePath + "\\stop_enabled.png";
        stopButton.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        String image2 = Configuration.currentPath + Configuration.imagePath + "\\stop.png";
        stopButton.setIcon(new ImageIcon(image2));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        player.stop();
        String image = Configuration.currentPath + Configuration.imagePath + "\\play.png";
        playButton.setIcon(new ImageIcon(image));
        play = false;
        String image2 = Configuration.currentPath + Configuration.imagePath + "\\pause.png";
        pauseButton.setIcon(new ImageIcon(image2));
        pause = false;
        songNameDisplay.setText("Stopped");
        System.out.println("stopButton");

    }
}
