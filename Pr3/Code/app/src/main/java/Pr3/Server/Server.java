package Pr3.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;

import Pr3.Server.Services.Printer;
import Pr3.Server.Services.Reader;

public class Server {

    private Thread printer;

    Server(int port) {

        ExecutorService clients = Executors.newFixedThreadPool(20);
        ServerSocket server;

        try {
            server = new ServerSocket(port);
        } catch (IOException e1) {
            throw new Error("Can't up server", e1);
        }

        Printer printer = new Printer();
        this.printer = new Thread(printer);
        this.printer.start();

        try {
            while (true) {
                Socket socket = server.accept();
                var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                var sysOut = new BufferedWriter(new OutputStreamWriter(System.out));
                clients.execute(new Reader(in, sysOut));
                printer.addConsumer(out);
            }
        } catch (IOException ignore) {} 
        finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new Error("Can't down server", e);
            }
            this.printer.interrupt();
            clients.shutdown();
        }
    }

    public static void main(String[] args) {
        new Server(8080);
    }

}
