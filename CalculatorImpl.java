package tp2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    
    // Constructor must throw RemoteException
    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    @Override
    public double modulo(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("Modulo by zero");
        }
        return a % b;
    }

    @Override
    public double mod(double a, double b) throws RemoteException {
        return modulo(a, b);
    }

    @Override
    public double puissance(double a, double b) throws RemoteException {
        return Math.pow(a, b);
    }

    @Override
    public double power(double a, double b) throws RemoteException {
        return puissance(a, b);
    }

    @Override
    public double racineCarree(double a) throws RemoteException {
        if (a < 0) {
            throw new ArithmeticException("Square root of a negative number is undefined for real numbers");
        }
        return Math.sqrt(a);
    }

    @Override
    public double sqrt(double a) throws RemoteException {
        return racineCarree(a);
    }

    @Override
    public double squareRoot(double a) throws RemoteException {
        return racineCarree(a);
    }
}
