package expression;

import java.util.*;

public class Main {
    public static void main(String[] args) {

     //   String str = "Welcode4(ToAlibaba(To3)2)2";
        String str = "(Hello42)(World3)";

        List<Token> tokens = lexer(str);

        System.out.println("输入：" + str);
        System.out.print("输出：");

        parse(tokens);
    }


    static class Token {
        int type;
        String value;

        public Token(int type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static String parse(List<Token> tokens) {

        Stack<Token> stack = new Stack<>();
        for (Token token : tokens) {

            // 处理字符串
            if (token.type == 1) {
                stack.push(token);
                continue;
            }

            // 处理数字
            if (token.type == 2) {


                List<Token> tmpTokens = new ArrayList<>();

                if (stack.peek().type == 1) {
                    // 出栈
                    while (!stack.isEmpty() && stack.peek().type != 3) {
                        tmpTokens.add(stack.pop());
                    }

                } else if (stack.peek().type == 4) {
                    // 右括号出栈
                    stack.pop();

                    // 子串出栈
                    while (stack.peek().type != 3) {
                        tmpTokens.add(stack.pop());
                    }

                    // 左括号出栈
                    stack.pop();
                }

                // 翻倍
                for (int i = 0; i < Integer.parseInt(token.value); i++) {
                    for (Token tmpToken : tmpTokens) {
                        stack.push(tmpToken);
                    }
                }
                continue;
            }

            // 处理左括号
            if (token.type == 3) {
                stack.push(token);
                continue;
            }

            // 处理右括号

            if (token.type == 4) {
                stack.push(token);
                continue;
            }
        }


        Map<String, Integer> map = new HashMap<>();

        for (Token token : stack) {
            Integer num = map.get(token.value);
            if (num == null) {
                map.put(token.value, 1);
            } else {
                map.put(token.value, num + 1);
            }
        }
        System.out.println(map);

        return "";
    }

    public static List<Token> lexer(String str) {
        List<Token> tokens = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 处理单词
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                String word = chars[i++] + "";
                while (i < chars.length && chars[i] >= 'a' && chars[i] <= 'z') {
                    word += chars[i++];
                }
                tokens.add(new Token(1, word));
                i--;
                continue;
            }
            // 处理数字
            if (chars[i] >= '0' && chars[i] <= '9') {
                String word = chars[i++] + "";
                while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
                    word += chars[i++];
                }
                tokens.add(new Token(2, word));
                i--;
                continue;
            }

            // 处理左括号
            if (chars[i] == '(') {
                tokens.add(new Token(3, "("));
                continue;
            }

            // 处理右括号
            if (chars[i] == ')') {
                tokens.add(new Token(4, ")"));
                continue;
            }
        }
        return tokens;
    }
}
