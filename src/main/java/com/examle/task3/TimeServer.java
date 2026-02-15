package com.examle.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimeServer implements Subject {

    private int timeState = 0;
    private Timer timer;
    private TimerTask task;
    private boolean active = false;

    private final List<IObserver> observers = new ArrayList<>();

    private static final int DELAY = 0;
    private static final int PERIOD = 1000; // 1 сек

    public void start() {
        if (active) return;

        timer = new Timer(true);
        task = new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        };
        timer.scheduleAtFixedRate(task, DELAY, PERIOD);
        active = true;

        notifyAllObservers();
    }

    public void stop() {
        if (!active) return;

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        task = null;
        active = false;

        notifyAllObservers();
    }

    public void reset() {
        timeState = 0;
        notifyAllObservers();
    }

    public boolean isActive() {
        return active;
    }

    private void tick() {
        timeState++;
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for (IObserver obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public void attach(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void detach(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public int getState() {
        return timeState;
    }

    @Override
    public void setState(int time) {
        timeState = time;
        notifyAllObservers();
    }
}