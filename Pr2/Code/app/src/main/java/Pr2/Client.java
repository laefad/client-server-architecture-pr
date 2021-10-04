package Pr2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(Server.port);

        Equation calculator = (Equation) registry.lookup(Server.name);

        Answer answer = calculator.solve(1,2,5);
        System.out.println();
        System.out.println(answer.toString());
    }
}
