package utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;


public class Mark implements Comparable<Mark>, Serializable {
    private static final HashMap<Double, String> GRADE_TABLE = new HashMap<>();

    static {
        GRADE_TABLE.put(0.0, "F");
        GRADE_TABLE.put(1.0, "D");
        GRADE_TABLE.put(1.33, "D+");
        GRADE_TABLE.put(1.67, "C-");
        GRADE_TABLE.put(2.0, "C");
        GRADE_TABLE.put(2.33, "C+");
        GRADE_TABLE.put(2.67, "B-");
        GRADE_TABLE.put(3.0, "B");
        GRADE_TABLE.put(3.33, "B+");
        GRADE_TABLE.put(3.67, "A-");
        GRADE_TABLE.put(4.0, "A");
    }

    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    public Mark() {
        super();
    }

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
    }

    public double getFirstAttestation() {
        return firstAttestation;
    }

    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation;
    }

    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }


    public double getTotalMark() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public double getGPA() {
        double total = getTotalMark();
        if (total < 50) {
            return 0.0;
        }
        total = Math.min(100, total);
        int index = ((int) total - 51) / 5 + 1;
        return GRADE_TABLE.keySet().toArray(new Double[0])[index];
    }

    
    public String getLetterGPA() {
        double total = getTotalMark();
        if (total < 50) {
            return "F";
        }
        total = Math.min(100, total);
        int index = ((int) total - 51) / 5 + 1;
        return GRADE_TABLE.get(GRADE_TABLE.keySet().toArray(new Double[0])[index]);
    }

    @Override
    public int compareTo(Mark o) {
        return Double.compare(getTotalMark(), o.getTotalMark());
    }

    @Override
    public String toString() {
        return Double.toString(getTotalMark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalMark());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mark other = (Mark) obj;
        return Double.compare(getTotalMark(), other.getTotalMark()) == 0;
    }
}

