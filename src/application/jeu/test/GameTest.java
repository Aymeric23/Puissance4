/*
 * GameTest.java                                  25 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package application.jeu.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import application.Game;
import application.Grid;

/** 
 * Test unitaire de la classe {@link Game}      
 * @author aymeric.thevenet
 *         romain.trimouille
 */
class GameTest {

    private final int[] MODE_JEU = {1, 2, 3};
    private final int[] MODE_JEU_INVALIDE = {0, 4, -1, 1490, 18, -12};
    private final String[] TO_STRING_CORRECTE = {
                    "Game [gamemode: 1]", "Game [gamemode: 2]", 
                    "Game [gamemode: 3]"
                    };
    private final String[] TO_STRING_INCORRECTE = {
                    "Game [gamemode: 0]", "Game [gamemode: 4]", 
                    "Game [gamemode: solo]"
                    };
    
    /**
     * Test du constructeur : on vérifie que qu'il ne lève pas l'exception si le
     * mode de jeu est valide
     */
    @Test
    @DisplayName("Test constructeur valide")
    void testGame() {
        for (int i = 0; i < MODE_JEU.length; i++) {
            final int MODE = MODE_JEU[i];
            assertDoesNotThrow(
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        new Game(MODE);
                    }
                }
            );
        }
    }
    
    /**
     * Test du constructeur : on vérifie qu'il lève l'exception si le
     * mode de jeu est invalide
     */
    @Test
    @DisplayName("Test constructeur invalide")
    void testGameInvalide() {
        for (int i = 0; i < MODE_JEU_INVALIDE.length; i++) {
            final int MODE = MODE_JEU_INVALIDE[i];
            assertThrows( IllegalArgumentException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        new Game(MODE);
                    }
                }
            );
        }
    }
    
    @Test
    @DisplayName("Test toString")
    void testToString() {
        for (int i = 0; i < MODE_JEU.length; i++) {
            Game game = new Game(MODE_JEU[i]);
            assertEquals(TO_STRING_CORRECTE[i], game.toString());
        }
        for (int i = 0; i < MODE_JEU.length; i++) {
            Game game = new Game(MODE_JEU[i]);
            assertNotEquals(TO_STRING_INCORRECTE[i], game.toString());
        }
    }
}