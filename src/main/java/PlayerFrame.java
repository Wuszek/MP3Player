import jaco.mp3.player.MP3Player;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

public class PlayerFrame extends javax.swing.JFrame{
    private JPanel mainPanel;
    private JLabel muteButton;
    private JLabel volFullButton;
    private JLabel volUpButton;
    private JLabel volDownButton;
    private JPanel controlPanel;
    private JLabel stopButton;
    private JLabel playButton;
    private JLabel pauseButton;
    private JLabel repeatButton;
    private JLabel appTitle;
    private JLabel exitButton;
    private JLabel settingsButton;
    private JPanel headPanel;
    private JPanel songNamePanel;
    private JLabel openButton;
    private JPanel songNameSubPanel;
    private JLabel songNameDisplay;
    private JPanel playlistPanel;
    private JTextPane playlistPane;
    private JList playList1;
    private JTable playlistTable;
    DefaultListModel lm = new DefaultListModel();
    DefaultTableModel model = new DefaultTableModel();
    private JLabel playlistLabel;
    private JTextArea playlistArea;

    MP3Player player;
    File songFile;
    File newSongFile;
    String currentDirectory = "home.user";
    String currentPath;
    String imagePath;
    boolean repeat = false;
    boolean play = false;
    boolean pause = false;
    boolean mute = false;
    boolean volfull = false;
    boolean windowCollapsed = false;
    int xMouse, yMouse;
    static String title = "Simple mp3 player";


    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

    new PlayerFrame().setVisible(true);
    }

    public PlayerFrame() throws IOException, UnsupportedAudioFileException {

        setTitle("MP3 Player");
        setUndecorated(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        String defaultTrack = Configuration.currentPath+ Configuration.imagePath+"\\test.mp3";

        songFile = new File(defaultTrack);
        String fileName = songFile.getName();
        songNameDisplay.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);
        appTitle.setText(title);


        playlistTable.setDefaultEditor(Object.class, null);
        Object[] collumns = {"Nazwa", "Czas", "Ścieżka"};
        model.setColumnIdentifiers(collumns);
        playlistTable.setModel(model);

        model.addRow(new Object[]{songFile.getName(), SongDurationCalculator.getDurationWithMp3Spi(songFile),songFile});
        playButton.addMouseListener(new PlayMouseListener(playButton, play, pauseButton, player));
        stopButton.addMouseListener(new StopMouseListener(playButton,play, pause, stopButton, pauseButton, songNameDisplay, player));
        pauseButton.addMouseListener(new PauseMouseListener(pauseButton, pause, player));
        repeatButton.addMouseListener(new RepeatMouseListener(repeat, repeatButton, player));
        exitButton.addMouseListener(new ExitMouseListener(exitButton));
        settingsButton.addMouseListener(new SettingsMouseListener(settingsButton));
        openButton.addMouseListener(new OpenMouseListener(openButton, songFile, model, currentDirectory));
        appTitle.addMouseListener(new AppTitleMouseListener(windowCollapsed, songNamePanel, controlPanel, playlistPanel, appTitle, songFile, title, this));
        volDownButton.addMouseListener(new VolDownMouseListener(volDownButton, volfull, mute, volFullButton, muteButton));
        volUpButton.addMouseListener(new VolUpMouseListener(volUpButton,volfull, mute, volFullButton, muteButton));
        volFullButton.addMouseListener(new VolFullMouseListener(volfull, mute, volFullButton, muteButton));
        muteButton.addMouseListener(new MuteMouseListener(volfull, mute, volFullButton, muteButton));
        appTitle.addMouseListener(new AppTitleMousePressed(xMouse, yMouse));
        appTitle.addMouseMotionListener(new AppTitleMouseDragged(xMouse, yMouse, this));
        playlistTable.addMouseListener(new PlaylistTableMouseListener(playlistTable, songFile, player, playButton, play, pauseButton, songNameDisplay, currentDirectory));
        playlistTable.addKeyListener(new PlaylistTableKeyListener(model, playlistTable));

        pack();
        setLocationRelativeTo(null);
    }

    private MP3Player mp3Player(){
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

}
