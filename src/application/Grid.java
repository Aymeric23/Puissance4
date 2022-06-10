/* Grid.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

/**
 * Grille de jeu du Puissance 4
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Grid {

    /** grille de jeu */
    private int[][] matrice = new int[6][7];

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
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = 0;
            }
        }
    }
    
    /**
     * @return le grille
     */
    public int[][] getMatrice() {
        return matrice;
    }


    /** 
     * Affiche la grille dans la console
     * @param matrice 
     */
    public void showMatrice() {
        System.out.println(toString());
    }
    
    /** 
     * @return nombre de lignes
     */
    public int getNumberOfLines() {
        return matrice.length;
    }
    
    /** 
     * @return nombre de colonnes
     */
    public int getNumberOfColumns() {
        return matrice[0].length;
    }
    
    /** 
     * Permet d'obtenir le contenu d'une case de la grille (0 si vide,
     * 1 si joueur 1, 2 si joueur 2)
     * @param x 
     * @param y 
     * @return le contenu de la case
     */
    public int getCaseContent(int x, int y) {
        return matrice[x][y];
    }

    
    /**
     * Permet de definir le contenu d'une case de la grille (0 si vide,
     * 1 si joueur 1, 2 si joueur 2)
     * @param x
     * @param y
     * @param playerPrefix 
     * @param value 
     */
    public void setCaseContent(int x, int y, int playerPrefix) {
        matrice[x][y] = playerPrefix;
        setCurrentLine(x);
        setCurrentColumn(y);
        System.out.println("affichage grille");
        showMatrice();
    }
    
    
    /** Recherche la case vide la plus basse de la colone
     * @param colone de recherche
     */
    public void getEmptyCaseFromColumn(int colone) {
        boolean found = false;
        
        for (int ligne = matrice.length-1 ; ligne >= 0 && !found; ligne--) {
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
        return matrice[0][indice] != 0;
    }
    
    /** 
     * Verifie si la grille est pleine ou non  
     * @param indice de la colone
     * @return true si colone pleine false sinon
     */
    public boolean isFull() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == 0) {
                    return false;
                };
            }
        }
        return true;
    }
    
    /** 
     * Verifie si la case[x][y] est vide == "0"
     * @param x
     * @param y
     * @return true si vide false sinon
     */
    public boolean isEmptyCase(int x, int y) {
        return matrice[x][y] == 0;
    }

    /**
     * Modifie la grille avec le prefix du joueur à la colonne choisie
     * @param joueur   qui a sélectionné la colonne
     * @param column   colonne de la grille choisie par le joueur 
     */
    public void addJetonColumn(Player joueur, int column) {
        boolean ok = false;

        for (int i = matrice.length - 1; !ok && i >= 0; i--) {
            if (matrice[i][column] == 0) {
                matrice[i][column] = joueur.getPrefix();
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
        for(int i = x ; i<matrice.length && ok; i++) {
            if(matrice[y][i] == joueur.getPrefix()) {
                count ++;
            } else {
                ok = false;
            }
        }
        ok = true;
        for(int i = x ; i>0 && ok; i--) {
            if(matrice[y][i] == joueur.getPrefix()) {
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
            if (matrice[i+1][j-1] == joueur.getPrefix()) {
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
            if (matrice[i-1][j+1] == joueur.getPrefix()) {
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
            if (matrice[i+1][j+1] == joueur.getPrefix()) {
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
            if (matrice[i-1][j-1] == joueur.getPrefix()) {
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
            if (matrice[currentLine][j+1] == joueur.getPrefix()) {
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
            if (matrice[currentLine][j-1] == joueur.getPrefix()) {
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
            if (matrice[i+1][currentColumn] == joueur.getPrefix()) {
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

    




    /*TODO simplifier et utiliser des méthodes existantes*/
    @Override
    public String toString() {
        String gridString = "";
        for (int i = 0 ; i < matrice.length ; i ++) {
            for (int j = 0 ; j < matrice[i].length; j++) {
                gridString += matrice[i][j] + " ";
            }
            gridString += "\n";
        }
        return "Grille :\n" + gridString;
    }

}
