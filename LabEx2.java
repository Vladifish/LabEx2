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

    /*
     * Base Template for infix to Prefix : implement later
     * public static String[] infixToPrefix(String[] array) {
     * Stack<Character> s1 = new Stack<>(array.length) //Placeholder for operators
     * Stack<Character> fix = new Stack<>(array.length) //Operands and operators
     * String[] prefix = new String[array.length]
     * 
     * //Use of semi-pseudocode
     * 
     * for (int i = array.length - 1; i >= 0; i--) {
     * if (array[i] == operand) {
     * fix.push(array[i]);
     * }
     * else {
     * while (!s1.isEmpty() && precedence(array[i]) < precedence(peek(s1)))
     * fix.push(s1.pop());
     * s1.push(array[i]);
     * }
     * }
     * 
     * while (!s1.isEmpty())
     * fix.push(s1.pop());
     * while (!fix.isEmpty())
     * prefix[k++] = fix.pop();
     * 
     * return prefix;
     * }
     */

    public static void main(String[] args) {
        // a.
        System.out.println("""
                1CSC - Tan, Jam Meisy and Velazco, Vladimir Gray
                ------------------------------------------------
                1 - Prefix to Infix Notation + Evaluate
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

    public static int precedence(String x, int code) {
        // PREFIX In-Stack 1, Incoming 2
        // POSTFIX In-Stack 3, Incoming 4
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
            /*
             * case "+", "-" -> 2;
             * case "*", "/" -> 4;
             * case "^" -> 5;
             * case ")" -> 0;
             * default -> -999;
             */
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
            /*
             * case 2 -> {
             * return switch (x) {
             * case "+", "-" -> 1;
             * case "*", "/" -> 3;
             * case "^" -> 6;
             * case ")" -> 9;
             * case "(" -> 10;
             * default -> -999;
             * };
             * }
             * 
             */
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
