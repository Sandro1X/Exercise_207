package BL;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
    private ArrayList<Station> stations = new ArrayList<>();
    private String[] colNames = {"Place","Sea level","Temperature","rel. Humidity"};
    
    public void add(Station s){
        stations.add(s);
        fireTableRowsInserted(stations.size()-1, stations.size()-1);
    }
    
    public void remove(Station s){
        stations.remove(s);
        fireTableRowsDeleted(0, stations.size()-1);
    }
    
    @Override
    public int getRowCount() {
        return stations.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return stations.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
}
