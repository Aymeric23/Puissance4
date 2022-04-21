/*
 * Grid.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */

/** TODO commenter la responsabilité de cette classe
 * @author aymer
 *
 */
public class Grid {
    
    private static String grid[][] = {
                    {"", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", ""},
                    };
    
    private static int currentLigne;
    
    private static int currentColonne;
    
    /**
     * TODO commenter l'état initial atteint
     */
    public Grid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "O";
            }
        }
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     */
    public static void afficherGrid() {
        
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + ' ');
            }
            System.out.println();
        }
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param joueur 
     * @param colonne 
     */
    public static void setGrid(Joueur joueur, int colonne) {
        
        boolean ok = false;
        for (int i = grid.length - 1; !ok && i >= 0; i--) {
            if (grid[i][colonne] == "O") {
                grid[i][colonne] = joueur.getValue();
                ok = true;
                currentLigne = i;
                currentColonne = colonne;
            }
        }
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param joueur 
     * @return TODO
     */
    public static boolean isAligner(Joueur joueur) {
        boolean ok = false;
        
//        if (isAlignerVertical(joueur) || isAlignerHorizontal(joueur) || isAlignerDiagonale(joueur)) {
//            ok = true;
//        } else {
//            ok = false;
//        }
        if (isAlignerHorizontal(joueur)) {
            ok = true;
        }
        return ok;
    }


    /** TODO commenter le rôle de cette méthode (SRP)
     * @return
     */
    private static boolean isAlignerDiagonale(Joueur joueur) {
        boolean ok;
        return false; //bouchon
    }


    /** TODO commenter le rôle de cette méthode (SRP)
     * @return
     */
    private static boolean isAlignerHorizontal(Joueur joueur) {
        boolean ok = true;
        for (int i = currentLigne; i <= 0; i--) {
            if (grid[i][currentColonne] != joueur.getValue()) {
                ok = false;
            }
        }
        return ok;
    }


    /** TODO commenter le rôle de cette méthode (SRP)
     * @return
     */
    private static boolean isAlignerVertical(Joueur joueur) {
        return false; //bouchon
    }
}
