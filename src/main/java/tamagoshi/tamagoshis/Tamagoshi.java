package tamagoshi.tamagoshis;

import java.util.Random;

/**
 * La classe Tamagoshi représente un Tamagoshi avec un nom, une quantité d'énergie et de fun, et un âge.
 * Un Tamagoshi peut manger, jouer, vieillir, et consommer de l'énergie et du fun.
 */
public class Tamagoshi {
    private int id;
    private String name;
    /**

     La variable protégée "generateur" de la classe Tamagoshi représente un générateur de nombres aléatoires utilisé pour
     initialiser les valeurs aléatoires des attributs "maxEnergy", "maxFun", "energy" et "fun" de chaque Tamagoshi.
     */
    protected Random generateur;
    private int age;
    private int maxEnergy;
    private int maxFun;
    private int fun;
    /**

     La variable protégée "energy" de la classe Tamagoshi représente la quantité d'énergie actuelle du Tamagoshi.
     Cette valeur diminue lorsqu'il consomme de l'énergie et augmente lorsqu'il mange ou se repose. Si cette valeur atteint
     0 ou moins, le Tamagoshi meurt. La valeur maximale de l'énergie est définie par l'attribut "maxEnergy".
     */
    protected int energy;
    private static int lifeTime = 10;

    /**
     * Constructeur de la classe Tamagoshi.
     * Initialise le nom, l'âge, la quantité d'énergie et de fun du Tamagoshi.
     * @param name le nom du Tamagoshi
     */
    public Tamagoshi(String name) {
        this.name = name;
        this.generateur = new Random();
        this.id = generateur.nextInt(4);
        this.age = 0;
        this.maxEnergy = this.generateur.nextInt(5) + 5;
        this.maxFun = this.generateur.nextInt(5) + 5;
        this.energy = this.generateur.nextInt(5) + 3;
        this.fun = this.generateur.nextInt(5) + 3;
    }

    /**
     * Retourne l'état du Tamagoshi sous forme de chaîne de caractères.
     * L'état du Tamagoshi dépend de sa quantité d'énergie et de fun.
     * @return une chaîne de caractères représentant l'état du Tamagoshi
     */
    public String getEtat() {
        String s = "";
        if (this.energy < 5) {
            s = s + "je suis affamé";
        }

        if (this.fun < 5) {
            if (!s.isEmpty()) {
                s = s + " et ";
            }

            s = s + "je m'ennuie à mourrir";
        }

        if (s.isEmpty()) {
            s = "je suis heureux !";
        } else {
            s = s + " !";
        }
        return s;
    }

    /**
     * Retourne l'ID du Tamagoshi.
     * @return l'ID du Tamagoshi
     */
    public int getId() {
        return this.id;
    }

    /**
     * Fait manger le Tamagoshi et augmente sa quantité d'énergie.
     * Si le Tamagoshi n'a pas faim, rien ne se passe.
     * @return true si le Tamagoshi a mangé, false sinon
     */
    public boolean mange() {
        if (this.energy < this.maxEnergy) {
            this.energy += this.generateur.nextInt(3) + 1;
            this.parler("Merci !");
            return true;
        } else {
            this.parler("je n'ai pas faim !!");
            return false;
        }
    }

    /**
     * Fait vieillir le Tamagoshi et retourne true si le Tamagoshi atteint l'âge limite.
     * L'âge limite est fixée à la variable statique lifeTime.
     * @return true si le Tamagoshi atteint l'âge limite, false sinon
     */
    public boolean vieillit() {
        ++this.age;
        return this.age == getLifeTime();
    }

/**

 Consomme de l'énergie et retourne true si le Tamagoshi est encore en vie, false sinon.
 Si le Tamagoshi n'a plus d'énergie, il meurt.
    Si le Tamagoshi a peu d'énergie, il a besoin de manger.
    @return true si le Tamagoshi est encore en vie, false sinon
    */

    public boolean consommeEnergy() {
        this.energy -= this.generateur.nextInt(4);
        if (this.energy <= 0) {
            this.parler("je suis KO: Arrrggh !");
            return false;
        } else if (this.energy < 2 ) {
            this.parler("j'ai besoin d'un bon café !");
            return true;
        } else {
            --this.energy;
            return true;
        }
    }

    /**

     Consomme du fun et retourne true si le Tamagoshi est encore en vie, false sinon.
     Si le Tamagoshi n'a plus de fun, il tombe en dépression et meurt.
     @return true si le Tamagoshi est encore en vie, false sinon
     */
    public boolean consommeFun() {
        this.fun -= this.generateur.nextInt(4);
        if (this.fun <= 0) {
            this.parler("snif : je fais une dépression, ciao!");
            return false;
        } else if (this.fun < 2) {
            this.parler("j'ai besoin d'un bon jeu vidéo !");
            return true;
        } else {
            --this.fun;
            return true;
        }
    }

    /**

     Fait jouer le Tamagoshi et augmente sa quantité de fun.
     Si le Tamagoshi ne veut pas jouer, rien ne se passe.
     @return true si le Tamagoshi a joué, false sinon
     */
    public boolean joue() {
        if (this.fun < this.maxFun) {
            this.fun += this.generateur.nextInt(3) + 1;
            this.parler("On se marre !");
            return true;
        } else {
            this.parler("laisse-moi tranquille, je bouquine !!");
            return false;
        }
    }

    /**

     Retourne le nom du Tamagoshi.
     @return le nom du Tamagoshi
     */
    public String getName() {
        return this.name;
    }

    /**

     Retourne la quantité d'énergie du Tamagoshi.
     @return la quantité d'énergie du Tamagoshi
     */
    public int getEnergy() {
        return this.energy;
    }

    /**

     Retourne l'âge limite des Tamagoshis.
     @return l'âge limite des Tamagoshis
     */
    public static int getLifeTime() {
        return lifeTime;
    }

    /**
     Retourne une chaîne de caractères représentant le Tamagoshi et sa quantité d'énergie et de fun.
     @return une chaîne de caractères représentant le Tamagoshi et sa quantité d'énergie et de fun
     */
    public String toString() {
        return this.name + " : energy=" + this.energy + ", fun=" + this.fun;
    }

    /**

     Fait parler le Tamagoshi en affichant une phrase à l'écran.
     @param phrase la phrase à afficher
     */
    private void parler(String phrase) {
        System.out.println("\n\t" + this.name + " : \"" + phrase + "\"");
    }

    /**

     Retourne la quantité de fun du Tamagoshi.
     @return la quantité de fun du Tamagoshi
     */
    public int getFun() {
        return this.fun;
    }

    /**

     Modifie la quantité de fun du Tamagoshi.
     @param fun la nouvelle quantité de fun
     */
    public void setFun(int fun) {
        this.fun = fun;
    }

    /**

     Retourne l'âge du Tamagoshi.
     @return l'âge du Tamagoshi
     */
    public int getAge() {
        return this.age;
    }
}