import java.util.Scanner;

public class LabEx2 {
    final static Scanner console = new Scanner(System.in);

    static String prefixToInfix(String e) {
        String[] tokenizedPrefix = e.split(" ", 0);
        int n = tokenizedPrefix.length;
        // I believe the index starts at 1, might have to change
        Stack<String> mainStack = new Stack<>(n);
        Stack<String> operatorStack = new Stack<>();
        String infix = "something"; // will change to something else later
        return infix;
    }

    public static void main(String[] args) {
        String prefixExpression = console.nextLine();
        System.out.println(prefixToInfix(prefixExpression));

    }
}
