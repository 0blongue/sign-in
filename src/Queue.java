import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;
public class Queue extends JList {

    private DefaultListModel model;
    private DefaultListSelectionModel smodel;
    private MouseListener m;
    public Queue() {
        model = new DefaultListModel();
        smodel = new DefaultListSelectionModel();
        setModel(model);        
        setSelectionModel(new ToggleSelectionModel());

        CustomRenderer cr = new CustomRenderer();
        cr.setPreferredSize(new Dimension(50, 25));
        setCellRenderer(cr);
    }

    public void addNew(Object asdf) {
        model.addElement(asdf);
    }
    
    public void remove() {
        if(getSelectedIndex() != -1)
            model.removeElementAt(getSelectedIndex());
    }

    public Object getPatient() {
        return getSelectedValue();
    }

    public Object[] getData() {
        return model.toArray();
    }
}
