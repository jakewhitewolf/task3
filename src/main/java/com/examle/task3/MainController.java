package com.examle.task3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    private TimeServer timeServer;
    private ComponentOne componentOne;
    private ComponentTwo componentTwo;
    private ComponentThree componentThree;

    @FXML private Label timeLabel;
    @FXML private Label serverStatusLabel;
    @FXML private Label currentTimeLabel;

    @FXML private Button serverStartBtn;
    @FXML private Button serverStopBtn;
    @FXML private Button serverResetBtn;

    @FXML private Button comp2StartBtn;
    @FXML private Button comp2StopBtn;

    @FXML private Button comp3StartBtn;
    @FXML private Button comp3StopBtn;

    @FXML private Circle animationCircle;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();

        componentOne = new ComponentOne(timeLabel);
        componentTwo = new ComponentTwo();
        componentThree = new ComponentThree(animationCircle);

        componentOne.setSubject(timeServer);
        componentTwo.setSubject(timeServer);
        componentThree.setSubject(timeServer);

        setupHandlers();
        updateUI();
    }

    private void setupHandlers() {
        serverStartBtn.setOnAction(e -> { timeServer.start(); updateUI(); });
        serverStopBtn.setOnAction(e -> { timeServer.stop(); updateUI(); });
        serverResetBtn.setOnAction(e -> { timeServer.reset(); updateUI(); });

        comp2StartBtn.setOnAction(e -> { componentTwo.start(); updateUI(); });
        comp2StopBtn.setOnAction(e -> { componentTwo.stop(); updateUI(); });

        comp3StartBtn.setOnAction(e -> { componentThree.start(); updateUI(); });
        comp3StopBtn.setOnAction(e -> { componentThree.stop(); updateUI(); });
    }

    private void updateUI() {
        if (timeServer.isActive()) {
            serverStatusLabel.setText("СЕРВЕР АКТИВЕН");
            serverStartBtn.setDisable(true);
            serverStopBtn.setDisable(false);
        } else {
            serverStatusLabel.setText("СЕРВЕР НЕАКТИВЕН");
            serverStartBtn.setDisable(false);
            serverStopBtn.setDisable(true);
        }

        currentTimeLabel.setText("Время: " + timeServer.getState() + " сек");

        comp2StartBtn.setDisable(componentTwo.isActive());
        comp2StopBtn.setDisable(!componentTwo.isActive());

        comp3StartBtn.setDisable(componentThree.isActive());
        comp3StopBtn.setDisable(!componentThree.isActive());
    }
}