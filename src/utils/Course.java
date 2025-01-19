package utils;

import database.Data;
import enums.CourseType;
import enums.School;
import enums.Period;
import users.Student;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

public class Course implements Serializable, Comparable<Course> {
    private String code;
    private String courseName;
    private int credits;
    private HashMap<School, CourseType> courseType;
    
    private Vector<Lesson> lessons = new Vector<>();
    private Period period;
    private int year;
    private HashMap<Student, Mark> studentMark = new HashMap<>();
    private int studentLimit;

    public Course() {
        
    }

    public Course(String code, String courseName, int credits, HashMap<School, CourseType> courseType, int limit) {
        this.code = code;
        this.courseName = courseName;
        this.credits = credits;
        this.courseType = courseType;
        this.studentLimit = limit;
        this.period = Data.INSTANCE.getPeriod();
        this.year = Data.INSTANCE.getYear();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public HashMap<School, CourseType> getCourseType() {
        return courseType;
    }

    public void setCourseType(HashMap<School, CourseType> courseType) {
        this.courseType = courseType;
    }

    public Vector<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Vector<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public HashMap<Student, Mark> getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(HashMap<Student, Mark> studentMark) {
        this.studentMark = studentMark;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }

    public Vector<Student> getEnrolledStudents() {
        Vector<Student> students = new Vector<>();
        students.addAll(studentMark.keySet());
        return students;
    }
    

    
    public void displayReport() {
        System.out.println("Report for Course: " + courseName + " (" + code + ")");
        System.out.println("===================================================");
        System.out.println("| Student Name      | Total Mark | GPA  | Grade   |");
        System.out.println("---------------------------------------------------");

        for (Map.Entry<Student, Mark> entry : studentMark.entrySet()) {
            Student student = entry.getKey();
            Mark mark = entry.getValue();
            String studentName = student.getFirstName() + " " + student.getLastName();
            double totalMark = mark.getTotalMark();
            double gpa = mark.getGPA();
            String letterGrade = mark.getLetterGPA();

            System.out.printf("| %-18s | %-10.2f | %-4.2f | %-7s |\n", 
                              studentName, totalMark, gpa, letterGrade);
        }

        System.out.println("===================================================");
    }


    @Override
    public int compareTo(Course o) {
        if (year == o.getYear()) {
            return -period.compareTo(o.getPeriod());
        }
        return -Integer.compare(year, o.getYear());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "|" + code + "| " + courseName;
    }
}
