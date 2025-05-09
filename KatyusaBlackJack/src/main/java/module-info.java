module katyusablackjack.katyusablackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens org.katyusablackjack.katyusablackjack to javafx.fxml;
    exports org.katyusablackjack.katyusablackjack;
}