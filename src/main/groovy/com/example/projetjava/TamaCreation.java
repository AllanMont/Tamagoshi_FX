package com.example.projetjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tamagoshi.tamagoshis.Tamagoshi;

import java.util.ArrayList;
import java.util.List;

/**

 Cette classe permet de créer une interface graphique pour la création de Tamagoshis.
 */
public class TamaCreation extends Application {

    /**

     Cette méthode lance l'application pour la création de Tamagoshis.

     Elle crée une interface graphique qui permet à l'utilisateur de choisir la difficulté et de créer les Tamagoshis.

     @param primaryStage l'étape primaire de l'application JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        Label difficultyLabel = new Label("Difficulté :");
        ChoiceBox<Integer> difficultyChoiceBox = new ChoiceBox<>();
        difficultyChoiceBox.getItems().addAll(1, 2, 3, 4);
        difficultyChoiceBox.setValue(1);

        Button createButton = new Button("Créer les Tamagoshis");
        createButton.setOnAction(e -> {
            int difficulty = difficultyChoiceBox.getValue();
            List<Tamagoshi> tamagoshis = new ArrayList<>();
            for (int i = 0; i < difficulty; i++) {
                String tamaName = askTamaName(i + 1);
                Tamagoshi tama = new Tamagoshi(tamaName);
                tamagoshis.add(tama);
            }

            TamaGame display = new TamaGame(tamagoshis, difficulty);
            display.show(primaryStage);
            primaryStage.close();
        });

        root.getChildren().addAll(difficultyLabel, difficultyChoiceBox, createButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**

     Cette méthode ouvre une fenêtre d'alerte qui demande à l'utilisateur d'entrer un nom pour un Tamagoshi.

     @param i l'indice du Tamagoshi

     @return le nom saisi par l'utilisateur
     */
    private String askTamaName(int i) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nom du Tamagoshi");
        alert.setHeaderText("Tamagoshi n°" + i);
        alert.setContentText("Entrez un nom :");

        TextField input = new TextField();
        input.setText("Tama"+i);
        input.setOnMouseClicked(e -> {
            if (input.getText().equals("Tama"+i)) {
                input.setText("");
            }
        });

        String name = "";
        boolean validName = false;
        while (!validName) {
            alert.getDialogPane().setContent(input);
            alert.showAndWait();
            name = input.getText();
            if (name.isEmpty()) {
                alert.setHeaderText("Tamagoshi n°" + i + " (Nom vide)");
            } else {
                validName = true;
            }
        }

        return name;
    }

    /**

     Méthode principale permettant de lancer l'application.

     @param args les arguments passés à l'application
     */
    public static void main(String[] args) {
        launch(args);
    }

}
