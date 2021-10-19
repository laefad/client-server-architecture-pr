package Pr3.Client.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


import Pr3.Client.Client;


public class TransmiteService implements Runnable {

    private BufferedWriter out;
    private BufferedReader in;
    private Client client;

    public TransmiteService(Client client) throws IOException{
        this.client = client;
        this.out = new BufferedWriter(new OutputStreamWriter(client.getSocket().getOutputStream()));
        this.in = client.getInput();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                var str = in.readLine();
                out.write(client.getName() + ": " + str + "\n");
                out.flush();
            } catch (IOException e) {
                System.out.println("TransmiteService stopped, where client = " + client.getName());
                break;
            }
        }
    }
    
}
