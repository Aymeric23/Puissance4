/* GameDuo.java                                  25 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.Game;
import application.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Classe contrôleur qui gère l'intéractivité avec la vue décrite 
 * dans le fichier fxml GameDuo.fxml
 * @author aymeric.thevenet
 */
public class GameDuo {
    
    private Stage stage;
    private Scene scene;
    private Parent racine;

    @FXML
    private TextField pseudoP1;
    @FXML
    private TextField pseudoP2;
    
    @FXML
    private Circle c00;
    
    @FXML
    private HBox grille;
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static Game partie = new Game(date.format(LocalDateTime.now()), 2);
    
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
     * @param id
     * @param color 
     */
    public void setCircleColor(String id, String color) {
        System.out.println("looking for " + id);
        ((Shape) grille.lookup("#"+id)).setFill(Paint.valueOf(color));
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param event 
     * @param a 
     */
    @FXML
    public void getIndiceColone(MouseEvent event) {
        VBox colone = (VBox) event.getSource();
        int idColone = Integer.parseInt(colone.getId());
        int[] a = partie.addJeton(idColone, partie.getJ1());
        setCircleColor(""+a[1]+a[0], partie.getJ1().getColorHexa());
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    @FXML
    public void runTheGame() {
        Player j1 = new Player(getPseudoP1(), 1);
        Player j2 = new Player(getPseudoP2(), 2);
        partie.startGame(j1, j2);
    }
    
}