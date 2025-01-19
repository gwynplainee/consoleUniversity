package research;


import java.util.Vector;

import enums.Format;
import journal.Journal;

public class ResearchPaper implements Comparable<ResearchPaper> {
    private String title;
    private Vector<Researcher> authors = new Vector<Researcher>();
    private String journal;
    private int pages;
    private String date;
    private String doi;
    private Journal journalObj;
    private int citations;

    public ResearchPaper() {

    }

    public ResearchPaper(String title, String journal, int pages, String date, String doi, Journal journalObj, int citations) {
        this.title = title;
        this.journal = journal;
        this.pages = pages;
        this.date = date;
        this.doi = doi;
        this.journalObj = journalObj;
        this.citations = citations;
    }

   /* public String getCitation() {
        String authorsString = String.join(", ", authors);
        return String.format("\"%s\" by %s. Published in %s on %s, Pages: %d-%d, DOI: %s",
                             title, authorsString, journal, date, pages, doi);
    }*/

    public Vector<Researcher> getAuthors() {
        return authors;
    }
    public void addAuthor(Researcher r) {
        authors.add(r);
    }

    public String getJournalDetails() {
        return "Published in journal: " + journalObj.getJournalName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Journal getJournalObj() {
        return journalObj;
    }

    public void setJournalObj(Journal journalObj) {
        this.journalObj = journalObj;
    }

    public int getCitations() {
        return this.citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public String getCitation(Format format) {
        if(format == Format.PLAINTEX) {
            return String.format("%s et al., \"%s\", %tY, pages: %d, doi: %s", this.authors, this.title, this.date, this.pages, this.doi);
        }
        if(format == Format.BIBTEX) {
            return String.format("@article{author={%s}, title={%s}, year={%tY}, , pages={%d}, doi={%s}}", this.authors, this.title, this.date, this.pages, this.doi);
        }
        return "Unsupported format";
    }

    public String toString() {
        return "ResearchPaper's name: " + this.title + ", authors: " + this.authors + ", pages: " + this.pages + ", date: " + this.date + ", citations: " + this.citations + ", doi: " + this.doi;
    }



    public int compareTo(ResearchPaper r) {
        if(this.citations > r.citations) return -1;
        if(this.citations < r.citations) return 1;
        return 0;
    }
}