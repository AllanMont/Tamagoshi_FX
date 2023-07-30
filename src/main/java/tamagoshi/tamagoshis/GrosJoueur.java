//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.tamagoshis;

/**

 La classe GrosJoueur représente un type de Tamagoshi qui consomme du fun plus rapidement que les autres.

 Cette classe hérite de la classe Tamagoshi.
 */
public class GrosJoueur extends Tamagoshi {
    /**

     Crée un nouveau GrosJoueur avec le nom spécifié.
     @param name le nom du GrosJoueur.
     */
    public GrosJoueur(String name) {
        super(name);
    }

    /**

     Consomme une unité de fun et met à jour l'état du GrosJoueur en conséquence.
     @return true si le GrosJoueur est encore en vie, false sinon.
     */
    public boolean consommeFun() {
        this.setFun(this.getFun() - 1);
        return super.consommeFun();
    }
}
