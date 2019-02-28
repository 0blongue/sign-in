import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

public class QTableCellRenderer implements TableCellRenderer
{
    
    public QTableCellRenderer(){
    
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,int column){
        Component cell = null;
        if(value instanceof Boolean){
            cell = new JCheckBox();
            ((JCheckBox)cell).setSelected((boolean)value);
        } else{
            cell = new JLabel(String.valueOf(value));
            cell.setFont(new Font("Calibri", Font.PLAIN, 30));
            ((JLabel)cell).setOpaque(true);
        }

        

        if(table.getModel().getValueAt(row, 2).toString().equals("Waiting")) {
            cell.setBackground(Color.yellow);
            cell.setForeground(Color.black);
        }
        if((table.getModel().getValueAt(row, 2)).toString().equals("Seen")) {
            cell.setBackground(Color.green);
            cell.setForeground(Color.black);
        }
        if((table.getModel().getValueAt(row, 2)).toString().equals("In Room")) {
            cell.setBackground(Color.cyan);
            cell.setForeground(Color.black);
        }
        if((table.getModel().getValueAt(row, 2)).toString().equals("Rescheduled")) {
            cell.setBackground(Color.blue);
            cell.setForeground(Color.white);
        }
        if((table.getModel().getValueAt(row, 2)).toString().equals("Left Early")) {
            cell.setBackground(Color.red);
            cell.setForeground(Color.black);
        }
        if((table.getModel().getValueAt(row, 2)).toString().equals("Ready To Be Seen")) {
            cell.setBackground(Color.orange);
            cell.setForeground(Color.black);
        }

        if((boolean)(table.getModel().getValueAt(row, 6))){
            cell.setBackground(Color.black);
            cell.setForeground(Color.red);
        }
        
        return cell;
    }
}
