package com.javakid.tictactoe;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TicTacToe implements ActionListener {
    Panel windowContent;
    Button squares[][];
    Button newGameButton;
    Label score;
    Label Wins;
    Label Losses;
    TextField fieldForWins;
    TextField fieldForLosses;
    int emptySquaresLeft = 9;
    int fieldForWinsValue = 0;
    int fieldForLossesValue = 0;
    static int userWin;

    // Конструктор класса TicTacToe, в котором создаются все компоненты окна
    TicTacToe () {
        // Создаем панель и задаем схему размещения для этой панели
        windowContent = new Panel(new BorderLayout());
        // Задаем цвет фона панели
        windowContent.setBackground(Color.CYAN);
        // Изменяем шрифт так, чтобы он был жирным и имел размер 20
        windowContent.setFont(new Font("Monospaced", Font.BOLD, 25));

        // Создаем кнопку “New Game” и регистрируем в ней слушатель действия
        newGameButton = new Button("New Game");
        newGameButton.addActionListener(this);

        /* Добавьте на верхнюю панель класса TicTacToe две надписи для подсчета выигрышей и проигрышей.
        Для этого объявите две переменные в классе и увеличивайте соответствующую переменную каждый раз,
        когда человек выигрывает или проигрывает. Счет должен обновляться сразу после того,
        как программа выводит сообщение “You won” или “You lost”.
         */
        // Создаем поля для хранения счета выигрышей и проигрышей.
        Wins = new Label("Wins: ");
        fieldForWins = new TextField(3);

        // Считываем победы предыдущих игр и добавляем в поле fieldForWins
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("userwins.dat")));
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return;
        }
        while (true) {
            try {
                fieldForWinsValue = dataInputStream.readInt();
            } catch (EOFException eof) {
                break;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        fieldForWins.setText(""+fieldForWinsValue);

        Losses = new Label("Losses: ");
        fieldForLosses = new TextField(3);

        // Считываем поражения предыдущих игр и добавляем в поле fieldForLosses
        DataInputStream dataInputStreamForLosses;
        try {
            dataInputStreamForLosses = new DataInputStream(new BufferedInputStream
                    (new FileInputStream("userlosses.dat")));
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return;
        }
        while (true) {
            try {
                fieldForLossesValue = dataInputStreamForLosses.readInt();
            } catch (EOFException eof) {
                break;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        fieldForLosses.setText(""+fieldForLossesValue);

        // Создаем панель topPanel и добавляем на нее компоненты
        Panel topPanel = new Panel();
        topPanel.add(newGameButton);
        topPanel.add(Wins);
        topPanel.add(fieldForWins);
        topPanel.add(Losses);
        topPanel.add(fieldForLosses);
        // Помещаем панель topPanel в северную область окна
        windowContent.add(topPanel, "North");

        // Создаем панель centerPanel и задаем схему размещения для этой панели
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(3, 3, 0, 0));
        centerPanel.setFont(new Font("Monospaced", Font.BOLD, 50));
        // Помещаем панель centerPanel в центральную область
        windowContent.add(centerPanel, "Center");

        // создаем двухмерный массив, чтобы хранить ссылки на 9 кнопок
        squares = new Button[3][3];
        // Создаем кнопки, сохраняем ссылки на них в массиве, регистрируем в них слушатель событий,
        // красим кнопки в оранжевый цвет и добавляем на панель
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new Button();
                squares[i][j].addActionListener(this);
                squares[i][j].setBackground(Color.ORANGE);
                centerPanel.add(squares[i][j]);
            }
        }

        // Создаем Label score и помещаем в ююжную область
        score = new Label("Your turn!");
        windowContent.add(score,"South");

        // Создаём фрейм и задаём его основную панель
        Frame frame = new Frame("Крестики и нолики");
        frame.add(windowContent);
        // делаем размер окна достаточным для того, чтобы вместить все компоненты
        frame.setSize(600, 600);
        // Отображаем окно (делаем видимым)
        frame.setVisible(true);

    }

    /* Добавьте метод main() к классу TicTacToe, чтобы иметь возможность запускать игру
    как Java-приложение.
     */
    // Добавляем метод main () в наш класс TicTacToe и создаем в нем обект класса
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }

    // Данный метод обрабатывает все события
    @Override
    public void actionPerformed(ActionEvent e) {
        Button thebutton = (Button) e.getSource();

        // Это кнопка New Game ?
        if (thebutton == newGameButton) {
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares.length; j++) {
                    squares[i][j].setEnabled(true);
                    squares[i][j].setLabel("");
                    squares[i][j].setBackground(Color.ORANGE);
                }
            }
            emptySquaresLeft = 9;
            score.setText("Your turn!");
            newGameButton.setEnabled(false);
            return; // выходим из метода
        }

        String winner = "";

        // Это одна из клеток?
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                if (thebutton == squares[i][j]) {
                    /* Наша программа позволяет щелкать на клетке, в которой уже есть крестик или нолик.
                    Это ошибка! Программа продолжает работать, как будто вы сделали правильный ход.
                    Измените текст программы так, чтобы нажатия на такие клетки игнорировались.
                     */
                    // Добавляем условие, благодаря которому невозможно поставить крестик, где стоит нолик
                    if (squares[i][j].getLabel().equals("")) {
                        squares[i][j].setLabel("X");
                        winner = lookForWinner();
                        if (!"".equals(winner)) {
                            endTheGame();
                        } else {
                            computerMove();
                            winner = lookForWinner();
                            if (!"".equals(winner)) {
                                endTheGame();
                            }
                        }
                    }
                    break; // Завершаем выполнение цикла и переходим в его конец
                }
            }
        } // конец цикла for

        if (winner.equals("X")) {
            score.setText("You Won!");
            fieldForWinsValue++;
            fieldForWins.setText(""+fieldForWinsValue);

            // Записываем результат количества побед в двоичный файл
            DataOutputStream dataOutputStreamForWins;
            try {
                dataOutputStreamForWins = new DataOutputStream(new BufferedOutputStream
                        (new FileOutputStream("userwins.dat")));
            } catch (FileNotFoundException fnf) {
                System.out.println("File not found");
                return;
            }
            try {
                dataOutputStreamForWins.writeInt(fieldForWinsValue);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                dataOutputStreamForWins.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (winner.equals("O")) {
            score.setText("You Lost!");
            fieldForLossesValue++;
            fieldForLosses.setText(""+fieldForLossesValue);

            // Записываем результат количества поражений в двоичный файл
            DataOutputStream dataOutputStreamForLosses;
            try {
                dataOutputStreamForLosses = new DataOutputStream(new BufferedOutputStream
                        (new FileOutputStream("userlosses.dat")));
            } catch (FileNotFoundException fnf) {
                System.out.println("File not found");
                return;
            }
            try {
                dataOutputStreamForLosses.writeInt(fieldForLossesValue);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                dataOutputStreamForLosses.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else if (winner.equals("D")) {
            score.setText("It's a Draw!");
        }
    } // конец метода actionPerformed

    // Метод вызывается после каждого хода, чтобы узнать есть ли победитель
    String lookForWinner() {
        String theWinner = "";
        emptySquaresLeft--;
        if (emptySquaresLeft == 0) {
            return "D"; // Это ничья
        }
        // Проверяем ряд 1
        if (!squares[0][0].getLabel().equals("") && squares[0][0].getLabel().equals(squares[0][1].getLabel())
                && squares[0][0].getLabel().equals(squares[0][2].getLabel())) {
            theWinner = squares[0][0].getLabel();
            highlightWinner(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2});
        }
        // Проверяем ряд 2
        if (!squares[1][0].getLabel().equals("") && squares[1][0].getLabel().equals(squares[1][1].getLabel())
                && squares[1][0].getLabel().equals(squares[1][2].getLabel())) {
            theWinner = squares[1][0].getLabel();
            highlightWinner(new int[]{1, 0}, new int[]{1, 1}, new int[]{1, 2});
        }
        // Проверяем ряд 3
        if (!squares[2][0].getLabel().equals("") && squares[2][0].getLabel().equals(squares[2][1].getLabel())
                && squares[2][0].getLabel().equals(squares[2][2].getLabel())) {
            theWinner = squares[2][0].getLabel();
            highlightWinner(new int[]{2, 0}, new int[]{2, 1}, new int[]{2, 2});
        }
        // Проверяем колонку 1
        if (!squares[0][0].getLabel().equals("") && squares[0][0].getLabel().equals(squares[1][0].getLabel())
                && squares[0][0].getLabel().equals(squares[2][0].getLabel())) {
            theWinner = squares[0][0].getLabel();
            highlightWinner(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0});
        }
        // Проверяем колонку 2
        if (!squares[0][1].getLabel().equals("") && squares[0][1].getLabel().equals(squares[1][1].getLabel())
                && squares[0][1].getLabel().equals(squares[2][1].getLabel())) {
            theWinner = squares[0][1].getLabel();
            highlightWinner(new int[]{0, 1}, new int[]{1, 1}, new int[]{2, 1});
        }
        // Проверяем колонку 3
        if (!squares[0][2].getLabel().equals("") && squares[0][2].getLabel().equals(squares[1][2].getLabel())
                && squares[0][2].getLabel().equals(squares[2][2].getLabel())) {
            theWinner = squares[0][2].getLabel();
            highlightWinner(new int[]{0, 2}, new int[]{1, 2}, new int[]{2, 2});
        }
        // Проверяем диагональ 1
        if (!squares[0][0].getLabel().equals("") && squares[0][0].getLabel().equals(squares[1][1].getLabel())
                && squares[0][0].getLabel().equals(squares[2][2].getLabel())) {
            theWinner = squares[0][0].getLabel();
            highlightWinner(new int[]{0, 0}, new int[]{1, 1}, new int[]{2, 2});
        }
        // Проверяем диагональ 2
        if (!squares[0][2].getLabel().equals("") && squares[0][2].getLabel().equals(squares[1][1].getLabel())
                && squares[0][2].getLabel().equals(squares[2][0].getLabel())) {
            theWinner = squares[0][2].getLabel();
            highlightWinner(new int[]{0, 2}, new int[]{1, 1}, new int[]{2, 0});
        }
        return theWinner;
    }

    // Набор правил компьютерного хода
    void computerMove () {
        int[] selectedSquare;

        // Сначала компьютер пытается найти пустую клетку рядом с двумя клетками с ноликами, чтобы выиграть

        selectedSquare = findEmptySquare("O");

        // Если он не может найти два нолика, то пытается не дать оппоненту сделать ряд из 3-х крестиков,
        // поместив нолик рядом с двумя крестиками
        if (selectedSquare[0] == -1) {
            selectedSquare = findEmptySquare("X");
        }

        // Если компьютер не может найти два крестика, то пытается занять центральную клетку
        if (selectedSquare[0] == -1 && squares[1][1].getLabel().equals("")) {
            selectedSquare[0] = 1;
            selectedSquare[1] = 1;
        }

        // В случае, если центральная клетка занята, то занимает случайную клетку
        if (selectedSquare[0] == -1){
            selectedSquare = getRandomSquare();
        }
        squares[selectedSquare[0]][selectedSquare[1]].setLabel("O");
    }

    // Проверяем каждый ряд, колонку и диагональ чтобы узнать, есть ли в ней две клетки с одинаковыми надписями и
    // пустой клеткой. В метод передается X – для пользователя, O – для компьютера. Метод возвращает номер свободной
    // клетки или -1, если не найдено две клетки с одинаковыми надписями
    int []findEmptySquare(String player) {
        int weight[][] = new int[3][3];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (squares[i][j].getLabel().equals("O")) {
                    weight[i][j] = -1;
                } else if (squares[i][j].getLabel().equals("X")) {
                    weight[i][j] = 1;
                } else {
                    weight[i][j] = 0;
                }
            }
        }

        int twoWeights = player.equals("O") ? -2 : 2;

        // Проверим, есть ли в ряду 1 две одинаковые клетки и одна пустая.
        if (weight[0][0] + weight[0][1] + weight[0][2] == twoWeights) {
            if (weight[0][0] == 0) {
                return new int[]{0, 0};
            } else if (weight[0][1] == 0) {
                return new int[]{0, 1};
            } else {
                return new int[]{0, 2};
            }
        }
        // Проверим, есть ли в ряду 2 две одинаковые клетки и одна пустая.
        if (weight[1][0] + weight[1][1] + weight[1][2] == twoWeights) {
            if (weight[1][0] == 0) {
                return new int[]{1, 0};
            } else if (weight[1][1] == 0) {
                return new int[]{1, 1};
            } else {
                return new int[]{1, 2};
            }
        }
        // Проверим, есть ли в ряду 3 две одинаковые клетки и одна пустая.
        if (weight[2][0] + weight[2][1] + weight[2][2] == twoWeights) {
            if (weight[2][0] == 0) {
                return new int[]{2, 0};
            } else if (weight[2][1] == 0) {
                return new int[]{2, 1};
            } else {
                return new int[]{2, 2};
            }
        }
        // Проверим, есть ли в колонке 1 две одинаковые клетки и одна пустая.
        if (weight[0][0] + weight[1][0] + weight[2][0] == twoWeights) {
            if (weight[0][0] == 0) {
                return new int[]{0, 0};
            } else if (weight[1][0] == 0) {
                return new int[]{1, 0};
            } else {
                return new int[]{2, 0};
            }
        }
        // Проверим, есть ли в колонке 2 две одинаковые клетки и одна пустая.
        if (weight[0][1] + weight[1][1] + weight[2][1] == twoWeights) {
            if (weight[0][1] == 0) {
                return new int[]{0, 1};
            } else if (weight[1][1] == 0) {
                return new int[]{1, 1};
            } else {
                return new int[]{2, 1};
            }
        }
        // Проверим, есть ли в колонке 3 две одинаковые клетки и одна пустая.
        if (weight[0][2] + weight[1][2] + weight[2][2] == twoWeights) {
            if (weight[0][2] == 0) {
                return new int[]{0, 2};
            } else if (weight[1][2] == 0) {
                return new int[]{1, 2};
            } else {
                return new int[]{2, 2};
            }
        }
        // Проверим, есть ли в диагонали 1 две одинаковые клетки и одна пустая.
        if (weight[0][0] + weight[1][1] + weight[2][2] == twoWeights) {
            if (weight[0][0] == 0) {
                return new int[]{0, 0};
            } else if (weight[1][1] == 0) {
                return new int[]{1, 1};
            } else {
                return new int[]{2, 2};
            }
        }
        // Проверим, есть ли в диагонали 2 две одинаковые клетки и одна пустая.
        if (weight[0][2] + weight[1][1] + weight[2][0] == twoWeights) {
            if (weight[0][2] == 0) {
                return new int[]{0, 2};
            } else if (weight[1][1] == 0) {
                return new int[]{1, 1};
            } else {
                return new int[]{2, 0};
            }
        }
        // В случае, если не найдено двух одинаковых соседних клеток
        return new int[]{-1, -1};
    } // конец метода findEmptySquare()

    // Выбираем любую пустую клетку и возвращаем вызвавшему методу случайно выбранный номер клетки
    int []getRandomSquare() {
        boolean gotEmptySquare = false;
        int []selectedSquare = {-1, -1};
        do {
            selectedSquare[0] = (int) (Math.random() * 3);
            selectedSquare[1] = (int) (Math.random() * 3);
            if (squares[selectedSquare[0]][selectedSquare[1]].getLabel().equals("")) {
                gotEmptySquare = true;
            }
        } while (!gotEmptySquare);

        return selectedSquare;
    } // конец метода getRandomSquare()

    // Выделяем выигравшую линию. Первая, вторая и третья клетки для выделения
    void highlightWinner (int[] win1, int[] win2, int[] win3) {
        squares[win1[0]][win1[1]].setBackground(Color.CYAN);
        squares[win2[0]][win2[1]].setBackground(Color.CYAN);
        squares[win3[0]][win3[1]].setBackground(Color.CYAN);
    }

    // Делаем недоступными клетки и доступной кнопку ”New Game”
    void endTheGame () {
        newGameButton.setEnabled(true);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j].setEnabled(false);
            }
        }
    }
}
