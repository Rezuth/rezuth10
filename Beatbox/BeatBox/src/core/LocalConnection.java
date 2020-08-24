package core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class LocalConnection {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public void init(String IP, int port) throws IOException {
        // create a new socket to specified IP and port and use its streams for 
        // sending and receiving data 
        socket = new Socket(IP, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }
    
    public void sendObject(Object object) throws IOException {
        oos.writeObject(object);
    }
    
    public Object receiveObject() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }
    
}
