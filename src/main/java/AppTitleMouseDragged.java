import lombok.AllArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class AppTitleMouseDragged extends MouseAdapter {

    private int xMouse, yMouse;
    private PlayerFrame playerFrame;

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        System.out.println(x + " " + y);
        playerFrame.setLocation(x - xMouse, y - yMouse);
        playerFrame.repaint();
    }
}
