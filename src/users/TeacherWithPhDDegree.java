package users;


import research.ResearchPaper;
import research.Researcher;
import research.ResearcherMethods;

import java.util.List;

import enums.*;

public class TeacherWithPhDDegree extends Teacher implements Researcher {

    private static final long serialVersionUID = 6104106972848195705L;
    private DegreeType degreeType;

    public TeacherWithPhDDegree() {
        super();
    }

    public TeacherWithPhDDegree(String firstName, String lastName,School school, DegreeType degreeType) {
        super(firstName, lastName, school);
        if (degreeType != DegreeType.PHD) {
            throw new IllegalArgumentException("A teacher can only have a PhD degree.");
        }
        this.degreeType = DegreeType.PHD;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        if (degreeType != DegreeType.PHD) {
            throw new IllegalArgumentException("A teacher can only have a PhD degree.");
        }
        this.degreeType = degreeType;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " is a teacher with a " + degreeType + " degree.";
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