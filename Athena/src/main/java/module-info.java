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
    requires java.mail;
    requires log4j;
    requires java.desktop;

    opens com.example.athena.graphical_controller to javafx.fxml;
    exports com.example.athena.graphical_controller;
    exports com.example.athena.use_case_controllers;
    opens com.example.athena.use_case_controllers to javafx.fxml;
    exports com.example.athena.view;
    opens com.example.athena.view to javafx.fxml;
    exports com.example.athena.view.scene_decorators;
    opens com.example.athena.view.scene_decorators to javafx.fxml;
    exports com.example.servers;
    opens com.example.servers to javafx.fxml;
    exports com.example.athena.entities;
    opens com.example.athena.entities to javafx.fxml;
}