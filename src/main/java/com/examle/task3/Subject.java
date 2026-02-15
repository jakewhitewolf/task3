package com.examle.task3;

public interface Subject {
    void notifyAllObservers();
    void attach(IObserver obs);
    void detach(IObserver obs);

    int getState();
    void setState(int time);
}
