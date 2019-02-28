import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class CustomRenderer extends JLabel implements ListCellRenderer {

    public CustomRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
        
        if(arg1 instanceof Patient){
            setFont(new Font("Calibri", Font.PLAIN, 30));
            if(((Patient)arg1).getStatus().equals("Waiting")) {
                setBackground(Color.yellow);
                setForeground(Color.black);
            }
            if(((Patient)arg1).getStatus().equals("Seen")) {
                setBackground(Color.green);
                setForeground(Color.black);
            }
            if(((Patient)arg1).getStatus().equals("Rescheduled")) {
                setBackground(Color.blue);
                setForeground(Color.white);
            }
            if(((Patient)arg1).getStatus().equals("Left Early")) {
                setBackground(Color.red);
                setForeground(Color.black);
            }
            if(((Patient)arg1).getStatus().equals("Ready To Be Seen")) {
                setBackground(Color.orange);
                setForeground(Color.black);
            }
            if(((Patient)arg1).getInsured()) {
                setForeground(Color.red);
            }
            if(arg3) {
                setBackground(Color.gray);
                setForeground(Color.white);
            }
        } else{
            if(arg1.equals("Waiting")) {
                setBackground(Color.yellow);
                setForeground(Color.black);
            }
            if(arg1.equals("Seen")) {
                setBackground(Color.green);
                setForeground(Color.black);
            }
            if(arg1.equals("In Room")) {
                setBackground(Color.cyan);
                setForeground(Color.black);
            }
            if(arg1.equals("Rescheduled")) {
                setBackground(Color.blue);
                setForeground(Color.white);
            }
            if(arg1.equals("Left Early")) {
                setBackground(Color.red);
                setForeground(Color.black);
            }
            if(arg1.equals("Ready To Be Seen")) {
                setBackground(Color.orange);
                setForeground(Color.black);
            }
            
        }

        setText((arg2+1) + ". " +arg1.toString());
        //setText(((Patient)arg1).getName());
        return this;
    }

}