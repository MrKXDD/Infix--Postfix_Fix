import java.util.Scanner;

public class InfixToPostfix {

    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return 0;
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static boolean isValidInfix(String expr) {
        int balance = 0;
        char prev = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (isOperand(c)) {
                if (prev != 0 && isOperand(prev)) return false;
                prev = c;
            } else if (isOperator(c)) {
                if (prev == 0 || isOperator(prev) || prev == '(') return false;
                prev = c;
            } else if (c == '(') {
                if (prev != 0 && isOperand(prev)) return false;
                balance++;
                prev = c;
            } else if (c == ')') {
                if (isOperator(prev) || prev == '(' || prev == 0) return false;
                balance--;
                if (balance < 0) return false;
                prev = c;
            } else {
                return false;
            }
        }

        return balance == 0 && (isOperand(prev) || prev == ')');
    }

    public static String toPostfix(String expr) {
        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (isOperand(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                       (c == '^' ? precedence(stack.peek()) > precedence(c)
                                 : precedence(stack.peek()) >= precedence(c))) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private static void processExpression(String line, int exprNum) {
        line = line.trim();
        if (line.isEmpty()) return;

        String expr = line.contains(":") ? line.substring(line.indexOf(':') + 1) : line;

        System.out.println("Expression " + exprNum + ":");
        System.out.println("Infix exp:" + expr);

        if (isValidInfix(expr)) {
            System.out.println("Valid");
            System.out.println("Postfix exp: " + toPostfix(expr));
        } else {
            System.out.println("Not-Valid");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int exprNum = 1;
        System.out.println("Infix to Postfix Converter (type 'exit' to quit)");
        System.out.println("------------------------------------------------");
        while (true) {
            System.out.print("Enter infix expression: ");
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            if (!line.trim().isEmpty()) {
                processExpression(line, exprNum++);
            }
        }
        scanner.close();
    }
}
