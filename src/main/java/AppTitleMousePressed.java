import lombok.AllArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class AppTitleMousePressed extends MouseAdapter {

    private int xMouse, yMouse;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        xMouse = e.getX();
        yMouse = e.getY();
    }
}
