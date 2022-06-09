/* GameDuo.java                                  25 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import application.Color;
import application.Game;
import application.Grid;
import application.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
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
    private HBox grille;
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static Game partie = new Game(date.format(LocalDateTime.now()), 2);
    
    /**
     * Change la scene actuelle par la scene qui correspond au menu
     * Permet un retour a l'ecran du menu
     * @param PrimaryStage 
     * @throws IOException 
     */
    @FXML
    private void showSceneMenu(MouseEvent event) throws IOException {
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
    
    /**
     * Recupere le pseudo du joueur 1 saisi dans la TextField fx:id=pseudoP1
     * @return le pseudo du joueur 1
     */
    @FXML
    public String getPseudoP1() {
        return pseudoP1.getText();
    }
    
    /**
     * Recupere le pseudo du joueur 1 saisi dans la TextField fx:id=pseudoP1
     * @return le pseudo du joueur 2
     */
    @FXML
    public String getPseudoP2() {
        return pseudoP2.getText();
    }
    
    
    /** 
     * Affecte une couleur aux 2 joueurs de façon aléatoire
     * @param Player1 Joueur1
     * @param Player2 Joueur2
     */
    private static void randomizePlayerColor(Player player1, Player player2) {
        Color c1 = new Color(1, "#e45555");
        Color c2 = new Color(2, "#fbfd87");
        
        Color couleurs[] = {c1, c2, c1};
        
        Random aleatoire = new Random();
        int numCouleur = aleatoire.nextInt(2); // 0 ou 1
        player1.setColor(couleurs[numCouleur]);
        player2.setColor(couleurs[numCouleur+1]);
    }
    
    
    /**
     * Lancement de la partie en mode Duo
     */
    @FXML
    public void runTheGame() {
        Player j1 = new Player(getPseudoP1(), 1);
        Player j2 = new Player(getPseudoP2(), 2);
        /*Affecte une couleur de manière aléatoire*/
        randomizePlayerColor(j1, j2);
        partie.setPlayer1(j1);
        partie.setPlayer2(j2);
        partie.setPlayerPlaying(j1);
        
        /* lancement de la partie */
        partie.startGame();      
    }
    
    
    /** 
     * Se déclenche lorsque le joueur clic sur une colone
     * @param event 
     */
    @FXML
    private void clickingOnColone(MouseEvent event) {
        /* Récupère l'id de la colone cliquée */
        VBox idColone = (VBox) event.getSource();
        /* Récupère l'id sous forme de String et le converti en int*/
        int idColoneInt = Integer.parseInt(idColone.getId());
        
        if (!partie.getGrid().isFullColumn(idColoneInt)) {
            /*Recherche une case vide*/
            partie.getGrid().getEmptyCaseFromColumn(idColoneInt);
            int x = partie.getGrid().getCurrentColumn();
            int y = partie.getGrid().getCurrentLine();
            addJeton(x, y, partie.getPlayerPlaying());
            
            if (partie.getGrid().isAlign(partie.getPlayerPlaying())) {
                System.out.println("winner");
                System.out.println("winner");
                System.out.println("winner");
                System.out.println("winner");
                System.out.println("winner");
                System.out.println("winner");
                partie.setWinner(partie.getPlayerPlaying());
                alerteVictoire();
                
                
            }
            partie.switchPlayingPlayer();
         }
    }
   

    
    /**
     * Lorsque la partie est gagnee pas un joueur, une alrte/popup est affichee 
     * a l'ecran pour indiquer la victoire
     */
    private static void alerteVictoire() {
        if(partie.getWinner() != null) {
            Alert popUp = new Alert(AlertType.INFORMATION);
            popUp.
            popUp.setAlertType(AlertType.INFORMATION);
            popUp.setTitle("Fin de la partie");
            popUp.setHeaderText("Partie remportée par " + partie.getWinner().getPseudo() + " !");
            popUp.show();
        }
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param x 
     * @param y 
     * @param colone à modifier
     * @param player 
     * 
     */
    public void addJeton(int x, int y, Player player) {
        partie.getGrid().setCase(y, x, player);
        setCircleColor(""+ x + y, player.getColor().getColorHexa());
    }
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param id
     * @param color 
     */
     private void setCircleColor(String id, String color) {
        System.out.println("looking for " + id);
        ((Shape) grille.lookup("#"+id)).setFill(Paint.valueOf(color));
    }
}