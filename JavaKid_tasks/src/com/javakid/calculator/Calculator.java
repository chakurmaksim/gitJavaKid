package com.javakid.calculator;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

public class Calculator {
    // Объявляем все компоненты калькулятора.
    JPanel windowContent;
    JFormattedTextField displayFormattedField;

    /* Модифицируйте класс Calculator.java так, чтобы все кнопки с цифрами хранились в массиве с десятью элементами,
    который должен быть объявлен вот так:
    Buttons[] numButtons= new Buttons[10];
    Замените 10 строк кода, которые начинаются с button0=new JButton("0"); циклом, который создаёт кнопки и
    добавляет их в массив.
     */
    JButton [] button;
    JButton buttonPoint;
    JButton buttonEqual;

    JButton buttonPlus;
    JButton buttonMinus;
    JButton buttonMultiply;
    JButton buttonDivide;

    JButton buttonDelete;
    JButton buttonPercent;
    JButton buttonSqrt;

    JPanel p1;
    JPanel p2;
    JPanel p3;

    Calculator() {
        // Создаём панель
        windowContent = new JPanel();
        // Задаём схему размещения для этой панели
        BorderLayout b1 = new BorderLayout();
        windowContent.setLayout(b1);

        /*Прочитайте про класс JFormattedTextField в интернете и измените исходный код калькулятора так,
        чтобы этот класс использовался вместо JTextField. Целью является создание поля ввода с выравниванием по
        правому краю, как в настоящих калькуляторах.
         */
        // Создаём и отображаем поле, добавляем его в Северную область окна
        displayFormattedField = new JFormattedTextField();
        displayFormattedField.setHorizontalAlignment(SwingConstants.RIGHT);
        windowContent.add("North", displayFormattedField);

        button = new JButton[10];

        // Создаём кнопки, используя конструктор класса JButton, который принимает текст кнопки в качестве параметра
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton("" +i);
        }
        buttonPoint = new JButton(".");
        buttonEqual = new JButton("=");

        // Создаём панель с GridLayout, которая содержит 12 кнопок - 10 кнопок с числами, кнопки с точкой и знаком равно
        p1 = new JPanel();
        GridLayout g1 = new GridLayout(4, 3);
        p1.setLayout(g1);

        // Добавляем кнопки на панель p1
        for (int i = 1; i < button.length; i++) {
            p1.add(button[i]);
        }
        p1.add(buttonPoint);
        p1.add(button[0]);
        p1.add(buttonEqual);

        // Помещаем панель p1 в центральную область окна
        windowContent.add("Center", p1);

        /*Модифицируйте класс Calculator.java добавив в него кнопки +, -, /, и *.
        Поместите эти кнопки на панель p2, и положите эту панель на Восточную область основной панели.
         */
        buttonPlus = new JButton("+");
        buttonMinus = new JButton("-");
        buttonMultiply = new JButton("*");
        buttonDivide = new JButton("/");

        p2 = new JPanel();
        GridLayout g2 = new GridLayout(4,1);
        p2.setLayout(g2);

        p2.add(buttonPlus);
        p2.add(buttonMinus);
        p2.add(buttonMultiply);
        p2.add(buttonDivide);
        windowContent.add("East", p2);

        buttonDelete = new JButton("C");
        buttonSqrt = new JButton("√");
        buttonPercent = new JButton("%");

        p3 = new JPanel();
        p3.setLayout(g2);

        p3.add(buttonDelete);
        p3.add(buttonSqrt);
        p3.add(buttonPercent);
        windowContent.add("West", p3);

        //Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);

        // делаем размер окна достаточным для того, чтобы вместить все компоненты
        frame.pack();
        // Отображаем окно (делаем видимым)
        frame.setVisible(true);

        // Создаем экземпляр класса слушателя событий и пердаем его конструктору ссылку на экземпляр класса Calculator
        // Ссылка указывает на то место в памяти, где находится экземпляр класса Calculator.
        CalculatorEngine calculatorEngine = new CalculatorEngine(this);
        // слушатель событий регистрируем в каждой кнопке
        for (int i = 0; i < button.length; i++) {
            button[i].addActionListener(calculatorEngine);
        }
        buttonPoint.addActionListener(calculatorEngine);

        buttonPlus.addActionListener(calculatorEngine);
        buttonMinus.addActionListener(calculatorEngine);
        buttonMultiply.addActionListener(calculatorEngine);
        buttonDivide.addActionListener(calculatorEngine);
        buttonEqual.addActionListener(calculatorEngine);

        buttonDelete.addActionListener(calculatorEngine);
        buttonSqrt.addActionListener(calculatorEngine);
        buttonPercent.addActionListener(calculatorEngine);

    }
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
    }
}
