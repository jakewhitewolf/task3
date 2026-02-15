package com.examle.task3;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ComponentTwo implements IObserver {
    private Subject subject;

    private int playInterval = 10;
    private int lastPlayTime = 0;
    private boolean active = false;

    private MediaPlayer mediaPlayer;

    public ComponentTwo() {
        try {
            String musicFile = "src/main/resources/correct.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            System.out.println("Не удалось загрузить music.mp3: " + e.getMessage());
            mediaPlayer = null;
        }
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
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    public boolean isActive() {
        return active;
    }

    public void setPlayInterval(int seconds) {
        playInterval = Math.max(seconds, 1);
    }

    @Override
    public void update(Subject subject) {
        if (!active || mediaPlayer == null) return;

        int t = subject.getState();
        if (t >= lastPlayTime + playInterval) {
            mediaPlayer.stop();
            mediaPlayer.play();
            lastPlayTime = t;
        }
    }
}
