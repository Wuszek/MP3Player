import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class AppTitleMousePressed extends MouseAdapter {

    private int xMouse, yMouse;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        xMouse = e.getX();
        System.out.println(xMouse + "x z pierwszej metody");
        yMouse = e.getY();
    }

}
