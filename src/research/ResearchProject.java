package research;

import exceptions.InvalidPaperMembersException;
import exceptions.InvalidSupervisorException;
import exceptions.NonResearcherException;
import users.TeacherWithPhDDegree;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;


public class ResearchProject implements Serializable {

    private static final long serialVersionUID = -5138145928696802088L;

    private String topic;

    private Vector<ResearchPaper> publishedPapers = new Vector<>();
    private Researcher researchSupervisor;
    private Vector<Researcher> participants = new Vector<>();

    public ResearchProject() {}


    public ResearchProject(String topic, Researcher researchSupervisor, Vector<ResearchPaper> publishedPapers, Vector<Researcher> participants) throws InvalidSupervisorException, InvalidPaperMembersException {

        validateSupervisor(researchSupervisor);
        validatePublishedPapers(publishedPapers, participants);

        this.topic = topic;
        this.researchSupervisor = researchSupervisor;
        this.publishedPapers = publishedPapers;
        this.participants = participants;
    }

    private void validateSupervisor(Researcher supervisor) throws InvalidSupervisorException {
        if (!(supervisor instanceof TeacherWithPhDDegree)) {
            throw new InvalidSupervisorException("Supervisor must be a teacher with a PhD degree.");
        }
        if (((TeacherWithPhDDegree) supervisor).calculateHIndex() <= 3) {
            throw new InvalidSupervisorException("Supervisor must have an h-index greater than 3.");
        }
    }


    private void validatePublishedPapers(Vector<ResearchPaper> papers, Vector<Researcher> participants)
            throws InvalidPaperMembersException {
        for (ResearchPaper paper : papers) {
            boolean hasParticipantAuthor = paper.getAuthors().stream()
                    .anyMatch(participants::contains);
            if (!hasParticipantAuthor) {
                throw new InvalidPaperMembersException("Each research paper must include at least one participant as an author.");
            }
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
    }

    public Researcher getResearchSupervisor() {
        return researchSupervisor;
    }

    public void setResearchSupervisor(Researcher researchSupervisor) {
        this.researchSupervisor = researchSupervisor;
    }

    public Vector<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(Object participant) throws NonResearcherException {
        if (!(participant instanceof Researcher)) {
            throw new NonResearcherException("Participant must be a Researcher.");
        }
        participants.add((Researcher) participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(researchSupervisor, topic);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResearchProject other = (ResearchProject) obj;
        return Objects.equals(researchSupervisor, other.researchSupervisor) && Objects.equals(topic, other.topic);
    }

    @Override
    public String toString() {
        return "Topic: \"" + topic + "\" Supervisor: "  + researchSupervisor + " | Participants: " +
                participants.stream()
                        .map(Researcher::toString)
                        .collect(Collectors.joining(", "));
    }
}