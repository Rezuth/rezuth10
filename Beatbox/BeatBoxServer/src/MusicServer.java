
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MusicServer {

    private Map<Long, ObjectOutputStream> userOutputStreams;
    private Vector<String> users;
    private Long currentUserID;

    /**
     * In the constructor we initialize the current user id to 0 and the output
     * streams Map
     */
    public MusicServer() {
        currentUserID = new Long(0);
        userOutputStreams = new HashMap<>();
        users = new Vector<>();
    }

    public static void main(String[] args) {
        new MusicServer().go();
    }

    // here we create the GUI and start an Executor service
    private void go() {
        SwingUtilities.invokeLater(() -> {
            buildGUI();
        });
        
        // try to get control over port 4242 on localhost and listen for users
        // trying to connect
        ExecutorService es = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            System.out.println("Server started!");

            while (true) {
                Socket userSocket = serverSocket.accept();
                System.out.println("User connected!");
                
                final ObjectOutputStream out = new ObjectOutputStream(userSocket.getOutputStream());
                userOutputStreams.put(currentUserID, out);
                
                es.execute(new UserHandler(userSocket, currentUserID, this));
                currentUserID++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // creates a frame and just displays that the server is running
    private void buildGUI() {
        JFrame frame = new JFrame("Server");
        Label label = new Label("The server is running!");
        JPanel panel = new JPanel();
        
        panel.add(label);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setVisible(true);
    }
    
    public Map<Long, ObjectOutputStream> getUserOutputStreams() {
        return userOutputStreams;
    }

    public Vector<String> getUsers() {
        return users;
    }

    /**
     * 
     * 
     * @param message
     * @param pattern
     * @param tempoFactor 
     * @param sender 
     * @param receiver 
     */
    public void sendMessage(Object message, Object pattern, Object tempoFactor, 
            String sender, String receiver) {

        if (message == null) return;
        
        ObjectOutputStream senderOOS = userOutputStreams.get(
                (long) users.indexOf(sender));
        
        ObjectOutputStream reveiverOOS = userOutputStreams.get(
                (long) users.indexOf(receiver));
        try {
            senderOOS.writeObject(message);
            senderOOS.writeObject(pattern);
            senderOOS.writeObject(tempoFactor);
            reveiverOOS.writeObject(message);
            reveiverOOS.writeObject(pattern);
            reveiverOOS.writeObject(tempoFactor);
        } catch (IOException ex) {
            System.out.println("Objects can't be sent.");
            ex.printStackTrace();
        }
    }
    
    public void sendConnectedUsersList() {
        userOutputStreams.values().forEach((userOOS) -> {
            try {
                userOOS.writeObject(users.toArray(new String[users.size()]));
            } catch (IOException ex) {
                System.out.println("Objects can't be sent.");
                ex.printStackTrace();
            }
        });
    }
}
