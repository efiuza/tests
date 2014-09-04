
import java.lang.Object;
import java.lang.String;
import java.lang.System;

import javax.sound.midi.Sequencer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class MusicTestOne extends Object {

    public MusicTestOne() {
        super();
    }

    public void play() {
        try {
            Sequencer sq = MidiSystem.getSequencer();
            System.out.println("We got a sequencer...");
        } catch (MidiUnavailableException e) {
            System.out.println("Ooops...");
        }
    }

    public static void main(String[] args) {
        MusicTestOne mt = new MusicTestOne();
        mt.play();
    }

}

