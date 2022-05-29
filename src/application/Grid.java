/* Grid.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import java.util.Random;

/**
 * Grille de jeu du Puissance 4
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Grid {

    /** grille de jeu */
    private static int[][] grid = new int[6][7];

    /** ligne qui a été sélectionnée par le joueur */
    private int currentLine;

    /** colonne qui a été sélectionnée par le joueur */
    private int currentColumn;

    /**
     * Définition des caractéristiques de la grille
     */
    public Grid() {
        currentLine = 0;
        currentColumn = 0;

        /* Initialisation de la grille avec des 0 */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }


    /** 
     * Affiche la grille dans la console
     * @param grid 
     */
    public void showGrid() {
        System.out.println(toString());
    }

    /**
     * Modifie la grille avec la couleur du joueur à la colonne choisie
     * @param player   joueur qui a sélectionné la colonne
     * @param column   colonne de la grille choisie par le joueur 
     */
    public void setGrid(Player player, int column) {
        boolean ok = false;

        for (int i = grid.length - 1; !ok && i >= 0; i--) {
            if (grid[i][column] == 0) {
                grid[i][column] = player.getColor().getColorId();
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
    

    /** TODO commenter le rôle de cette méthode (SRP)
     * @param x 
     * @param y 
     * @param joueur
     * @return TODO
     */
    public boolean isAlignHorrizontallyBis(int x, int y, Player joueur) {
        int count = 0;
        boolean ok = true;
        for(int i = x ; i<grid.length && ok; i++) {
            if(grid[y][i] == joueur.getColor().getColorId()) {
                count ++;
            } else {
                ok = false;
            }
        }
        ok = true;
        for(int i = x ; i>0 && ok; i--) {
            if(grid[y][i] == joueur.getColor().getColorId()) {
                count ++;
            } else {
                ok = false;
            }
        }
        System.out.println(count);
        return count-1>=4;
    }

    /**
     * Vérifie l'alignement diagonal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement diagonal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         diagonalement
     *         false sinon
     */
    private boolean isAlignDiagonally(Player joueur) {
        boolean ok;
        int diagonalTopBottom;
        int diagonalBottomTop;

        diagonalTopBottom = topLeft(joueur) + bottomRight(joueur);
        diagonalBottomTop = bottomLeft(joueur) + topRight(joueur);

        ok = diagonalTopBottom + 1 >= 4 
                        || diagonalBottomTop + 1 >= 4? true : false;
                        return ok;
    }

    /** 
     * Compte le nombre de jetons de la couleur du joueur qui se situe en bas 
     * à gauche du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe en bas 
     *         à gauche du jeton courant
     */
    private int bottomLeft(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int i = currentLine, j = currentColumn; ok && i < 5 && j > 0 && count <= 4; i++, j--) {
            if (grid[i+1][j-1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
    }

    /** 
     * Compte le nombre de jetons de la couleur du joueur qui se situe en haut 
     * à droite du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe en haut
     *         à droite du jeton courant
     */
    private int topRight(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int i = currentLine, j = currentColumn; ok && i > 0 && j < 6 && count <= 4; i--, j++) {
            if (grid[i-1][j+1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
    }

    /**
     * Compte le nombre de jetons de la couleur du joueur qui se situe en bas 
     * à droite du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe en bas 
     *         à droite du jeton courant
     */
    private int bottomRight(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int i = currentLine, j = currentColumn; ok && i < 5 && j < 6 && count <= 4; i++, j++) {
            if (grid[i+1][j+1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }

        }
        return count;
    }

    /**
     * Compte le nombre de jetons de la couleur du joueur qui se situe en haut 
     * à gauche du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe en haut
     *         à gauche du jeton courant
     */
    private int topLeft(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int i = currentLine, j = currentColumn; ok && i > 0 && j > 0 && count <= 4; i--, j--) {
            if (grid[i-1][j-1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
    }

    /** 
     * Vérifie l'alignement horizontal de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement horizontal de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         horizontalement
     *         false sinon
     */
    private boolean isAlignHorizontally(Player joueur) {
        boolean ok;

        ok = left(joueur) + right(joueur) + 1 >= 4 ? true : false;
        return ok;
    }

    /** 
     * Compte le nombre de jetons de la couleur du joueur qui se situe à droite 
     * du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe à droite 
     *         du jeton courant
     */
    private int right(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int j = currentColumn; ok && j < 6 && count <= 4; j++) {
            if (grid[currentLine][j+1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
    }

    /**
     * Compte le nombre de jetons de la couleur du joueur qui se situe à gauche 
     * du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe à gauche 
     *         du jeton courant
     */
    private int left(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int j = currentColumn; ok && j > 0 && count <= 4; j--) {
            if (grid[currentLine][j-1] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
    }

    /** 
     * Vérifie l'alignement vertical de quatre jetons de la couleur d'un joueur
     * @param joueur joueur dont on vérifie l'alignement vertical de ces pions
     * @return true si les quatre jetons de la couleur du joueur sont alignées
     *         verticalement
     *         false sinon
     */
    private boolean isAlignVertically(Player joueur) {
        boolean ok;

        ok = bottom(joueur) + 1 >= 4 ? true : false;
        return ok;
    }

    /**
     * Compte le nombre de jetons de la couleur du joueur qui se situe en bas 
     * du jeton courant
     * @param  joueur joueur dont on compte les jetons de sa couleur
     * @return le nombre de jeton de la couleur du joueur qui se situe en bas 
     *         du jeton courant
     */
    private int bottom(Player joueur) {
        boolean ok = true;
        int count = 0;

        for (int i = currentLine; ok && i < 5; i++) {
            if (grid[i+1][currentColumn] == joueur.getColor().getColorId()) {
                ok = true;
                count++;
            } else {
                ok = false;
            }
        }
        return count;
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

    /**
     * @return the currentLine
     */
    public int getCurrentLine() {
        return currentLine;
    }
    
    /**
     * @return the currentColumn
     */
    public int getCurrentColumn() {
        return currentColumn;
    }

    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param x
     * @param y
     * @param player 
     * @param value 
     */
    public void setCase(int x, int y, Player player) {
        grid[x][y] = player.getColor().getColorId();
        setCurrentLine(x);
        setCurrentColumn(y);
        System.out.println("affichage grille");
        showGrid();
    }
    
    
    /** Recherche la case vide la plus basse de la colone
     * @param colone de recherche
     */
    public void getEmptyCaseFromColumn(int colone) {
        boolean found = false;
        
        for (int ligne = grid.length-1 ; ligne >= 0 && !found; ligne--) {
            if (isEmptyCase(ligne, colone)) {
                found = true;
                setCurrentLine(ligne);
                setCurrentColumn(colone);
            }
        }
    }
    
    
    /** 
     * Verifie si la colone est pleine ou non  
     * @param indice de la colone
     * @return true si colone pleine false sinon
     */
    public boolean isFullColumn(int indice) {
        return grid[0][indice] != 0;
    }
    
    /** 
     * Verifie si la case[x][y] est vide == "0"
     * @param x
     * @param y
     * @return true si vide false sinon
     */
    public boolean isEmptyCase(int x, int y) {
        return grid[x][y] == 0;
    }



    /*TODO simplifier et utiliser des méthodes existantes*/
    @Override
    public String toString() {
        String gridString = "";
        for (int i = 0 ; i < grid.length ; i ++) {
            for (int j = 0 ; j < grid[i].length; j++) {
                gridString += grid[i][j] + " ";
            }
            gridString += "\n";
        }
        return "Grille :\n" + gridString;
    }

}
