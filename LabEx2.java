/* Vladimir Gray P. Velazco | Jam Meisy T. Tan
 * ICS 2605
 * 1 - CSC
 * Lab Exercise 2 : Main
 */

import java.util.Scanner;

public class LabEx2 {
    final static Scanner console = new Scanner(System.in);

    // parang baliktad yung code nito from the given pseudocode
    static String prefixToInfix(String e) {
        String[] tokenizedPrefix = e.split(" ", 0);
        int n = tokenizedPrefix.length;
        // I believe the index starts at 1, might have to change
        Stack<String> mainStack = new Stack<>(n);
        Stack<String> operatorStack = new Stack<>();
        String infix = "something"; // will change to something else later
        return infix;
    }

    static double evalPrefix(String e) {
        String[] tokenizedPrefix = e.split(" ", 0);
        int n = tokenizedPrefix.length;
        double evaluatedPfx = 0;
        return evaluatedPfx;
    }

    public static void main(String[] args) {
        // a.
        System.out.println("Tan, Jam Meisy : Velazco, Vladimir Gray");
        // b.
        System.out.println("Input integer t : Range (1-2)");
        int t = console.nextInt();
        console.nextLine(); // eats up the trailing newline
        System.out.println("Input a String (s) consisting of operators and operands delimited by spaces");
        String s = console.nextLine();

        if (t == 1) {
            System.out.println("Prefix: " + s);
            System.out.println("Infix: " + prefixToInfix(s));
            System.out.println("Value: " + evalPrefix(s));
        }

    }
}
