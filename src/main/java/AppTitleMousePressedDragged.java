import lombok.AllArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class AppTitleMousePressedDragged extends MouseAdapter {

    private int xMouse, yMouse;
    private PlayerFrame playerFrame;



    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        xMouse = e.getX();
        System.out.println(xMouse + "x z pierwszej metody");
        yMouse = e.getY();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

        super.mouseDragged(e);
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        System.out.println(x + " z drugiej metody " + y);
        playerFrame.setLocation(x - xMouse, y - yMouse);
        System.out.println((x-xMouse) + " " + (y-yMouse));
        System.out.println(xMouse + " xMouse z drugiej");
        playerFrame.repaint();
    }
}
