package utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import users.Student;

public class Transcript implements Serializable {
    private Student student;
    private Map<Course, Mark> courseMarks = new HashMap<>();

    public Transcript(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void addCourseWithMark(Course course, Mark mark) {
        courseMarks.put(course, mark);
    }

    public Map<Course, Mark> getCourseMarks() {
        return courseMarks;
    }

    public double calculateGPA() {
        if (courseMarks.isEmpty()) return 0.0;

        double totalGPA = 0.0;
        int totalCredits = 0;

        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();
            double gpa = mark.getGPA();

            totalGPA += gpa * course.getCredits();
            totalCredits += course.getCredits();
        }

        return totalCredits > 0 ? totalGPA / totalCredits : 0.0;
    }

    public String getFormattedTranscript() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
        sb.append("====================================\n");
        sb.append("| Course Code | Course Name       | Credits | Mark | GPA |\n");
        sb.append("----------------------------------------------------\n");

        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();

            sb.append(String.format("| %-11s | %-17s | %-7d | %-4.1f | %-3.2f |\n",
                    course.getCode(),
                    course.getCourseName(),
                    course.getCredits(),
                    mark.getTotalMark(),
                    mark.getGPA()));
        }

        sb.append("====================================\n");
        sb.append("Overall GPA: ").append(String.format("%.2f", calculateGPA())).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transcript other = (Transcript) obj;
        return Objects.equals(student, other.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student);
    }
}

