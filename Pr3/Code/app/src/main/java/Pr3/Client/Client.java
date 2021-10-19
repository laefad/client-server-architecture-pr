package Pr3.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;

import Pr3.Client.Services.RecieveService;
import Pr3.Client.Services.TransmiteService;


public class Client {

    private Socket socket;
    private String name;
    private BufferedReader in;
    private BufferedWriter out;
    private Thread reciever;
    private Thread transmitter;

    public static Client makeClient(BufferedReader in, BufferedWriter out) throws IOException, Error {

        String addrDefault = "localhost";
        int portDefault = 8080;

        String name, addr;
        int port;

        while (true) {
            System.out.print("Enter your name: ");
            try {
                name = in.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Bad input");
                continue;
            }
        }

        while (true) {
            System.out.print("Enter address [default localhost]: ");
            try {
                addr = in.readLine();
                if (addr.equals(""))
                    addr = addrDefault;
                break;
            } catch (IOException e) {
                System.out.println("Bad input");
                continue;
            }
        }

        while (true) {
            System.out.print("Enter port [default 8080]: ");
            try {
                var portStr = in.readLine();
                if (portStr.equals(""))
                    port = portDefault;
                else 
                    port = Integer.parseInt(portStr);
                break;
            } catch (IOException e) {
                System.out.println("Bad input");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Input value not a number");
                continue;
            }
        }

        return new Client(name , addr, port, in, out);
    }
    
    public Client(String name, String addr, int port, BufferedReader in, BufferedWriter out) throws Error {
        this.name = name;
        this.in = in;
        this.out = out;
        try {
            this.socket = new Socket(addr, port);
            startServices();
            System.out.println("Client started...");
        } catch (IOException e) {
            throw new Error("Client initialization failed", e);
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getInput() {
        return this.in;
    }

    public BufferedWriter getOutput() {
        return this.out;
    }

    public String getName() {
        return this.name;
    }

    public void startServices() throws IOException {
        this.reciever = new Thread(new RecieveService(this));
        this.transmitter = new Thread(new TransmiteService(this));
        reciever.start();
        transmitter.start();
    }

    public static void main(String[] args) {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            Client.makeClient(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }
    
}
