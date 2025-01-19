package comparators;


import java.util.Comparator;
import users.Teacher;

public class TeacherRatingComparator implements Comparator<Teacher> {

	public int compare(Teacher o1, Teacher o2) {
		return Double.compare(o1.getTeacherRating(), o2.getTeacherRating());
	}
    
}
