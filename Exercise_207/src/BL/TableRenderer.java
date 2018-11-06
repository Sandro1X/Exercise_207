package BL;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableRenderer implements TableCellRenderer{
    private boolean level;

    public void setLevel(boolean level) {
        this.level = level;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        Station s = (Station) value;
        label.setOpaque(true);
        
        if(!level){
            switch(column){
            case 0: label.setText(s.getPlace());break;
            case 1: label.setText(s.getLevel()+"m");break;
            case 2: label.setText(s.getTemp()+"°");break;
            case 3: label.setText(s.getHum()+"%");break;
            }
        }else{
            switch(column){
            case 0: label.setText(s.getPlace());break;
            case 1: label.setText(s.getTemp()+"°");break;
            case 2: label.setText(s.getHum()+"%");break;
            }
        }
        if(s.getTemp()>25&&s.getHum()<20){
            label.setBackground(Color.yellow);
        }else if(s.getTemp()<0&&s.getHum()<30){
            label.setBackground(Color.blue);
        }else if((s.getTemp()>0||s.getTemp()<25)&&s.getHum()>50){
            label.setBackground(Color.green);
        }else{
            label.setBackground(Color.white);
        }
        
        return label;
    }
}
