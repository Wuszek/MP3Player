import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class VolUpMouseListener extends MouseAdapter {

    private JLabel volUpButton;
    private boolean volfull;
    private boolean mute;
    private JLabel volFullButton;
    private JLabel muteButton;

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\volume_up_enabled.png";
        volUpButton.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\volume_up.png";
        volUpButton.setIcon(new ImageIcon(image2));
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        new VolumeUpControl(0.05);
        volfull = false;
        mute = false;
        String image = Configuration.currentPath+Configuration.imagePath+"\\volume_full.png";
        volFullButton.setIcon(new ImageIcon(image));
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\mute.png";
        muteButton.setIcon(new ImageIcon(image2));
    }
}
