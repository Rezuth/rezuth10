package main;

import components.MenuBar;
import core.LocalConnection;
import core.MidiPlayer;
import listeners.EventDispatcher;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BeatBox {

    public final static String SENDER_CODE_IDENTIFIER = "$$$";
    public final static String RECEIVER_CODE_IDENTIFIER = "&&&";
    private final static String APP_NAME = "BeatBox";
    
    // GUI objects
    private JFrame mainFrame;
    private JTextField userMessage; 
    private JTextField campDialog;
    private JTextField valoareSliderTextField;
    private JDialog fereastraDialog;
    private JDialog helpDialog;
    private JLabel usernameLabel;
    private JPopupMenu popupMenu;
    private JSlider slider;
    private JList<String> incomingList;
    private List<JCheckBox> checkboxList;
    private String userName;
    private String otherUser;
    
    // Core objects used for event dispatching, sound playing and connecting to
    // the local server
    private EventDispatcher dispatcher;
    private MidiPlayer midiPlayer;
    private final LocalConnection connection;
    
    
    // Variables used for calculations
    private Vector<String> incomingUsernames;
    private Vector<String> chatUsers;
    private JList<String> connectedUsersList;
    private Map<String, boolean[]> receivedPatternsMap;    
    private Float sliderValue;    
    private List<Float> valoriSliderPrimite;

    // public constructor
    public BeatBox() {
        this.campDialog = null;
        this.userName = null;
        this.sliderValue = 1.0f;
        
        this.midiPlayer = new MidiPlayer();
        this.connection = new LocalConnection();
        this.dispatcher = new EventDispatcher(this);
        this.receivedPatternsMap = new HashMap<>();
        this.incomingUsernames = new Vector<>();
        this.chatUsers = new Vector<>();
        this.valoriSliderPrimite = new ArrayList<>();
        this.checkboxList = new ArrayList<>();
        this.incomingList = new JList<>(new DefaultListModel<>());
    }
    
    /**
     * In main we initialize the look and feel of the GUI to use the theme of OS
     * 
     * @param args arguments at startup
     */
    public static void main(String[] args) {
        setLookAndFeel();
        new BeatBox().startUp();
    }
    
    //sets the UI of the app to match the theme of the OS
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException 
                | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method creates the GUI and launches 2 new user threads
     */
    private void startUp() {
        SwingUtilities.invokeLater(() -> {
            buildGUI();
        });

        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            /**
             * The first thread's purpose is to try and connect to the server
             * app which runs on localhost, port 4242. If it fails to connect,
             * it will retry every 250 ms.
             */
            executor.execute(() -> {
                boolean successful = false;
                synchronized (connection) {
                    while (!successful) {
                        try {
                            // try connecting to localhost, port 4242
                            connection.init("127.0.0.1", 4242);
                            System.out.println("Conectare reusita.");
                            successful = true;
                        } catch (IOException e) {
                            System.out.println("Se incearca conectarea la server...");
                            putThreadToSleep(250);
                        }
                    }
                }
            });

            /**
             * The second thread's purpose is to wait for incoming messages
             * received from the other users of the app after the connection
             * with the server app is established.
             */
            executor.execute(() -> {
                boolean[] receivedPattern;
                String senderName;
                String[] localChatUsers;
                Object receivedObjects;
                synchronized (connection) {
                    while (true) {
                        try {                
                            while ((receivedObjects = connection.receiveObject()) != null) {
                                try {
                                    localChatUsers = (String[]) receivedObjects;
                                    this.chatUsers.clear();
                                    this.chatUsers.addAll(
                                            Arrays.asList(localChatUsers).stream()
                                                    .filter(nume -> !this.userName.equals(nume))
                                                    .sorted()
                                                    .collect(Collectors.toList()));
                                } catch (Exception ex) {
                                    senderName = (String) receivedObjects;
                                    receivedPattern = (boolean[]) connection.receiveObject();
                                    valoriSliderPrimite.add((Float) connection.receiveObject());
                                    receivedPatternsMap.put(senderName, receivedPattern);
                                    incomingUsernames.add(senderName);
                                    DefaultListModel<String> listModel = 
                                            (DefaultListModel<String>) incomingList.getModel();
                                    listModel.removeAllElements();
                                    incomingUsernames.forEach(e -> listModel.addElement(e));
                                }
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        putThreadToSleep(1000);
                    }
                }
            });
            
            executor.shutdown();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    // simple method which puts the current running thread to sleep for x amount ms
    private void putThreadToSleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
        }
    }

    // creates the main JFrame, adds a menuBar to it and sets visual details
    private void buildGUI() {
        initPopupMenuSelectAll();        
        
        mainFrame = new JFrame(APP_NAME);
        mainFrame.setJMenuBar(new MenuBar(dispatcher).getMenuBar());
        mainFrame.getContentPane().add(getMainPanel());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    // creates a popup menu which allows the user to select all checkboxes
    private void initPopupMenuSelectAll() {
        popupMenu = new JPopupMenu();
        
        JMenuItem selectAllMenuItem = new JMenuItem("Select All", 
                new ImageIcon(this.getClass().getResource("all.png")));
        selectAllMenuItem.setActionCommand(EventDispatcher.ACTION_SELECT_ALL);
        selectAllMenuItem.addActionListener(dispatcher);
        
        popupMenu.add(selectAllMenuItem);
    }
    
    /**
     * Creates the main panel which serves as a container for the other panels
     * 
     * @return the main JPanel
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private JPanel getMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        panel.setToolTipText("Right click to select all.");
        panel.addMouseListener(new SelectAllMenu());
        new DropTarget(panel, new DnDListener());

        panel.add(BorderLayout.EAST, getPanelPrincipalDreapta());
        panel.add(BorderLayout.WEST, getPanelInstrumentNames());
        panel.add(BorderLayout.CENTER, getCheckBoxPanel());
        
        return panel;
    }
    
    // creates the panel in the center which contains all the checkboxes
    private JPanel getCheckBoxPanel() {
        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(5);
        grid.setHgap(5);
        JPanel checkBoxPanel = new JPanel(grid);
        checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            checkBoxPanel.add(c);
        }
        
        return checkBoxPanel;
    }

    // creates the panel with instrument names to the coresponding rows
    private JPanel getPanelInstrumentNames() {
        Font font = new Font("sanserif", Font.BOLD, 16);
        
        GridLayout layout = new GridLayout(16, 1);
        layout.setVgap(4);
        
        JPanel panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JLabel labelInstName;
        for (int i = 0; i < 16; i++) {
            labelInstName = new JLabel(MidiPlayer.INSTRUMENT_NAMES[i]);
            labelInstName.setFont(font);
            labelInstName.setToolTipText("Click to (de)select row.");
            labelInstName.addMouseListener(new CheckRowListener());
            panel.add(labelInstName);
        }
        return panel;
    }
    
    /**
     * Creates the panel in the right part of the GUI which serves as a container
     * for other panels
     * 
     * @return right container panel
     */
    private JPanel getPanelPrincipalDreapta() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel mainPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(getPanelUsername(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(getPanelButoaneMedia(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;        
        mainPanel.add(getPanelTextToSend(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 240;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(getPanelIncomingMessages(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipady = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(getPanelSlider(), gbc);
        
        return mainPanel;
    }
    
    // creates the panel of the play, stop, reset buttons
    private JPanel getPanelButoaneMedia() {
        JPanel panel = new JPanel();
        
        JButton startButton = new JButton("Play");
        startButton.setActionCommand(EventDispatcher.ACTION_START_PLAYING);
        startButton.addActionListener(dispatcher);
        startButton.setPreferredSize(new Dimension(202, 25));
        startButton.setForeground(Color.GREEN.darker());
        panel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand(EventDispatcher.ACTION_STOP_PLAYING);
        stopButton.addActionListener(dispatcher);
        stopButton.setForeground(Color.RED.darker());
        panel.add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setActionCommand(EventDispatcher.ACTION_RESET);
        resetButton.addActionListener(dispatcher);
        panel.add(resetButton);
        
        return panel;
    }
    
    // creates the panel containg user text and send button
    private JPanel getPanelTextToSend() {
        JPanel panel = new JPanel();
        
        userMessage = new JTextField(24);
        userMessage.setForeground(Color.BLUE.darker());
        userMessage.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke("ENTER"), "Go Enter");
        userMessage.getActionMap().put("Go Enter", sendMyMessage);
        panel.add(userMessage);

        JButton sendIt = new JButton("Send");
        sendIt.setActionCommand(EventDispatcher.ACTION_SEND_MESSAGE);
        sendIt.addActionListener(dispatcher);
        panel.add(sendIt);
        
        return panel;
    }
    
    // creates the panel of the tempo factor slider
    private JPanel getPanelSlider() {
        JPanel panel = new JPanel();
        JLabel labelSlider = new JLabel("Tempo Factor:");
        panel.add(labelSlider);
        slider = new JSlider(SwingConstants.HORIZONTAL, 1, 200, 100);
        slider.addChangeListener(new SliderChangeListener());
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(25);
        panel.add(slider);
        valoareSliderTextField = new JTextField("1.0", 3);
        valoareSliderTextField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "Set Tempo");
        valoareSliderTextField.getActionMap().put("Set Tempo", setTempo);
        panel.add(valoareSliderTextField);
        
        return panel;
    }
    
    // creates the panel with the username of the app
    private JPanel getPanelUsername() {
        JPanel panel = new JPanel();
        usernameLabel = new JLabel("Your USERNAME is not set yet.");
        usernameLabel.setForeground(new Color(182, 68, 68));
        usernameLabel.setFont(new Font(null, Font.ITALIC, 20));
        usernameLabel.setToolTipText("Double-click to set");
        usernameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                if (ev.getClickCount() == 2) {
                    launchSetUsernameDialog();
                }
            }
        });
        panel.add(usernameLabel);
        
        return panel;
    }
    
    // creates the panel containing incoming messages
    private JPanel getPanelIncomingMessages() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        incomingList.addListSelectionListener(new IncomingListListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel<String> listModel = 
                (DefaultListModel<String>) incomingList.getModel();
        listModel.removeAllElements();
        incomingUsernames.forEach(e -> listModel.addElement(e));
        JScrollPane theList = new JScrollPane(incomingList);
        theList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(theList, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Creates a dialog box prompting user to set a username in order to be able
     * to send messages to other users of the app
     */
    private void launchSetUsernameDialog() {
        fereastraDialog = new JDialog(mainFrame, true);
        fereastraDialog.setTitle("Setare Username");
        fereastraDialog.setSize(350, 100);
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(new Label("Username"));
        
        JPanel panel = new JPanel();
        campDialog = new JTextField(18);
        campDialog.setText("");
        campDialog.addKeyListener(new KeyListenerGetUsername());
        campDialog.requestFocus();
        
        JButton butonDialog = new JButton("OK");
        butonDialog.setActionCommand(EventDispatcher.ACTION_SETARE_USERNAME);
        butonDialog.addActionListener(dispatcher);
        
        panel.add(campDialog);
        panel.add(butonDialog);
        
        box.add(panel);
        
        fereastraDialog.setContentPane(box);
        fereastraDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fereastraDialog.setLocationRelativeTo(mainFrame);
        fereastraDialog.setResizable(false);
        fereastraDialog.setVisible(true);
    }
    
    // de/increases the tempo factor of the player by a specified amount
    private void modifyTempoFactorBy(float amount) {
        Float newTempoFactor = midiPlayer.getTempoFactor() + amount;
        midiPlayer.setTempoFactor(newTempoFactor);
        String s = String.format(Locale.ROOT, "%.2f", newTempoFactor);
        valoareSliderTextField.setText(s);
        newTempoFactor *= 100f;
        slider.setValue(newTempoFactor.intValue());
    }
    
    // this method changes the pattern of checkboxes as desired
    private void changeSequence(boolean[] checkboxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox c = (JCheckBox) checkboxList.get(i);
            if (checkboxState[i]) {
                c.setSelected(true);
            } else {
                c.setSelected(false);
            }
        }
    }
    
    /**
     * Stops the midi player from playing sounds
     */
    public void stopPlaying() {
        midiPlayer.stop();
    }
    
    /**
     * Makes the player play faster
     */
    public void tempoFactorUp() {
        modifyTempoFactorBy(0.03f);
    }

    /**
     * Makes the player play slower
     */
    public void tempoFactorDown() {
        modifyTempoFactorBy(-0.03f);
    }

    /**
     * The method will reset the checkboxes pattern
     */
    public void resetBeatBox() {
        stopPlaying();
        checkboxList.forEach((c) -> {
            c.setSelected(false);
        });
    }
    
    /**
     * The method called when the user sends a message to all other users of the app
     */
    public void sendUserMessage() {
        if (userName == null) {
            launchSetUsernameDialog();
        }
        if (otherUser == null) {
            openConnectMenu();
        }
        if (!userMessage.getText().equals("")) {
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox c = (JCheckBox) checkboxList.get(i);
                checkboxState[i] = c.isSelected();
            }
            try {
                connection.sendObject(userName + ": " + userMessage.getText());
                connection.sendObject(checkboxState);
                connection.sendObject(sliderValue);
            } catch (IOException ex) {
                System.out.println("Nu se poate trimite!");
            }
            userMessage.setText("");
        }
    }

    /**
     * Called when the user sets his username
     */
    public void getUsernameAndCloseDialog() {
        if (!campDialog.getText().equals("")) {
            userName = campDialog.getText();
            usernameLabel.setText("________" + userName + "__");
            usernameLabel.setForeground(new Color(68, 100, 203));
            mainFrame.setTitle("BeatBox - " + userName);
            fereastraDialog.dispose();
            try {
                connection.sendObject(userName + SENDER_CODE_IDENTIFIER);
            } catch (IOException ex) {
                System.out.println("Nu se poate trimite!");
            }
        }
    }

    /**
     * Method called when the user exports his current pattern and tempo factor to the disk
     */
    public void saveFileToDisk() {
        boolean[] checkboxState = new boolean[256];
        for (int i = 0; i < 256; i++) {
            final JCheckBox check = (JCheckBox) checkboxList.get(i);
            if (check.isSelected()) {
                checkboxState[i] = true;
            }
        }
        JFileChooser saveFile = new JFileChooser(
                BeatBox.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        saveFile.showSaveDialog(mainFrame);

        try (ObjectOutputStream outputStreamSaveFile = new ObjectOutputStream(
                new FileOutputStream(saveFile.getSelectedFile()))) {
            outputStreamSaveFile.writeObject(checkboxState);
            outputStreamSaveFile.writeObject(sliderValue);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Method called when the user loads a previously saved pattern from the disk
     */
    public void loadFileFromDisk() {
        stopPlaying();
        boolean[] checkboxState = null;
        JFileChooser loadFile = new JFileChooser(
                BeatBox.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        loadFile.showOpenDialog(mainFrame);
        try (ObjectInputStream inputStreamLoadFile = new ObjectInputStream(
                new FileInputStream(loadFile.getSelectedFile()))) {
            checkboxState = (boolean[]) inputStreamLoadFile.readObject();
            sliderValue = (Float) inputStreamLoadFile.readObject();
            midiPlayer.setTempoFactor(sliderValue);
            slider.setValue((sliderValue *= 100).intValue());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(BeatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeSequence(checkboxState);
        buildTrackAndStart();
    }

    /**
     * Method called when the user presses play
     */
    public void buildTrackAndStart() {
        midiPlayer.buildTrackAndStart(checkboxList);
    }
    
    /**
     * The method checks all checkboxes in the pattern
     */
    public void selectAllCheckBoxes() {
        for (int i = 0; i < 256; i++) {
            JCheckBox c = (JCheckBox) checkboxList.get(i);
            c.setSelected(true);
        }
    }

    public void openHelpMenu() {
        helpDialog = new JDialog(mainFrame, "Help");
        JPanel panel = new JPanel();
        Font font = new Font("Comis Sans", Font.BOLD + Font.ITALIC, 14);
        StringBuilder readmeText = new StringBuilder();
        try {
            File readme = new File(this.getClass().getResource("readme.txt").toURI());
            Scanner scanner = new Scanner(readme);
            while(scanner.hasNext()) {
                readmeText.append(scanner.nextLine());
                readmeText.append("<br>");
            }
            
        } catch (URISyntaxException | FileNotFoundException ex) {
            Logger.getLogger(BeatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JLabel label = new JLabel("<html>" + readmeText.toString() + "</html>");
        label.setFont(font);
        panel.add(label);
        helpDialog.setContentPane(panel);
        helpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        helpDialog.setResizable(false);
        helpDialog.setLocationRelativeTo(null);
        helpDialog.pack();
        helpDialog.setVisible(true);
    }
    
    public void openConnectMenu() {
        JDialog dialog = new JDialog(mainFrame, "Private message");
        Font font = new Font("", Font.BOLD, 14);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        JLabel label = new JLabel("<html><h3>Select user to chat with: </h3><p/><p/></html>");
        
        connectedUsersList = new JList<>();
        connectedUsersList.addListSelectionListener(new ChatUsersListener());
        connectedUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectedUsersList.setFont(font);
        connectedUsersList.setListData(chatUsers);
        
        JScrollPane theList = new JScrollPane(connectedUsersList);
        theList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(ev -> dialog.dispose());
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(theList, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);
        
        dialog.setContentPane(panel);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    // action used when user presses ENTER to send a message
    private final Action sendMyMessage = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            sendUserMessage();
        }
    };

    // action used then user changes the tempo and presses ENTER
    private final Action setTempo = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Float f = Float.parseFloat(valoareSliderTextField.getText());
                if (f > 0 && f <= 2) {
                    midiPlayer.setTempoFactor(f);
                    f *= 100f;
                    slider.setValue(f.intValue());
                } else {
                    handle();
                }
            } catch (NumberFormatException ex) {
                handle();
            }
        }

        private void handle() {
            JDialog dialog = new JDialog(mainFrame, "Eroare", false);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(new Dimension(250, 100));
            JLabel l = new JLabel("Numarul nu este valid.");
            JPanel p = new JPanel();
            p.add(l);
            dialog.add(p);
            dialog.setLocationRelativeTo(null);
            valoareSliderTextField.selectAll();
            dialog.setVisible(true);
        }
    };
    
    private class ChatUsersListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent ev) {
            if (!ev.getValueIsAdjusting()) {
                final String aux = (String) connectedUsersList.getSelectedValue();
                
                if (otherUser != null && !otherUser.equals(aux)) {
                    DefaultListModel<String> model
                            = (DefaultListModel<String>) incomingList.getModel();
                    model.removeAllElements();
                    incomingUsernames.removeAllElements();
                }
                
                otherUser = aux;
                try {
                    connection.sendObject(otherUser + RECEIVER_CODE_IDENTIFIER);
                } catch (IOException ex) {
                    Logger.getLogger(BeatBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Listener for when the user loads one of the patterns sent by other users
     */
    private class IncomingListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent ev) {
            if (!ev.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    stopPlaying();
                    Float x = valoriSliderPrimite.get(incomingList.getSelectedIndex());
                    midiPlayer.setTempoFactor(x);
                    slider.setValue((x *= 100).intValue());
                    boolean[] selectedPattern = (boolean[]) receivedPatternsMap.get(selected);
                    changeSequence(selectedPattern);
                    buildTrackAndStart();
                }
            }
        }
    }

    // shows the popup menu at the click coordinates
    private class SelectAllMenu extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            checkForTriggerEvent(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            checkForTriggerEvent(e);
        }

        void checkForTriggerEvent(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Un/checks a whole row when its correspondent instrument name is clicked
    private class CheckRowListener extends MouseAdapter {

        boolean isAtLeastOneUnchecked = false;

        public void select(int first, int last) {
            JCheckBox checkBox;
            for (int i = first; i < last; i++) {
                checkBox = (JCheckBox) checkboxList.get(i);
                if (!checkBox.isSelected()) {
                    isAtLeastOneUnchecked = true;
                    break;
                }
            }

            if (isAtLeastOneUnchecked) {
                for (int i = first; i < last; i++) {
                    checkBox = (JCheckBox) checkboxList.get(i);
                    checkBox.setSelected(true);
                }
            } else {
                for (int i = first; i < last; i++) {
                    checkBox = (JCheckBox) checkboxList.get(i);
                    checkBox.setSelected(false);
                }
            }
            isAtLeastOneUnchecked = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JLabel l = (JLabel) e.getSource();
            String numeLabel = l.getText();

            for (int i = 0; i < MidiPlayer.INSTRUMENT_NAMES.length; i++) {
                if (numeLabel.equals(MidiPlayer.INSTRUMENT_NAMES[i])) {
                    select(16 * i, 16 * i + 16);
                }
            }
        }
    }
    
    // Listener used in the username setting dialog
    private class KeyListenerGetUsername extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                getUsernameAndCloseDialog();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                fereastraDialog.dispose();
            }
        }
    }
    
    // Listens then the tempo slider changes and sets the right tempo factor 
    private class SliderChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            sliderValue = (slider.getValue() / 100f);
            midiPlayer.setTempoFactor(sliderValue);
            String s = sliderValue.toString();
            valoareSliderTextField.setText(s);
        }
    }

    // Listens for incoming drag-n-drop events and loads the respective pattern
    private class DnDListener extends DropTargetAdapter {

        @Override
        public void drop(DropTargetDropEvent dtde) {
            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            Transferable t = dtde.getTransferable();
            DataFlavor[] flavors = t.getTransferDataFlavors();
            if (flavors[0].isFlavorJavaFileListType()) {
                try {
                    List<File> listaTransferata = (List<File>) t.getTransferData(flavors[0]);
                    ObjectInputStream o = new ObjectInputStream(new FileInputStream(listaTransferata.get(0)));
                    boolean[] b = (boolean[]) o.readObject();
                    sliderValue = (Float) o.readObject();
                    o.close();
                    midiPlayer.setTempoFactor(sliderValue);
                    slider.setValue((sliderValue *= 100).intValue());
                    changeSequence(b);
                    buildTrackAndStart();
                } catch (ClassNotFoundException | UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
