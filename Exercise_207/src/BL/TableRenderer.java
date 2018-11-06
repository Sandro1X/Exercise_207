package BL;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        Station s = (Station) value;
        label.setOpaque(true);
        
        switch(column){
            case 0: label.setText(s.getPlace());break;
            case 1: label.setText(s.getLevel()+"m");break;
            case 2: label.setText(s.getTemp()+"°");break;
            case 3: label.setText(s.getHum()+"%");break;
        }
        return label;
    }
}
