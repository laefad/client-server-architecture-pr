package Pr2;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static final String name = "serverPr2";
    public static final int port = 8000;

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        final Equation server = new EquationImpl();

        final Registry registry = LocateRegistry.createRegistry(Server.port);

        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(Server.name, stub);

        Thread.sleep(Integer.MAX_VALUE);

    }

}
