package com.example.democalculator0;

public class Main {
    private double operand1;
    private double operand2;
    private char operator;
    private double result;
    private boolean isError;

    public void setOperand1(double operand) {
        this.operand1 = operand;
    }

    public void setOperand2(double operand) {
        this.operand2 = operand;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void calculate() {
        isError = false; // Reset error state before calculation
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    isError = true; // Handle division by zero
                }
                break;
            default:
                isError = true; // Handle invalid operators
        }
    }

    public double getResult() {
        return result;
    }

    public boolean isError() {
        return isError;
    }

    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = ' ';
        result = 0;
        isError = false;
    }
}
