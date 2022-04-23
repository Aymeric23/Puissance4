/* Menu.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3
 */

package info1.jeu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** TODO commenter la responsabilité de cette classe
 * @author aymeric.thevenet
 */
public class Menu {
    
    /**
     * TODO commenter l'état initial atteint
     */
    public Menu() {
        super();
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void playSolo() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        new Game(date.format(LocalDateTime.now()), 1);
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void playTwo() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        new Game(date.format(LocalDateTime.now()), 2);

    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void playPuzzle() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        new Game(date.format(LocalDateTime.now()), 3);

    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void listSavedGames() {
        // TODO Auto-generated method stub

    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void showRules() {
        // TODO Auto-generated method stub

    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private static void leaveGame() {
        // TODO Auto-generated method stub

    }

    /** TODO commenter le rôle de cette méthode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
//        playSolo();
//        playTwo();
//        playPuzzle();
//        listSavedGames();
//        showRules();
//        leaveGame();
    }
}
