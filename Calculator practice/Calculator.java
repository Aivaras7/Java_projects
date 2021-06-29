import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculator {

    private static final int calculatorWidth = 410; // Calculator frame width variable that can be modified if wanted as
    // the value is reused in the code
    private static final int calculatorHeight = 600; // Calculator frame height
    private static final int buttonWidth = 80; // Button width
    private static final int buttonHeight = 70; // Button Height
    private static final int marginX = 20; // X margin
    private static final int marginY = 60; // Y margin
    private static final int inputWidth = 350; // Calculation display width
    private static final int inputHeight = 70; // Calculation display height

    private static boolean scientificView = false; // Boolean for scientific button

    private JFrame calculatorFrame; // Main frame for the calculator
    private JComboBox<String> cBoxTheme; // Combo boxes to select Calculator theme
    private JTextField inText; // Display to show the calculation
    private JButton btnClear, btnScientific, btnBack, btnMod, btnDiv, btnMultiply, btnSubtract, btnAdd, btn0, btn1,
            btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnEqual, btnRoot, btnPower, btnLog; // Buttons
                                                                                                           // for the
                                                                                                           // calculator

    private char operator = ' '; // Save operator
    private boolean go = true; // Boo lean expression in order to calculate with operator !=(=)
    private boolean write = true; // Adding(connecting) numbers together on the display
    private double val = 0; // Save the value typed for calculation

    public Calculator() {

        calculatorFrame = new JFrame("Calculator"); // Creating a frame for calculator and naming it
        calculatorFrame.setSize(calculatorWidth, calculatorHeight); // Calculator size accordingly to the dimensions
        calculatorFrame.setLocationRelativeTo(null); // Centering the frame

        cBoxTheme = initCombo(new String[] { "Simple", "Colored", "DarkTheme" }, 230, 30, "Theme",
                themeSwitchEventConsumer); // Creating a combo box for the calculator themes

        int[] x = { marginX, marginX + 90, 200, 290, 380 }; // Creating an x axis arrayy in order to locate the buttons
                                                            // inside the calculator window
        int[] y = { marginY, marginY + 100, marginY + 180, marginY + 260, marginY + 340, marginY + 420 }; // Y axis

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], inputWidth, inputHeight); // Placing the calculator display with x and y arrays
        inText.setEditable(false);
        inText.setBackground(null);
        inText.setBorder(null);
        inText.setForeground(Color.BLACK);
        inText.setFont(new Font("Arial", Font.PLAIN, 33));
        calculatorFrame.add(inText);

        btnClear = initBtn("C", x[0], y[1], event -> {

            inText.setText("0");
            operator = ' ';
            val = 0;
        });

        btnScientific = initBtn("Scientific", 20, 32, event -> {

            if (scientificView == false) {
                calculatorFrame.setSize(calculatorWidth + 80, calculatorHeight); // Adding extra length to the
                                                                                 // calculator frame so buttons will be
                                                                                 // visible
                inText.setSize(inputWidth + 80, inputHeight); // More display space
                btnRoot.setVisible(true); // Making root button show up
                btnPower.setVisible(true); // Power
                btnLog.setVisible(true); // Log
                scientificView = true; // Scientific view is shown = true

            }

            else if (scientificView == true) {
                inText.setSize(inputWidth, inputHeight);
                calculatorFrame.setSize(calculatorWidth, calculatorHeight);
                btnRoot.setVisible(false);
                btnPower.setVisible(false);
                btnLog.setVisible(false);
                scientificView = false;
            }
        });

        btnScientific.setFont(new Font("Arial", Font.PLAIN, 10)); // Lowering font size of scientific button
        btnScientific.setSize(buttonWidth + 15, buttonHeight / 3); // Button size

        btnBack = initBtn("<-", x[1], y[1], event -> {
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });

        btnMod = initBtn("%", x[2], y[1], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '%';
                    go = false;
                    write = false;
                }
        });

        btnDiv = initBtn("/", x[3], y[1], event -> {

            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '/';
                    go = false;
                    write = false;
                } else {
                    operator = '/';
                }
        });

        btn7 = initBtn("7", x[0], y[2], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("7");
                } else {
                    inText.setText(inText.getText() + "7");
                }
            } else {
                inText.setText("7");
                write = true;
            }
            go = true;
        });

        btn8 = initBtn("8", x[1], y[2], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("8");
                } else {
                    inText.setText(inText.getText() + "8");
                }
            } else {
                inText.setText("8");
                write = true;
            }
            go = true;
        });

        btn9 = initBtn("9", x[2], y[2], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            } else {
                inText.setText("9");
                write = true;
            }
            go = true;
        });

        btnMultiply = initBtn("*", x[3], y[2], event -> {

            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '*';
                    go = false;
                    write = false;
                } else {
                    operator = '*';
                }
        });

        btn4 = initBtn("4", x[0], y[3], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                write = true;
            }
            go = true;
        });

        btn5 = initBtn("5", x[1], y[3], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                write = true;
            }
            go = true;
        });

        btn6 = initBtn("6", x[2], y[3], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                write = true;
            }
            go = true;
        });

        btnSubtract = initBtn("-", x[3], y[3], event -> {

            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }

                    operator = '-';
                    go = false;
                    write = false;
                } else {
                    operator = '-';
                }
        });

        btn1 = initBtn("1", x[0], y[4], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                write = true;
            }
            go = true;
        });

        btn2 = initBtn("2", x[1], y[4], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                write = true;
            }
            go = true;
        });

        btn3 = initBtn("3", x[2], y[4], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                write = true;
            }
            go = true;
        });

        btnAdd = initBtn("+", x[3], y[4], event -> {

            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '+';
                    go = false;
                    write = false;
                } else {
                    operator = '+';
                }
        });

        btnPoint = initBtn(".", x[0], y[5], event -> {

            if (write) {
                if (!inText.getText().contains(".")) {
                    inText.setText(inText.getText() + ".");
                }
            } else {
                inText.setText("0.");
                write = true;
            }
            go = true;
        });

        btn0 = initBtn("0", x[1], y[5], event -> {

            if (write) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                write = true;
            }
            go = true;
        });

        btnEqual = initBtn("=", x[2], y[5], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    if (inText.getText().toString().equals("1337")) {
                        Font easterEgg = new Font("Courier New", Font.BOLD, 20);
                        inText.setBackground(Color.BLACK);
                        inText.setForeground(Color.GREEN);
                        inText.setFont(easterEgg);
                    }
                    operator = '=';
                    write = false;
                }
        });

        btnEqual.setSize(2 * buttonWidth + 10, buttonHeight);

        btnRoot = initBtn("√", x[4], y[1], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = Math.sqrt(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '√';
                    write = false;
                }
        });
        btnRoot.setVisible(false);

        btnPower = initBtn("pow", x[4], y[2], event -> {

            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), operator);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = '^';
                    go = false;
                    write = false;
                } else {
                    operator = '^';
                }
        });
        btnPower.setFont(new Font("Arial", Font.PLAIN, 24));
        btnPower.setVisible(false);

        btnLog = initBtn("ln", x[4], y[3], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = Math.log(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    operator = 'l';
                    write = false;
                }
        });

        btnEqual.setSize(2 * buttonWidth + 10, buttonHeight);

        btnLog.setVisible(false);

        calculatorFrame.setLayout(null);
        calculatorFrame.setResizable(false);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        calculatorFrame.setVisible(true);
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        calculatorFrame.add(combo);

        return combo;
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, buttonWidth, buttonHeight);
        btn.setFont(new Font("Arial", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        calculatorFrame.add(btn);

        return btn;
    }

    public double calc(double x, String input, char operator) {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            case '%':
                return x % y;
            case '^':
                return Math.pow(x, y);
            default:
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    private Consumer<ItemEvent> themeSwitchEventConsumer = event -> {

        JButton[] numberButtons = new JButton[] { btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9 };
        JButton[] functionButtons = new JButton[] { btnClear, btnBack, btnMod, btnDiv, btnMultiply, btnSubtract, btnAdd,
                btnEqual, btnLog, btnPower, btnRoot, btnPoint, btnScientific };

        if (event.getStateChange() != ItemEvent.SELECTED)
            return;

        String selectedTheme = (String) event.getItem();
        switch (selectedTheme) {
            case "Simple":
                calculatorFrame.getContentPane().setBackground(null);

                for (JButton nButtons : numberButtons) {
                    nButtons.setBackground(null);
                    nButtons.setForeground(Color.BLACK);
                    nButtons.setBorderPainted(true);
                }

                for (JButton fButtons : functionButtons) {
                    fButtons.setBackground(null);
                    fButtons.setForeground(Color.BLACK);
                    fButtons.setBorderPainted(true);
                }

                inText.setBackground(null);
                inText.setBorder(null);
                inText.setForeground(Color.BLACK);

                cBoxTheme.setBackground(null);
                cBoxTheme.setBorder(null);
                cBoxTheme.setForeground(Color.BLACK);

                break;

            case "Colored":

                final Color bgColor = new Color(248, 118, 128);
                final Color lineColor = new Color(212, 47, 60);
                calculatorFrame.getContentPane().setBackground(bgColor);

                for (JButton fButtons : functionButtons) {
                    fButtons.setBackground(null);
                    fButtons.setForeground(Color.WHITE);
                    fButtons.setBorderPainted(false);

                }

                for (JButton nButtons : numberButtons) {
                    nButtons.setBackground(null);
                    nButtons.setForeground(Color.WHITE);
                    nButtons.setBorderPainted(false);

                }
                inText.setBackground(null);
                inText.setForeground(Color.WHITE);
                inText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lineColor));

                cBoxTheme.setBackground(null);
                cBoxTheme.setForeground(Color.WHITE);

                break;

            case "DarkTheme":

                final Color primaryDarkColor = new Color(32, 32, 32);
                final Color secondaryDarkColor = new Color(225, 225, 225);

                calculatorFrame.getContentPane().setBackground(new Color(18, 18, 18));

                btnPoint.setBackground(secondaryDarkColor);

                for (JButton nButtons : numberButtons) {
                    nButtons.setForeground(secondaryDarkColor);
                    nButtons.setBackground(primaryDarkColor);
                    nButtons.setBorderPainted(false);
                }

                for (JButton fButtons : functionButtons) {
                    fButtons.setForeground(secondaryDarkColor);
                    fButtons.setBackground(primaryDarkColor);
                    fButtons.setBorderPainted(false);
                }

                cBoxTheme.setForeground(secondaryDarkColor);
                cBoxTheme.setBackground(primaryDarkColor);

                inText.setForeground(secondaryDarkColor);
                inText.setBackground(primaryDarkColor);
        }
    };

    public static void main(String[] args) {
        new Calculator();
    }
}