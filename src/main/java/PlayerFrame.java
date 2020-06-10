import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;

public class PlayerFrame extends javax.swing.JFrame{
    private JPanel mainPanel;
    private JLabel Mute;
    private JLabel VolFull;
    private JLabel VolUp;
    private JLabel VolDown;
    private JPanel controlPanel;
    private JLabel Stop;
    private JLabel Play;
    private JLabel Pause;
    private JLabel Repeat;
    private JLabel AppTitle;
    private JLabel Exit;
    private JLabel Settings;
    private JPanel headPanel;
    private JPanel songNamePanel;
    private JLabel Open;
    private JPanel songNameSubPanel;
    private JLabel songNameDisplay;

    MP3Player player;
    File songFile;
    String currentDirectory = "home.user";
    String currentPath;
    String imagePath;
    boolean repeat = false;
    boolean windowCollapsed = false;
    int xMouse, yMouse;
    static String title = "App title";

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }


        JFrame frame = new JFrame(title);
        frame.setContentPane(new PlayerFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setResizable(false);

    }



    public PlayerFrame() {
        songFile = new File("C:\\Users\\Wiktor\\Documents\\GIT projects\\MP3Player\\lib\\test.mp3");
        String fileName = songFile.getName();
        songNameDisplay.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);

        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\images";
    }


    private MP3Player mp3Player(){
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

}
