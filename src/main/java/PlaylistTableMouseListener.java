import jaco.mp3.player.MP3Player;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

@AllArgsConstructor
public class PlaylistTableMouseListener extends MouseAdapter {

    private JTable playlistTable;
    private File songFile;
    MP3Player player;
    private JLabel playButton;
    private boolean play;
    private JLabel pauseButton;
    private JLabel songNameDisplay;
    private String currentDirectory;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(e.getClickCount() == 2) {

            int column = 2;
            int row = playlistTable.getSelectedRow();
            String newPath = playlistTable.getModel().getValueAt(row, column).toString();
            System.out.println(newPath);

            songFile = new File(newPath);

            player.addToPlayList(songFile);
            player.skipForward();
            currentDirectory = songFile.getAbsolutePath();
            songNameDisplay.setText("Playing now... | " + songFile.getName());

            String image = Configuration.currentPath+Configuration.imagePath+"\\play_enabled.png";
            playButton.setIcon(new ImageIcon(image));
            play = true;
            String image2 = Configuration.currentPath+Configuration.imagePath+"\\pause.png";
            pauseButton.setIcon(new ImageIcon(image2));
        }
    }
}
