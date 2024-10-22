module com.example.democalculator0 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.democalculator0 to javafx.fxml;
    exports com.example.democalculator0;
}