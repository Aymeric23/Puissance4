/* Menu.java                                  23 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;

import application.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Classe contrôleur qui gère l'intéractivité avec la vue décrite 
 * dans le fichier fxml Menu.fxml
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Menu {
    
    private static final String SCENE_SOLO = "/application/fxml/GameSolo.fxml";
    private static final String SCENE_DUO = "/application/fxml/GameDuo.fxml";
    private static final String SCENE_PUZZLE = "/application/fxml/GamePuzzle.fxml";
    private static final String SCENE_RULES = "/application/fxml/Rules.fxml";
    private Parent racine;
    private Game sauvegarde;
    
    @FXML
    private VBox box;

    /**
     * Change la scene actuelle par la scene qui correspond
     * @throws IOException 
     */
    @FXML
    private void loadScene(ActionEvent event) throws IOException {
        String destinationFXML = null;
        
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
        //Récupère le boutton sur lequel le joueur clic
        Button bouton = (Button) event.getSource();
        String IdBouton = bouton.getId();
        
        //Charge le fichier fxml en question
        switch (IdBouton) {
            case "solo":
                destinationFXML = SCENE_SOLO;
                break;
            case "duo":
                destinationFXML = SCENE_DUO;
                GameDuo.createDuoGame();
                break;
            case "puzzle":
                destinationFXML = SCENE_PUZZLE;
                break;
            case "load":
                /* Chargement de la sauvegarde */
                sauvegarde = Game.chargerSauvegarde();
                if (sauvegarde.getGamemode() == 1) {
                    //partie solo
                    destinationFXML =  SCENE_SOLO;
                } else if (sauvegarde.getGamemode() == 2) {
                    //partie duo
                    GameDuo.loadDuoGame(sauvegarde);
                    destinationFXML = SCENE_DUO;
                } else if (sauvegarde.getGamemode() == 3) {
                    //partie puzzle
                    destinationFXML = SCENE_PUZZLE;
                }
                break;
            case "rules":
                destinationFXML = SCENE_RULES;
                break;

            case "exit":
                destinationFXML = null;
                break;   
        }
        if (destinationFXML.isEmpty() || destinationFXML == null) {
            exitApp();
        } else {
            // charge le fichier FXML
            chargeurFXML.setLocation(getClass().getResource(destinationFXML));
             
            racine = chargeurFXML.load();
            box.getChildren().setAll(racine);
        }
    }
    
    /**
     * Ferme la fenêtre de jeu
     */
    @FXML
    private void exitApp() {
        Platform.exit();
    }
}
