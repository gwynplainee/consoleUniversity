package users;

import database.Data;
import enums.StudentType;
import enums.DegreeType;
import enums.ManagerType;
import enums.School;
import journal.Journal;
import utils.Generator;

public class Admin extends Employee {

    private static final long serialVersionUID = -2367011236765922983L;

    public Admin() {
        super();
    }

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }


    public void addUser(String firstName, String lastName, StudentType studentType) {
        if (studentType == null) throw new IllegalArgumentException("StudentType cannot be null.");

        Student student = new Student(firstName, lastName);
        student.setStudentType(studentType);
        int enrolledYear = Data.INSTANCE.getYear();
        student.setEnrolledYear(enrolledYear);

        addUser(student);
    }

    public void addUser(String firstName, String lastName, ManagerType managerType) {
        Manager manager = new Manager(firstName, lastName, managerType, null);
        addUser(manager);
        Data.INSTANCE.getManagers().add(manager);
    }

    public void addUser(String firstName, String lastName, School school, DegreeType degree) {
        Teacher teacher = new Teacher(firstName, lastName, school, degree);
        addUser(teacher);
        Data.INSTANCE.getTeachers().add(teacher);
    }

    public void addUser(String firstName, String lastName, School school, DegreeType degree, boolean isPhD) {
        if (isPhD) {
            TeacherWithPhDDegree teacherPhD = new TeacherWithPhDDegree(firstName, lastName, school, degree);
            addUser(teacherPhD);
            Data.INSTANCE.getTeachers().add(teacherPhD);
        } else {
            throw new IllegalArgumentException("Invalid argument. Use the correct method for non-PhD teachers.");
        }
    }

    public void addUser(String firstName, String lastName, StudentType studentType, boolean isGraduate) {
        if (isGraduate) {
            GraduateStudent graduateStudent = new GraduateStudent(firstName, lastName);
            graduateStudent.setStudentType(studentType);
            int enrolledYear = Data.INSTANCE.getYear();
            graduateStudent.setEnrolledYear(enrolledYear);

            addUser(graduateStudent);
            Data.INSTANCE.getStudents().add(graduateStudent);
        } else {
            throw new IllegalArgumentException("Invalid argument. Use the correct method for undergraduate students.");
        }
    }


    public void removeUser(String id) {
        Data.INSTANCE.getUsers().keySet().removeIf(generator -> generator.getId().equals(id));
        System.out.println("User with ID " + id + " has been removed.");
    }

    private String determineUserType(User user) {
        if (user instanceof Student) return "STUDENT";
        if (user instanceof Teacher) return "TEACHER";
        if (user instanceof Manager) return "MANAGER";
        if (user instanceof Admin) return "ADMIN";
        throw new IllegalArgumentException("Unknown user type.");
    }

    private void subscribeUserToAllJournals(User user) {
        for (Journal journal : Data.INSTANCE.getJournals()) {
            journal.subscribe(user);
            System.out.println(user.getFirstName() + " " + user.getLastName() + " subscribed to journal: " + journal.getJournalName());
        }
    }

    public void addUser(User user) {
        String userType = determineUserType(user);
        int year = Data.INSTANCE.getYear();
        String id = Generator.generateId(userType,
                user instanceof Student ? ((Student) user).getStudentType() : null,
                year);
        String password = Generator.generatePassword(8);

        System.out.println("Generated ID: " + id);
        System.out.println("Generated Password: " + password);
        System.out.println("DO NOT SHARE! Save this information for later use.");

        subscribeUserToAllJournals(user);

        Generator newGenerator = new Generator(id, password);
        Data.INSTANCE.getUsers().put(newGenerator, user);
    }
}
