package tp2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    double modulo(double a, double b) throws RemoteException;
    double mod(double a, double b) throws RemoteException;
    double puissance(double a, double b) throws RemoteException;
    double power(double a, double b) throws RemoteException;
    double racineCarree(double a) throws RemoteException;
    double sqrt(double a) throws RemoteException;
    double squareRoot(double a) throws RemoteException;
}
