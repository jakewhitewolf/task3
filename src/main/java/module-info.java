module com.examle.task3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.examle.task3 to javafx.fxml;
    exports com.examle.task3;
}