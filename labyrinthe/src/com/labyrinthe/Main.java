package com.labyrinthe;

/**`
 * la classe <code>Main</code> sert à démarrer le programme
 */
public class Main {

    public static void main(String[] args) {
        /**
         * Instancie la première fenetre du programme
         */
        Fenetre f = new Fenetre();
        f.menu1();
        f.visible();
    }
}
