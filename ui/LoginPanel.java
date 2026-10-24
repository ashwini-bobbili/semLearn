
package ui;

import javax.swing.*;
import java.awt.*;
import model.Student;
import service.StudentService;

public class LoginPanel extends JPanel {

    public LoginPanel(
            JFrame frame,
            StudentService studentService,
            JPanel mainPanel,
            CardLayout cardLayout) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
                "Student Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JTextField idField = new JTextField(18);
        JPasswordField passwordField = new JPasswordField(18);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Create Account");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridy = 4;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {

            String id = idField.getText().trim();
            String password =
                    new String(passwordField.getPassword());

            Student student =
                    studentService.login(id, password);

            if (student != null) {

                DashboardPanel dashboard =
                        new DashboardPanel(student);

                mainPanel.add(dashboard, "DASHBOARD");
                cardLayout.show(mainPanel, "DASHBOARD");

            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Student ID or Password!");
            }
        });

        registerButton.addActionListener(e ->
                cardLayout.show(mainPanel, "REGISTER"));
    }
}
