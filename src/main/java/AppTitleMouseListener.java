import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class AppTitleMouseListener extends MouseAdapter {

    private boolean windowCollapsed;
    private JPanel songNamePanel;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(e.getClickCount() == 2){
            System.out.println("Double clicked");
            if(windowCollapsed == false){
                windowCollapsed = true;

                setSize(new Dimension(700, 50));

                //AppTitle.setFont(new Font("Nirmala UI", 0, 12));

                appTitle.setText("Playing now... | " + songFile.getName());

                songNamePanel.setVisible(false);
                controlPanel.setVisible(false);
                playlistPanel.setVisible(false);

            } else if (windowCollapsed == true){
                windowCollapsed = false;
                setSize(new Dimension(700, 300));

                //AppTitle.setFont(new Font("Nirmala UI", 0, 18));
                appTitle.setText(title);
                songNamePanel.setVisible(true);
                controlPanel.setVisible(true);
                playlistPanel.setVisible(true);

            }
        }
    }
}
