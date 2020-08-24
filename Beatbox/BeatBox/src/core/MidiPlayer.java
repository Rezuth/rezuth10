package core;

import java.awt.Color;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JCheckBox;

/**
 *
 */
public class MidiPlayer {
    
    // All instrument names and constants used across the app
    public static final String[] INSTRUMENT_NAMES = {"Bass drum", "Closed Hi-Hat",
        "Open HI-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom",
        "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
        "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    public static final int[] INSTRUMENT_CODES = {35, 42, 46, 38, 49, 39, 50,
        60, 70, 72, 64, 56, 58, 47, 67, 63};
    
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    public MidiPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException | MidiUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    
    private void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        return event;
    }
    
    public void buildTrackAndStart(List<JCheckBox> checkboxList) {
        int[] trackList;
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        for (int i = 0; i < 16; i++) {
            trackList = new int[16];
            int key = INSTRUMENT_CODES[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i));
                if (jc.isSelected()) {
                    jc.setBackground(Color.GREEN.darker().darker());
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                    jc.setBackground(null);
                }
            }
            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));
        }
        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setTempoFactor(float factor) {
        sequencer.setTempoFactor(factor);
    }
    
    public float getTempoFactor() {
        return sequencer.getTempoFactor();
    }
    
    public void stop() {
        sequencer.stop();
    }
}
