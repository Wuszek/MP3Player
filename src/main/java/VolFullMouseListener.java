import lombok.AllArgsConstructor;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

@AllArgsConstructor
public class VolFullMouseListener extends MouseAdapter {

    private boolean volfull;
    private boolean mute;
    private JLabel volFullButton;
    private JLabel muteButton;



    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\volume_full_enabled.png";
        volFullButton.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if(volfull == false) {
            String image2 = Configuration.currentPath+Configuration.imagePath + "\\volume_full.png";
            volFullButton.setIcon(new ImageIcon(image2));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(volfull == false) {
            volfull = true;
            String image = Configuration.currentPath+Configuration.imagePath+"\\volume_full_enabled.png";
            volFullButton.setIcon(new ImageIcon(image));
            try {
                new VolumeControl(1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }
            mute = false;
            String image2 = Configuration.currentPath+Configuration.imagePath+"\\mute.png";
            muteButton.setIcon(new ImageIcon(image2));

        }
        else if(volfull == true){
            volfull = false;
            String image = Configuration.currentPath+Configuration.imagePath+"\\volume_full.png";
            volFullButton.setIcon(new ImageIcon(image));
            try {
                new VolumeControl(0.5);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }

        }
    }
}
