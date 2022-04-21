/* Grid.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package info1.jeu;

/**
 * Grille de jeu du Puissance 4
 * @author aymeric.thevenet
 *         romain.trimouille
 */
public class Grid {
    
    /** grille de jeu */
    private static String grid[][];
    
    /** ligne qui a été sélectionnée par le joueur */
    private static int currentLine;
    
    /** colonne qui a été sélectionnée par le joueur */
    private static int currentColumn;
    
    /**
     * Définition des caractéristiques de la grille
     */
    public Grid() {
        currentLine = 0;
        currentColumn = 0;
        
        /* Initialisation de la grille avec des "O" */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "O";
            }
        }
    }
    
    
    /** 
     * Affiche la grille dans la console
     */
    public void showGrid() {
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + ' ');
            }
            System.out.println();
        }
    }
    
    /**
     * Modifie la grille avec la couleur du joueur à la colonne choisie
     * @param player   joueur qui a sélectionné la colonne
     * @param column   colonne de la grille choisie par le joueur 
     */
    public void setGrid(Player player, int column) {
        boolean ok = false;
        
        for (int i = grid.length - 1; !ok && i >= 0; i--) {
            if (grid[i][column] == "O") {
                grid[i][column] = player.getColor();
                ok = true;
                setCurrentLine(i);
                setCurrentColumn(column);
            }
        }
    }
    
    /** 
     * Vérifie l'alignement vertical, horizontal et diagonal
     * de quatre jetons de la couleur d'un joueur
     * @param  joueur joueur dont on vérifie l'alignement de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         false sinon
     */
    public boolean isAlign(Player joueur) {
        boolean ok = false;
        
        if (isAlignVertically(joueur) || isAlignHorizontally(joueur) || isAlignDiagonally(joueur)) {
            ok = true;
        } else {
            ok = false;
        }
        return ok;
    }


    /**
     * Vérifie l'alignement diagonal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement diagonal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         diagonalement
     *         false sinon
     */
    private boolean isAlignDiagonally(Player joueur) {
        return false; //bouchon
    }


    /** 
     * Vérifie l'alignement horizontal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement horizontal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         horizontalement
     *         false sinon
     */
    private boolean isAlignHorizontally(Player joueur) {
        return false; //bouchon
    }


    /** 
     * Vérifie l'alignement vertical de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement vertical de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         verticalement
     *         false sinon
     */
    private boolean isAlignVertically(Player joueur) {
        return false; //bouchon
    }

    /**
     * Modifie la ligne courante de la grille
     * @param currentLine la ligne sélectionnée
     */
    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }
    
    /**
     * Modifie la colonne courante de la grille
     * @param currentColumn la colonne sélectionnée
     */
    public void setCurrentColumn(int currentColumn) {
        this.currentColumn= currentColumn;
    }
}
