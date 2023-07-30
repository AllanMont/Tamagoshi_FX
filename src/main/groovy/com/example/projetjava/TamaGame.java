package com.example.projetjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tamagoshi.tamagoshis.Tamagoshi;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.List;
import java.util.Random;

/**

 Cette classe implémente l'interface graphique du jeu Tamagoshi.

 Elle permet de créer des Tamagoshis et de jouer à travers des actions

 de nourrir et de jouer avec eux, tout en surveillant leur état de santé.

 @author MontagneAllan
 */
public class TamaGame {
    private ObservableList<Tamagoshi> tamagoshis = FXCollections.observableArrayList();
    private int nbTour = 0;
    private int score = 0;
    private Random generateur = new Random();

    private Label tourLabel;
    private Label scoreLabel;
    private Button nextButton;

    private Scene scene;
    private Stage stage;

    /**

     Constructeur de la classe TamaGame
     @param tamagoshis la liste de tamagoshis à jouer
     @param difficulty la difficulté de la partie
     */
    public TamaGame(List<Tamagoshi> tamagoshis, int difficulty) {
        this.tamagoshis.addAll(tamagoshis);
    }

    /**

     Méthode pour afficher la scène du jeu.

     @param primaryStage la fenêtre principale de l'application
     */
    public void show(Stage primaryStage) {
        BorderPane root = new BorderPane();
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefSize(800, 600);



        StackPane centerStackPane = new StackPane();
        centerStackPane.setAlignment(Pos.TOP_CENTER);

        VBox tamagoshiBox = new VBox();
        tamagoshiBox.setAlignment(Pos.TOP_CENTER);
        tamagoshiBox.setSpacing(20);

        final int nbFood = generateur.nextInt(4) + 1; // Nombre de fois que le tamagoshi peut manger
        final int nbPlay = generateur.nextInt(4) + 1; // Nombre de fois que le tamagoshi peut jouer

        final int[] finalNbFood = {nbFood};
        final int[] finalNbPlay = {nbPlay};

        Label nbFoodLabel = new Label("Nombre de nourriture restante : " + nbFood);
        Label nbPlayLabel = new Label("Nombre de jouets restants : " + nbPlay);


        for (Tamagoshi t : tamagoshis) {
            ImageView imageView = new ImageView(new Image("file:assets/tamagoshi/tamagoshi_" + t.getId() + ".png"));
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            Label responseLabel = new Label();

            GridPane gridPane = new GridPane();
            gridPane.add(responseLabel, 1, 6);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            Label nameLabel = new Label("Nom : " + t.getName());
            nameLabel.setTextAlignment(TextAlignment.CENTER);
            nameLabel.setStyle("-fx-font-size: 13pt; -fx-font-weight: bold;");

            Label ageLabel = new Label("Age : " + Integer.toString(t.getAge()));
            Label energyLabel = new Label("Energie : " + Integer.toString(t.getEnergy()));
            Label funLabel = new Label("Fun : " + Integer.toString(t.getFun()));


            if (t.getEtat() != null) {
                responseLabel.setText(t.getName() + " dit : " + t.getEtat());
            }

            Button feedButton = new Button("Nourrir");
            Button playButton = new Button("Jouer");


            feedButton.setOnAction(e -> {
                if (finalNbFood[0] > 0) {
                    if (t.mange()) {
                        responseLabel.setText(t.getName() + " dit : " + t.getEtat());
                        finalNbFood[0]--;
                        nbFoodLabel.setText("Nombre de nourriture restante : " + finalNbFood[0] + " fois");
                        energyLabel.setText("Energie : " + Integer.toString(t.getEnergy()));
                    } else {
                        energyLabel.setText("Energie : " + Integer.toString(t.getEnergy()));
                        responseLabel.setText(t.getName() + " dit : Je n'ai pas faim");
                    }
                } else {
                    responseLabel.setText(t.getName() + " dit : Tu n'as plus de nourriture à me donner");
                }
                if (finalNbFood[0] == 0 && finalNbPlay[0] == 0) {
                    feedButton.setDisable(true);
                    playButton.setDisable(true);
                }
            });


            playButton.setOnAction(e -> {
                if (finalNbPlay[0] > 0) {
                    if (t.joue()) {
                        responseLabel.setText(t.getName() + " dit : " + t.getEtat());
                        finalNbPlay[0]--;
                        nbPlayLabel.setText("Nombre de jouets restants : " + finalNbPlay[0] + " fois");
                        funLabel.setText("Fun : " + Integer.toString(t.getFun()));

                    } else {
                        funLabel.setText("Fun : " + Integer.toString(t.getFun()));
                        responseLabel.setText(t.getName() + " dit : Je n'ai pas envie de jouer");
                    }
                } else {
                    responseLabel.setText(t.getName() + " dit : Tu n'as plus de jouets à me donner");
                }
                if (finalNbFood[0] == 0 && finalNbPlay[0] == 0) {
                    feedButton.setDisable(true);
                    playButton.setDisable(true);
                }
            });

            gridPane.add(nameLabel, 1, 0);
            gridPane.add(ageLabel, 1, 1);
            gridPane.add(energyLabel, 1, 2);
            gridPane.add(funLabel, 1, 3);
            gridPane.add(feedButton, 1, 4);
            gridPane.add(playButton, 1, 5);
            gridPane.add(imageView, 0, 0, 1, 6);



            Rectangle rect = new Rectangle(700, 200);
            rect.setStroke(Color.BLACK);
            rect.setFill(Color.WHITE);
            gridPane.setConstraints(rect, 0, 0, 2, 9);
            gridPane.getChildren().add(0, rect);

            tamagoshiBox.getChildren().add(gridPane);
        }

        centerStackPane.getChildren().add(tamagoshiBox);

        tourLabel = new Label("Tour : " + nbTour);
        scoreLabel = new Label("Score : " + score);
        HBox tourBox = new HBox();
        tourBox.setAlignment(Pos.CENTER);
        tourBox.setSpacing(20);
        tourBox.getChildren().addAll(scoreLabel, tourLabel);
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(20);
        nextButton = new Button("Tour suivant");
        nextButton.setOnAction(e -> {
            nextTurn(primaryStage);
            tourLabel.setText("Tour : " + nbTour);
        });



        bottomBox.getChildren().addAll(tourBox, nextButton,nbFoodLabel, nbPlayLabel);
        root.setCenter(centerStackPane);
        root.setBottom(bottomBox);

        if (scene == null) {
            scene = new Scene(root);
        } else {
            scene.setRoot(root);
        }

        if (stage == null) {
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tamagoshi");
        }
        stage.show();
    }


    /**

     Gère le passage au tour suivant du jeu.
     Cette méthode fait vieillir les Tamagoshis, les fait consommer leur énergie
     et leur fun, met à jour le score et affiche la fin du jeu si tous les Tamagoshis
     sont morts.
     @param primaryStage la fenêtre principale de l'application
     */
    public void nextTurn(Stage primaryStage) {
        nbTour++;
        for (Tamagoshi t : tamagoshis) {
            t.consommeEnergy();
            t.consommeFun();
            t.vieillit();
            if (t.getEnergy() <= 0 || t.getFun() <= 0) {
                tamagoshis.remove(t);
                break;
            }
            System.out.println(t.getAge());
            System.out.println(t.getEnergy());
            System.out.println(t.getFun());
            score = score + t.getEnergy() * t.getFun() * t.getAge();
        }
        score += tamagoshis.size() * 10;
        if (tamagoshis.size() <= 0) {
            finPartie(primaryStage);
        } else {
            show(primaryStage);
        }
    }

    /**

     Affiche la fin du jeu, enregistrant le score du joueur et permettant de recommencer ou réinitialiser les scores.
     @param primaryStage la fenêtre principale de l'application
     */
    public void finPartie(Stage primaryStage) {
        tamagoshis.clear();
        show(primaryStage);
        String message = "La partie est terminée! \nNombre de tours: " + nbTour + "\nScore final: " + score;
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Fin de la partie");
        alert.showAndWait();

        ScoreManager.saveScore(score);

        List<Integer> highScores = ScoreManager.getScores();
        Alert highScoresAlert = new Alert(Alert.AlertType.INFORMATION, highScores.toString(), ButtonType.OK);
        highScoresAlert.setHeaderText("High Scores");
        highScoresAlert.showAndWait();

        Button restartButton = new Button("Recommencer");
        Button ResetScore = new Button("Réinitialiser scores");
        HBox buttonBox = new HBox(restartButton, ResetScore);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        ((BorderPane) scene.getRoot()).setBottom(buttonBox);

        restartButton.setOnAction(e -> {
            stage.close();
            TamaCreation tamaCreation = new TamaCreation();
            tamaCreation.start(primaryStage);
        });

        ResetScore.setOnAction(e -> {
            ScoreManager.clearScores();
        });
    }

}