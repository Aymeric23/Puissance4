/*
 * GridTest.java                                  25 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package application.jeu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Grid;

/** TODO commenter la responsabilité de cette classe
 * @author Romain
 *
 */
class GridTest {

    @Test
    void Gridtest() {
        Grid grid = new Grid();
        grid.randomGeneration(null, null);
        grid.showMatrice();
    }

}
