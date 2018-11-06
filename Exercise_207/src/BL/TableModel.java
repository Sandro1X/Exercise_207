package BL;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private ArrayList<Station> stations = new ArrayList<>();
    private String[] colNames = {"Place", "Sea level", "Temperature", "rel. Humidity"};
    private String[] colNames2 = {"Place", "Temperature", "rel. Humidity"};
    private boolean level;

    public void add(Station s) {
        stations.add(s);
        Collections.sort(stations);
        fireTableRowsInserted(stations.size() - 1, stations.size() - 1);
    }

    public void remove(int i) {
        stations.remove(i);
        fireTableDataChanged();
    }

    public void load(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Station s;

        try {
            while ((s = (Station) ois.readObject()) != null) {
                this.add(s);
            }
            ois.close();
        } catch (EOFException eof) {

        }

    }

    public void safe(File f) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

        for (Station s : stations) {
            oos.writeObject(s);
        }

        oos.flush();
        oos.close();
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public boolean isLevel() {
        return level;
    }

    public void setLevel(boolean level) {
        this.level = level;
    }

    @Override
    public int getRowCount() {
        return stations.size();
    }

    @Override
    public int getColumnCount() {
        if (level) {
            return colNames2.length;
        }
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return stations.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (level) {
            return colNames2[column];
        }
        return colNames[column];
    }
}
