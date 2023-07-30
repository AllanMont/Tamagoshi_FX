//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;
import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

/**
 La classe TamaGameWithLambda représente le jeu Tamagoshi.
 Elle permet de créer une partie avec un nombre de Tamagoshi donné,
 de jouer des tours et d'afficher le résultat final.
 */
public class TamaGameWithLambda {
    private List<Tamagoshi> allTamagoshis = new ArrayList();
    private List<Tamagoshi> aliveTamagoshis;

    private TamaGameWithLambda() {
        this.initialisation();
    }

    /**
     * Méthode privée initialisation qui initialise les Tamagoshi de la partie.
     */
    private void initialisation() {
        System.out.println("Entrez le nombre de tamagoshis désiré !");
        int n = 0;

        while(n < 1) {
            System.out.println("Saisisez un nombre > 0 :");

            try {
                n = Integer.parseInt(Utilisateur.saisieClavier());
            } catch (NumberFormatException var3) {
                System.out.println("Il faut saisir un nombre !");
            }
        }

        IntStream.range(0, n).forEach((i) -> {
            System.out.println("Entrez le nom du tamagoshi numéro " + i + " : ");
            if (Math.random() < 0.5) {
                this.allTamagoshis.add(new GrosJoueur(Utilisateur.saisieClavier()));
            } else {
                this.allTamagoshis.add(new GrosMangeur(Utilisateur.saisieClavier()));
            }

        });
        this.aliveTamagoshis = new ArrayList(this.allTamagoshis);
    }

    /**
     * Méthode privée tamaSelection qui permet de sélectionner un Tamagoshi
     * dans la liste des Tamagoshi encore vivants.
     * Elle demande à l'utilisateur de saisir le numéro du Tamagoshi désiré
     * et retourne le Tamagoshi correspondant.
     *
     * @param question la question à poser à l'utilisateur.
     * @return le Tamagoshi sélectionné.
     */
    private Tamagoshi tamaSelection(String question) {
        System.out.println(question);
        int index = 0;
        ListIterator<Tamagoshi> iterator = this.aliveTamagoshis.listIterator();

        while(iterator.hasNext()) {
            System.out.print("\t(" + iterator.nextIndex() + ") " + ((Tamagoshi)iterator.next()).getName() + "  ");
        }

        System.out.print("\n\tEntrez un choix : ");

        try {
            index = Integer.parseInt(Utilisateur.saisieClavier());
        } catch (NumberFormatException var4) {
            System.out.println("Il faut saisir un nombre !");
            return this.tamaSelection(question);
        }

        if (index >= 0 && index < this.aliveTamagoshis.size()) {
            return (Tamagoshi)this.aliveTamagoshis.get(index);
        } else {
            System.out.println("il n'y a pas de tamagoshi portant le numéro " + index);
            return this.tamaSelection(question);
        }
    }

    /**
     * Lance le jeu.
     */
    public void play() {
        int cycle = 1;

        while(!this.aliveTamagoshis.isEmpty()) {
            System.out.println("\n------------Cycle n°" + cycle++ + "-------------");
            this.aliveTamagoshis.forEach(Tamagoshi::getEtat);
            this.tamaSelection("\n\tNourrir quel tamagoshi ?").mange();
            this.tamaSelection("\n\tJouer avec quel tamagoshi ?").joue();
            this.aliveTamagoshis.removeIf((t) -> {
                return !t.consommeFun() || !t.consommeFun() || t.vieillit();
            });
        }

        System.out.println("\n\t--------- fin de partie !! ----------------\n\n");
        this.resultat();
    }

    private double score() {
        int score = this.allTamagoshis.stream().mapToInt(Tamagoshi::getAge).sum();
        return (double)(score * 100 / (Tamagoshi.getLifeTime() * this.allTamagoshis.size()));
    }

    private void resultat() {
        System.out.println("-------------bilan------------");
        this.allTamagoshis.forEach((t) -> {
            String classe = t.getClass().getSimpleName();
            System.out.println(t.getName() + " qui était un " + classe + " " + (t.getAge() == Tamagoshi.getLifeTime() ? "a survécu et vous remercie :)" : "n'est pas arrivé au bout et ne vous félicite pas :("));
        });
        System.out.println("\nniveau de difficulté : " + this.allTamagoshis.size() + ", score obtenu : " + this.score() + "%");
    }

    /**
     * Méthode main qui lance le jeu.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        TamaGameWithLambda jeu = new TamaGameWithLambda();
        jeu.play();
    }
}
