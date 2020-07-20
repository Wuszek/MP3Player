import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

@AllArgsConstructor
public class openMouseListener extends MouseAdapter {

    private JLabel openButton;
    private File songFile;
    private DefaultTableModel model;
    private String currentDirectory;


    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        String image = Configuration.currentPath+Configuration.imagePath+"\\open_enabled.png";
        openButton.setIcon(new ImageIcon(image));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        String image2 = Configuration.currentPath+Configuration.imagePath+"\\open.png";
        openButton.setIcon(new ImageIcon(image2));
    }

    @SneakyThrows
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        JFileChooser openFileChooser = new JFileChooser(currentDirectory);
        openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "openButton MP3 files only"));
        int result = openFileChooser.showOpenDialog(null);
        try{
            if (result == JFileChooser.APPROVE_OPTION){
                songFile = openFileChooser.getSelectedFile();
                model.addRow(new Object[]{songFile.getName(), new getDurationWithMp3Spi(songFile), songFile});

            }
        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null, "Unsupported file." + "\n" + "This program approves only MP3 audio files.", "Incorrect audio file", JOptionPane.WARNING_MESSAGE);

        }

    }
}
