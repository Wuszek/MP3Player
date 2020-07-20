import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class ExitMouseListener extends MouseAdapter {

    private JLabel exit;

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\quit_enabled.png";
        exit.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\quit.png";
        exit.setIcon(new ImageIcon(image2));
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("Exiting.");
        System.exit(0);
    }
}
