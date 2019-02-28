import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class EditPane extends JFrame {
    private Form f;
    private JButton update;
    private Queue q;

    public EditPane(Queue temp) {
        super("Patient Data Editor");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    if (JOptionPane.showConfirmDialog(null, 
                        "Changes will not be saved unless you use the update button. \nDo you want to exit and discard changes?", "Are you sure?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        setVisible(false);
                    }
                }
            });
        setSize(1320,768);
        setLayout(new GridLayout(3,4));

        f = new Form(true);
        q = temp;

        update = new JButton("Update Patient Info and Return to Main Screen");
        update.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ((Patient) q.getSelectedValue()).editData(new Patient(f.getPatient()));
                    q.updateUI();
                    q.clearSelection();
                    setVisible(false);
                }
            });

        add(f);
        add(update);
    }

    
    
    public void changeInfo(Patient temp) {
        f.setFirstText(temp.getFirstName());
        f.setLastText(temp.getLastName());
        f.setNew(temp.isNew());
        f.setInsured(temp.getInsured());
        f.setVisited(temp.getVisited());
        f.setStatus(temp.getStatus());
        f.setFood(temp.getFood());
        f.setProvider(temp.getProvider());
    }
}