import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class QTable extends JTable{
    private TableColumn first, last, unduplicated, waitStatus, nof, wantsFood, insured, provider;
    private JComboBox waitSelection, providerSelection;
	private int totalnew, totalfollow, totalundup;

    public QTable(Object[] providers){
        super(new DefaultTableModel(new String[]{"First Name", "Last Name", "Wait Status", "Unduplicated", "New", "Wants Food", "Insured", "Provider", "Delete"},0));
        //setDefaultRenderer(String.class,new QTableCellRenderer());
        setRowSelectionAllowed(false);
        setColumnSelectionAllowed(false);
        setFont(new Font("Calibri", Font.PLAIN, 36));
        setRowHeight(30);        
        getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 24));

        first = getColumnModel().getColumn(0);
        last = getColumnModel().getColumn(1);
        waitStatus = getColumnModel().getColumn(2);
        unduplicated = getColumnModel().getColumn(3);
        nof = getColumnModel().getColumn(4);
        wantsFood = getColumnModel().getColumn(5);
        insured = getColumnModel().getColumn(6);
        provider = getColumnModel().getColumn(7);

        first.setCellRenderer(new QTableCellRenderer());
        last.setCellRenderer(new QTableCellRenderer());
        
        waitSelection = new JComboBox();
        waitSelection.addItem("Waiting");
        waitSelection.addItem("Ready To Be Seen");
        waitSelection.addItem("In Room");
        waitSelection.addItem("Seen");
        waitSelection.addItem("Rescheduled");
        waitSelection.addItem("Left Early");
        waitSelection.setRenderer(new CustomRenderer());
        providerSelection = new JComboBox(); 

        for(int i=0;i<providers.length;i++){
            providerSelection.addItem(providers[i]);
        }

        waitStatus.setCellEditor(new DefaultCellEditor(waitSelection));
        provider.setCellEditor(new DefaultCellEditor(providerSelection));

        Action delete = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };
 
    ButtonColumn buttonColumn = new ButtonColumn(this, delete, 8);

        // TableCellRenderer headerRenderer = getTableHeader().getDefaultRenderer();

        // unduplicated.setHeaderRenderer(headerRenderer);
        // nof.setHeaderRenderer(headerRenderer);
        // wantsFood.setHeaderRenderer(headerRenderer);

        // unduplicated.sizeWidthToFit();
        // nof.sizeWidthToFit();
        // wantsFood.sizeWidthToFit();

        // updateColumnWidths();
    }

	public Object[] getData(){
		Object[] data = ((DefaultTableModel)getModel()).getDataVector().toArray();
		for(Object datum : data){
			Object[] d = ((Vector)datum).toArray();
			// for(Object item: d){
			// 	System.out.println(item.toString());
			// }
		}
		
		return data;
	}
	
	public int[] getNewCount(){
		return new int[]{0};
	}
	
	public int[] getUnduplicatedCount(){
		return new int[]{0};
	}
	
    public void updateColumnWidths(){
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        TableCellRenderer headerRenderer = getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++){
            column = getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                null, column.getHeaderValue(),
                false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = getDefaultRenderer(getModel().getColumnClass(i)).
            getTableCellRendererComponent(
                this, new Object[]{"First Name", "Last Name", "Wait Status", true, true, true, "Provider"},
                false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }
	
    public void updateProviders(Object[] temp){
        for(int i=providerSelection.getModel().getSize();i<temp.length;i++){
            providerSelection.addItem(temp[i]);
        }
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        if(getValueAt(row, column) instanceof Boolean) {
            return super.getDefaultRenderer(Boolean.class);
        } else {
            return super.getCellRenderer(row, column);
        }
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if(getValueAt(row, column) instanceof Boolean) {
            return super.getDefaultEditor(Boolean.class);
        } else {
            return super.getCellEditor(row, column);
        }
    }
    
    public void editingStopped(){
        getColumnModel().getSelectionModel().clearSelection();
    }
    
    public void editingCanceled(){
        getColumnModel().getSelectionModel().clearSelection();
    }
    
    public void addRow(Patient temp){
        ((DefaultTableModel)getModel()).addRow(new Object[]{temp.getFirstName(),temp.getLastName(),temp.getStatus(),!(temp.getVisited()),temp.isNew(),temp.getFood(), temp.getInsured(), temp.getProvider(), "X"});
    }
}