package tp2;

import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Lookup remote object from the RMI registry
            Calculator calc = (Calculator) Naming.lookup("rmi://localhost:1099/CalculatorService");
            
            double a = 12.5, b = 4.5;
            System.out.println("Addition: " + calc.add(a, b));
            System.out.println("Subtraction: " + calc.subtract(a, b));
            System.out.println("Multiplication: " + calc.multiply(a, b));
            System.out.println("Division: " + calc.divide(a, b));
            System.out.println("Modulo (" + a + " % " + b + "): " + calc.modulo(a, b));
            System.out.println("Puissance (" + a + " ^ " + b + "): " + calc.puissance(a, b));
            System.out.println("Racine carrée (sqrt(" + a + ")): " + calc.racineCarree(a));
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}