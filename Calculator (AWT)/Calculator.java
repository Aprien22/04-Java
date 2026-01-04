import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame; 
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JTextField;

public class Calculator extends Frame implements ActionListener {

    JTextField display;
    String operator = "";
    BigDecimal first = BigDecimal.ZERO;
    boolean startNewNumber = true; // Flag to start new number after operator

    Calculator() {
        // Frame title
        super("AWT Calculator");

        // Display
        display = new JTextField("0");

        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(5, 4, 4, 4));

        String[] buttons = {
            "CE", "C", "Back", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        ActionListener listener = this;
        for (String text : buttons) {
            Button b = new Button(text);
            b.setFont(new Font("Arial", Font.BOLD, 18));
            b.addActionListener(listener);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);
        setSize(350, 450);
        setVisible(true);

        // Close window properly
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            // Number input
            if (cmd.matches("[0-9]")) {
                if (startNewNumber || display.getText().equals("0")) {
                    display.setText(cmd);
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + cmd);
                }
            }

            // Decimal
            else if (cmd.equals(".")) {
                if (startNewNumber) {
                    display.setText("0.");
                    startNewNumber = false;
                } else if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }

            // Operators
            else if ("+-×÷".contains(cmd)) {
                computeIntermediate();
                operator = cmd;
                startNewNumber = true;
            }

            // Equals
            else if (cmd.equals("=")) {
                computeIntermediate();
                operator = "";
                startNewNumber = true;
            }

            // Clear all
            else if (cmd.equals("C")) {
                display.setText("0");
                first = BigDecimal.ZERO;
                operator = "";
                startNewNumber = true;
            }

            // Clear entry
            else if (cmd.equals("CE")) {
                display.setText("0");
                startNewNumber = true;
            }

            // Backspace
            else if (cmd.equals("Back")) {
                String t = display.getText();
                if (t.length() > 1) {
                    display.setText(t.substring(0, t.length() - 1));
                } else {
                    display.setText("0");
                    startNewNumber = true;
                }
            }

            // Plus/Minus
            else if (cmd.equals("+/-")) {
                BigDecimal n = new BigDecimal(display.getText());
                display.setText(n.negate().toPlainString());
            }

        } catch (Exception ex) {
            display.setText("Error");
            startNewNumber = true;
        }
    }

    // Compute intermediate result for continuous operations
    private void computeIntermediate() {
        BigDecimal current = new BigDecimal(display.getText());

        if (!operator.isEmpty()) {
            switch (operator) {
                case "+":
                    first = first.add(current);
                    break;
                case "-":
                    first = first.subtract(current);
                    break;
                case "×":
                    first = first.multiply(current);
                    break;
                case "÷":
                    if (current.compareTo(BigDecimal.ZERO) == 0) {
                        display.setText("Error");
                        first = BigDecimal.ZERO;
                        operator = "";
                        startNewNumber = true;
                        return;
                    }
                    first = first.divide(current, 20, RoundingMode.HALF_UP);
                    break;
            }
            display.setText(first.stripTrailingZeros().toPlainString());
        } else {
            first = current;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
