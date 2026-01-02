import javax.swing.JOptionPane;

public class Calculator {

    public static void main(String[] args) {
        
        String firstNum = JOptionPane.showInputDialog("Enter First Number: ");
        String secondNum = JOptionPane.showInputDialog("Enter Second Number: ");

        int first = Integer.parseInt(firstNum);
        int second = Integer.parseInt(secondNum);

        int sum = first + second;

        JOptionPane.showMessageDialog(null, "The sum of " + first + " and " + second + " is " + sum, "Sum of Two Integers", JOptionPane.PLAIN_MESSAGE);

    }
}