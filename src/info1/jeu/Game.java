/* Game.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package info1.jeu;

import java.util.Random;
import java.util.Scanner;

/**
 * Partie jouable par des joueurs avec une grille de jeu
 * @author aymeric.thevenet
 *         romain.trimouille
 */
public class Game {

    /** nom de cette partie */
    private String name;
    
    /** mode de jeu de cette partie */
    private int gamemode;
    
    /**
     * Définition des caractéristiques de la partie
     * @param name nom de la partie, correspond à la date et l'heure du jour 
     * du lancement de la partie
     * @param gamemode mode de jeu de la partie, 1 pour une partie solo
     *                                           2 pour une partie à deux
     *                                           3 pour une partie en casse tête
     */
    public Game(String name, int gamemode) {
        this.name = name;
        this.gamemode = gamemode;
    }
    
    /** 
     * Lancement de la partie
     * @param args non utilisé
     */
    public static void main(String[] args) {
        
        Grid grid = new Grid();
        Scanner entree = new Scanner(System.in); 
        
        System.out.print("Entrez votre pseudo : ");
        Player j1 = new Player(entree.next(),"T");
        System.out.print("Entrez votre pseudo : ");
        Player j2 = new Player(entree.next(),"T");
        
        Player joueurActuel = j1;
        Player joueurAttente = j2;
        Player joueurTemporaire;
        
        Random aleatoire = new Random();
        boolean couleur = aleatoire.nextBoolean();
        if (couleur) {
            j1.setColor("R");
            j2.setColor("J");
            joueurActuel = j1;
            joueurAttente = j2;
        } else {
            j1.setColor("J");
            j2.setColor("R");
            joueurActuel = j2;
            joueurAttente = j1;
        }  
        
        String vainqueur = "Egalité";
        int colonneChoisie;
        boolean ok = false;
        while (!ok) {
            grid.showGrid();
            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            colonneChoisie = entree.nextInt();
            grid.setGrid(joueurActuel, colonneChoisie);
            
            ok = grid.isAlign(joueurActuel);
            if (grid.isAlign(joueurActuel)) {
                grid.showGrid();
                vainqueur = "Le vainqueur est " + joueurActuel.getPseudo();
            }
            
            joueurTemporaire = joueurAttente;
            joueurAttente = joueurActuel;
            joueurActuel = joueurTemporaire;
        }
        
        System.out.println("Le vainqueur est : " + vainqueur);
    }
}
