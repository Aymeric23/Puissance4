/* Game.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import application.controleur.GameDuo;
import application.controleur.GameSolo;
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
    
    /** mode de jeu de cette partie */
    private Grid grid = new Grid();
    
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
     * @return la grille
     */
    public Grid getGrid() {
        return grid;
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
    
    
    /**
     * Lancement d'une partie en fonction du gamemode choisi
     */
    public void startGame() {
        switch (gamemode) {
        case 1: {
            System.out.println("[Info] - Lancement d'une partie solo");
            startGameSolo();
        }
            break;
        case 2: {
            System.out.println("[Info] - Lancement d'une partie duo");
            startGameDuo();
        }
            break;
        case 3: {
            System.out.println("[Info] - Lancement d'une partie puzzle");
            startGamePuzzle();
        }
            break;
        default:
            throw new IllegalArgumentException("Ce mode de jeu est invalide: " 
                                               + gamemode);
        }
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    public void startGamePuzzle() {
        
    }

    /** TODO commenter le rôle de cette méthode (SRP)
     * TODO Exceptions si nécessaire
     */
    public void startGameDuo() {        
        GameDuo controler = new GameDuo();
        Player j1 = new Player(controler.getPseudoP1(),null);
        Player j2 = new Player(controler.getPseudoP2(),null);
        Color c1 = new Color("R", "#e45555");
        Color c2 = new Color("J", "#fbfd87");
        
        Player joueurActuel = j1;
        Player joueurAttente = j2;
        Player joueurTemporaire;
        
        Random aleatoire = new Random();
        boolean couleur = aleatoire.nextBoolean();
        if (couleur) {
            j1.setColor(c1);
            j2.setColor(c2);
            joueurActuel = j1;
            joueurAttente = j2;
        } else {
            j1.setColor(c1);
            j2.setColor(c2);
            joueurActuel = j2;
            joueurAttente = j1;
        }  
        
        String vainqueur = "Egalité";
        int colonneChoisie;
        boolean ok = false;
        while (!ok) {
//            grid.showGrid();
//            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            colonneChoisie = Integer.valueOf(controler.getIndiceColonne(null));
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
        GameSolo controler = new GameSolo();
        Player j1 = new Player(controler.getPseudoP1(),null);
        Player j2 = new Player("IA", null);
        
    }
}
