/* Player.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

/**
 * Joueur qui joue a une partie de Puissance 4
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Player {
    
    /** nom de ce joueur */
    private String pseudo;
    
    /** couleur de ce joueur */
    private String color;
    
    private final String[] colorHexa = {"#e45555", "#fbfd87"};
    
    /**
     * Définition des caractéristiques du joueur
     * @param pseudo   pseudonyme du joueur 
     * @param color    couleur du joueur ("R" pour rouge, "J" pour jaune) 
     */
    public Player(String pseudo, String color) {
        this.pseudo = pseudo;
        this.color = color;
    }
    
    /**
     * Modifie la couleur du joueur
     * @param nouvelleCouleur  nouvelle couleur du joueur
     */
    public void setColor(String nouvelleCouleur) {
        this.color = nouvelleCouleur;
    }
    
    /**
     * @return le pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }
    
    /**
     * @return la couleur du joueur
     */
    public String getColor() {
        return color;
    }
    
}
