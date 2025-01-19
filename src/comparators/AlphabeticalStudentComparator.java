package comparators;

import java.util.Comparator;

import users.Student;

public class AlphabeticalStudentComparator implements Comparator<Student> {
	    public int compare(Student student1, Student student2) {
	        return student1.getFirstName().compareTo(student2.getFirstName());
	    }
	}
