/* Menu.java                                  23 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe contrôleur qui gère l'intéractivité avec la vue décrite 
 * dans le fichier fxml Menu.fxml
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Menu {
    
    private Parent racine;
    
    @FXML
    VBox box;

    /**
     * Change la scene actuelle par la scene qui correspond
     * @throws IOException 
     */
    @FXML
    private void loadScene(ActionEvent event) throws IOException {
        String destinationFXML;
        
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
        //Récupère le boutton sur lequel le joueur clic
        Button bouton = (Button) event.getSource();
        String IdBouton = bouton.getId();
        
        //Charge le fichier en question
        switch (IdBouton) {
            case "solo":
                destinationFXML = "/application/fxml/GameSolo.fxml";
                break;
            case "duo":
                destinationFXML = "/application/fxml/GameDuo.fxml";
                break;
            case "puzzle":
                destinationFXML = "/application/fxml/GamePuzzle.fxml";
                break;
            case "load":
                destinationFXML = "/application/fxml/ListSaveGame.fxml";
                break;
            case "exit":
                destinationFXML = null;
                break;   
            default:
                throw new IllegalArgumentException("Id bouton invalide : " + IdBouton);
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
