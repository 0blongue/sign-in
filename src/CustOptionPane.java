import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.event.*;
import java.util.*;
public class CustOptionPane extends JOptionPane
{
    // public CustOptionPane(){
    // content = new JPanel(new GridLayout(1,2));
    // controls = new JPanel();
    // question = new JPanel();
    // main = new JPanel(new BorderLayout());
    // next = new JButton("Next ==>");
    // cancel = new JButton("Cancel");
    // label = new JLabel("Label");
    // text = new JTextField("");
    // dialog = new JDialog();
    // dialog.setTitle("Message");
    // dialog.setModal(true);

    // next.setBackground(Color.green);
    // next.setFont(new Font("Arial", 1, 30));
    // next.addActionListener(new ActionListener(){
    // public void actionPerformed(ActionEvent e){
    // goNext();
    // }
    // });

    // cancel.setBackground(Color.red);
    // cancel.setFont(new Font("Arial", 1, 30));
    // cancel.addActionListener(new ActionListener(){
    // public void actionPerformed(ActionEvent e){
    // cancel();
    // dialog.dispose();
    // }
    // });

    // controls.add(cancel);
    // controls.add(next);
    // content.add(label);
    // content.add(text);

    // main.add(controls, BorderLayout.SOUTH);
    // main.add(content, BorderLayout.CENTER);
    // dialog.setContentPane(main);
    // dialog.pack();

    // dialog.setVisible(true);
    // }

    public static String showInputDialog(final Object message, final Object[] options)
    throws HeadlessException {

        JButton button = new JButton(String.valueOf(options[0]));
        button.setFont(new Font("Arial", 1, 30));
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel(String.valueOf(message));
        label.setFont(new Font("Arial", 1, 30));
        JTextField text = new JTextField();
        text.setFont(new Font("Arial", 1, 30));
        panel.add(label);
        panel.add(text);
        button.setBackground(Color.green);
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.getRootFrame().dispose();
                }
            });
        JButton[] buttons = {button};
        JOptionPane pane = new JOptionPane(panel, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), buttons, buttons[0]);

        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final String title = UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        dialog.dispose();
        final Object value = text.getText();
        return (((String)value).equals("")) ? showInputDialog(message,options):(String)value;
    }

    public static void showMessageDialog(final Object message, final Object[] options)
    throws HeadlessException {
        JButton button = new JButton(String.valueOf(options[0]));
        button.setFont(new Font("Arial", 1, 30));
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel(String.valueOf(message));
        label.setFont(new Font("Arial", 1, 30));
        panel.add(label);
        button.setBackground(Color.green);
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.getRootFrame().dispose();
                }
            });
        JButton[] buttons = {button};
        JOptionPane pane = new JOptionPane(panel, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), buttons, buttons[0]);

        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final String title = UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        dialog.dispose();
    }

    public static void showTimedDialog(final Object message, final Object[] options, int timeout)
    throws HeadlessException {
        JButton button = new JButton(String.valueOf(options[0]));
        button.setFont(new Font("Arial", 1, 30));
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel(String.valueOf(message));
        label.setFont(new Font("Arial", 1, 30));
        panel.add(label);

        button.setBackground(Color.green);
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.getRootFrame().dispose();
                }
            });
        JButton[] buttons = {button};
        JOptionPane pane = new JOptionPane(panel, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), null/*buttons*/, null/*buttons[0]*/);

        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final String title = UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        javax.swing.Timer t = new javax.swing.Timer(timeout, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.out.println("asdf");
                        dialog.dispose();
                        pane.getRootFrame().dispose();
                    }
                });
        t.start();
        dialog.dispose();
    }

    public static String showConfirmDialog(final Object message, final Object[] options)
    throws HeadlessException {
        final JOptionPane pane = new JOptionPane(message, QUESTION_MESSAGE,
                OK_CANCEL_OPTION, null,
                options, null);
        pane.setWantsInput(false);
        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final String title = UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        dialog.dispose();
        final Object value = pane.getValue();
        return (value == UNINITIALIZED_VALUE) ? null : (String) value;
    }

    public static String showChoiceDialog(final String message, final Object[] options, final Object[] choices)
    throws HeadlessException {
        JPanel panel = new JPanel(new GridLayout(choices.length+1,1));
        JLabel label = new JLabel(String.valueOf(message));
        label.setFont(new Font("Arial", 1, 30));
        ButtonGroup group = new ButtonGroup();
        ArrayList<JRadioButton> rbuttons = new ArrayList<JRadioButton>();
        panel.add(label);
        for(Object c: choices){
            rbuttons.add(new JRadioButton(String.valueOf(c)));
            group.add(rbuttons.get(rbuttons.size()-1));
            rbuttons.get(rbuttons.size()-1).setFont(new Font("Arial", 1, 30));
            panel.add(rbuttons.get(rbuttons.size()-1));
        }
        rbuttons.get(0).setSelected(true);
        JButton button = new JButton(String.valueOf(options[0]));
        button.setFont(new Font("Arial", 1, 30));
        button.setBackground(Color.green);
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.getRootFrame().dispose();
                }
            });
        JButton[] buttons = {button};
        JOptionPane pane = new JOptionPane(panel, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), buttons, buttons[0]);

        pane.setWantsInput(false);
        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final String title = UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        dialog.dispose();
        String value = "";
        for(JRadioButton j:rbuttons)
            if(j.isSelected())
                return j.getText();
        return value;
    }
}
