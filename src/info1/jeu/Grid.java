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
    
    /** ligne qui a �t� s�lectionn�e par le joueur */
    private static int currentLine;
    
    /** colonne qui a �t� s�lectionn�e par le joueur */
    private static int currentColumn;
    
    /**
     * D�finition des caract�ristiques de la grille
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
     * Modifie la grille avec la couleur du joueur � la colonne choisie
     * @param player   joueur qui a s�lectionn� la colonne
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
     * V�rifie l'alignement vertical, horizontal et diagonal
     * de quatre jetons de la couleur d'un joueur
     * @param  joueur joueur dont on v�rifie l'alignement de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont align�es
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
     * V�rifie l'alignement diagonal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on v�rifie l'alignement diagonal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont align�es
     *         diagonalement
     *         false sinon
     */
    private boolean isAlignDiagonally(Player joueur) {
        return false; //bouchon
    }


    /** 
     * V�rifie l'alignement horizontal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on v�rifie l'alignement horizontal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont align�es
     *         horizontalement
     *         false sinon
     */
    private boolean isAlignHorizontally(Player joueur) {
        return false; //bouchon
    }


    /** 
     * V�rifie l'alignement vertical de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on v�rifie l'alignement vertical de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont align�es
     *         verticalement
     *         false sinon
     */
    private boolean isAlignVertically(Player joueur) {
        return false; //bouchon
    }

    /**
     * Modifie la ligne courante de la grille
     * @param currentLine la ligne s�lectionn�e
     */
    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }
    
    /**
     * Modifie la colonne courante de la grille
     * @param currentColumn la colonne s�lectionn�e
     */
    public void setCurrentColumn(int currentColumn) {
        this.currentColumn= currentColumn;
    }
}
