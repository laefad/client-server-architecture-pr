package Pr2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Equation extends Remote {

    Answer solve(double a, double b, double c) throws RemoteException;
    
}
