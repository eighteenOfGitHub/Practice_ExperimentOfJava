
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Server extends Frame implements ActionListener  {
    private Panel p1,p2;
    private Label labelSay=new Label("Say:"),labelServerPort=new Label("Port:"),labelSetting=new Label("serversetting");
    private TextArea ta = new TextArea();
    private TextField tfSay = new TextField(10),tfPort=new TextField("4000",10);
    private Button sayBtn = new Button("Say"),startBtn=new Button("Start");
    private BufferedReader in;
    private PrintWriter out;
    public static ServerSocket serversocket = null;
    public static Socket socket = null;
    public Server() {
        super("Server");
        sayBtn.addActionListener(this);
        add(ta, BorderLayout.CENTER);
        startBtn.addActionListener(this);
        p1 = new Panel();p2=new Panel();
        p1.add(labelSetting);
        p1.add(labelServerPort);
        p1.add(tfPort);
        p1.add(startBtn);
        add(p1,BorderLayout.NORTH);
        p2.add(labelSay);
        p2.add(tfSay);
        p2.add(sayBtn);
        add(p2, BorderLayout.SOUTH);
        setSize(700, 500);
        setVisible(true);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.append("Server starting.....\n");
                    serversocket = new ServerSocket(Integer.parseInt(tfPort.getText()));
                    socket = serversocket.accept();
                    ta.append("Client connected......\n");
                    mythread thread = new mythread();
                    thread.start();
                } catch (IOException a) {
                    System.out.println("错误");
                }
            }
        });
        sayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out = new PrintWriter(socket.getOutputStream());
                    String si = tfSay.getText();
                    out.write(si+"\n");
                    out.flush();
                    ta.append("Server:  "+si+"\n");
                } catch (IOException e1) {
                    System.out.println("错误");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class mythread extends Thread{
        public void run(){
            try{
                while(true){
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = in.readLine();
                    ta.append("Server:  "+line+"\n");
                }
            }catch(Exception e2){
                System.out.println("错误");
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}