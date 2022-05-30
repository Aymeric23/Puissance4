/* Game.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import java.util.Random;
import application.controleur.GameDuo;
import application.controleur.GameSolo;

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
    
    private Player player1;
    private Player player2;
    
    /**Joueur qui joue actuellement**/
    private Player playerPlaying;
    
    /** Joueur vainqueur **/
    private Player winner;
    
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
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }
    
    /**
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }
    
    /**
     * @return the playerPlaying
     */
    public Player getPlayerPlaying() {
        return playerPlaying;
    }
    
    /**
     * @return the winner
     */
    public Player getWinner() {
        return winner;
    }
    
    
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    /**
     * @param playerPlaying the playerPlaying to set
     */
    public void setPlayerPlaying(Player playerPlaying) {
        this.playerPlaying = playerPlaying;
    }
    
    /**
     * @param winner the winner to set
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    
    
    /**
     * @return la partie sous forme String
     */
    public String toString() {
        return ("Game [Name : " + this.name +", gamemode: " + this.gamemode + "]");
    }
    
    
    /**
     * Lancement d'une partie en fonction du gamemode choisi
     * @param player1 
     * @param player2 
     */
    public void startGame() {
        switch (gamemode) {
        case 1: {
            System.out.println("[Info] - Lancement d'une partie solo");
            startGameSolo();
        }
            break;
        case 2: {
            System.out.println("[Info] - Lancement d'une partie duo \n"
                               + "Joueur 1 : " + player1.getPseudo() + " | Joueur 2 : " + player2.getPseudo());
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
     * @param player1 
     * @param player2 
     */
    public void startGameDuo() {    
        /*Affectation des couleurs aléatoirement*/
        System.out.println("Couleur player1 : " + player1.getColor().getColorId());
        System.out.println("Couleur player2 : " + player2.getColor().getColorId());
        grid = new Grid();
        grid.showGrid();
    }

    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    public void startGameSolo() {
        GameSolo controler = new GameSolo();
        //Player player1 = new Player(controler.getPseudoP1(),null);
        //Player player2 = new Player("IA", null);
        
    }
    
    /** 
     * Donne la main au joueur en attente
     * @param partie
     */
    public void switchPlayingPlayer() {
        /*Si le joueur qui vient de jouer est le joueur 1*/
        if (getPlayerPlaying() == getPlayer1()) {
            /* Donne la main au joueur 2 */
            setPlayerPlaying(getPlayer2());
        } else {
            /* Donne la main au joueur 1 */
            setPlayerPlaying(getPlayer1());
        }
    }
}
