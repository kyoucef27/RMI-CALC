package tp2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create the servant instance
            CalculatorImpl calc = new CalculatorImpl();
            
            // Start RMI registry on default port 1099
            LocateRegistry.createRegistry(1099);
            
            // Bind the servant to a name in the registry
            Naming.rebind("CalculatorService", calc);
            
            System.out.println("Calculator RMI Server is ready.");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}