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
    
    private int prefix;
    
    
    /**
     * Définition des caractéristiques du joueur
     * @param pseudo   pseudonyme du joueur 
     * @param prefix   1 si joueur1 2 si joueur2 
     * @throws IllegalArgumentException si il y a plus de 10 caractères
     */
    public Player(String pseudo) {
        if (pseudo.length() > 10) {
            throw new IllegalArgumentException("Il y a trop de caractères");
        }
        this.pseudo = pseudo;
    }
    
    /**
     * @return la couleur du joueur
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Modifie la couleur du joueur
     * @param nouvelleCouleur du joueur
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
     * Modifie le pseudo du joueur
     * @param nouveauPseudo du joueur
     */
    public void setPseudo(String nouveauPseudo) {
        this.pseudo = nouveauPseudo;
    }
    
    /**
     * @return prefix du joueur
     */
    public int getPrefix() {
        return prefix;
    }
    
    /**
     * Modifie le prefix du joueur
     * @param nouveauPrefix 
     */
    public void setPrefix(int nouveauPrefix) {
        this.prefix = nouveauPrefix;
    }

    @Override
    public String toString() {
        return "Player [pseudo=" + pseudo + ", color=" + color.toString() + ", prefix="
                        + prefix + "]";
    }
}
