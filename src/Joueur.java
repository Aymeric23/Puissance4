/*
 * Joueur.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */

/** TODO commenter la responsabilité de cette classe
 * @author aymer
 *
 */
public class Joueur {
    
    private String pseudo;
    private String value;
    
    /**
     * TODO commenter l'état initial atteint
     * @param pseudo 
     * @param value 
     */
    public Joueur(String pseudo, String value) {
        this.pseudo = pseudo;
        this.value = value;
    }
    
    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
