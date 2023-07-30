package com.example.projetjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**

 La classe TamaAccueil représente la fenêtre d'accueil du jeu TamaGame.
 Elle permet de lancer une nouvelle partie en cliquant sur le bouton "Commencer une partie".
 */
public class TamaAccueil extends Application {

    /**
     * Lance la fenêtre d'accueil.
     * @param primaryStage la fenêtre principale de l'application
     */
    @Override
    public void start(Stage primaryStage) {

        Text title = new Text("Bienvenue dans TamaGame !");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Button startButton = new Button("Commencer une partie");
        startButton.setOnAction(e -> {
            TamaCreation tamaCreation = new TamaCreation();
            tamaCreation.start(primaryStage);
        });

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(title);

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.getChildren().add(startButton);

        root.setBottom(box);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("TamaGoshi - Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Méthode principale permettant de lancer l'application.
     * @param args les arguments passés à l'application
     */
    public static void main(String[] args) {
        launch(args);
    }

}
