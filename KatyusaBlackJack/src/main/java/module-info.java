module katyusablackjack.katyusablackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires java.desktop;

    exports org.main;
    opens org.main to javafx.fxml;
    exports org.controllers;
    opens org.controllers to javafx.fxml;
}