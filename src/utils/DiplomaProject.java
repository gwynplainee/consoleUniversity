package utils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

import exceptions.InvalidPaperMembersException;
import exceptions.InvalidSupervisorException;
import research.ResearchPaper;
import users.Student;
import users.Teacher;
import users.TeacherWithPhDDegree;


public class DiplomaProject implements Serializable {

    private static final long serialVersionUID = -3027112353433280898L;
    private String topic;
    private Teacher supervisor;
    private Student participant;
    private Vector<ResearchPaper> publishedPapers;
    private Date submissionDeadline;

    public DiplomaProject() {}


    public DiplomaProject(String topic, Teacher supervisor, Student participant, Date submissionDeadline)
            throws InvalidSupervisorException, InvalidPaperMembersException {
        validateSupervisor(supervisor);
        validateParticipant(participant);

        this.topic = topic;
        this.supervisor = supervisor;
        this.participant = participant;
        this.submissionDeadline = submissionDeadline;
    }


    private void validateSupervisor(Teacher supervisor) throws InvalidSupervisorException {
        if (!(supervisor instanceof TeacherWithPhDDegree)) {
            throw new InvalidSupervisorException("Supervisor must be a teacher with a PhD degree.");
        }
        if (((TeacherWithPhDDegree) supervisor).calculateHIndex() <= 3) {
            throw new InvalidSupervisorException("Supervisor must have an h-index greater than 3.");
        }
    }


    private void validateParticipant(Student participant) throws InvalidPaperMembersException {
        for (ResearchPaper paper : publishedPapers) {
            boolean isParticipantAuthor = paper.getAuthors().stream().anyMatch(author -> author.equals(participant));

            if (!isParticipantAuthor) {
                throw new InvalidPaperMembersException("The participant must be an author in at least one research paper.");
            }
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Teacher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Teacher supervisor) throws InvalidSupervisorException {
        validateSupervisor(supervisor);
        this.supervisor = supervisor;
    }

    public Student getParticipant() {
        return participant;
    }

    public void setParticipant(Student participant) throws InvalidPaperMembersException {
        validateParticipant(participant);
        this.participant = participant;
    }

    public Date getSubmissionDeadline() {
        return submissionDeadline;
    }

    public void setSubmissionDeadline(Date submissionDeadline) {
        this.submissionDeadline = submissionDeadline;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }


    @Override
    public int hashCode() {
        return Objects.hash(topic, supervisor, participant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DiplomaProject other = (DiplomaProject) obj;
        return Objects.equals(topic, other.topic) &&
                Objects.equals(supervisor, other.supervisor) &&
                Objects.equals(participant, other.participant);
    }

    @Override
    public String toString() {
        return "\"" + topic + "\": Supervised by " + supervisor.getFirstName() + " " + supervisor.getLastName() +
                " | Participant: " + participant.getFirstName() + " " + participant.getLastName();
    }
}