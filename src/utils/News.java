package utils;

import comparators.HIndexResearcherComparator;
import database.Data;
import enums.NewsType;
import research.Researcher;

import java.util.Vector;

public class News implements Comparable<News> {
	private String title;
	private String content;
	private NewsType newsType;
	
	public News() {
		
	}
	
	public News(String title, String content, NewsType newsType) {
		this.title = title;
		this.content = content;
		if (newsType == null) {
            throw new IllegalArgumentException("News type cannot be null");
        }
        this.newsType = newsType;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public static News autoGenerate() {
		Vector<Researcher> researchers = Data.INSTANCE.getResearchers();

		researchers.sort(new HIndexResearcherComparator());

		if (!researchers.isEmpty()) {
			Researcher topResearcher = researchers.lastElement(); // Last in sorted list is the top

			if (topResearcher.calculateHIndex() != 0) {
				return new News(
						"Top researcher of our university: ",  topResearcher +
								" (" + topResearcher.calculateHIndex() + " HIndex)",
						NewsType.RESEARCH
				);
			}
		}

		return new News(
				"Top researcher of our university: ","We don't have a top researcher yet, but you could become the one!",
				NewsType.RESEARCH
		);
	}


	@Override
	    public int compareTo(News other) {
	        if (this.newsType == NewsType.RESEARCH && other.newsType != NewsType.RESEARCH) {
	            return -1;
	        } else if (this.newsType != NewsType.RESEARCH && other.newsType == NewsType.RESEARCH) {
	            return 1;
	        }
			return 0;
	    }
	
	   public String toString() {
		   return title + "\n" + newsType +"\n" + content ;
				   
	   }
	
}
