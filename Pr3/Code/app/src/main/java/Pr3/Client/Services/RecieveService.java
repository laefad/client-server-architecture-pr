package Pr3.Client.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import Pr3.Client.Client;


public class RecieveService implements Runnable {

    private BufferedWriter out;
    private BufferedReader in;
    private Client client;

    public RecieveService(Client client) throws IOException{
        this.client = client;
        this.out = client.getOutput();
        this.in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                var str = in.readLine();
                if (str == null) {
                    System.out.println("RecieveService stopped");
                    client.stopTransmitter();
                    break;
                }
                out.write(str + "\n");
                out.flush();
            } catch (IOException ignore) {}
        }
    }
    
}

