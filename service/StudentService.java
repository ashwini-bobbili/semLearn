package service;

import model.Student;
import java.io.*;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    private final String FILE_NAME = "students.dat";

    public StudentService() {
        loadStudents();
    }

    public boolean registerStudent(Student student) {

        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(
                    student.getStudentId())
                    || s.getEmail().equalsIgnoreCase(
                    student.getEmail())) {
                return false;
            }
        }

        students.add(student);
        saveStudents();
        return true;
    }

    public Student login(String studentId, String password) {

        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(studentId)
                    && s.getPassword().equals(password)) {
                return s;
            }
        }

        return null;
    }

    private void saveStudents() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                         new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: "
                               + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(
                         new FileInputStream(file))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: "
                               + e.getMessage());
        }
    }
}
