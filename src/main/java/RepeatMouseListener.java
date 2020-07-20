import jaco.mp3.player.MP3Player;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class RepeatMouseListener extends MouseAdapter {

    private boolean repeat;
    private JLabel repeatButton;
    MP3Player player;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(repeat == false){
            repeat = true;
            player.setRepeat(repeat);

            System.out.println(Configuration.currentPath);
            System.out.println(Configuration.imagePath);

            String image = Configuration.currentPath+Configuration.imagePath+"\\repeat_enabled.png";
            repeatButton.setIcon(new ImageIcon(image));
        }else if (repeat == true){
            repeat = false;
            player.setRepeat(repeat);

            String image = Configuration.currentPath+Configuration.imagePath+"\\repeat.png";
            repeatButton.setIcon(new ImageIcon(image));
        }

    }
}
