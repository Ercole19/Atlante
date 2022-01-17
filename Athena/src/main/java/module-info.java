module com.example.athena {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires com.google.gson;

    opens com.example.athena.GraphicalController to javafx.fxml;
    exports com.example.athena.GraphicalController;
    exports com.example.athena.UseCaseControllers;
    opens com.example.athena.UseCaseControllers to javafx.fxml;
    exports com.example.athena.View;
    opens com.example.athena.View to javafx.fxml;
    exports com.example.athena.View.SceneDecorators;
    opens com.example.athena.View.SceneDecorators to javafx.fxml;
}