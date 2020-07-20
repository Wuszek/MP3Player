import jaco.mp3.player.MP3Player;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

@AllArgsConstructor
public class playMouseListener extends MouseAdapter {


    private JLabel playButton;
    private boolean play;
    private JLabel pauseButton;
    MP3Player player;


    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\play_enabled.png";
        playButton.setIcon(new ImageIcon(image));

    }
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);

        if(play == false){
            //System.out.println("exited");
            String image2 = Configuration.currentPath+Configuration.imagePath+"\\play.png";
            playButton.setIcon(new ImageIcon(image2));
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        player.play();
        String image = Configuration.currentPath+Configuration.imagePath+"\\play_enabled.png";
        playButton.setIcon(new ImageIcon(image));
        play = true;
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\pause.png";
        pauseButton.setIcon(new ImageIcon(image2));
        System.out.println("Play");

    }
}
