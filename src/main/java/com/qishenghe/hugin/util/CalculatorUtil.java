package com.qishenghe.hugin.util;

import java.util.Collections;
import java.util.Stack;

/**
 * 表达式运算
 *
 * @author qishenghe
 * @date 2020/12/28 17:11
 * @change 2020/12/28 17:11 by qishenghe@bonc.com.cn for init
 */
public class CalculatorUtil {

    /**
     * 后缀式栈
     */
    private Stack<String> postfixStack = new Stack<>();

    /**
     * 运算符栈
     */
    private Stack<Character> opStack = new Stack<>();

    /**
     * 运用运算符ASCII码-40做索引的运算符优先级
     */
    private int[] operatPriority = new int[] {0, 3, 2, 1, -1, 1, 0, 2};

    /**
     * 运算
     *
     * @param expression 运算表达式
     * @return 运算结果
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:23
     * @change 2020/12/28 17:23 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    public static double conversion(String expression) {
        // 去除表达式中无效空格
        expression = expression.replaceAll(" ", "");

        // 判断是否需要计算
        if (!judgmentHaveOperator(expression)) {
            // 不存在运算表达式，不需要计算
            try {
                return Double.parseDouble(expression);
            } catch (Exception e) {
                // 无效数字
                return Double.NaN;
            }
        }

        double result;
        CalculatorUtil cal = new CalculatorUtil();
        try {
            expression = transform(expression);
            result = cal.calculate(expression);
        } catch (Exception e) {
            // 运算错误返回NaN
            return Double.NaN;
        }
        return result;
    }

    /**
     * 将表达式中负数的符号更改
     *
     * @param expression 表达式（例如-2+-1*(-3E-2)-(-1) 被转为 ~2+~1*(~3E~2)-(~1)）
     * @return 符号修改结果
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:12
     * @change 2020/12/28 17:12 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private static String transform(String expression) {
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = '~';
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                        arr[i] = '~';
                    }
                }
            }
        }
        if (arr[0] == '~' || arr[1] == '(') {
            arr[0] = '-';
            return "0" + new String(arr);
        } else {
            return new String(arr);
        }
    }

    /**
     * 按照给定的表达式计算
     *
     * @param expression 表达式
     * @return 计算结果
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:14
     * @change 2020/12/28 17:14 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private double calculate(String expression) {
        Stack<String> resultStack = new Stack<String>();
        prepare(expression);
        // 将后缀式栈反转
        Collections.reverse(postfixStack);
        // 参与计算的第一个值，第二个值和算术运算符
        String firstValue, secondValue, currentValue;
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {
                // 如果不是运算符则存入操作数栈中
                currentValue = currentValue.replace("~", "-");
                resultStack.push(currentValue);
            } else {// 如果是运算符则从操作数栈中取两个值和该数值一起参与运算
                secondValue = resultStack.pop();
                firstValue = resultStack.pop();

                // 将负数标记符改为负号
                firstValue = firstValue.replace("~", "-");
                secondValue = secondValue.replace("~", "-");

                String tempResult = calculate(firstValue, secondValue, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
        return Double.valueOf(resultStack.pop());
    }

    /**
     * 数据准备阶段将表达式转换成为后缀式栈
     *
     * @param expression 表达式
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:14
     * @change 2020/12/28 17:14 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private void prepare(String expression) {
        // 运算符放入栈底元素逗号，此符号优先级最低
        opStack.push(',');
        char[] arr = expression.toCharArray();
        // 当前字符的位置
        int currentIndex = 0;
        // 上次算术运算符到本次算术运算符的字符的长度便于或者之间的数值
        int count = 0;
        // 当前操作符和栈顶操作符
        char currentOp, peekOp;
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {
                // 如果当前字符是运算符
                if (count > 0) {
                    // 取两个运算符之间的数字
                    postfixStack.push(new String(arr, currentIndex, count));
                }
                peekOp = opStack.peek();
                if (currentOp == ')') {
                    // 遇到反括号则将运算符栈中的元素移除到后缀式栈中直到遇到左括号
                    while (opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }
        if (count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {
            // 最后一个字符不是括号或者其他运算符的则加入后缀式栈中
            postfixStack.push(new String(arr, currentIndex, count));
        }

        while (opStack.peek() != ',') {
            // 将操作符栈中的剩余的元素添加到后缀式栈中
            postfixStack.push(String.valueOf(opStack.pop()));
        }
    }

    /**
     * 判断是否为算术符号
     *
     * @param c 运算符
     * @return 是否
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:17
     * @change 2020/12/28 17:17 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 判断是否存在算数运算符号
     *
     * @param expression 运算表达式
     * @return 判断结果
     * @since 1.0.0
     * @author qishenghe@bonc.com.cn
     * @date 2021/1/4 10:08
     * @change 2021/1/4 10:08 by qishenghe@bonc.com.cn for init
     */
    private static boolean judgmentHaveOperator(String expression) {
        boolean flag = false;
        char[] chars = expression.toCharArray();

        CalculatorUtil calculatorUtil = new CalculatorUtil();
        for (char c : chars) {
            boolean singleFlag = calculatorUtil.isOperator(c);
            if (singleFlag) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 利用ASCII码-40做下标去算术符号优先级
     *
     * @param cur 当前操作符
     * @param peek 栈顶操作符
     * @return 是否优先
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:18
     * @change 2020/12/28 17:18 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private boolean compare(char cur, char peek) {
        // 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        boolean result = false;
        if (operatPriority[(peek) - 40] >= operatPriority[(cur) - 40]) {
            result = true;
        }
        return result;
    }

    /**
     * 按照给定的算术运算符做计算
     *
     * @param firstValue 第一个值
     * @param secondValue 第二个值
     * @param currentOp 当前运算符
     * @return 运算结果
     * @author qishenghe@bonc.com.cn
     * @date 2020/12/28 17:22
     * @change 2020/12/28 17:22 by qishenghe@bonc.com.cn for init
     * @since 1.0.0
     */
    private String calculate(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
            case '+':
                result = String.valueOf(ArithUtil.add(firstValue, secondValue));
                break;
            case '-':
                result = String.valueOf(ArithUtil.sub(firstValue, secondValue));
                break;
            case '*':
                result = String.valueOf(ArithUtil.mul(firstValue, secondValue));
                break;
            case '/':
                result = String.valueOf(ArithUtil.div(firstValue, secondValue));
                break;
        }
        return result;
    }

}
