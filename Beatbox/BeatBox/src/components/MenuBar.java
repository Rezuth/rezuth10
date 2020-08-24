package components;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import listeners.EventDispatcher;

/**
 *
 */
public class MenuBar {
    
    private EventDispatcher dispatcher;

    public MenuBar(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public JMenuBar getMenuBar() {
        JMenuBar baraMeniu = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openFile = new JMenuItem("Open",
                new ImageIcon(this.getClass().getResource("open.png")));
        openFile.setActionCommand(EventDispatcher.ACTION_OPEN_FILE);
        openFile.addActionListener(dispatcher);

        JMenuItem saveFile = new JMenuItem("Save",
                new ImageIcon(this.getClass().getResource("save.png")));
        saveFile.setActionCommand(EventDispatcher.ACTION_SAVE_FILE);
        saveFile.addActionListener(dispatcher);

        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        JMenu connectMenu = new JMenu("Connect");
        JMenuItem connectItem = new JMenuItem("Private message");
        connectItem.setActionCommand(EventDispatcher.ACTION_SELECT_USERS);
        connectItem.addActionListener(dispatcher);
        
        connectMenu.add(connectItem);
        
        JMenu tempoMenu = new JMenu("Tempo");
        JMenuItem tempoUp = new JMenuItem("Tempo Up", new ImageIcon(
                this.getClass().getResource("up.png")));
        tempoUp.setActionCommand(EventDispatcher.ACTION_TEMPO_UP);
        tempoUp.addActionListener(dispatcher);

        JMenuItem tempoDown = new JMenuItem("Tempo Down", new ImageIcon(
                this.getClass().getResource("down.png")));
        tempoDown.setActionCommand(EventDispatcher.ACTION_TEMPO_DOWN);
        tempoDown.addActionListener(dispatcher);

        tempoMenu.add(tempoUp);
        tempoMenu.add(tempoDown);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("Help", new ImageIcon(
                this.getClass().getResource("help.png")));
        helpMenuItem.setActionCommand(EventDispatcher.ACTION_HELP);
        helpMenuItem.addActionListener(dispatcher);

        helpMenu.add(helpMenuItem);

        baraMeniu.add(fileMenu);
        baraMeniu.add(connectMenu);
        baraMeniu.add(tempoMenu);
        baraMeniu.add(helpMenu);
        
        return baraMeniu;
    }
    
}
