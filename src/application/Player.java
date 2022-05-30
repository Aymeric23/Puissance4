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
    private Color color;
    
    private int prefix;
    
    
    /**
     * Définition des caractéristiques du joueur
     * @param pseudo   pseudonyme du joueur 
     * @param prefix   1 si joueur1 2 si joueur2 
     */
    public Player(String pseudo, int prefix) {
        this.pseudo = pseudo;
        
        if(prefix > 2 || prefix < 1) {
            throw new IllegalArgumentException("Prefix Joueur invalide");
        }
        this.prefix = prefix;
    }
    
    /**
     * Modifie la couleur du joueur
     * @param nouvelleCouleur  nouvelle couleur du joueur
     */
    public void setColor(Color nouvelleCouleur) {
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
    public Color getColor() {
        return color;
    }
   
    
    /**
     * @return the prefix
     */
    public int getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return "Player [pseudo=" + pseudo + ", color=" + color.toString() + ", prefix="
                        + prefix + "]";
    }
}
