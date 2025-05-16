package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.database.UserDAO;
import org.models.User;

import java.io.IOException;
import java.util.Objects;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToRegistView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/regist-view.fxml"));
        Parent root = loader.load();
        UserController userController = loader.getController();
        Stage stage = new Stage();
        userController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.setTitle("Regisztráció");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/bj.png"))));
        stage.initModality(Modality.WINDOW_MODAL);
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.initOwner(parentStage);
        stage.setX(100);
        stage.setY(50);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void switchToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-view.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();

        Stage loginStage = new Stage();
        loginController.setStage(loginStage);

        // Itt kell kinyerni a fő ablakot
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginController.setMainStage(mainStage);

        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Bejelentkezés");
        loginStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/bj.png"))));
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.initOwner(mainStage);
        loginStage.setX(100);
        loginStage.setY(50);
        loginStage.setResizable(false);
        loginStage.showAndWait();
    }

    public void switchToRulesView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/rules-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game-view.fxml"));
        Parent root = loader.load();

        Scene gameScene = new Scene(root);
        stage.setTitle("Black Jack");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/bj.png"))));
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.setX(100);
        stage.setY(50);
        stage.show();
    }

    public void switchToHelloView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/main-view.fxml")));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private WebView webView;

    @FXML
    public void initialize() {
        if (webView != null) {
            webView.getEngine().loadContent("""
                         <html lang="hu">
                         <head>
                             <meta charset="UTF-8">
                             <meta name="viewport" content="width=device-width, initial-scale=1.0">
                             <title>Blackjack Szabályok</title>
                             <style>
                                 body {
                                     font-family: 'Arial', sans-serif;
                                     background-color: #0a5c36;
                                     color: #fff;
                                     margin: 0;
                                     padding: 15px;
                                     width: auto;
                                     height: 450px;
                                     overflow: auto;
                                     line-height: 1.5;
                                 }
                                 .container {
                                     background-color: rgba(0, 0, 0, 0.7);
                                     border-radius: 10px;
                                     padding: 20px;
                                     box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
                                     border: 2px solid #d4af37;
                                 }

                                 h1 {
                                     color: #d4af37;
                                     text-align: center;
                                     font-size: 20px;
                                     margin-top: 0;
                                     text-shadow: 1px 1px 2px #000;
                                 }

                                 h2 {
                                     color: #d4af37;
                                     font-size: 18px;
                                     margin-top: 20px;
                                     border-bottom: 1px solid #d4af37;
                                     padding-bottom: 5px;
                                 }

                                 .highlight {
                                     color: #ffcc00;
                                     font-weight: bold;
                                 }
   \s
                                 .term {
                                     color: #d4af37;
                                     font-style: italic;
                                 }
      \s
                                 ul {
                                     padding-left: 20px;
                                 }
       \s
                                 li {
                                     margin-bottom: 8px;
                                 }
       \s
                                 .card-example {
                                     background-color: #fff;
                                     color: #000;
                                     padding: 5px 10px;
                                     border-radius: 5px;
                                     display: inline-block;
                                     margin: 5px 0;
                                     font-weight: bold;
                                 }
       \s
                                 .gold {
                                     color: #d4af37;
                                 }
                                 .button:hover {
                                     background-color: #ffcc00;
                                 }
     \s
                                 ::-webkit-scrollbar {
                                     width: 8px;
                                 }
                                 ::-webkit-scrollbar-track {
                                     background: #0a5c36;
                                 }
                                 ::-webkit-scrollbar-thumb {
                                     background: #d4af37;
                                     border-radius: 4px;
                                 }
                             </style>
                         </head>
                         <body>
                             <div class="container">
  \s
                                 <p>A blackjack célja az osztó legyőzése olyan lapokkal, amelyek összege <span class="highlight">közelebb van a 21-hez</span>, de <span class="highlight">nem haladja meg</span> azt.</p>
                   \s
                                 <h2>Alapvető szabályok</h2>
                                 <ul>
                                     <li>A tízesek, bubik, dámák és királyok <span class="highlight">értéke 10</span>.</li>
                                     <li>Az ászok értéke <span class="highlight">1 vagy 11</span> (játékos választhat).</li>
                                     <li>Ha az összeg <span class="term">meghaladja a 21-et</span> (<span class="term">"besokallás"</span>), automatikusan vesztesz.</li>
                                     <li>Ha az osztó besokall, minden játékos nyer, aki nem sokallt be.</li>
                                     <li>Döntetlen esetén (<span class="term">"push"</span>) visszakapod a téted.</li>
                                 </ul>
                   \s
                                 <div class="card-example">Példa: ász + 4 = <span class="gold">5 vagy 15</span> (puha 15)</div>
                   \s
                                 <h2>Játékmenet lehetőségek</h2>
                                 <ul>
                                     <li><span class="highlight">Lapkérés (Hit)</span>: További kártyát kérsz, amíg nem éred el vagy meghaladod a 21-et.</li>
                                     <li><span class="highlight">Megállás (Stand)</span>: Nem kérsz több lapot, a jelenlegi kézzel játszol.</li>
                                 </ul>
                   \s
                                 <p>A <span class="term">"természetes blackjack"</span> (ász + 10-es lap) általában 3:2 arányban fizet, és automatikusan nyer, kivéve ha az osztónak is blackjacke van.</p>
                             </div>
                         </body>
                         </html>
       \s""");
        }
    }

}