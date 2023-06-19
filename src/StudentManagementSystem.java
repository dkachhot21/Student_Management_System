import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;
    private final int pass_percent = 40;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public List<Student> searchStudent(String query) {
        List<Student> searchResults = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(query) || String.valueOf(student.getRollNumber()).equals(query)) {
                searchResults.add(student);
            }
        }

        return searchResults;
    }

    public Student getStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        return null;
    }

    public boolean deleteStudent(int rollNumber) {
        Student student = getStudentByRollNumber(rollNumber);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    public String calculateStatistics() {
        int totalStudents = students.size();
        int totalMarks = calculateTotalMarks(students);
        double averageMarks = calculateAverageMarks(students);
        int highestMarks = calculateHighestMarks(students);
        int lowestMarks = calculateLowestMarks(students);
        int total_pass_student = calculate_pass_student(students);

        String statistics = "Total Students: " + totalStudents + "\n" +
                "Total Marks: " + totalMarks + "\n" +
                "Average Marks: " + averageMarks + "\n" +
                "Highest Marks: " + highestMarks + "\n" +
                "Lowest Marks: " + lowestMarks + "\n"+
                "Total Passed Students: "+total_pass_student+"\n"+
                "Pass percentage: "+cal_pass_percent(students)+"%";

        return statistics;
    }

    double cal_pass_percent(List<Student> students){
        double pass_percent=calculate_pass_student(students);

        return pass_percent*100/students.size();
    }

    int calculate_pass_student(List<Student> students) {
        int passed = 0;
        for (Student student : students) {
            int totalMark = student.getTotalMarks();
            int maxMark = student.getSubjectNames().length * 100;
            double percentage = (double) totalMark / maxMark * 100;
            if (percentage >= pass_percent) {
                passed += 1;
            }
        }
        return passed;
    }

    int calculateTotalMarks(List<Student> students) {
        int totalMarks = 0;
        for (Student student : students) {
            totalMarks += student.getTotalMarks();
        }
        return totalMarks;
    }

    double calculateAverageMarks(List<Student> students) {
        int totalMarks = calculateTotalMarks(students);
        return (double) totalMarks / students.size();
    }

    int calculateHighestMarks(List<Student> students) {
        int highestMarks = 0;
        for (Student student : students) {
            if (student.getTotalMarks() > highestMarks) {
                highestMarks = student.getTotalMarks();
            }
        }
        return highestMarks;
    }

    int calculateLowestMarks(List<Student> students) {
        int lowestMarks = Integer.MAX_VALUE;
        for (Student student : students) {
            if (student.getTotalMarks() < lowestMarks) {
                lowestMarks = student.getTotalMarks();
            }
        }
        return lowestMarks;
    }
}
