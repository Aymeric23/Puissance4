/*
 * GridTest.java                                  25 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package application.jeu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import application.Grid;
import application.Player;

/** TODO commenter la responsabilité de cette classe
 * @author Romain
 *
 */
class GridTest {

    /** Grille par défaut */
    final int[][] INIT_GRID = { 
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
    };
    
    final int[][] GRID1 = { 
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0}
    };

    @Test
    @DisplayName("Test du constructeur")
    void testGrid() {
        Grid grid = new Grid();
        assertArrayEquals(INIT_GRID, grid.getMatrice());
    }

    @Test
    @DisplayName("Test du constructeur")
    void Gridtest() {
        Grid grid = new Grid();
        Player player = new Player("joueur");
        assertArrayEquals(grid.getMatrice(), INIT_GRID);

        for (int i = 0; i < INIT_GRID.length; i++) {
            for (int j = 0; j < INIT_GRID[i].length; j++) {
                assertTrue(grid.isEmptyCase(i, j));
            }
        }
        
        for (int i = 0; i < INIT_GRID[0].length; i++) {
            assertFalse(grid.isFullColumn(i));
        }
        
        
        
    }

}