import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class Main extends JFrame {

    private JTextField passwordField;
    private JTextField lengthField;
    private JCheckBox upperCaseBox, lowerCaseBox, numbersBox, symbolsBox;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?/";

    public Main() {
        setTitle("Password Generator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Password display field
        passwordField = new JTextField();
        passwordField.setEditable(false);
        add(passwordField);

        // Length input field
        JPanel lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("Password Length:"));
        lengthField = new JTextField("8", 5);
        lengthPanel.add(lengthField);
        add(lengthPanel);

        // Options
        upperCaseBox = new JCheckBox("Uppercase");
        lowerCaseBox = new JCheckBox("Lowercase");
        numbersBox = new JCheckBox("Numbers");
        symbolsBox = new JCheckBox("Symbols");

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(upperCaseBox);
        optionsPanel.add(lowerCaseBox);
        optionsPanel.add(numbersBox);
        optionsPanel.add(symbolsBox);
        add(optionsPanel);

        // Generate button
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        add(generateButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generatePassword() {
        StringBuilder charPool = new StringBuilder();

        if (upperCaseBox.isSelected()) charPool.append(UPPER);
        if (lowerCaseBox.isSelected()) charPool.append(LOWER);
        if (numbersBox.isSelected()) charPool.append(NUMBERS);
        if (symbolsBox.isSelected()) charPool.append(SYMBOLS);

        if (charPool.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one option!");
            return;
        }

        int length;
        try {
            length = Integer.parseInt(lengthField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid length!");
            return;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        passwordField.setText(password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
