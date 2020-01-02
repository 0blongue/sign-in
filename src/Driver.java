import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;
import javax.sound.sampled.*;
import java.net.*;
import java.time.LocalDateTime;

public class Driver extends JFrame {
    Form f;
    JButton enqueue, remove, edit, addprov, remprov;
    JPanel top, controls, langs, center, prov, provcontrols, tablepanel;
    JRadioButton eng, esp;
    ButtonGroup lang;
    static Queue q, provlist;
    JScrollPane scroll, scroll2, scroll3;
    EditPane editor; 
    URL url;
    AudioInputStream ais;
    Clip clip;
    static String name = LocalDateTime.now().getMonth().getValue() + "_" + LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getYear() + ".txt";
    JTabbedPane tabs;
    String[] fields;
    static QTable table;
    public Driver() {
        super("Sign In Kiosk");

        fields = new String[]{"General Adult/Medico General", "Child/Pediatra", "Women's Care/Cuidado De la Mujer", "Dental/Dental", "Eye/Occulista", "Skin/La Piel", "Pregnancy/Embarazada", "Mental Health/Salud Mental"};
        setLayout(new BorderLayout());
        top = new JPanel(new GridLayout(4,1));
        controls = new JPanel();
        provcontrols = new JPanel();
        langs = new JPanel();
        center = new JPanel(new BorderLayout());
        prov = new JPanel(new BorderLayout());
        tablepanel = new JPanel();
        tabs = new JTabbedPane();

        try {
            url = this.getClass().getResource("notification.wav");
            ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch(Exception e) {
            e.printStackTrace();
        }

        f = new Form(false);
        q = new Queue();
        provlist = new Queue();
        editor = new EditPane(q);
        table = new QTable(provlist.getData());
        scroll = new JScrollPane(q);
        scroll2 = new JScrollPane(provlist);
        scroll3 = new JScrollPane(table);

        lang = new ButtonGroup();
        eng = new JRadioButton("English", true);
        esp = new JRadioButton("Español", false);
        enqueue = new JButton("Sign In/Registrarse");
        enqueue.setFont(new Font("Calibri", Font.PLAIN, 20));
        remove = new JButton("Remove");
        remove.setFont(new Font("Calibri", Font.PLAIN, 20));
        edit = new JButton("Edit Patient");
        edit.setFont(new Font("Calibri", Font.PLAIN, 20));
        addprov = new JButton("Add Provider");
        addprov.setFont(new Font("Calibri", Font.PLAIN, 20));
        remprov = new JButton("Remove");
        remprov.setFont(new Font("Calibri", Font.PLAIN, 20));

        enqueue.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Patient temp = showSignWizard();
                    if(temp!=null){
                        q.addNew(new Patient(temp));
                        table.addRow(new Patient(temp));
                        clip.loop(1);
                    }
                    clip.loop(1);
                }
            });
        enqueue.setFont(new Font("Calibri", Font.BOLD, 30));
        // enqueue.setBackground(Color.green);
        remove.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    q.remove();
                }
            });

        edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q.getPatient() != null) {
                        Patient selected = new Patient((Patient) q.getPatient());
                        editor.changeInfo(selected);
                        editor.setVisible(true);
                    }
                }
            });

        eng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.setLanguage(eng.isSelected());
                    enqueue.setText("Submit");
                    remove.setText("Remove");
                }
            });

        esp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.setLanguage(eng.isSelected());
                    enqueue.setText("Registrarse");
                    remove.setText("Retirar");
                }
            });

        addprov.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String provname, provfield;
                    provname = CustOptionPane.showInputDialog("What is the name of the Provider?", new String[]{"Next ==>"});
                    provfield = CustOptionPane.showChoiceDialog("What is the Provider's Field?", new String[]{"Next ==>"}, fields);
                    provlist.addNew(new Provider(provname,provfield));
                    table.updateProviders(provlist.getData());
                }
            });

        remprov.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    provlist.remove();
                }
            });
        lang.add(eng);
        lang.add(esp);
        langs.add(eng);
        langs.add(esp);

        controls.add(remove);
        controls.add(edit);

        center.add(scroll, BorderLayout.CENTER);
        center.add(controls, BorderLayout.NORTH);

        provcontrols.add(addprov);
        //provcontrols.add(remprov);

        prov.add(provcontrols, BorderLayout.NORTH);
        prov.add(scroll2, BorderLayout.CENTER);

        tabs.addTab("Providers", prov);
        tabs.addTab("Sign In/Registrarse", enqueue);
        //tabs.addTab("Waiting List", center);
        tabs.addTab("Waiting List", scroll3);

        add(tabs);
    }

    public Patient showSignWizard(){
        String [][][] options = {{{"Next ==>","Cancel"},{"New Patient", "Follow Up"}, {"Yes", "No"}, {"English", "Español"}, {"Return to Main Screen", null}},
                {{"Continuar ==>", "Cancelar"},{"Paciente nuevo", "Seguimiento"},{"Sí", "No"}, {"English", "Español"}, {"Volver a la Pantalla Principal", null}}};
        String [][] labels = {
                {"What is your first name?", "What is your last name", "Are you a New Patient or a Follow Up?", "Do you have insurance?", "Have you had an appointment at the clinic in " + LocalDateTime.now().getYear() + "?", "Would you like to collect groceries from the food pantry?", "Sign In Successful", "Which provider would you like to see?", "Thank you for signing in. Please have a seat in the waiting room."},
                {"¿Cuál es tu primer nombre?", "¿Cuál es tu apellido?", "¿Eres un paciente nuevo o un seguimiento?", "¿Tienes seguro?", "¿Ha tenido una cita en la clínica en  " + LocalDateTime.now().getYear() + "?", "¿Te gustaría recoger comestibles de la despensa de alimentos?", "Iniciar sesión exitoso", "¿Qué proveedor te gustaría ver?", "Gracias por registrarse. Siéntese en la sala de espera."}
            };
        String y = CustOptionPane.showChoiceDialog("English/Español",new String[]{"Next/Continuar ==>"}, options[0][3]);
        Patient temp = null;
        if(y!=null){
            int x = (y == "English") ? 0:1;
            String identifier;
            String[] strings = new String[7];
            strings[0] = (CustOptionPane.showInputDialog(labels[x][0], options[x][0]));
            if(strings[0]==null)
                return null;
            strings[1] = (CustOptionPane.showInputDialog(labels[x][1], options[x][0]));
            if(strings[1]==null)
                return null;
            strings[2] = (CustOptionPane.showChoiceDialog(labels[x][2], options[x][0], options[x][1]));
            if(strings[2]==null)
                return null;
            strings[3] = (CustOptionPane.showChoiceDialog(labels[x][3], options[x][0], options[x][2]));
            if(strings[3]==null)
                return null;
            strings[4] = (CustOptionPane.showChoiceDialog(labels[x][4], options[x][0], options[x][2]));
            if(strings[4]==null)
                return null;
            strings[5] = (CustOptionPane.showChoiceDialog(labels[x][5], options[x][0], options[x][2]));
            if(strings[5]==null)
                return null;
            if(provlist.getData().length>0){
                strings[6] = (CustOptionPane.showChoiceDialog(labels[x][7], options[x][0], provlist.getData()));
            }
            CustOptionPane.showMessageDialog(labels[x][8], options[x][4]);
            // CustOptionPane.showTimedDialog(labels[x][8], options[x][3], 5);

            String insur = "", visit = "Has not Visited in 2018", newP = "F";
            String name = strings[0] + " " + strings[1];
            String food = "Does not want food";
            boolean insured = strings[3].equals(options[0][2][0])||strings[3].equals(options[1][2][0]);
            boolean visited = strings[4].equals(options[0][2][0])||strings[3].equals(options[1][2][0]);
            boolean isnew = strings[2].equals(options[0][1][0])||strings[2].equals(options[1][1][0]);
            boolean wantsfood = strings[5].equals(options[0][2][0])||strings[5].equals(options[1][2][0]);
            if(insured)
                insur = "[HAS INSURANCE]";
            if(visited)
                visit = "Has Visited in 2018";
            if(isnew)
                newP = "N";
            if(wantsfood)
                food = "Wants food";
            identifier = insur + " " + name + " | " + visit + " | " + "Waiting" + " | " + newP + " | " + food + " | " + strings[6];
            temp = new Patient(strings[0], strings[1], insured, visited, isnew, identifier, wantsfood, strings[6]);
        }
        return temp;
    }

    public static void main(String[] args) {
        JFrame frame = new Driver();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    int x = JOptionPane.showConfirmDialog(frame, 
                            "Are you sure you want to exit?\n(Only close this application once all patients are no longer waiting)", "Are you sure?", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (x == JOptionPane.YES_OPTION){
                        createLog();
                        System.exit(0);
                    }
                }
            });
        frame.setSize(1320,768);
        frame.setVisible(true);
    }

    public static void createLog() {

        String path = "C:/Users/" + System.getProperty("user.name") + "/Desktop/SignInLogs/";
        FileWriter fw = null;
        BufferedWriter bw = null;
        int totalnew=0, totalfollowup=0, totalunduplic=0, total=0;
        try {
            File file = new File(System.getProperty("user.dir") + "/Logs/"+ name);
            if(!file.exists())
                file.createNewFile();
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            // for(Object p : q.getData()){
                // String s = p.toString();
                // if(((Patient)p).isNew())
                    // totalnew++;
                // else
                    // totalfollowup++;
                // if(!((Patient)p).getVisited())
                    // totalunduplic++;
                // bw.write(s);
                // bw.newLine();
            // }
			for(Object asdf : table.getData()){
				// System.out.println(asdf.toString());
                String logName = "" + ((Vector)asdf).get(0)+" "+((Vector)asdf).get(1);
                String logNew = (Boolean)((Vector)asdf).get(4) ? "N":"F";
                String logUnduplicated = (Boolean)((Vector)asdf).get(3) ? "Unduplicated":"Duplicated";
                String logInsured = (Boolean)((Vector)asdf).get(6) ? "Has Insurance":"Does not Have Insurance";
                String logProvider = ((Vector)asdf).get(7).toString();
                String logEntry = logName + " | " + logNew + " | " + logUnduplicated + " | " + logInsured + " | " + logProvider + "\n";

                bw.write(logEntry);
                if((Boolean)(((Vector)asdf).get(4))){
                    total++;
                    totalnew++;
                } else{
                    System.out.println("follow");
                    totalfollowup++;
                    total++;
                }
                if((Boolean)(((Vector)asdf).get(3))){
                    System.out.println("undup");
                    totalunduplic++;
                }
			}
            bw.write("Total Patients: " + total);
            bw.newLine();
            bw.write("Total New Patients: " + totalnew);
            bw.newLine();
            bw.write("Total Follow Ups: " + totalfollowup);
            bw.newLine();
            bw.write("Total Unduplicated Patients: " + totalunduplic);
            bw.newLine();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null)
                    bw.close();
                if(fw !=null)
                    fw.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }
}
