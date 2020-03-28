package rule;

import java.util.Stack;

public class ExpressionToValue {

    public static void main(String[] args) {
        ExpressionToValue expressionToValue = new ExpressionToValue();
        String expression = "1 * 3 - 3 / 3 + 3 * 3 * ( 3 + 2 )";
        System.out.println(expressionToValue.eval(expression.split(" ")));
    }

    public String eval(String[] words) {
        Stack<String> operatorStack = new Stack<>();
        Stack<String> numStack = new Stack<>();

        for (String word : words) {
            switch (word) {
                case "(":
                case "*":
                case "/":
                    operatorStack.push(word);
                    break;
                case "+":
                case "-":
                    if (operatorStack.peek().equals("*")) {
                        double left = Double.parseDouble(numStack.pop());
                        double right = Double.parseDouble(numStack.pop());
                        double v = left * right;
                        numStack.push(String.valueOf(v));
                        operatorStack.pop();
                    } else if (operatorStack.peek().equals("/")) {
                        double left = Double.parseDouble(numStack.pop());
                        double right = Double.parseDouble(numStack.pop());
                        double v = right / left;
                        numStack.push(String.valueOf(v));
                        operatorStack.pop();
                    }
                    operatorStack.push(word);
                    break;
                case ")":
                    while (!operatorStack.peek().equals("(")) {
                        String op = operatorStack.pop();
                        double left = Double.parseDouble(numStack.pop());
                        double right = Double.parseDouble(numStack.pop());
                        double v = 0;
                        switch (op) {
                            case "*":
                                v = left * right;
                                break;
                            case "+":
                                v = left + right;
                                break;
                            case "-":
                                v = right - left;
                                break;
                            case "/":
                                v = right / left;
                                break;
                        }
                        numStack.push(String.valueOf(v));
                    }
                    operatorStack.pop();
                    break;
                default:
                    numStack.push(word);
                    break;
            }
        }

        while (!operatorStack.isEmpty()) {
            String op = operatorStack.pop();
            double left = Double.parseDouble(numStack.pop());
            double right = Double.parseDouble(numStack.pop());
            double v = 0;
            switch (op) {
                case "*":
                    v = left * right;
                    break;
                case "+":
                    v = left + right;
                    break;
                case "-":
                    v = right - left;
                    break;
                case "/":
                    v = right / left;
                    break;
            }
            numStack.push(String.valueOf(v));
        }

        return numStack.pop();
    }
}
