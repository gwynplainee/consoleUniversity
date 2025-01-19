
package users;

import java.util.List;

import enums.School;
import enums.StudentType;

import research.ResearchPaper;
import research.Researcher;
import research.ResearcherMethods;

public class GraduateStudent extends Student implements Researcher {

    private static final long serialVersionUID = 1L;
    private StudentType studentType;

    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String firstName, String lastName){
        super(firstName,lastName);
    }

    public GraduateStudent(String firstName, String lastName, School school, int enrolledYear, StudentType studentType) {
        super(firstName, lastName);
        if (studentType == StudentType.BACHELOR) {
            throw new IllegalArgumentException("GraduateS student cannot have a bachelor degree");
        }
        setSchool(school);
        setEnrolledYear(enrolledYear);
        this.studentType = studentType;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        if (studentType == StudentType.BACHELOR) {
            throw new IllegalArgumentException("Graduate student cannot have a bachelor degree");
        }
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " is a graduate student pursuing a " + studentType + " degree";
    }

    @Override
    public List<ResearchPaper> printPapers() {
        return ResearcherMethods.printPapers(this);
    }

    @Override
    public int calculateHIndex() {
        return ResearcherMethods.calculateHIndex(this);
    }
}
