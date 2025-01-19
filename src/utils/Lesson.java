package utils;

import enums.LessonType;
import users.Teacher;

import java.io.Serializable;


public class Lesson implements Serializable {
    private Teacher instructor;
    private LessonType lessonType;


    public Lesson(Teacher instructor, LessonType lessonType){
        this.instructor = instructor;
        this.lessonType = lessonType;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String toString() {
        return "Lesson{" +
                "instructor=" + instructor.getFirstName() + " " + instructor.getLastName() +
                ", lessonType=" + lessonType + '}';
    }

}
