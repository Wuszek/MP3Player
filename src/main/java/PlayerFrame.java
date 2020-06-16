import jaco.mp3.player.MP3Player;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
    static String title = "App title";
    String playlist;


    public static void main(String[] args) {

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

    public PlayerFrame() {

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

//        lm.addElement(songFile);
//        playList1.setModel(lm);

        table1.setDefaultEditor(Object.class, null);
        Object[] collumns = {"Nazwa", "Ścieżka"};
        model.setColumnIdentifiers(collumns);
        table1.setModel(model);

//        Object[] row = new Object[2];
//        row[0] = songFile.getName();
//        row[1] = songFile;

        model.addRow(new Object[]{songFile.getName(), songFile});



        Play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                String image = currentPath+imagePath+"\\play_enabled.png";
                Play.setIcon(new ImageIcon(image));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(play == false){
                //System.out.println("exited");
                String image2 = currentPath+imagePath+"\\play.png";
                Play.setIcon(new ImageIcon(image2));
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.play();
                String image = currentPath+imagePath+"\\play_enabled.png";
                Play.setIcon(new ImageIcon(image));
                play = true;
                String image2 = currentPath+imagePath+"\\pause.png";
                Pause.setIcon(new ImageIcon(image2));
                System.out.println("Play");

            }
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
                JOptionPane .showMessageDialog(null, "About");
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

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser openFileChooser = new JFileChooser(currentDirectory);
                openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 files only"));
                int result = openFileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    songFile = openFileChooser.getSelectedFile();

                    //String filename = songFile.getName();

                    //player.addToPlayList(songFile);
                    //player.skipForward();
                    //currentDirectory = songFile.getAbsolutePath();
                    //songNameDisplay.setText("Playing now... | " + songFile.getName());

                    lm.addElement(songFile);
                    model.addRow(new Object[]{songFile.getName(), songFile});

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
                volumeDownControl(0.05);
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
                volumeUpControl(0.05);
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
                    volumeControl(1);
                    mute = false;
                    String image2 = currentPath+imagePath+"\\mute.png";
                    Mute.setIcon(new ImageIcon(image2));

                }
                else if(volfull == true){
                    volfull = false;
                    String image = currentPath+imagePath+"\\volume_full.png";
                    VolFull.setIcon(new ImageIcon(image));
                    volumeControl(0.5);

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
                  volumeControl(0);
                  mute = true;
                  volfull = false;
                  String image2 = currentPath+imagePath+"\\volume_full.png";
                  VolFull.setIcon(new ImageIcon(image2));
                }
                else if(mute == true){
                    String image = currentPath+imagePath+"\\mute.png";
                    Mute.setIcon(new ImageIcon(image));
                    volumeControl(0.5);
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
                //Point p = e.getPoint();
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

                    int column = 1;
                    int row = table1.getSelectedRow();
                    String newPath = table1.getModel().getValueAt(row, column).toString();
                    System.out.println(newPath);

                    //newSongFile = new File(newPath);
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
    }



    private MP3Player mp3Player(){
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

    private void volumeDownControl(double valueToPlusMinus){
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    System.out.println(currentVolume);
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float)currentVolume-(double)volumeToCut);
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException){
            } catch (IllegalArgumentException illException){
                } finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }

    }

    private void volumeUpControl(double valueToPlusMinus){
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    System.out.println(currentVolume);
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float)currentVolume+(double)volumeToCut);
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException){
                } catch (IllegalArgumentException illException){
                } finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }

    }

    private void volumeControl(double valueToPlusMinus){
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((double)volumeToCut);
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException){
                } catch (IllegalArgumentException illException){
                } finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }

    }

}
