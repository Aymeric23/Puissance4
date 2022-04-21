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
    
    /**
     * Définition des caractéristiques de la partie
     * @param name nom de la partie
     */
    public Game(String name) {
        this.name = name;
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
        
        int colonneChoisie;
        boolean ok = false;
        while (!ok) {
            grid.showGrid();
            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            colonneChoisie = entree.nextInt();
            grid.setGrid(joueurActuel, colonneChoisie);
            
            ok = grid.isAlign(joueurActuel);
            
            joueurTemporaire = joueurAttente;
            joueurAttente = joueurActuel;
            joueurActuel = joueurTemporaire;
            System.out.println(ok);
        }
        
        String vainqueur;
        if (grid.isAlign(joueurActuel)) {
            vainqueur = "Le vainqueur est " + joueurActuel.getPseudo();
        } else {
            vainqueur = "Egalité";
        }
        
    }
}
