
import javax.swing.*;
import java.awt.*;

import service.StudentService;
import ui.RegisterPanel;
import ui.LoginPanel;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("SemLearn");
            frame.setSize(700, 550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            StudentService studentService = new StudentService();

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // Welcome screen
            JPanel welcomePanel = new JPanel(
                    new GridLayout(3, 1, 10, 10));

            welcomePanel.setBorder(
                    BorderFactory.createEmptyBorder(
                            70, 80, 70, 80));

            JLabel title = new JLabel(
                    "Welcome to SemLearn",
                    SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 28));

            JLabel subtitle = new JLabel(
                    "Your Semester Learning Companion",
                    SwingConstants.CENTER);

            JButton startButton = new JButton("Get Started");

            welcomePanel.add(title);
            welcomePanel.add(subtitle);
            welcomePanel.add(startButton);

            // Add the screens
            mainPanel.add(welcomePanel, "WELCOME");

            mainPanel.add(
                    new RegisterPanel(frame, studentService, mainPanel, cardLayout),
                    "REGISTER");

            mainPanel.add(
                    new LoginPanel(
                            frame, studentService,
                            mainPanel, cardLayout),
                    "LOGIN");

            // Welcome button opens registration
            startButton.addActionListener(e ->
                    cardLayout.show(mainPanel, "REGISTER"));

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
