package journal;

import java.util.ArrayList;
import java.util.List;

import database.Data;
import enums.Format;
import enums.NewsType;
import research.ResearchPaper;
import utils.News;

public class Journal implements Subject {
    private String journalName;
    private List<Observer> subscribers; 
    private List<ResearchPaper> papers; 

    public Journal(String journalName) {
        this.journalName = journalName;
        this.subscribers = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    @Override
    public void subscribe(Observer observer) {
        if (!subscribers.contains(observer)) {
            subscribers.add(observer);
        }
    }

    @Override
    public void notifyObservers(String paperTitle) {
        for (Observer subscriber : subscribers) {
            subscriber.update(journalName, paperTitle);
        }
    }

    public void publishPaper(ResearchPaper paper) {
        papers.add(paper); 
        System.out.println("New paper published in " + journalName + ": " + paper.getTitle());
        String title = "New research paper about "  + paper.getTitle() + "was published!";
        String content = paper.getAuthors() + " has published paper called " + paper.getTitle();
        News news = new News(title,content, NewsType.RESEARCH);
        Data.INSTANCE.getNews().add(news);
        notifyObservers(paper.getTitle());  
    }

    public String getJournalName() {
        return journalName;
    }

    public void printPublishedPapers(Format format) {
        for (ResearchPaper paper : papers) {
            System.out.println(paper.getCitation(format));
        }
    }
}
