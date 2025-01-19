package research;

import java.util.List;

public interface Researcher {
    List<ResearchPaper> printPapers();
    int calculateHIndex();
}