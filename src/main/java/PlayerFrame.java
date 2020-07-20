import jaco.mp3.player.MP3Player;
import lombok.SneakyThrows;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;

import static javax.swing.JOptionPane.*;

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
    private JPanel playlistPanel;
    private JTextPane playlistPane;
    private JList playList1;
    private JTable table1;
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

        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\src\\main\\resources";

        String defaultTrack = currentPath+imagePath+"\\test.mp3";

        songFile = new File(defaultTrack);
        String fileName = songFile.getName();
        songNameDisplay.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);
        AppTitle.setText(title);


        table1.setDefaultEditor(Object.class, null);
        Object[] collumns = {"Nazwa", "Czas", "Ścieżka"};
        model.setColumnIdentifiers(collumns);
        table1.setModel(model);



        model.addRow(new Object[]{songFile.getName(), getDurationWithMp3Spi(songFile),songFile});



        Play.addMouseListener(new playMouseListener(Play, play, Pause, player) {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                super.mouseEntered(e);
//                String image = currentPath+imagePath+"\\play_enabled.png";
//                Play.setIcon(new ImageIcon(image));
//
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//                super.mouseExited(e);
//                if(play == false){
//                //System.out.println("exited");
//                String image2 = currentPath+imagePath+"\\play.png";
//                Play.setIcon(new ImageIcon(image2));
//                }
//
//            }
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                player.play();
//                String image = currentPath+imagePath+"\\play_enabled.png";
//                Play.setIcon(new ImageIcon(image));
//                play = true;
//                String image2 = currentPath+imagePath+"\\pause.png";
//                Pause.setIcon(new ImageIcon(image2));
//                System.out.println("Play");
//
//            }
        });

        Stop.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\stop_enabled.png";
                Stop.setIcon(new ImageIcon(image));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\stop.png";
                Stop.setIcon(new ImageIcon(image2));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.stop();
                String image = currentPath+imagePath+"\\play.png";
                Play.setIcon(new ImageIcon(image));
                play = false;
                String image2 = currentPath+imagePath+"\\pause.png";
                Pause.setIcon(new ImageIcon(image2));
                pause = false;
                songNameDisplay.setText("Stopped");
                System.out.println("Stop");

            }
        });
        Pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\pause_enabled.png";
                Pause.setIcon(new ImageIcon(image));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(pause == false) {
                    String image2 = currentPath + imagePath + "\\pause.png";
                    Pause.setIcon(new ImageIcon(image2));
                }
            }


            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.pause();
                pause = true;
                String image = currentPath+imagePath+"\\pause_enabled.png";
                Pause.setIcon(new ImageIcon(image));

            }
        });
        Repeat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(repeat == false){
                    repeat = true;
                    player.setRepeat(repeat);

                    System.out.println(currentPath);
                    System.out.println(imagePath);

                    String image = currentPath+imagePath+"\\repeat_enabled.png";
                    Repeat.setIcon(new ImageIcon(image));
                }else if (repeat == true){
                    repeat = false;
                    player.setRepeat(repeat);

                    String image = currentPath+imagePath+"\\repeat.png";
                    Repeat.setIcon(new ImageIcon(image));
                }

            }
        });

        Exit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\quit_enabled.png";
                Exit.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\quit.png";
                Exit.setIcon(new ImageIcon(image2));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Exiting.");
                System.exit(0);
            }


        });
        Settings.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\settings_enabled.png";
                Settings.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\settings.png";
                Settings.setIcon(new ImageIcon(image2));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showMessageDialog(null, "About");
            }
        });


        Open.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\open_enabled.png";
                Open.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\open.png";
                Open.setIcon(new ImageIcon(image2));
            }

            @SneakyThrows
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser openFileChooser = new JFileChooser(currentDirectory);
                openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 files only"));
                int result = openFileChooser.showOpenDialog(null);
                try{
                if (result == JFileChooser.APPROVE_OPTION){
                    songFile = openFileChooser.getSelectedFile();
                    model.addRow(new Object[]{songFile.getName(), getDurationWithMp3Spi(songFile), songFile});

                }
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Unsupported file." + "\n" + "This program approves only MP3 audio files.", "Incorrect audio file", JOptionPane.WARNING_MESSAGE);

                }

            }
        });
        AppTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2){
                    System.out.println("Double clicked");
                    if(windowCollapsed == false){
                        windowCollapsed = true;

                        setSize(new Dimension(700, 50));

                        //AppTitle.setFont(new Font("Nirmala UI", 0, 12));

                        AppTitle.setText("Playing now... | " + songFile.getName());

                        songNamePanel.setVisible(false);
                        controlPanel.setVisible(false);
                        playlistPanel.setVisible(false);

                    } else if (windowCollapsed == true){
                        windowCollapsed = false;
                        setSize(new Dimension(700, 300));

                        //AppTitle.setFont(new Font("Nirmala UI", 0, 18));
                        AppTitle.setText(title);
                        songNamePanel.setVisible(true);
                        controlPanel.setVisible(true);
                        playlistPanel.setVisible(true);

                    }
                }
            }
        });
        VolDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\volume_down_enabled.png";
                VolDown.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\volume_down.png";
                VolDown.setIcon(new ImageIcon(image2));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new volumeDownControl(0.05);
                volfull = false;
                mute = false;
                String image = currentPath+imagePath+"\\volume_full.png";
                VolFull.setIcon(new ImageIcon(image));
                String image2 = currentPath+imagePath+"\\mute.png";
                Mute.setIcon(new ImageIcon(image2));
            }

        });


        VolUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\volume_up_enabled.png";
                VolUp.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                String image2 = currentPath+imagePath+"\\volume_up.png";
                VolUp.setIcon(new ImageIcon(image2));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new volumeUpControl(0.05);
                volfull = false;
                mute = false;
                String image = currentPath+imagePath+"\\volume_full.png";
                VolFull.setIcon(new ImageIcon(image));
                String image2 = currentPath+imagePath+"\\mute.png";
                Mute.setIcon(new ImageIcon(image2));
            }
        });
        VolFull.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\volume_full_enabled.png";
                VolFull.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(volfull == false) {
                  String image2 = currentPath + imagePath + "\\volume_full.png";
                  VolFull.setIcon(new ImageIcon(image2));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(volfull == false) {
                    volfull = true;
                    String image = currentPath+imagePath+"\\volume_full_enabled.png";
                    VolFull.setIcon(new ImageIcon(image));
                    try {
                        new volumeControl(1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    mute = false;
                    String image2 = currentPath+imagePath+"\\mute.png";
                    Mute.setIcon(new ImageIcon(image2));

                }
                else if(volfull == true){
                    volfull = false;
                    String image = currentPath+imagePath+"\\volume_full.png";
                    VolFull.setIcon(new ImageIcon(image));
                    try {
                        new volumeControl(0.5);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }

                }
            }
        });
        Mute.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\mute_enabled.png";
                Mute.setIcon(new ImageIcon(image));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(mute == false) {
                  String image2 = currentPath + imagePath + "\\mute.png";
                  Mute.setIcon(new ImageIcon(image2));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(mute == false) {
                  String image = currentPath+imagePath+"\\mute_enabled.png";
                  Mute.setIcon(new ImageIcon(image));
                    try {
                        new volumeControl(0);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    mute = true;
                  volfull = false;
                  String image2 = currentPath+imagePath+"\\volume_full.png";
                  VolFull.setIcon(new ImageIcon(image2));
                }
                else if(mute == true){
                    String image = currentPath+imagePath+"\\mute.png";
                    Mute.setIcon(new ImageIcon(image));
                    try {
                        new volumeControl(0.5);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    mute = false;
                }
            }
        });

        AppTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        AppTitle.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                System.out.println(x + " " + y);
                setLocation(x - xMouse, y - yMouse);
                repaint();
            }
        });

        pack();
        setLocationRelativeTo(null);


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2) {

                    int column = 2;
                    int row = table1.getSelectedRow();
                    String newPath = table1.getModel().getValueAt(row, column).toString();
                    System.out.println(newPath);

                    songFile = new File(newPath);

                    player.addToPlayList(songFile);
                    player.skipForward();
                    currentDirectory = songFile.getAbsolutePath();
                    songNameDisplay.setText("Playing now... | " + songFile.getName());

                    String image = currentPath+imagePath+"\\play_enabled.png";
                    Play.setIcon(new ImageIcon(image));
                    play = true;
                    String image2 = currentPath+imagePath+"\\pause.png";
                    Pause.setIcon(new ImageIcon(image2));
                }
            }
        });


        table1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int i = table1.getSelectedRow();
                    if(i >= 0){
                        model.removeRow(i);
                    }
                    else{
                        System.out.println("Delete error!");
                    }
                }
            }
        });
    }



    private MP3Player mp3Player(){
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }



    private static String getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException {

        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000) % 60;
            int min = (mili / 1000) / 60;
            //System.out.println(min + ":" + sec);
            String minuta = String.valueOf(min);
            String sekunda = String.valueOf(sec);
            String czas = minuta + ":" + sekunda;
            return czas;
        } else {
            throw new UnsupportedAudioFileException();

        }

    }







}
