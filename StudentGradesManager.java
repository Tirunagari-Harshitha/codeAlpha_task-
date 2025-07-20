import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradesManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Input student data
        for (int i = 0; i < num; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter grade of " + name + ": ");
            int grade = scanner.nextInt();
            scanner.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Initialize summary variables
        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String topStudent = "";
        String lowStudent = "";

        // Process data
        for (Student s : students) {
            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }

            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }
        }

        double average = (double) total / students.size();

        // Display summary
        System.out.println("\n--- Student Grades Summary ---");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Highest Grade: " + highest + " (" + topStudent + ")");
        System.out.println("Lowest Grade : " + lowest + " (" + lowStudent + ")");
        System.out.println("\nDetailed Report:");
        for (Student s : students) {
            System.out.println(s.name + ": " + s.grade);
        }

        scanner.close();
    }
}