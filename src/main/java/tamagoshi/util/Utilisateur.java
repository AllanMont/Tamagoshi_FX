//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**

 La classe Utilisateur permet de lire une saisie clavier de la part de l'utilisateur.
 Elle contient une méthode statique qui lit l'entrée de l'utilisateur à partir de la console.
 */
public class Utilisateur {
    /**
     * Constructeur privé de la classe Utilisateur.
     * La classe Utilisateur ne doit pas être instanciée.
     */
    public Utilisateur() {
    }

    /**
     * Méthode statique qui lit l'entrée de l'utilisateur à partir de la console.
     * @return la saisie de l'utilisateur sous forme de chaîne de caractères
     */
    public static String saisieClavier() {
        try {
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        } catch (IOException var1) {
            var1.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    /**
     * Point d'entrée de la classe Utilisateur.
     * Permet de tester la méthode saisieClavier() en lisant une saisie clavier et en l'affichant à l'écran.
     */
    public static void main(String[] args) {
        String saisie = saisieClavier();
        System.out.println("la saisie est : " + saisie);
    }

}
