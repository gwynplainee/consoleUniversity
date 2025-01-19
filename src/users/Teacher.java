package users;

import database.Data;
import enums.*;
import utils.*;

import java.time.Year;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

public class Teacher extends Employee{
    private DegreeType degree;
    private School school;
    private Vector<Integer> ratings;

    public Teacher(){
        super();
    }

    public Teacher(String firstName, String lastName, School school){
        super(firstName, lastName);

    }

    public Teacher(String firstName, String lastName, School school, DegreeType degree){
        super(firstName,lastName);
        this.school = school;
        this.degree = degree;

    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public DegreeType getDegreeType() {
        return degree;
    }

    public void setDegreeType(DegreeType degree) {
        this.degree = degree;
    }

    public Vector<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Vector<Integer> rating) {
        this.ratings = rating;
    }

    public Vector<Course> viewCourses() {
        Vector<Course> myCourses = new Vector<>();

        for (Course course : Data.INSTANCE.getCourses()) {
            if (course.getYear() == Data.INSTANCE.getYear() && course.getPeriod() == Data.INSTANCE.getPeriod()) {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getInstructor().equals(this) && !myCourses.contains(course)) myCourses.add(course);
                }
            }
        }
        return myCourses; 
    }

    public Vector<String> getCoursesDescriptions() {
        Vector<Course> courses = this.viewCourses();
        return courses.stream()
                .map(Course::toString)
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<Student> viewStudents() {
        Vector<Student> myStudents = new Vector<>();
        for (Course course : viewCourses()) {
            for (Student student : course.getEnrolledStudents()) {
                if (!myStudents.contains(student)) {
                    myStudents.add(student);
                }
            }
        }
        return myStudents;
    }

    public Vector<String> getStudentsDescriptions() {
        Vector<Student> students = this.viewStudents();
        return students.stream()
                .map(Student::toString)
                .collect(Collectors.toCollection(Vector::new));
    }

    public Optional<Student> findStudentByIndex(int index) {
        Vector<Student> students = this.viewStudents();
        if (index < 0 || index >= students.size()) {
            return Optional.empty();
        }
        return Optional.of(students.get(index));
    }

    public boolean putMark(Student student, Course course, double mark, GradeType gradeType) {
        if (!course.getEnrolledStudents().contains(student)) {
            return false;
        }

        Mark marks = course.getStudentMark().get(student);
        if (marks == null) {
            marks = new Mark();
            course.getStudentMark().put(student, marks);
        }

        switch (gradeType) {
            case FIRST_ATT -> marks.setFirstAttestation(mark);
            case SECOND_ATT -> marks.setSecondAttestation(mark);
            case FINAL -> marks.setFinalExam(mark);
            default -> throw new IllegalArgumentException("Invalid grade type.");
        }

        return true;
    }

    public boolean sendComplaint(Student student, Complaint complaint) {
        School studentSchool = student.getSchool();
        int joiningYear = student.getEnrolledYear();
        int currentYear = Year.now().getValue();

        ManagerType managerType;
        if (joiningYear == currentYear) {
            managerType = ManagerType.DEAN_1ST;
        } else if (joiningYear == currentYear - 1) {
            managerType = ManagerType.DEAN_2ND;
        } else if (joiningYear == currentYear - 2) {
            managerType = ManagerType.DEAN_3RD;
        } else {
            managerType = ManagerType.DEAN_4TH;
        }

        Optional<Manager> responsibleManager = Data.INSTANCE.getManagers().stream()
                .filter(m -> m.getManagerType() == managerType)
                .filter(m -> m.getSchool().equals(studentSchool))
                .findFirst();

        if (responsibleManager.isPresent()) {
            responsibleManager.get().getComplaints().add(complaint);
            return true;
        } else {
            return false;
        }
    }

    public double getTeacherRating() {
        double sum = ratings.stream().mapToDouble(Integer::doubleValue).sum();
        return ratings.isEmpty() ? 0.0 : sum / ratings.size();
    }
    
    public void sendRequest(String content) {      
        Dean dean = Data.INSTANCE.getSchoolDeans().get(school);
        if (dean == null) {
            throw new IllegalArgumentException("No dean found");
        }

        Request request = new Request(content, this, dean);
        dean.getRequests().add(request);
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher other = (Teacher) obj;

        if (!super.equals(other)) return false;
        return Objects.equals(degree, other.degree) &&
                Objects.equals(school, other.school) &&
                Objects.equals(ratings, other.ratings);
    }
}
