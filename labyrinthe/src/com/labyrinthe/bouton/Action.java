package com.labyrinthe.bouton;

import com.labyrinthe.element.Element;

/**
 * L'interface <code>Action</code> permet de récupérer l'action faites
 *par le boutons sélectionner et y appliquer l'element correspondant
* @author Anne-Sophie Besnard, Romain Lechartier
 */
public interface Action {
    /**
     * sauvegarder l'element en cours pour l'utiliser dans le listener
     * @param e Element
     */
    void saveElem(Element e);

    /**
     * return l'element en cours associe au bouton
     * @return Element
     */
    Element getElem();

    /**
     *Permet de mettre si Thésée est placé dans la grille
     * @param bool boolean
     */
    void theseeLa(boolean bool);

    /**
     *permet de savoir si Thésée est placée dans la grille
     * @return bool
     */
    boolean isTheseeLa();

    /**
     *Permet de mettre si la sortie est placée dans la grille
     * @param bool boolean
     */
    void sortieLa(boolean bool);

    /**
     *permet de savoir si la sortie est placée dans la grille
     * @return bool
     */
    boolean isSortieLa();


}
