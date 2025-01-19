package comparators;

import research.Researcher;

import java.util.Comparator;

public class HIndexResearcherComparator implements Comparator<Researcher> {
    public int compare(Researcher r1, Researcher r2){
        return Double.compare(r1.calculateHIndex(), r2.calculateHIndex());
    }
}
