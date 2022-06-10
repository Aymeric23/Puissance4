/* Game.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Partie jouable par des joueurs avec une grille de jeu
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Game {
    
    /** Dossier contenant les sauvegardes **/
    private static final String DOSSIER_SAUVEGARDE = "sauvegardes";
    
    /** Fichier contenant les donnees sauvegardees **/
    private static final String FICHIER_SAUVEGARDE = "sauvegarde.txt";
    
    /** Separateur de donnees dans le fichier de sauvegarde **/
    private static final String SEPARATEUR_SAUVEGARDE = ",";

    
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
     * @throws IllegalArgumentException si gamemode invalide.
     */
    public Game(int gamemode) {
        if (gamemode < 1 || gamemode > 3) {
            System.out.println(gamemode);
            throw new IllegalArgumentException("Le gamemode doit être egal a 1 ou 2");
        }
        this.gamemode = gamemode;
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
     * @param grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    /**
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }
    
    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
        this.player1.setPrefix(1);
    }
    
    /**
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }
    
    
    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
        this.player2.setPrefix(2);
    }
    
    /** 
     * retouve un joueur a partir de son prefix
     * @param prefix du joueur recherche
     * @return le joueur recherche
     */
    public Player getPlayerByPrefix(int prefix) {
        Player player = null;
        if (prefix==1) {
            player = this.player1;
        } else if (prefix == 2) {
            player = this.player2;
        }
        return player;
    }
    
    /**
     * @return the playerPlaying
     */
    public Player getPlayerPlaying() {
        return playerPlaying;
    }
    
    /**
     * @param playerPlaying the playerPlaying to set
     */
    public void setPlayerPlaying(Player playerPlaying) {
        this.playerPlaying = playerPlaying;
    }
    
    /**
     * @return the winner
     */
    public Player getWinner() {
        return winner;
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
        return ("Game [gamemode: " + this.gamemode + "]");
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
    
    /**
     * Affiche les info de la partie en console
     */
    public void startGamePuzzle() {
        
    }
    
    
    
    /** 
     * Affiche les info de la partie en console
     * @param player1 
     * @param player2 
     */
    public void startGameDuo() { 
        System.out.println("Couleur joueur1 : " + player1.getColor());
        System.out.println("Couleur joueur2 : " + player2.getColor());
        grid.showMatrice();
    }

    /**
     * Affiche les info de la partie en console
     */
    public void startGameSolo() {
        System.out.println("Couleur joueur1 : " + player1.getColor());
        System.out.println("Couleur joueur2 : " + player2.getColor());
        grid.showMatrice();
        
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
    
    /**
     * Sauvegarde la partie sous forme de fichier text
     * Format d'enregistrement :
     * gamemode
     * playerplaying
     * infos joueur1
     * infos joueur1
     * grille
     * @throws FileNotFoundException si fichier non trouve
     * @throws IOException si une erreur inconnue ce produit
     */
    public void sauvegarder() {
        File fichier = new File(DOSSIER_SAUVEGARDE +"\\" + FICHIER_SAUVEGARDE);

        try (FileWriter redacteur = new FileWriter(fichier)) {
                /* Si fichier non existant -> cree le fichier */
                System.out.println("Sauvegarde des donnees du projet dans le fichier " 
                                           + fichier.getName() + " en cours...");
                /* Sauvegarde le mode de jeu et les joueurs */
                redacteur.write(gamemode + "\n"
                                //Sauvegarde des joueurs
                                + playerPlaying.getPrefix() + "\n"
                                + player1.getPseudo() + SEPARATEUR_SAUVEGARDE 
                                + player1.getPrefix() + SEPARATEUR_SAUVEGARDE 
                                + player1.getColor()+"\n"
                                + player2.getPseudo() + SEPARATEUR_SAUVEGARDE 
                                + player2.getPrefix() + SEPARATEUR_SAUVEGARDE 
                                + player2.getColor());
                /* Sauvegarde la grille */
                for (int i = 0; i < grid.getNumberOfLines(); i++) {
                    redacteur.write("\n");
                    for (int j = 0; j < grid.getNumberOfColumns(); j++) {
                        redacteur.write(grid.getCaseContent(i, j) + SEPARATEUR_SAUVEGARDE);
                    }
                }
                redacteur.close();
        } catch (FileNotFoundException exception) {
                System.err.println("Le dossier " + DOSSIER_SAUVEGARDE +" n'existe pas");
        } catch (IOException exception) {
                System.err.println("Un erreur c'est produite lors de la sauvegarde du fichier : " 
                                           + exception.getMessage());
        }
        System.out.println("La partie a ete sauvegardee avec succes");
    }


    /**
     * Charge les elements contenus dans un ficher de sauvegarde
     * @return la partie chargée
     * @throws IllegalArgumentException si fichier charge est vide
     * @throws FileNotFoundException si fichier non trouve
     * @throws IOException si une erreur inconnue ce produit
     */
    public static Game chargerSauvegarde() {
        Game partie = null;
        File fichier = new File(DOSSIER_SAUVEGARDE + "\\" + FICHIER_SAUVEGARDE);
        int partieGamemode = -1; // 1 (solo) ou 2 (duo)
        int partiePlayerPlayingPrefix; // 1 (J1) ou 2 (J2)
        Player joueur1;
        Player joueur2;
        Grid grille;
        String[] elementsLigne; //contiendra les elements stockés dans chaque ligne
        try {
            Scanner lecteur = new Scanner(fichier);
            if (!lecteur.hasNextLine()) {
                lecteur.close();
                throw new IllegalArgumentException("Le fichier de sauvegarde est vide");
            }
            /* charge la prochaine ligne #1 (gamemode) */
            elementsLigne = lecteur.nextLine().split(SEPARATEUR_SAUVEGARDE);
            partieGamemode = Integer.valueOf(elementsLigne[0]);

            /* charge la prochaine ligne #2 (playerPlaying) */
            elementsLigne = lecteur.nextLine().split(SEPARATEUR_SAUVEGARDE);
            partiePlayerPlayingPrefix = Integer.valueOf(elementsLigne[0]);

            /* charge la prochaine ligne #3 (Joueur 1) */
            elementsLigne = lecteur.nextLine().split(SEPARATEUR_SAUVEGARDE);
            joueur1 = new Player(elementsLigne[0]);
            joueur1.setColor(elementsLigne[2]);
            
            /* charge la prochaine ligne #4 (Joueur 2) */
            elementsLigne = lecteur.nextLine().split(SEPARATEUR_SAUVEGARDE);
            joueur2 = new Player(elementsLigne[0]);
            joueur2.setColor(elementsLigne[2]);
                
            /* Charge les prochaines lignes contenant la grille */
            grille = new Grid();
            for (int x = 0; x < 6; x++) {
                elementsLigne = lecteur.nextLine().split(SEPARATEUR_SAUVEGARDE);
                for (int y =0 ; y < elementsLigne.length ; y++)
                grille.setCaseContent(x, y, Integer.valueOf(elementsLigne[y]));
            }
            
            lecteur.close();
            partie = new Game(partieGamemode);
            partie.setPlayer1(joueur1);
            partie.setPlayer2(joueur2);
            partie.setGrid(grille);
            /* on defini le joueur qui va commencer a jouer */
            partie.setPlayerPlaying(partie.getPlayerByPrefix(partiePlayerPlayingPrefix));
            
            
        } catch (FileNotFoundException exception) {
            System.err.println("Le dossier " +DOSSIER_SAUVEGARDE +" n'existe pas");
        }

        /* Charge les taches du projet */
        System.out.println("La partie a ete charge avec succes");
        return partie;
    }
}
