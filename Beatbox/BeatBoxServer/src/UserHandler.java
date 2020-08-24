import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 *
 * @author Andrei
 */
 public class UserHandler implements Runnable {

        public final static String SENDER_CODE_IDENTIFIER = "$$$";
        public final static String RECEIVER_CODE_IDENTIFIER = "&&&";
     
        private String username;
        private String userITalkToUsername;
        private ObjectInputStream in;
        private Socket userSocket;
        private Long userID;
        private MusicServer server;

        // initialize fields and try to obtain the input stream of this particular user
        public UserHandler(Socket socketUser, Long userID, MusicServer server) {
            this.server = server;
            this.userID = userID;
            this.userSocket = socketUser;
            
            try {
                this.in = new ObjectInputStream(userSocket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /**
         * In this method the program waits until it receives objects from the
         * user and then tries to send the objects to all other users connected to this server
         */
        @Override
        public void run() {
            Object message;
            Object pattern;
            Object tempoFactor;

            try {
                while ((message = in.readObject()) != null) {
                    final String aux = (String) message;
                    if (aux.contains(SENDER_CODE_IDENTIFIER)) {
                        this.username = aux.substring(0, aux.length() - 3);
                        server.getUsers().add(this.username);
                        server.sendConnectedUsersList();
                    } else if (aux.contains(RECEIVER_CODE_IDENTIFIER)) {
                        this.userITalkToUsername = aux.substring(0, aux.length() - 3);
                    } else {
                        pattern = in.readObject();
                        tempoFactor = in.readObject();
                        server.sendMessage(message, pattern, tempoFactor, 
                                this.username, this.userITalkToUsername);
                    }
                }
            } catch (IOException | ClassNotFoundException x) {
                System.out.println("Utilizator deconectat.");
                server.getUserOutputStreams().remove(userID);
                System.out.println("Utilizatorul nr. " + userID + " a fost eliminat din lista.");
                inchideInputStream();
            }
        }

        // The method tries to close the input stream of a disconnected user
        private void inchideInputStream() {
            try {
                in.close();
                server.getUserOutputStreams().remove(userID);
                server.getUsers().remove(username);
                server.sendConnectedUsersList();
                System.out.println("Am reusit sa inchid inputStream-ul " + userID + ".");
            } catch (IOException e) {
                System.out.println("Nu am reusit sa inchid inputStream-ul.");
            }
        }
    }
