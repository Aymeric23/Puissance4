/* GameSolo.java                                  25 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.Game;
import application.Grid;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Classe contrôleur qui gère l'intéractivité avec la vue décrite 
 * dans le fichier fxml GameSolo.fxml
 * @author aymeric.thevenet
 */
public class GameSolo {
    
    private Stage stage;
    private Scene scene;
    private Parent racine;

    @FXML
    private TextField pseudoP1;
    @FXML
    private TextField pseudoP2;
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static Game gameSolo = new Game(date.format(LocalDateTime.now()), 1);
    
    /**
     * Change la scene actuelle par la scene qui correspond au menu
     * @param PrimaryStage 
     * @throws IOException 
     */
    @FXML
    private void sceneMenu(MouseEvent event) throws IOException {
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
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @return le pseudo du joueur 1
     */
    @FXML
    public String getPseudoP1() {
        return pseudoP1.getText();
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @return le pseudo du joueur 2
     */
    @FXML
    public String getPseudoP2() {
        return pseudoP2.getText();
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param event 
     * @param a 
     * @return l'indice de la colonne choisie par l'utilisateur
     */
    @FXML
    public int getIndiceColone(MouseEvent event) {
        VBox colone = (VBox) event.getSource();
        int idColone = Integer.parseInt(colone.getId());
        
        System.out.println("clic sur la colonne " + idColone);
        
        System.out.println("est full? " + gameSolo.getGrid().isColumnFull(idColone));
        return idColone; //bouchon
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        //gameSolo.startGame();
    }

}