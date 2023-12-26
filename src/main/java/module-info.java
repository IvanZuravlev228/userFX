module com.example.minitest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;

    opens ivan.app to javafx.fxml;
    exports ivan.app;
    exports ivan.app.controller;
    opens ivan.app.controller to javafx.fxml;
}
