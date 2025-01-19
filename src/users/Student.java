package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import enums.OrganizationRole;
import enums.School;
import enums.StudentType;
import utils.Course;
import utils.Organization;
import utils.Transcript;
import utils.OrganizationMembership;
import utils.Lesson;

public class Student extends User implements Serializable {

	private School school;
	private int enrolledYear;
	private StudentType studentType;
	private Transcript transcript;
	private Vector<Course> selectedCourses = new Vector<>();
	private List<OrganizationMembership> memberships = new ArrayList<>();
	 
	public Student() {
		super();
	}
	
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
		this.transcript = new Transcript(this);
	}
	

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(int enrolledYear) {
        this.enrolledYear = enrolledYear;
    }

    public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}
	
	public StudentType getStudentType() {
		return studentType;
	}
    
    public Transcript getTranscript() {
        return transcript;
    }

    public void viewTranscript() {
        System.out.println(transcript.getFormattedTranscript());
    }	
    
    public Vector<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void addSelectedCourse(Course course) {
        if (!selectedCourses.contains(course)) {
            selectedCourses.add(course);
        }
    }

    public void clearSelectedCourses() {
        selectedCourses.clear();
    }
    
    public void joinOrganization(Organization organization, OrganizationRole role) {
        memberships.add(new OrganizationMembership(organization, role));
    }

    public void viewOrganizations() {
        if (memberships.isEmpty()) {
            System.out.println(getFirstName() + " " + getLastName() + " is not a member of any organizations.");
        } else {
            System.out.println(getFirstName() + " " + getLastName() + " is a member of the following organizations:");
            for (OrganizationMembership membership : memberships) {
                System.out.println("- " + membership);
            }
        }
    }
    
 public void viewTeacherInfo(Course course) {
        
        for (Lesson lesson : course.getLessons()) {
            Teacher teacher = lesson.getInstructor();
            if (teacher != null) {
                System.out.println("Teacher for course '" + course.getCourseName() + "':");
                System.out.println("Name: " + teacher.getFirstName() + " " + teacher.getLastName());
                System.out.println("School: " + teacher.getSchool());
                System.out.println("Degree: " + teacher.getDegreeType());
                System.out.println("Rating: " + teacher.getTeacherRating());
                return;
            }
        }
        System.out.println("No teacher assigned for this course: " + course.getCourseName());
 }
 
 public void rateTeacher(Teacher teacher, int rating) {
     teacher.getRatings().add(Math.max(1, Math.min(rating, 5)));
 }
 
 public void viewRegisteredCourses() {
	    if (selectedCourses.isEmpty()) {
	        System.out.println(getFirstName() + " " + getLastName() + " is not registered for any courses.");
	    } else {
	        System.out.println(getFirstName() + " " + getLastName() + " is registered for the following courses:");
	        for (Course course : selectedCourses) {
	            System.out.println("- " + course.getCourseName() + " (" + course.getCode() + ")");
	        }
	    }
	}
 
 @Override
 public boolean equals(Object obj) {
     if (this == obj) return true;
     if (obj == null || getClass() != obj.getClass()) return false;

     Student other = (Student) obj;

     if (!super.equals(other)) return false;

     return enrolledYear == other.enrolledYear &&
             school == other.school &&
             studentType == other.studentType &&
             Objects.equals(transcript, other.transcript) &&
             Objects.equals(memberships, other.memberships) &&
             Objects.equals(selectedCourses, other.selectedCourses);
 }

}
	

	
	
	
	
	
	
	

