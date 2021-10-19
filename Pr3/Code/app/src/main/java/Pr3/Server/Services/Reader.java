package Pr3.Server.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import Pr3.Server.MessageBuffer;

public class Reader implements Runnable {
    
    private BufferedReader in;
    private BufferedWriter out;

    public Reader(BufferedReader in, BufferedWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                var text = in.readLine();
                if (text == null) {
                    System.out.println("Reader stopped");
                    break;
                }
                MessageBuffer.addMessage(text);
                out.write(text + "\n");
                out.flush();
            } catch (IOException e) {

            }
        }
    }


}
