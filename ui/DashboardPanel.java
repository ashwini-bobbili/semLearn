
package ui;

import javax.swing.*;
import java.awt.*;
import model.Student;

public class DashboardPanel extends JPanel {

    public DashboardPanel(Student student) {

        setLayout(new BorderLayout(15, 15));

        // Top section
        JPanel header = new JPanel(new BorderLayout());

        JLabel title = new JLabel("SemLearn Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel welcome = new JLabel(
                "Welcome, " + student.getName() + "  ");

        header.add(title, BorderLayout.WEST);
        header.add(welcome, BorderLayout.EAST);

        // Subject section
        JPanel subjects = new JPanel(new GridLayout(2, 2, 15, 15));
        subjects.setBorder(
                BorderFactory.createTitledBorder("My Semester Subjects"));

        String[] subjectNames = {
            "Java Programming",
            "Database Management",
            "Data Structures",
            "Computer Networks"
        };

        for (String subject : subjectNames) {
            JButton button = new JButton(subject);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            button.addActionListener(e ->
                    JOptionPane.showMessageDialog(
                            this,
                            subject + " lessons will be added next!"));

            subjects.add(button);
        }

        // Add sections
        add(header, BorderLayout.NORTH);
        add(subjects, BorderLayout.CENTER);
    }
}
