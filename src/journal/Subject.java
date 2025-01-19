package journal;

public interface Subject {
    void subscribe(Observer observer); 
    void notifyObservers(String paperTitle); 
}
