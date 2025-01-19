package test;

import java.util.HashMap;
import java.util.Vector;

import database.Data;
import enums.CourseType;
import enums.ManagerType;
import enums.NewsType;
import enums.School;
import enums.StudentType;
import enums.Urgency;
import utils.Complaint;
import utils.Course;
import utils.News;
import utils.Lesson;
import users.Manager;
import users.Student;
import users.Teacher;

public class Test {
    public static void main(String[] args) {
        // Test 1: Manager creation and getters
    	HashMap<School, CourseType> courseType = new HashMap<>();
        courseType.put(School.BS, CourseType.MAJOR);
        courseType.put(School.SITE, CourseType.ELECTIVE);
        Manager manager = new Manager("Alice", "Johnson", ManagerType.OR, School.SITE);
        System.out.println("Manager Created: " + manager.getFirstName() + " " + manager.getLastName());
        System.out.println("Manager Type: " + manager.getManagerType());
        System.out.println("Manager School: " + manager.getSchool());

        // Test 2: Add news
        News news = new News("New Course Added", "Details about the new course...", NewsType.EVENT);
        manager.addNews(news);
        System.out.println(news);

        // Test 3: Add courses
        HashMap<Course, Integer> coursesMap = new HashMap<>();
        coursesMap.put(new Course("CS101", "Intro to Computer Science", 6, courseType, 30), 30);
        coursesMap.put(new Course("CS102", "Data Structures", 6, courseType, 40), 40);
        manager.addCourses(coursesMap);
        System.out.println("Courses added.");

        // Test 4: Approve student registration
        Student student = new Student("Bob", "Smith");
        Vector<Course> studentCourses = new Vector<>();
        
        studentCourses.add(new Course("CS101", "Intro to Computer Science", 6, courseType, 30));
        studentCourses.add(new Course("CS102", "Data Structures", 6, courseType, 40));
        manager.approveStudentRegistration(student, studentCourses);

        // Test 5: Read complaints
        Complaint complaint1 = new Complaint("Issue with Course", manager, Urgency.HIGH, student);
        Complaint complaint2 = new Complaint("Schedule Issue", manager, Urgency.HIGH, student);
        Vector<Complaint> complaints = new Vector<>();
        complaints.add(complaint1);
        complaints.add(complaint2);
        complaint1.setRecipient(manager);
     //   manager.setComplaints(complaints);
        Data.INSTANCE.getComplaints().add(complaint1);
        manager.readComplaints();

        // Test 6: View teachers
        Vector<Teacher> teachers = manager.viewTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
        } else {
            System.out.println("Teachers:");
            for (Teacher teacher : teachers) {
                System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
            }
        }
    }
}

