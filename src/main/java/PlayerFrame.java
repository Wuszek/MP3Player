import jaco.mp3.player.MP3Player;


import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

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
    boolean play = false;
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

    new PlayerFrame().setVisible(true);



    }



    public PlayerFrame() {

        setUndecorated(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);



        songFile = new File("C:\\Users\\Wiktor\\Documents\\GIT projects\\MP3Player\\lib\\test.mp3");
        String fileName = songFile.getName();
        songNameDisplay.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);
        AppTitle.setText(title);
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\src\\main\\resources";

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
                System.out.println("exited");
                String image2 = currentPath+imagePath+"\\play.png";
                Play.setIcon(new ImageIcon(image2));
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.play();
                //boolean play = true;
                String image = currentPath+imagePath+"\\play_enabled.png";
                Play.setIcon(new ImageIcon(image));
                play = true;
            }
        });
        Stop.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.stop();
                String image = currentPath+imagePath+"\\play.png";
                Play.setIcon(new ImageIcon(image));
                play = false;

            }
        });
        Pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.pause();
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
                System.out.println("exited");
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
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane .showMessageDialog(null, "About");
            }
        });
        Open.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser openFileChooser = new JFileChooser(currentDirectory);
                openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 files only"));
                int result = openFileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    songFile = openFileChooser.getSelectedFile();
                    player.addToPlayList(songFile);
                    player.skipBackward();
                    currentDirectory = songFile.getAbsolutePath();
                    songNameDisplay.setText("Playing now... | " + songFile.getName());
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

                    } else if (windowCollapsed == true){
                        windowCollapsed = false;
                        setSize(new Dimension(700, 250));

                        //AppTitle.setFont(new Font("Nirmala UI", 0, 18));
                        AppTitle.setText(title);
                        songNamePanel.setVisible(true);
                        controlPanel.setVisible(true);

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
                System.out.println("exited");
                String image2 = currentPath+imagePath+"\\volume_down.png";
                VolDown.setIcon(new ImageIcon(image2));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                volumeDownControl(0.1);
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
                System.out.println("exited");
                String image2 = currentPath+imagePath+"\\volume_up.png";
                VolUp.setIcon(new ImageIcon(image2));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                volumeUpControl(0.1);
            }
        });
        VolFull.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                volumeControl(1.0);
            }
        });
        Mute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                volumeControl(0);
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

    public static void wait(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


}
