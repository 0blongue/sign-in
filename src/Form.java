import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Form extends JPanel {
    private JLabel fn, ln;
    private JTextField first, last;
    private ButtonGroup nof;
    private JRadioButton n, f;
    private JCheckBox insured, visited, wants;
    private JPanel top,top2,top3;
    private String identifier, labels[][], provider;
    private JComboBox wait, providers;
    
    public Form(boolean temp) {
        setLayout(new GridLayout(4,2));
        setFont(new Font("Calibri", Font.PLAIN, 20));
        top = new JPanel();
        top2 = new JPanel();
        top3 = new JPanel();
        nof = new ButtonGroup();
        labels = new String[][] {
            {"First Name", "Last Name", "New Patient", "Follow Up", "Do you have insurance?", "Have you visited the clinic in 2018?"},
            {"Nombre", "Apellidos", "Paciente Nuevo", "Paciente de Seguimiento", "¿Tienes seguro?", "¿Has visitado la clínica en 2018?"}
        };

        identifier = "";
        fn = new JLabel(labels[0][0]);
        fn.setFont(new Font("Calibri", Font.PLAIN, 20));
        ln = new JLabel(labels[0][1]);
        ln.setFont(new Font("Calibri", Font.PLAIN, 20));
        first = new JTextField("", 10);
        first.setFont(new Font("Calibri", Font.PLAIN, 20));
        last = new JTextField("", 10);
        last.setFont(new Font("Calibri", Font.PLAIN, 20));
        n = new JRadioButton(labels[0][2], false);
        n.setFont(new Font("Calibri", Font.PLAIN, 20));
        f = new JRadioButton(labels[0][3], true);
        f.setFont(new Font("Calibri", Font.PLAIN, 20));
        insured = new JCheckBox(labels[0][4]);
        insured.setFont(new Font("Calibri", Font.PLAIN, 20));
        visited = new JCheckBox(labels[0][5]);
        visited.setFont(new Font("Calibri", Font.PLAIN, 20));
        wait = new JComboBox(new String[] 
            {"Waiting", "Seen", "Ready To Be Seen", "Rescheduled", "Left Early"});
        wait.setFont(new Font("Calibri", Font.PLAIN, 20));
        wants = new JCheckBox("Wants Food");
        wants.setFont(new Font("Calibri", Font.PLAIN, 20));
        providers = new JComboBox(new String[] 
            {"Waiting", "Seen", "Ready To Be Seen", "Rescheduled", "Left Early"});
            
        top.add(fn);
        top.add(first);
        top.add(ln);
        top.add(last);

        nof.add(n);
        nof.add(f);

        top2.add(n);
        top2.add(f);
        top2.add(wants);

        top3.add(insured);
        top3.add(visited);
        if(temp)
            top3.add(wait);

        add(top);
        add(top2);
        add(top3);
    }

    public String getFirstName() {
        return first.getText();
    }

    public String getLastName() {
        return last.getText();
    }

    public boolean getVisited() {
        return visited.isSelected();
    }

    public boolean getInsured() {
        return insured.isSelected();
    }

    public boolean isNew() {
        return n.isSelected();
    }
    
    public boolean getFood(){
        return wants.isSelected();
    }
    
    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public String getIdentifier() {
        String insur = "", visit = "Has not Visited in 2018", newP = "F", wantsfood = "Does not want food";

        if(insured.isSelected())
            insur = "[HAS INSURANCE]";
        if(visited.isSelected())
            visit = "Has Visited in 2018";
        if(n.isSelected())
            newP = "N";
        if(wants.isSelected())
            wantsfood = "Wants food";
        identifier = insur + " " + getName() + " | " + visit + " | " + wait.getItemAt(wait.getSelectedIndex()) + " | " + newP + " | " + wantsfood + " | " + provider;
        return identifier;
    }

    public Patient getPatient() {
        Patient temp = new Patient(getFirstName(), getLastName(), getInsured(), getVisited(), isNew(), getIdentifier(), getFood(), provider);
        temp.setStatus((String) wait.getItemAt(wait.getSelectedIndex()));
        return temp;
    }

    public void setFirstText(String temp) {
        first.setText(temp);
    }

    public void setLastText(String temp) {
        last.setText(temp);
    }

    public void setVisited(boolean temp) {
        visited.setSelected(temp);
    }

    public void setInsured(boolean temp) {
        insured.setSelected(temp);
    }

    public void setNew(boolean temp) {
        n.setSelected(temp);
        f.setSelected(!temp);
    }

    public void setStatus(String temp) {
        wait.setSelectedItem(temp);
    }

    public void setFood(boolean temp){
        wants.setSelected(temp);
    }
    
    public void setProvider(String temp){
        provider = temp;
    }
    
    public void setLanguage(boolean x) {
        int i = x ? 0 : 1;
        fn.setText(labels[i][0]);
        ln.setText(labels[i][1]);
        n.setText(labels[i][2]);
        f.setText(labels[i][3]);
        insured.setText(labels[i][4]);
        visited.setText(labels[i][5]);
    }

    public void reset() {
        first.setText("");
        last.setText("");
        insured.setSelected(false);
        visited.setSelected(false);
    }
}
