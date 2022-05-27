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
     * Change la scene actuelle par la scene qui correspond au mode de jeu solo
     * @throws IOException 
     */
    @FXML
    private void sceneGameSolo(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("../../application/" 
                    + "fxml/GameSolo.fxml"));
         
        racine = chargeurFXML.load();
        box.getChildren().setAll(racine);
    }
    
    /**
     * Change la scene actuelle par la scene qui correspond au mode de jeu duo
     * @throws IOException 
     */
    @FXML
    private void sceneGameDuo(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("../../application/"
                    + "fxml/GameDuo.fxml"));
         
        racine = chargeurFXML.load();
        box.getChildren().setAll(racine);
    }
    
    /**
     * Change la scene actuelle par la scene qui correspond
     * au mode de jeu puzzle
     * @throws IOException 
     */
    @FXML
    private void sceneGamePuzzle(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("../../application/"
                    +"fxml/GamePuzzle.fxml"));
         
        racine = chargeurFXML.load();
        box.getChildren().setAll(racine);
    }
    
    /**
     * Change la scene actuelle par la scene qui correspond à la liste 
     * des parties sauvagardées
     * @throws IOException 
     */
    @FXML
    private void sceneListLoadGame(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("../../application/"
                    +"fxml/ListSaveGame.fxml"));
         
        racine = chargeurFXML.load();
        box.getChildren().setAll(racine);
    }
    
    /**
     * Change la scene actuelle par la scene qui correspond au règles du jeu
     * @throws IOException 
     */
    @FXML
    private void sceneRules(ActionEvent event) throws IOException {
        // création d'un chargeur de code FXML
        FXMLLoader chargeurFXML = new FXMLLoader();
         
        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("../../application/"
                    +"fxml/Rules.fxml"));
         
        racine = chargeurFXML.load();
        box.getChildren().setAll(racine);
    }
    
    /**
     * Ferme la fenêtre de jeu
     */
    @FXML
    private void leaveGame() {
        Platform.exit();
    }
}
