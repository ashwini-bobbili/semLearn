
package ui;

import javax.swing.*;
import java.awt.*;
import model.Student;
import service.StudentService;

public class RegisterPanel extends JPanel {
    public RegisterPanel(
        JFrame frame,
        StudentService studentService,
        JPanel mainPanel,
        CardLayout cardLayout) {
    
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
                "Create Your SemLearn Account",
                SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Already registered? Login");

        addRow(this, gbc, 0, title);
        addField(this, gbc, 1, "Full Name:", nameField);
        addField(this, gbc, 2, "Student ID:", idField);
        addField(this, gbc, 3, "Email:", emailField);
        addField(this, gbc, 4, "Password:", passwordField);
        addField(this, gbc, 5, "Confirm Password:", confirmField);
        addRow(this, gbc, 6, registerButton);
        addRow(this, gbc, 7, loginButton);

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (name.isEmpty() || id.isEmpty()
                    || email.isEmpty() || password.isEmpty()
                    || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame, "Please fill in all fields.");
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(
                        frame, "Please enter a valid email.");
                return;
            }

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(
                        frame, "Passwords do not match.");
                return;
            }

            Student student = new Student(
                    name, id, email, password);

            if (studentService.registerStudent(student)) {
                JOptionPane.showMessageDialog(
                        frame, "Registration successful! Please log in.");
                        cardLayout.show(mainPanel, "LOGIN");

                nameField.setText("");
                idField.setText("");
                emailField.setText("");
                passwordField.setText("");
                confirmField.setText("");

                // The login screen will be connected next.
            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Student ID or email already exists.");
            }
        });
        loginButton.addActionListener(e ->
        cardLayout.show(mainPanel, "LOGIN"));
        
    }

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent field) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        panel.add(component, gbc);
        gbc.gridwidth = 1;
    }
}
