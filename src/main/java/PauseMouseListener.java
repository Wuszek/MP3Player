import jaco.mp3.player.MP3Player;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class PauseMouseListener extends MouseAdapter {

    private JLabel pauseButton;
    private boolean pause;
    MP3Player player;

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\pause_enabled.png";
        pauseButton.setIcon(new ImageIcon(image));

    }
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if(pause == false) {
            String image2 = Configuration.currentPath+Configuration.imagePath + "\\pause.png";
            pauseButton.setIcon(new ImageIcon(image2));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        player.pause();
        pause = true;
        String image = Configuration.currentPath+Configuration.imagePath+"\\pause_enabled.png";
        pauseButton.setIcon(new ImageIcon(image));

    }
}

