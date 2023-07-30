//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

/**

 La classe TamaGame représente le jeu Tamagoshi.
 Elle permet de créer une partie avec un nombre de Tamagoshi donné,
 de jouer des tours et d'afficher le résultat final.
 @author MontagneAllan
 */
public class TamaGame {
    /**
     * Le nombre de tours joués dans la partie.
     */
    private int turns;
    /**
     * La liste de tous les Tamagoshi créés pour la partie.
     */
    private List<Tamagoshi> allTamagoshis = new ArrayList();

    /**
     * La liste des Tamagoshi encore vivants dans la partie.
     */
    private List<Tamagoshi> aliveTamagoshis = new ArrayList();

    /**
     * Constructeur de la classe TamaGame.
     * Il permet de créer une partie avec un nombre de Tamagoshi donné.
     *
     * @param tamagoshis la liste de Tamagoshi à ajouter à la partie.
     */
    public TamaGame(List<Tamagoshi> tamagoshis) {
        this.initialisation();
    }

    /**
     * Méthode privée initialisation qui initialise les Tamagoshi de la partie.
     * Elle demande le nombre de Tamagoshi désiré à l'utilisateur et ajoute
     * les Tamagoshi créés à la liste allTamagoshis.
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

        for(int i = 0; i < n; ++i) {
            System.out.println("Entrez le nom du tamagoshi numéro " + i + " : ");
            if (Math.random() < 0.5) {
                this.allTamagoshis.add(new GrosJoueur(Utilisateur.saisieClavier()));
            } else {
                this.allTamagoshis.add(new GrosMangeur(Utilisateur.saisieClavier()));
            }
        }

        this.aliveTamagoshis = new ArrayList(this.allTamagoshis);
    }

    /**
     * Méthode tamaSelection qui permet de choisir un Tamagoshi parmi les Tamagoshi encore vivants dans la partie.
     * Elle affiche les Tamagoshi disponibles et demande à l'utilisateur de saisir l'index du Tamagoshi souhaité.
     * Si l'index saisi n'est pas valide, la méthode rappelle tamaSelection jusqu'à ce qu'un index valide soit saisi.
     *
     * @param question la question à afficher pour demander à l'utilisateur de choisir un Tamagoshi.
     * @return le Tamagoshi choisi par l'utilisateur.
     */
    public Tamagoshi tamaSelection(String question) {
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
     * Joue une partie de Tamagoshi.
     */
    public void play() {
        int cycle = 1;

        label37:
        while(!this.aliveTamagoshis.isEmpty()) {
            System.out.println("\n------------Cycle n°" + cycle++ + "-------------");
            Iterator var3 = this.aliveTamagoshis.iterator();

            while(var3.hasNext()) {
                Tamagoshi t = (Tamagoshi)var3.next();
                t.getEtat();
            }

            this.tamaSelection("\n\tNourrir quel tamagoshi ?").mange();
            this.tamaSelection("\n\tJouer avec quel tamagoshi ?").joue();
            Iterator<Tamagoshi> iterator = this.aliveTamagoshis.iterator();

            while(true) {
                Tamagoshi t;
                do {
                    if (!iterator.hasNext()) {
                        continue label37;
                    }

                    t = (Tamagoshi)iterator.next();
                } while(t.consommeEnergy() && t.consommeFun() && !t.vieillit());

                iterator.remove();
            }
        }

        System.out.println("\n\t--------- fin de partie !! ----------------\n\n");
        this.resultat();
    }

    /**
     * Calcule et retourne le score final de la partie.
     *
     * @return Le score final de la partie
     */
    private double score() {
        int score = 0;

        Tamagoshi t;
        for(Iterator var3 = this.allTamagoshis.iterator(); var3.hasNext(); score += t.getAge()) {
            t = (Tamagoshi)var3.next();
        }

        return (double)(score * 100 / (Tamagoshi.getLifeTime() * this.allTamagoshis.size()));
    }

    /**
     * Affiche le bilan de la partie avec le nombre de Tamagoshis survivants, leur nom, leur âge et leur type (GrosMangeur ou GrosJoueur), ainsi que le score obtenu.
     */
    private void resultat() {
        System.out.println("-------------bilan------------");
        Iterator var2 = this.allTamagoshis.iterator();

        while(var2.hasNext()) {
            Tamagoshi t = (Tamagoshi)var2.next();
            String classe = t.getClass().getSimpleName();
            System.out.println(t.getName() + " qui était un " + classe + " " + (t.getAge() == Tamagoshi.getLifeTime() ? " a survécu et vous remercie :)" : " n'est pas arrivé au bout et ne vous félicite pas :("));
        }

        System.out.println("\nniveau de difficulté : " + this.allTamagoshis.size() + ", score obtenu : " + this.score() + "%");
    }

    /**
     * Vérifie si des Tamagoshis sont encore en vie.
     *
     * @return true si des Tamagoshis sont en vie, false sinon.
     */
    public boolean isTamagoshisAlive() {
        return !this.aliveTamagoshis.isEmpty();
    }


    /**
     * Méthode main qui permet de lancer une partie de Tamagoshi.
     *
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        TamaGame jeu = new TamaGame((List)null);
        jeu.play();
    }

    /**
     * Retourne la liste des Tamagoshis encore en vie.
     *
     * @return la liste des Tamagoshis encore en vie.
     */
    public List<Tamagoshi> getAliveTamagoshis() {
        return this.aliveTamagoshis;
    }

    /**
     * Retourne le score de la partie.
     *
     * @return le score de la partie.
     */
    public double getScore() {
        return this.score();
    }

    /**
     * Retourne le nombre de tours de jeu joués.
     *
     * @return le nombre de tours de jeu joués.
     */
    public String toString() {
        return "tamagame";
    }

    /**
     * Retourne la somme de l'énergie de tous les Tamagoshis encore en vie.
     * @return la somme de l'énergie de tous les Tamagoshis encore en vie.
     */
    public int getTotalEnergy() {
        int totalEnergy = 0;
        for (Tamagoshi t : this.aliveTamagoshis) {
            totalEnergy += t.getEnergy();
        }
        return totalEnergy;
    }

    /**
     * Retourne la somme du fun de tous les Tamagoshis encore en vie.
     * @return la somme du fun de tous les Tamagoshis encore en vie.
     */
    public int getTotalFun() {
        int totalFun = 0;
        for (Tamagoshi t : this.aliveTamagoshis) {
            totalFun += t.getFun();
        }
        return totalFun;
    }

    /**
     * Retourne le nombre de tours de jeu joués.
     * @return le nombre de tours de jeu joués.
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Modifie le nombre de tours de jeu joués.
     * @param turns le nouveau nombre de tours de jeu joués.
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }


    /**
     * Retourne le score de la partie.
     * @return le score de la partie.
     */
    public double computeScore() {
        return (double) (this.getTotalEnergy() + this.getTotalFun()) / (this.getTurns() * this.aliveTamagoshis.size());
    }


}
