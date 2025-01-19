package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import enums.Period;
import enums.School;
import enums.StudentType;
import journal.Journal;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import users.Dean;
import users.Manager;
import users.Student;
import users.Teacher;
import users.User;
import utils.Complaint;
import utils.Course;
import utils.Generator;
import utils.Lesson;
import utils.News;
import utils.Organization;
import utils.Post;
import utils.Request;

public class Data implements Serializable {

    private Vector<String> logs = new Vector<String>();

private HashMap<School, Dean> schoolDeans = new HashMap<School, Dean>();
    private Vector<News> news = new Vector<News>();
    private Vector<Course> courses = new Vector<Course>();
    private Vector<Student> students = new Vector<>();
    private Vector<Teacher> teachers = new Vector<>();  // Added vector for Teachers
    private Vector<Manager> managers = new Vector<>();
    private HashMap<Generator, User> users = new HashMap<Generator, User>();
    private Vector<Journal> journals = new Vector<>();
    private Vector<Post> messages = new Vector<>();
    private Vector<Complaint> complaints = new Vector<>();
    private Vector<Request> requests = new Vector<>();
    private Vector<Organization> organizations = new Vector<>();
    private Vector<ResearchPaper> researchPapers = new Vector<>();
    private Vector<ResearchProject> researchProjects = new Vector<>();
    private Vector<Researcher> researchers;
    private Period period;
    private int year;

    public static Data INSTANCE;
    
    static {
        if (new File("data").exists()) {
            try {
                INSTANCE = read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            INSTANCE = new Data();
        }
    }

    private Data() {}

    public static Data read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("data");
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (Data) oin.readObject();
    }

    public static void write() throws IOException {
        FileOutputStream fos = new FileOutputStream("data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(INSTANCE);
        oos.close();
    }

    public Vector<String> getLogs() {
        return logs;
    }

    public void setLogs(Vector<String> logs) {
        this.logs = logs;
    }

    public Vector<News> getNews() {
        return news;
    }

    public Period getPeriod() {
        updateTime();
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getYear() {
        updateTime();
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void updateTime() {
        LocalDate current = LocalDate.now();  // Используем LocalDate
        setYear(current.getYear());
        setPeriod((current.getMonthValue() < 5 ? Period.SPRING : 
                  (current.getMonthValue() < 8 ? Period.SUMMER : Period.FALL)));
    }

    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public HashMap<Generator, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Generator, User> users) {
        this.users = users;
    }

    public Vector<Student> getStudents() {
        return students;
    }
    
    public Vector<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Vector<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Vector<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Vector<Manager> managers) {
        this.managers = managers;
    }
    
    public Vector<Journal> getJournals() {
        return journals;
    }

    public void addJournal(Journal journal) {
        journals.add(journal);
    }
    
    public HashMap<School, Dean> getSchoolDeans() {
        return schoolDeans;
    }

    public void setSchoolDean(HashMap<School, Dean> schoolDeans) {
        this.schoolDeans = schoolDeans;
    }
    

    public Vector<Post> getMessages() {
        return messages;
    }

    public Vector<Complaint> getComplaints() {
        return complaints;
    }

    public Vector<Request> getRequests() {
        return requests;
    }
    
    public Vector<Organization> getOrganizations() { 
        return organizations;
    }

    public void setOrganizations(Vector<Organization> organizations) { 
        this.organizations = organizations;
}

    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public Vector<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector<Researcher> getResearchers() {
        return researchers;
    }

    public void setResearchers(Vector<Researcher> researchers) {
        this.researchers = researchers;
    }
}

