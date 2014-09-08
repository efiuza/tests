import java.lang.Object;
import java.lang.Integer;
import java.lang.String;
import java.lang.Thread;
import java.lang.System;
import java.lang.Exception;
import java.lang.NumberFormatException;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiEvent;

public class MiniMiniMusicApp extends Object {

    public MiniMiniMusicApp() {
        super();
    }

    public static void main(String[] args) {

        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        int instrument, note;

        try {
            instrument = args.length > 0 ? Integer.parseInt(args[0]) : 0;
        } catch (NumberFormatException e) {
            instrument = 0;
            System.out.println("Invalid argument for instrument... Default assumed.");
        }

        try {
            note = args.length > 1 ? Integer.parseInt(args[1]) : 0;
        } catch (NumberFormatException e) {
            note = 0;
            System.out.println("Ivalid argument for note... Default assumend.");
        }

        mini.play(instrument, note);

    }

    public void play(int instrument, int note) {

        try {

            Sequencer sqr;
            Sequence sq;
            Track tr;
            ShortMessage smi, sma, smb;
            MidiEvent chgInst, noteOn, noteOff;

            sqr = MidiSystem.getSequencer();
            sqr.open();
            sq = new Sequence(Sequence.PPQ, 4);
            tr = sq.createTrack();

            // adjust arguments
            // instrument = instrument & 0x7F;
            // note = note & 0x7F;
            // change instrument
            smi = new ShortMessage();
            smi.setMessage(192, 1, instrument, 0);
            // note on
            sma = new ShortMessage();
            sma.setMessage(144, 1, note, 100);
            // note off
            smb = new ShortMessage();
            smb.setMessage(128, 1, note, 100);

            chgInst = new MidiEvent(smi, 1);
            noteOn = new MidiEvent(sma, 1);
            noteOff = new MidiEvent(smb, 32);

            tr.add(chgInst);
            tr.add(noteOn);
            tr.add(noteOff);

            sqr.setSequence(sq);
            sqr.start();

            Thread.sleep(4800);

            sqr.close();
            System.out.println("The End...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
