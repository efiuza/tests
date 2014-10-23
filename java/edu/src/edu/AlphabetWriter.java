package edu;

import java.lang.Object;
import java.lang.String;
import java.lang.System;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public final class AlphabetWriter extends Object {

    public static final int ALPHABET_LENGTH = 26;

    private final byte[] alphabet;

    // constructor
    public AlphabetWriter() {
        super();
        this.alphabet = new byte[ALPHABET_LENGTH];
        this.populate();
    }

    // private methods
    private void populate() {
        for (int i = 0; i < ALPHABET_LENGTH; ++i) {
            this.alphabet[i] = (byte)(i + 0x41);
        }
    }

    // public methods
    public void write(String path, boolean append) throws IOException {
        FileOutputStream stream = new FileOutputStream(path, append);
        stream.write(this.alphabet);
        stream.close();
    }

    public static void main(String[] args) {
        AlphabetWriter writer;
        if (args.length < 1) {
            System.out.println("Oops... I was expecting a file name.");
            return;
        }
        try {
            writer = new AlphabetWriter();
            writer.write(
                args[0],
                args.length > 1 && args[1].equals("append")
            );
            System.out.println("Written...");
        } catch (IOException e) {
            String msg = (e instanceof FileNotFoundException)
                ? "Cannot open specified file..."
                : "Unexpected I/O Error...";
            System.out.println(msg);
        }
    }

}