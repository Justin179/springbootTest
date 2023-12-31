package com.justin.springboottest.designpatterns2.behavioral.interpreter2;

import java.util.Stack;

public class InterpreterPatternDemo {
    public static void main(String[] args) {
        String tokenString = "7 3 - 2 1 + *";
        Stack<Integer> stack = new Stack<>();

        String[] tokenList = tokenString.split(" ");
        for (String s : tokenList) {
            if (isOperator(s)) {
                Expression operator = getOperatorInstance(s);
                operator.interpret(stack);
            } else {
                Expression number = new Number(Integer.parseInt(s));
                number.interpret(stack);
            }
        }
        System.out.println("Result: " + stack.pop());
    }

    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }

    public static Expression getOperatorInstance(String s) {
        switch (s) {
            case "+":
                return new Plus();
            case "-":
                return new Minus();
            default:
                return null;
        }
    }
}

interface Expression {
    void interpret(Stack<Integer> s);
}

// 各實作類別自行解讀
class Number implements Expression {
    private int number;

    public Number(int number) {
        this.number = number;
    }

    public void interpret(Stack<Integer> s) {
        s.push(number);
    }
}

class Plus implements Expression {
    public void interpret(Stack<Integer> s) {
        s.push(s.pop() + s.pop());
    }
}

class Minus implements Expression {
    public void interpret(Stack<Integer> s) {
        int second = s.pop();
        int first = s.pop();
        s.push(first - second);
    }
}



