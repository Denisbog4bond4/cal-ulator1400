import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField inputScreen;
    private double result;
    private String operator;
    private boolean isOperationClicked;

    public Calculator() {
        result = 0;
        operator = "";
        isOperationClicked = false;

        createUI();
    }

    private void createUI() {
        inputScreen = new JTextField();
        inputScreen.setEditable(false);
        add(inputScreen, "North");

        JPanel panel = new JPanel();
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);

        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (!isOperationClicked) {
                inputScreen.setText(inputScreen.getText() + command);
            } else {
                inputScreen.setText(command);
                isOperationClicked = false;
            }
        } else if (command.charAt(0) == 'C') {
            inputScreen.setText("");
            result = 0;
            operator = "";
        } else if (command.charAt(0) == '=') {
            calculate(Double.parseDouble(inputScreen.getText()));
            inputScreen.setText("" + result);
            operator = "";
        } else {
            if (!operator.isEmpty()) {
                calculate(Double.parseDouble(inputScreen.getText()));
                inputScreen.setText("" + result);
            } else {
                result = Double.parseDouble(inputScreen.getText());
            }
            operator = command;
            isOperationClicked = true;
        }
    }

    private void calculate(double secondOperand) {
        switch (operator) {
            case "+":
                result += secondOperand;
                break;
            case "-":
                result -= secondOperand;
                break;
            case "*":
                result *= secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result /= secondOperand;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
