module org.blackjack.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens org.blackjack.demo1 to javafx.fxml;
    exports org.blackjack.demo1;
}