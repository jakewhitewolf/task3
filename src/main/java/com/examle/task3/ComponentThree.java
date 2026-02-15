package com.examle.task3;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ComponentThree implements IObserver {
    private Subject subject;
    private final Circle circle;

    private int interval = 20;
    private int lastTime = 0;
    private boolean active = false;

    private final Bloom bloom = new Bloom();
    private boolean animating = false;

    public ComponentThree(Circle circle) {
        this.circle = circle;
        bloom.setThreshold(0.2);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        if (subject != null) subject.attach(this);
    }

    public void start() {
        active = true;
    }

    public void stop() {
        active = false;

        Platform.runLater(() -> {
            animating = false;
            if (circle != null) circle.setEffect(null);
        });
    }

    @Override
    public void update(Subject subject) {
        if (!active) return;

        int t = subject.getState();
        if (t >= lastTime + interval) {
            lastTime = t;
            Platform.runLater(this::playAnimation);
        }
    }

    public boolean isActive() {
        return active;
    }

    private void playAnimation() {
        if (circle == null) return;
        if (animating) return;

        animating = true;

        circle.setEffect(bloom);

        TranslateTransition tr = new TranslateTransition(Duration.seconds(2), circle);
        tr.setFromX(0);
        tr.setToX(200);
        tr.setCycleCount(2);
        tr.setAutoReverse(true);

        tr.setOnFinished(e -> {
            circle.setEffect(null);
            animating = false;
        });

        tr.play();
    }
}