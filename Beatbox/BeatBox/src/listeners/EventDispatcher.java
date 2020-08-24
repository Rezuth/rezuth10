package listeners;


import main.BeatBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Main event dispatcher. Used on most of the buttons in the GUI. Contains 
 * action commands and the corresponding actions.
 * 
 */
public class EventDispatcher implements ActionListener {
    
    public static final String ACTION_OPEN_FILE = "open";
    public static final String ACTION_SAVE_FILE = "save";
    public static final String ACTION_TEMPO_UP = "tempoup";
    public static final String ACTION_TEMPO_DOWN = "tempodown";
    public static final String ACTION_HELP = "help";
    public static final String ACTION_START_PLAYING = "start";
    public static final String ACTION_STOP_PLAYING = "stop";
    public static final String ACTION_RESET = "reset";
    public static final String ACTION_SELECT_ALL = "selectall";
    public static final String ACTION_SETARE_USERNAME = "setusername";
    public static final String ACTION_SEND_MESSAGE = "send";
    public static final String ACTION_SELECT_USERS = "users";

    private BeatBox beatbox;
    
    public EventDispatcher(BeatBox beatbox) {
        this.beatbox = beatbox;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case ACTION_OPEN_FILE:
                beatbox.loadFileFromDisk();
                break;
            case ACTION_SAVE_FILE:
                beatbox.saveFileToDisk();
                break;
            case ACTION_TEMPO_UP:
                beatbox.tempoFactorUp();
                break;
            case ACTION_TEMPO_DOWN:
                beatbox.tempoFactorDown();
                break;
            case ACTION_HELP:
                beatbox.openHelpMenu();
                break;
            case ACTION_START_PLAYING:
                beatbox.buildTrackAndStart();
                break;
            case ACTION_STOP_PLAYING:
                beatbox.stopPlaying();
                break;
            case ACTION_RESET: 
                beatbox.resetBeatBox();
                break;
            case ACTION_SELECT_ALL:
                beatbox.selectAllCheckBoxes();
                break;
            case ACTION_SETARE_USERNAME:
                beatbox.getUsernameAndCloseDialog();
                break;
            case ACTION_SEND_MESSAGE:
                beatbox.sendUserMessage();
                break;
            case ACTION_SELECT_USERS:
                beatbox.openConnectMenu();
                break;
            default: 
                break;
        }
    }
    
}
