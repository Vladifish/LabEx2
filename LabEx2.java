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

        for (int i = n - 1; i >= 0; i--) {
            if (!isOperator(tokenizedPrefix[i]))
                mainStack.push(tokenizedPrefix[i]);
            else {
                String A = mainStack.pop();
                String B = mainStack.pop();
                String infixExp = String.format("( %s %s %s ) ", A, tokenizedPrefix[i], B);
                mainStack.push(infixExp);
            }
        }
        return mainStack.pop(); // returns the completed infix expression
    }

    static double evalPrefix(String e) {
        String[] tokenizedPrefix = e.split(" ", 0);
        int n = tokenizedPrefix.length;
        // I believe the index starts at 1, might have to change
        Stack<String> mainStack = new Stack<>(n);

        for (int i = n - 1; i >= 0; i--) {
            if (!isOperator(tokenizedPrefix[i]))
                mainStack.push(tokenizedPrefix[i]);
            else {
                String A = mainStack.pop();
                String B = mainStack.pop();
                mainStack.push(evaluate(A, tokenizedPrefix[i], B));
            }
        }
        return Double.parseDouble(mainStack.pop());
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

    // Utility Functions
    static String evaluate(String A, String oprt, String B) {
        double a = Double.parseDouble(A);
        double b = Double.parseDouble(B);

        switch (oprt) {
            case "+":
                return "" + (a + b);
            case "-":
                return "" + (a - b);
            case "*":
                return "" + (a * b);
            case "/":
                return "" + (a / b);
            case "^":
                return "" + (Math.pow(a, b));
            default:
                return "";
        }

    }

    // O(1) solution instead of checking all the characters
    static boolean isOperator(String s) {
        char firstChar = s.charAt(0);
        return firstChar == '-' || firstChar == '+' || firstChar == '/' || firstChar == '^' || firstChar == '*';
    }
}
