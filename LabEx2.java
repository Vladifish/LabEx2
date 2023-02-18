/* Vladimir Gray P. Velazco | Jam Meisy T. Tan
 * ICS 2605
 * 1 - CSC
 * Lab Exercise 2 : Main
 */

import java.util.Scanner;

public class LabEx2 {
    final static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        // a.
        System.out.println("""
                1CSC - Tan, Jam Meisy and Velazco, Vladimir Gray
                ------------------------------------------------
                1 - Prefix to Infix Notation + Evaluate
                2 - Infix to Prefix Notation + Evaluate
                ------------------------------------------------
                """);
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
        } else if (t == 2) {
            System.out.println("Infix: " + s);
            String prefixExp = infixToPrefix(s);
            System.out.println("Prefix: " + prefixExp);
            System.out.println("Value: " + evalPrefix(prefixExp));
        }

    }

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

    // apparently returns String for the lab ver.
    static String infixToPrefix(String e) {
        String[] tokenizedInfix = e.split(" ", 0);
        int n = tokenizedInfix.length;
        Stack<String> oprtStack = new Stack<>(n);
        Stack<String> mainStack = new Stack<>(n);

        for (int i = n - 1; i >= 0; i--) {
            String el = tokenizedInfix[i];
            if (!isOperator(el))
                mainStack.push(el);
            else {
                // incoming: 2, in-stack: 1
                // I just realized that is kinda close to the insertion sort, loop
                while (!oprtStack.isEmpty() && precedence(el, 2) < precedence(oprtStack.peek(), 1))
                    mainStack.push(oprtStack.pop());
                oprtStack.push(el);
            }
        }

        while (!oprtStack.isEmpty())
            mainStack.push(oprtStack.pop());

        String prefixString = "";
        while (!mainStack.isEmpty()) {
            prefixString += mainStack.pop() + " ";
        }
        return prefixString.trim(); // removes trailing String cause of while loop

    }

    // Utility Functions

    // this is what's currently causing the issues, cause no parenthesis
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
            case "%":
                return "" + (a % b);
            case "^":
                return "" + (Math.pow(a, b));
            default:
                return "";
        }
    }

    // O(1) solution instead of checking all the characters
    static boolean isOperator(String s) {
        char firstChar = s.charAt(0);
        return firstChar == '-' || firstChar == '+' || firstChar == '/' ||
                firstChar == '^' || firstChar == '*' || firstChar == '%' ||
                firstChar == '(' || firstChar == ')';
    }

    public static int precedence(String x, int code) {
        // PREFIX In-Stack 1, Incoming 2
        // POSTFIX In-Stack 3, Incoming 4, no modulo yet
        switch (code) {
            case 1 -> {
                return switch (x) {
                    case "+", "-" -> 2;
                    case "%" -> 4;
                    case "*", "/" -> 6;
                    case "^" -> 7;
                    case ")" -> 0;
                    default -> -999;
                };
            }

            case 2 -> {
                return switch (x) {
                    case "+", "-" -> 1;
                    case "%" -> 3;
                    case "*", "/" -> 5;
                    case "^" -> 8;
                    case ")" -> 9;
                    case "(" -> 10;
                    default -> -999;
                };
            }

            case 3 -> {
                return switch (x) {
                    case "+", "-" -> 2;
                    case "*", "/" -> 4;
                    case "^" -> 5;
                    case "(" -> 0;
                    default -> -999;
                };
            }
            case 4 -> {
                return switch (x) {
                    case "+", "-" -> 1;
                    case "*", "/" -> 3;
                    case "^" -> 6;
                    case "(" -> 9;
                    case ")" -> 10;
                    default -> -999;
                };
            }
        }
        return -999;
    }
}
