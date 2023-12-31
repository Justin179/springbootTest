package com.justin.springboottest.designpatterns2.behavioral.interpreter;

public class InterpreterClient {
    public static void main(String[] args) {
        // 构建表达式： 1 + 2 + 3
        Expression expression = new AddExpression(
                new AddExpression(new NumberExpression(1), new NumberExpression(2)),
                new NumberExpression(3)
        );

        // 解释并评估表达式
        int result = expression.interpret();
        System.out.println("Result: " + result); // 输出 6
    }
}

