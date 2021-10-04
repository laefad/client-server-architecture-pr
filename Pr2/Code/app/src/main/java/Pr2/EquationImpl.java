package Pr2;

import java.rmi.RemoteException;

public class EquationImpl implements Equation {

    @Override
    public Answer solve(double a, double b, double c) throws RemoteException {

        double d = b * b - 4 * a * c;

        if (d > 0) {
            return new Answer(new Complex( (-b + Math.sqrt(d)) / (2 * a) ),
                              new Complex( (-b - Math.sqrt(d)) / (2 * a) ));
        } else if (d == 0) {
            return new Answer(new Complex(-b / (2 * a)));
        } else {
            return new Answer(new Complex( -b / (2 * a), Math.sqrt(-d) / (2 * a)),
                              new Complex( -b / (2 * a), -Math.sqrt(-d) / (2 * a)));
        }
    }
    
}
