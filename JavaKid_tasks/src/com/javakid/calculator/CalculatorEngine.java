package com.javakid.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Создаем класс, реализующий интерфейс ActionListener, в котором содержится один метод actionPerformed(ActionEvent e)
public class CalculatorEngine implements ActionListener {
    // Зоздаем переменную parent типа Calculator для сохранения ссылки на экземпляр класса Calculator
    Calculator parent;

    // Арифметическое действие: +, -, /, или *
    char selectedAction = ' ';
    // Текущий результат
    double currentResult = 0;

    boolean tempValue = false;

    // Добавим конструктор, у которого будет один аргумент типа Calculator. Конструктор CalculatorEngine сохраняет
    // ссылку, указывающую на то место в памяти, где находится экземпляр класса Calculator, в переменной parent,
    // чтобы потом использовать его в методе actionPerformed() для доступа к дисплею калькулятора.
    CalculatorEngine (Calculator parent) {
        this.parent = parent;
    }

    // Когда произойдет событие, JVM вызовет метод данного класса-слушателя actionPerformed(ActionEvent),
    // и передаст ему необходимую информацию о событии в аргументе ActionEvent.
    @Override
    public void actionPerformed(ActionEvent e) { // Переменная e – это ссылка на объект-событие в памяти компьютера.
        // Метод getSource() возвращает источник события как экземпляр типа Object, который является предком всех
        // классов Java, включая компоненты окна.
        // Получаем источник текущего действия
        Object eventSource = e.getSource();

        // Создаем переменную для хранения источника события, нажатия на кнопку
        JButton clickedButton = null;
        // Проверяем, объект какого типа вызвал событие, а потом делаем приведение типа к JButton
        if (eventSource instanceof JButton) {
            clickedButton = (JButton) eventSource;
        }
        // Получаем текущий текст из поля вывода (“дисплея”) калькулятора
        String dispFieldText = parent.displayFormattedField.getText();

        double displayValue = 0;

        //  Если дисплей калькулятора не пустой присваиваем это значение переменной displayValue
        if (!"".equals(dispFieldText)) {
            try {
                displayValue = Double.parseDouble(dispFieldText);
            } catch (NumberFormatException nfe) {
                System.out.println("Проблема при приведении типов " + nfe.toString());
                JOptionPane.showConfirmDialog(null, "Пожалуйста, введите число",
                        "Неправильный ввод", JOptionPane.PLAIN_MESSAGE);
            }
        }

        if (eventSource == parent.buttonPlus){
            if (selectedAction == ' ') {
                selectedAction = '+';
                currentResult = displayValue;
                tempValue = true;

            } else {
                if (selectedAction == '+') {
                    currentResult += displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '+';
                } else if (selectedAction == '-') {
                    currentResult -= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '+';
                } else if (selectedAction == '*') {
                    currentResult *= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '+';
                } else if (selectedAction == '/') {
                    if (displayValue != 0) {
                        currentResult /= displayValue;
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = '+';
                    } else {
                        parent.displayFormattedField.setText("Не определено");
                        JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                                "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    }
                }
                tempValue = true;
            }

        } else if (eventSource == parent.buttonMinus) {
            if (selectedAction == ' ') {
                selectedAction = '-';
                currentResult = displayValue;
                tempValue = true;

            } else {
                if (selectedAction == '+') {
                    currentResult += displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '-';
                } else if (selectedAction == '-') {
                    currentResult -= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '-';
                } else if (selectedAction == '*') {
                    currentResult *= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '-';
                } else if (selectedAction == '/') {
                    if (displayValue != 0) {
                        currentResult /= displayValue;
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = '-';
                    } else {
                        parent.displayFormattedField.setText("Не определено");
                        JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                                "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    }
                }
                tempValue = true;
            }

        } else if (eventSource == parent.buttonMultiply) {
            if (selectedAction == ' ') {
                selectedAction = '*';
                currentResult = displayValue;
                tempValue = true;

            } else {
                if (selectedAction == '+') {
                    currentResult += displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '*';
                } else if (selectedAction == '-') {
                    currentResult -= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '*';
                } else if (selectedAction == '*') {
                    currentResult *= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '*';
                } else if (selectedAction == '/') {
                    if (displayValue != 0) {
                        currentResult /= displayValue;
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = '*';
                    } else {
                        parent.displayFormattedField.setText("Не определено");
                        JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                                "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    }
                }
                tempValue = true;
            }

        } else if (eventSource == parent.buttonDivide) {
            if (selectedAction == ' ') {
                selectedAction = '/';
                currentResult = displayValue;
                tempValue = true;

            } else {
                if (selectedAction == '+') {
                    currentResult += displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '/';
                } else if (selectedAction == '-') {
                    currentResult -= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '/';
                } else if (selectedAction == '*') {
                    currentResult *= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = '/';
                } else if (selectedAction == '/') {
                    if (displayValue != 0) {
                        currentResult /= displayValue;
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = '/';
                    } else {
                        parent.displayFormattedField.setText("Не определено");
                        JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                                "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    }
                }
                tempValue = true;
            }

        } else if (eventSource == parent.buttonDelete) {
            selectedAction = ' ';
            currentResult = displayValue = 0;
            parent.displayFormattedField.setText("0");
            tempValue = true;

        } else if (eventSource == parent.buttonSqrt) {
            selectedAction = ' ';
            currentResult = Math.sqrt(displayValue);
            parent.displayFormattedField.setText("" +currentResult);
            tempValue = true;

        } else if (eventSource == parent.buttonPercent) {
            if (selectedAction == ' ') {
                selectedAction = ' ';
                currentResult = displayValue/100;
                parent.displayFormattedField.setText("" +currentResult);
                tempValue = true;
            } else {
                if (selectedAction == '+') {
                    currentResult += currentResult * (displayValue/100);
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = ' ';
                } else if (selectedAction == '-') {
                    currentResult -= currentResult * (displayValue/100);
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = ' ';
                } else if (selectedAction == '*') {
                    currentResult *= currentResult * (displayValue/100);
                    parent.displayFormattedField.setText("" +currentResult);
                    selectedAction = ' ';
                } else if (selectedAction == '/') {
                    if (displayValue != 0) {
                        currentResult /= currentResult * (displayValue/100);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    } else {
                        parent.displayFormattedField.setText("Не определено");
                        JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                                "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                        parent.displayFormattedField.setText("" +currentResult);
                        selectedAction = ' ';
                    }
                }
                tempValue = true;
            }

        } else if (eventSource == parent.buttonEqual) {
            if (selectedAction == '+') {
                currentResult += displayValue;
                parent.displayFormattedField.setText("" +currentResult);
            } else if (selectedAction == '-') {
                currentResult -= displayValue;
                parent.displayFormattedField.setText("" +currentResult);
            } else if (selectedAction == '*') {
                currentResult *= displayValue;
                parent.displayFormattedField.setText("" +currentResult);
            } else if (selectedAction == '/') {
                /* Измените класс CalculatorEngine, чтобы отображалось сообщение ”На ноль делить нельзя”,
                если пользователь нажмет на кнопку ”Разделить”, когда дисплей калькулятора будет пуст.
                 */
                if (displayValue != 0) {
                    currentResult /= displayValue;
                    parent.displayFormattedField.setText("" +currentResult);
                } else {
                    parent.displayFormattedField.setText("Не определено");
                    JOptionPane.showConfirmDialog(null, "На ноль делить нельзя!",
                            "Диалоговое окно", JOptionPane.PLAIN_MESSAGE);
                    parent.displayFormattedField.setText("" +currentResult);
                }
            }
            selectedAction = ' ';
            tempValue = true;

        } else {
            if (tempValue) {
                parent.displayFormattedField.setText("");
                String clickedButtonLabel = clickedButton.getText();
                parent.displayFormattedField.setText("" + clickedButtonLabel);
                tempValue = false;
            } else {
                String clickedButtonLabel = clickedButton.getText();
                /* Измените класс CalculatorEngine , чтобы запретить вводить больше одной точки в числе.
                Применяем метод indexOf() класса String, чтобы узнать, есть ли уже в числе точка.
                 */
                if (dispFieldText.indexOf('.') == -1) {
                    parent.displayFormattedField.setText(dispFieldText + clickedButtonLabel);
                } else {
                    if (clickedButtonLabel.indexOf('.') == -1) {
                        parent.displayFormattedField.setText(dispFieldText + clickedButtonLabel);
                    }
                }
            }

        }

    }
}
