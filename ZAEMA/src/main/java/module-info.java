module com.example.zaema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.zaema to javafx.fxml;
    exports com.example.zaema;
}