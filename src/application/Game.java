/* Game.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import application.controleur.GameDuo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Partie jouable par des joueurs avec une grille de jeu
 * @author aymeric.thevenet,
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
     * @return le nom de la partie
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * @return le gamemode de la partie
     */
    public int getGamemode() {
        return gamemode;
    }
    
    
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * @return la partie sous forme String
     */
    public String toString() {
        return ("Game [Name : " + this.name +", gamemode: " + this.gamemode + "]");
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    public void startGamePuzzle() {
        Grid grid = new Grid();
        
    }

    /** TODO commenter le rôle de cette méthode (SRP)
     * @throws Exception 
     * 
     */
    public void startGameDuo() throws Exception {        
        Grid grid = new Grid();
        //Scanner entree = new Scanner(System.in); 
        GameDuo controler = new GameDuo();
        //System.out.print("Entrez votre pseudo : ");
        Player j1 = new Player(controler.getPseudoP1(),null);
//        System.out.print("Entrez votre pseudo : ");
        Player j2 = new Player(controler.getPseudoP2(),null);
        
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
//            grid.showGrid();
//            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            colonneChoisie = Integer.valueOf(controler.getAccessibleText());
            System.out.println(joueurActuel + "  " +joueurAttente + "  " + colonneChoisie);
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

    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    public void startGameSolo() {
        // TODO Auto-generated method stub
        
    }
}
