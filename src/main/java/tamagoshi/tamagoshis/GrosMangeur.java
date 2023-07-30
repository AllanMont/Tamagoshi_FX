//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tamagoshi.tamagoshis;

/**

 La classe GrosMangeur représente un Tamagoshi mangeur gourmand.

 Il hérite de la classe Tamagoshi et redéfinit la méthode consommeFun.
 */
public class GrosMangeur extends Tamagoshi {

    /**

     Constructeur de la classe GrosMangeur.
     @param name le nom du Tamagoshi.
     */
    public GrosMangeur(String name) {
        super(name);
    }

    /**

     Consomme une unité de fun en diminuant l'énergie de ce Tamagoshi.
     @return vrai si le Tamagoshi a encore de l'énergie pour consommer du fun, faux sinon.
     */
    public boolean consommeFun() {
        --this.energy;
        return super.consommeFun();
    }
}
