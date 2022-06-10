/* PlayerTest.java                                  10 juin 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package application.jeu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import application.Game;
import application.Player;

/** TODO commenter la responsabilité de cette classe
 * @author aymeric.thevenet
 */
class PlayerTest {

    final private String[] NAME_VALIDE = {
        "Aymeric", "ROMAIN", "Jean luc", "1JB$0§!", "Jean-Luc", "",
        "a", "abcdefghij"                   
    };
    
    final private String[] NAME_INVALIDE = {
        "abcdefghijk", "Jean pierre", ".§:;$*=+^¨1", "aymeric thevenet"
    };
    
    final private String[] TO_STRING_CORRECT= {
        "Player [pseudo = Aymeric + \", color = \" + color.toString() \r\n"
        + "                + \", prefix = \" + prefix + \"]",            
    };
    
    @Test
    @DisplayName("Test du constructeur")
    void testPlayer() {
        for (int i = 0; i < NAME_VALIDE.length; i++) {
            final String NAME = NAME_VALIDE[i];
            assertDoesNotThrow(
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        new Player(NAME);
                    }
                }
            );
        }
    }

    @Test
    @DisplayName("Test du constructeur invalide")
    void testPlayerInvalide() {
        for (int i = 0; i < NAME_INVALIDE.length; i++) {
            final String NAME = NAME_INVALIDE[i];
            assertThrows( IllegalArgumentException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        new Player(NAME);
                    }
                }
            );
        }
    }
}