/* Rules.java                                  25 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4 
 */
package application.controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe contrôleur qui gère l'intéractivité avec la vue décrite 
 * dans le fichier fxml Rules.fxml
 * @author aymeric.thevenet
 */
public class Rules {

    private Stage stage;
    private Scene scene;
    private Parent racine;
    
    /**
     * Change la scene actuelle par la scene qui correspond au menu
     * @throws IOException 
     */
    @FXML
    private void sceneMenu(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("/application/"
                    +"fxml/Menu.fxml"));
         
        racine = chargeurFXML.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(racine);
        stage.setScene(scene);
        stage.show();
    }
}
