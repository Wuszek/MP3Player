import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.JOptionPane.showMessageDialog;

@AllArgsConstructor
public class SettingsMouseListener extends MouseAdapter {

    private JLabel settings;


    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\settings_enabled.png";
        settings.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\settings.png";
        settings.setIcon(new ImageIcon(image2));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        showMessageDialog(null, "About");
    }
}
