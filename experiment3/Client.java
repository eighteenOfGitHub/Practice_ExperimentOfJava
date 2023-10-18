
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends Frame implements ActionListener {
    private Panel p1,p2;
    private Label labelSay=new Label("Say:"),labelServerPort=new Label("Port:"),labelSetting=new Label("clientsetting"),labelServerIP=new Label("IP:");
    private TextArea ta = new TextArea();
    private TextField tfSay = new TextField(10), tfIP=new TextField("127.0.0.1",10), tfPort=new TextField("4000",10);
    private Button sayBtn = new Button("Say"),connectBtn=new Button("Connect");
    private Socket socket=null;
    private ServerSocket serversocket=null;
    private BufferedReader in;
    private PrintWriter out;

    public Client() {
        super("Client");
        p1= new Panel();p2=new Panel();
        p1.add(labelSetting);
        p1.add(labelServerIP);
        p1.add(tfIP);
        p1.add(labelServerPort);
        p1.add(tfPort);
        p1.add(connectBtn);
        this.add(p1,BorderLayout.NORTH);
        add(ta, BorderLayout.CENTER);

        p2.add(labelSay,BorderLayout.SOUTH);
        p2.add(tfSay);
        p2.add(sayBtn);
        this.add(p2,BorderLayout.SOUTH);
        setSize(700, 500);
        setVisible(true);
        connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.append("Connect to server......\n");
                    socket = new Socket(tfIP.getText(), Integer.parseInt(tfPort.getText()));
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
                    ta.append("client:  "+si+"\n");
                } catch (IOException e1) {
                    System.out.println("错误");
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
    }
    class mythread extends Thread{
        public void run(){
            try{
                while(true){
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String si = in.readLine();
                    ta.append("Client:  "+si+"\n");
                }
            }catch(Exception e2){
                System.out.println("错误");
            }
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
