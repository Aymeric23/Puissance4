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
    
    private Player j1;
    private Player j2;
    
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
        this.grid = new Grid();
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
     * @param j1 
     * @param j2 
     */
    public void startGame(Player j1, Player j2) {
        switch (gamemode) {
        case 1: {
            System.out.println("[Info] - Lancement d'une partie solo");
            startGameSolo();
        }
            break;
        case 2: {
            System.out.println("[Info] - Lancement d'une partie duo \n"
                               + "Joueur 1 : " + j1.getPseudo() + " | Joueur 2 : " + j2.getPseudo());
            startGameDuoBis(j1, j2);
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
     * @param j1 
     * @param j2 
     */
    public void startGameDuoBis(Player j1, Player j2) {    
        /*Affectation des couleurs aléatoirement*/
        randomizePlayerColor(j1, j2);
        this.j1 = j1;
        this.j2 = j2;
        System.out.println("Couleur J1 : " + j1.getColor().getColorId());
        System.out.println("Couleur J2 : " + j2.getColor().getColorId());
        grid.showGrid();
        
        

    }
    
    

    
    /** 
     * Affecte une couleur aux 2 joueurs de façon aléatoire
     * @param j1 Joueur1
     * @param j2 Joueur2
     */
    private static void randomizePlayerColor(Player j1, Player j2) {
        Color c1 = new Color(1, "#e45555");
        Color c2 = new Color(2, "#fbfd87");
        
        Color couleurs[] = {c1, c2, c1};
        
        Random aleatoire = new Random();
        int numCouleur = aleatoire.nextInt(2); // 0 ou 1
        j1.setColor(couleurs[numCouleur]);
        j2.setColor(couleurs[numCouleur+1]);
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param colone à modifier
     * @param player 
     * @return 
     * 
     */
    public int[] addJeton(int colone, Player player) {
        System.out.println("ok");
        return grid.placeJeton(colone, player);
        
    }
    
    /**
     * @return J1
     */
    public Player getJ1() {
        return j1;
    }
    
    /**
     * @return J2
     */
    public Player getJ2() {
        return j2;
    }
    

    /** TODO commenter le rôle de cette méthode (SRP)
     * TODO Exceptions si nécessaire
     * @param j1 
     * @param j2 
     */
    public void startGameDuo(Player j1, Player j2) {        
        //Color c1 = new Color("R", "#e45555");
        //Color c2 = new Color("J", "#fbfd87");
        
        Player joueurActuel = j1;
        Player joueurAttente = j2;
        Player joueurTemporaire;
        
        Random aleatoire = new Random();
        boolean couleur = aleatoire.nextBoolean();
        if (couleur) {
            //j1.setColor(c1);
            //j2.setColor(c2);
            joueurActuel = j1;
            joueurAttente = j2;
        } else {
            //j1.setColor(c1);
            //j2.setColor(c2);
            joueurActuel = j2;
            joueurAttente = j1;
        }  
        
        String vainqueur = "Egalité";
        int colonneChoisie;
        boolean ok = false;
        while (!ok) {
//            grid.showGrid();
//            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            //colonneChoisie = Integer.valueOf(controler.getIndiceColone(null));
            //System.out.println(joueurActuel + "  " +joueurAttente + "  " + colonneChoisie);
            //grid.setGrid(joueurActuel, colonneChoisie);
            
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
        //Player j1 = new Player(controler.getPseudoP1(),null);
        //Player j2 = new Player("IA", null);
        
    }
}
