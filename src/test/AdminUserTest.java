package test;

import enums.DegreeType;
import enums.School;
import enums.StudentType;
import research.ResearchPaper;
import research.ResearchProject;
import users.Admin;
import users.GraduateStudent;
import users.TeacherWithPhDDegree;
import research.Researcher;

import java.util.Vector;

public class AdminUserTest {
    public static void main(String[] args) {
        // Create an Admin object
        Admin admin = new Admin("John", "Doe");

        // Test adding a TeacherWithPhDDegree
        System.out.println("Adding a Teacher with PhD degree...");
        TeacherWithPhDDegree teacherPhD = null;
        try {
            teacherPhD = new TeacherWithPhDDegree("Bob", "Brown", School.SITE, DegreeType.PHD);
            admin.addUser("Bob", "Brown", School.SITE, DegreeType.PHD, true);
            System.out.println("Teacher with PhD added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add Teacher with PhD: " + e.getMessage());
        }

        // Test adding a GraduateStudent
        System.out.println("Adding a Graduate Student...");
        GraduateStudent graduateStudent = null;
        try {
            graduateStudent = new GraduateStudent("Alice", "Smith");
            graduateStudent.setStudentType(StudentType.PHD);
            graduateStudent.setSchool(School.SITE);
            admin.addUser("Alice", "Smith", StudentType.PHD, true);
            System.out.println("Graduate Student added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add Graduate Student: " + e.getMessage());
        }

        // Create a Research Paper and add authors
        System.out.println("Creating a Research Paper...");
        ResearchPaper researchPaper = new ResearchPaper();
        researchPaper.setTitle("AI and Machine Learning Techniques");
        researchPaper.addAuthor(teacherPhD);
        researchPaper.addAuthor(graduateStudent);
        System.out.println("Research Paper created with title: " + researchPaper.getTitle());
        System.out.println("Authors: " + researchPaper.getAuthors());

        // Create a Research Project and add participants
        System.out.println("Creating a Research Project...");
        Vector<Researcher> participants = new Vector<>();
        participants.add(graduateStudent);
        try {
            ResearchProject researchProject = new ResearchProject("AI in Healthcare", teacherPhD, new Vector<>(), participants);
            System.out.println("Research Project created with topic: " + researchProject.getTopic());
            System.out.println("Participants: " + researchProject.getParticipants());
        } catch (Exception e) {
            System.err.println("Failed to create Research Project: " + e.getMessage());
        }

        System.out.println("All tests completed successfully.");
    }
}
