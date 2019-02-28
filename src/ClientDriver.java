import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientDriver extends JFrame implements Runnable
{
    Queue q;
    JScrollPane scroll;
    ServerSocket serverSocket;
    Thread t = new Thread(this);
    public ClientDriver()
    {
        q = new Queue();
        scroll = new JScrollPane();
        try{
            serverSocket = new ServerSocket(2712);
            serverSocket.setSoTimeout(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        scroll.add(q);
        add(scroll,BorderLayout.CENTER);
        t.start();
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Waiting for client on port " + 
                    serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                    + "\nGoodbye!");
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        JFrame frame = new ClientDriver();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    int x = JOptionPane.showConfirmDialog(frame, 
                            "Are you sure you want to exit?", "Are you sure?", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (x == JOptionPane.YES_OPTION){
                        //server.close();
                        System.exit(0);
                    }
                }
            });
        frame.setSize(1320,768);
        frame.setVisible(true);
    }
}
