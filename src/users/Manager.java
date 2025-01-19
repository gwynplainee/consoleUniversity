package users;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import database.Data;
import enums.ManagerType;
import enums.School;
import utils.Complaint;
import utils.Course;
import utils.News;
import utils.Request;
import enums.StudentType;
import utils.Lesson;

public class Manager extends Employee {
	private ManagerType managerType;
	private School school;

	
	public Manager() {
		super();
		this.managerType = ManagerType.OR;
	}
	
	
	/*Constructor with exception*/
	public Manager(String firstName, String lastName, ManagerType managerType, School school){
		super(firstName, lastName);
		if (managerType == null) {
            throw new IllegalArgumentException("Manager type cannot be null");
        }
        this.managerType = managerType;
        this.school = school;
        
    }
	
	public ManagerType getManagerType() {
		return managerType;
	}
	
	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	
	
	public List<Complaint> getComplaints() {
	    List<Complaint> receivedComplaints = Data.INSTANCE.getComplaints().stream()
	        .filter(comp -> comp.getRecipient().equals(this))
	        .collect(Collectors.toList());

	    return receivedComplaints;
	  }

	  public List<Request> getRequests() {
	    List<Request> receivedComplaints = Data.INSTANCE.getRequests().stream()
	        .filter(req -> req.getRecipient().equals(this))
	        .collect(Collectors.toList());

	    return receivedComplaints;
	  }
	 
	
	 public void addNews(News news) {
    	Data.INSTANCE.getNews().add(news);
    }
	 
	 public void viewCourseReport(Course course) {
		    System.out.println("Generating course report...");
		    course.displayReport();
		}
	 
	 public void addCourses(HashMap<Course, Integer> subjects) {
		    for (Course subject : subjects.keySet()) {
		        int courseLimit = subjects.get(subject);
		        
		        Course newCourse = new Course(
		            subject.getCode(),
		            subject.getCourseName(),
		            subject.getCredits(),
		            subject.getCourseType(), 
		            courseLimit
		        );

		        Data.INSTANCE.getCourses().add(newCourse);
		    }
		}
	 
	 public void addCoursesForRegistration(Vector<Course> courses, StudentType studentType, int year) {
	        for (Student student : Data.INSTANCE.getStudents()) {
	            if (student.getStudentType() == studentType && student.getEnrolledYear() == year) {
	                for (Course course : courses) {
	                    if (!Data.INSTANCE.getCourses().contains(course)) {
	                        Data.INSTANCE.getCourses().add(course);
	                    }
	                }
	            }
	        }
	        System.out.println("Courses added for registration for " + studentType + " year " + year);
	    }

	    public void approveStudentRegistration(Student student, Vector<Course> courses) {
	        int totalCredits = courses.stream().mapToInt(Course::getCredits).sum();
	        if (totalCredits >= 12 && totalCredits <= 18) {
	            for (Course course : courses) {
	                if (!course.getEnrolledStudents().contains(student)) {
	                    course.getEnrolledStudents().add(student);
	                }
	            }
	            student.clearSelectedCourses();
	            System.out.println("Registration approved for " + student.getFirstName() + " " + student.getLastName());
	        } else {
	            System.out.println("Registration denied: Total credits (" + totalCredits + ") are not within the allowed range (12-18).");
	        }
	    }

	 public void assignCourseToTeachers(Course course, Vector<Lesson> lessons) {
		   	course.setLessons(lessons);
		}
	 
	 
	 
		public Vector<Teacher> viewTeachers() {
			return Data.INSTANCE.getTeachers();
		}

		public Vector<Teacher> viewTeachers(Comparator<Teacher> comparator) {
			Vector<Teacher> teachers = viewTeachers();
			teachers.sort(comparator);
	        return teachers;
		}

		public Vector<Student> viewStudents() {
			return Data.INSTANCE.getStudents();
		}

		public Vector<Student> viewStudents(Comparator<Student> comparator) {
			Vector<Student> students = viewStudents();
			students.sort(comparator);
	        return students;	
	    }
		

public void readComplaints(){
    List<Complaint> receivedComplaints = getComplaints();

    if (receivedComplaints.isEmpty()) {
      System.out.println("No complaints for " + getFirstName() + " " + getLastName());
    } else {
      System.out.println("Complaints for " + getFirstName() + " " + getLastName() + ":");
      receivedComplaints.forEach(System.out::println);
    }
  }

  public void readRequests(){
    List<Request> receivedRequests = getRequests();

    if (receivedRequests.isEmpty()) {
      System.out.println("No requests for " + getFirstName() + " " + getLastName());
    } else {
      System.out.println("Requests for " + getFirstName() + " " + getLastName() + ":");
      receivedRequests.forEach(System.out::println);
    }
  }

}
