module com.example.ygv {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.sql;


    opens com.example.ygv to javafx.fxml;
    exports com.example.ygv;
}