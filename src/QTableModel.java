import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class QTableModel extends DefaultTableModel{
    private ArrayList<Object[]> data;
    private String[] columnNames;

    public QTableModel(String[] columnNames){
        super(columnNames,1);
        this.columnNames = columnNames;
        data = new ArrayList<Object[]>();
    }

    public Object getValueAt(int x, int y){
        return data.get(x)[y];
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return data.size();
    }

    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int row, int col){
        data.get(row)[col] = value;
        fireTableCellUpdated(row, col);
    }
}