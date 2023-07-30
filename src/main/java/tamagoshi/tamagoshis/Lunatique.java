//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.tamagoshis;

/**
 * La classe Lunatique représente un type de Tamagoshi qui peut perdre de l'énergie et du fun aléatoirement.
 * Si le générateur de nombres aléatoires de la classe choisit vrai, le Tamagoshi perd une unité d'énergie.
 * Si le générateur de nombres aléatoires de la classe choisit vrai, le Tamagoshi perd une unité de fun.
 */
public class Lunatique extends Tamagoshi {

    /**
     * Constructeur de la classe Lunatique.
     * @param name le nom du Tamagoshi Lunatique.
     */
    public Lunatique(String name) {
        super(name);
    }

    /**
     * Décrémente l'énergie du Tamagoshi d'une unité avec une chance aléatoire.
     * @return true si l'énergie a été décrémentée avec succès, false sinon.
     */
    public boolean consommeEnergy() {
        if (this.generateur.nextBoolean()) {
            --this.energy;
        }
        return super.consommeEnergy();
    }

    /**
     * Décrémente le fun du Tamagoshi d'une unité avec une chance aléatoire.
     * @return true si le fun a été décrémenté avec succès, false sinon.
     */
    public boolean consommeFun() {
        if (this.generateur.nextBoolean()) {
            this.setFun(this.getFun() - 1);
        }
        return super.consommeFun();
    }
}
