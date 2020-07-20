import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@AllArgsConstructor
public class PlaylistTableKeyListener extends KeyAdapter {

    private DefaultTableModel model;
    private JTable playlistTable;

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_DELETE) {
            int i = playlistTable.getSelectedRow();
            if(i >= 0){
                model.removeRow(i);
            }
            else{
                System.out.println("Delete error!");
            }
        }
    }
}
