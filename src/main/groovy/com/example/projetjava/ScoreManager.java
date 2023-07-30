package com.example.projetjava;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**

 Cette classe gère la sauvegarde, la récupération et la suppression des scores dans un fichier texte.
 */
public class ScoreManager {

    private static final String SCORES_FILE = "scores.txt";

    /**

     Sauvegarde le score passé en paramètre dans un fichier texte.
     @param score le score à sauvegarder
     */
    public static void saveScore(int score) {
        try (FileWriter writer = new FileWriter(SCORES_FILE, true)) {
            writer.write(Integer.toString(score));
            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Unable to save score: " + e.getMessage());
        }
    }

    /**

     Récupère les scores enregistrés dans le fichier texte et les renvoie dans une liste triée par ordre décroissant.
     @return une liste de scores triée par ordre décroissant
     */
    public static List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            // ignore missing scores file
        } catch (IOException e) {
            System.err.println("Unable to read scores: " + e.getMessage());
        }
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }

    /**

     Supprime le fichier texte contenant les scores enregistrés.
     */
    public static void clearScores() {
        File scoresFile = new File(SCORES_FILE);
        if (scoresFile.exists()) {
            scoresFile.delete();
        }
    }
}
