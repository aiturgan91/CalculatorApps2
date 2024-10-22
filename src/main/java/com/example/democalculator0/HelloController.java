package com.example.democalculator0;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class HelloController {

    @FXML
    private TextField txtDisplay;

    private Main calculator;  // Калькулятор для вычислений
    private StringBuilder currentInput;
    private boolean startNewNumber;  // Флаг для начала ввода нового числа

    public HelloController() {
        calculator = new Main();
        currentInput = new StringBuilder();
        startNewNumber = true;
    }

    @FXML
    public void initialize() {
        txtDisplay.setText("0");
    }

    @FXML
    public void handlerDigitAction(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();

        // Если начинаем новое число, очищаем предыдущий ввод
        if (startNewNumber) {
            currentInput.setLength(0);
            startNewNumber = false;
        }

        // Добавляем цифру и обновляем display
        currentInput.append(digit);
        txtDisplay.setText(currentInput.toString());
    }

    @FXML
    public void handlerGeneralAction(ActionEvent event) {
        try {
            // Сохраняем первый операнд только если есть что сохранять
            if (currentInput.length() > 0) {
                calculator.setOperand1(Double.parseDouble(currentInput.toString()));
            }

            // Устанавливаем оператор
            calculator.setOperator(((Button) event.getSource()).getText().charAt(0));

            // Подготавливаемся к вводу второго операнда
            startNewNumber = true;

        } catch (NumberFormatException e) {
            txtDisplay.setText("Ошибка");
            handleClearPress();
        }
    }

    @FXML
    public void handlerEqualAction() {
        try {
            // Проверяем, есть ли второй операнд для вычисления
            if (currentInput.length() > 0) {
                calculator.setOperand2(Double.parseDouble(currentInput.toString()));
                calculator.calculate();

                if (calculator.isError()) {
                    txtDisplay.setText("Ошибка: деление на ноль");
                } else {
                    // Форматируем результат
                    String result = formatResult(calculator.getResult());
                    txtDisplay.setText(result);
                    currentInput = new StringBuilder(result);
                }
            }
            startNewNumber = true;

        } catch (NumberFormatException e) {
            txtDisplay.setText("Ошибка");
            handleClearPress();
        }
    }

    @FXML
    public void handleClearPress() {
        calculator.reset();
        currentInput.setLength(0);
        txtDisplay.setText("0");
        startNewNumber = true;
    }

    @FXML
    public void handlerDecimalAction(ActionEvent event) {
        // Если начинаем новое число
        if (startNewNumber) {
            currentInput.setLength(0);
            currentInput.append("0");
            startNewNumber = false;
        }

        // Добавляем точку, если её ещё нет
        if (!currentInput.toString().contains(".")) {
            currentInput.append(".");
            txtDisplay.setText(currentInput.toString());
        }
    }

    // Вспомогательный метод для форматирования результата
    private String formatResult(double result) {
        // Удаляем trailing zeros после десятичной точки
        String formatted = String.valueOf(result);
        if (formatted.endsWith(".0")) {
            return formatted.substring(0, formatted.length() - 2);
        }
        return formatted;
    }

    public void hello(){}
}