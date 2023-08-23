module com.example.thefinish {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thefinish to javafx.fxml;
    exports com.example.thefinish;
}