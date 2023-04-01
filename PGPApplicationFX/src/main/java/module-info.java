module com.example.pgpapplicationfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pgpapplicationfx to javafx.fxml;
    exports com.example.pgpapplicationfx;
}