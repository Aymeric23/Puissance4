/* GameDuo.java                                  25 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */
package application.controleur;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import application.Game;
import application.Grid;
import application.Player;
import application.Puissance4;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
    
    /* elements FXML */
    @FXML
    private HBox grille;
    @FXML
    private Label joueur1,
                  joueur2;
    @FXML 
    private Circle cercleJoueur1,
                   cercleJoueur2;
    
    private static final String NOM_JEU = "Versus";
    private static Stage stage;
    private static Scene scene;
    private static Parent racine;
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public static Game partie;
    
    /**
     * Change la scene actuelle par la scene qui correspond au menu
     * Permet un retour a l'ecran du menu
     * @param PrimaryStage 
     * @throws IOException 
     */
    @FXML
    private void showSceneMenu(MouseEvent event) throws IOException {
        //Demande a l'utilisateur s'il veut sauvegarder avant de quitter
        alerteSauvegarder();
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
     * Chargement d'une partie en mode Duo
     * @param sauvegarde a lancer
     */
    public static void loadDuoGame(Game sauvegarde) {
        partie = sauvegarde;
        /* lancement de la partie */
        partie.startGame();      
    }
    
    /**
     * Creation d'une partie en mode Duo
     */
    public static void createDuoGame() {
        partie = new Game(2);
        String pseudoJ1 = alertePeudoJoueur1();
        /* Si pseudo vide */
        if (pseudoJ1.isBlank() || pseudoJ1 == null) {
            pseudoJ1 = "Joueur 1";
        }
        
        /* Si pseudo vide */
        String pseudoJ2 = alertePeudoJoueur2();
        if (pseudoJ2.isBlank() || pseudoJ2 == null) {
            pseudoJ2 = "Joueur 2";
        }
       // Demander les pseudos
        Player j1 = new Player(pseudoJ1);
        if (pseudoJ1.equals(pseudoJ2)) {
            pseudoJ2 += "2";
        }
        Player j2 = new Player(pseudoJ2);
        /*Affecte une couleur de manière aléatoire*/
        randomizePlayerColor(j1, j2);
        partie.setPlayer1(j1);
        partie.setPlayer2(j2);
        partie.setPlayerPlaying(j1);
        partie.setGrid(new Grid()); //Grille vide
        
        /* lancement de la partie */
        partie.startGame();      
    }
    
    
    /** 
     * Affecte une couleur aux 2 joueurs de façon aléatoire
     * @param Player1 Joueur1
     * @param Player2 Joueur2
     */
    private static void randomizePlayerColor(Player player1, Player player2) {
        String c1 = "#e45555";
        String c2 = "#fbfd87";
        
        String couleurs[] = {c1, c2, c1};
        
        Random aleatoire = new Random();
        int numCouleur = aleatoire.nextInt(2); // 0 ou 1
        player1.setColor(couleurs[numCouleur]);
        player2.setColor(couleurs[numCouleur+1]);
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
    
    
    //TODO vérifier que la grille n'est pas plein et pas de victoire => partie fini car victoire impossible
    
    /**
     * Lorsque la partie ce lance, les pseudos sont demandés 
     * @return pseudo1 saisi par l'utilisateur
     */
    private static String alertePeudoJoueur1() {        
        TextInputDialog popUp = new TextInputDialog();
        popUp.setTitle(Puissance4.NOM_LOGICIEL + " - " + NOM_JEU);
        popUp.setHeaderText("Saisissez le pseudo du joueur 1");
        Optional<String> resultat = popUp.showAndWait();

        return resultat.map(valeur-> {
            return valeur;} ).orElse("Joueur 1");
    }
    
    /**
     * Lorsque la partie ce lance, les pseudos sont demandés 
     * @return pseudo2 saisi par l'utilisateur
     */
    private static String alertePeudoJoueur2() {
        TextInputDialog popUp = new TextInputDialog();
        popUp.setTitle(Puissance4.NOM_LOGICIEL + " - " + NOM_JEU);
        popUp.setHeaderText("Saisissez le pseudo du joueur 2");
        Optional<String> resultat = popUp.showAndWait();

        return resultat.map(valeur-> {
            return valeur;} ).orElse("Joueur 2");
    }
    
    
    /**
     * Lorsque le joueur veut quitter si la partie n'est pas finie, 
     * le jeu lui demande s'il veut sauvegarder.
     */
    private static void alerteSauvegarder() {
        //TODO verifie partie non finie 
        Alert popUp = new Alert(AlertType.WARNING);
        popUp.setAlertType(AlertType.WARNING);
        popUp.setTitle(Puissance4.NOM_LOGICIEL + " - " + NOM_JEU);
        popUp.setHeaderText("Attention !\n"
                        + "Vous êtes sur le point de quitter une partie en cours.\n"
                        + "Souhaitez-vous sauvegarder la partie ?");
        ButtonType sauvegarder = new ButtonType("Sauvegarder et quitter");
        ButtonType nePasSauvegarder = new ButtonType("Quitter sans sauvegarder");
        ButtonType annuler = new ButtonType("Annuler");

        popUp.getButtonTypes().clear();
        popUp.getButtonTypes().addAll(sauvegarder, nePasSauvegarder, annuler);

        Optional<ButtonType> option = popUp.showAndWait();
        if (option.get() == sauvegarder) {
            partie.sauvegarder();
        } else if (option.get() == nePasSauvegarder) {
            //ne pas sauvegarder
        } else {
            //annuler
        }
    }


    
    /**
     * Lorsque la partie est gagnee pas un joueur, une alrte/popup est affichee 
     * a l'ecran pour indiquer la victoire
     */
    private static void alerteVictoire() {
        if(partie.getWinner() != null) {
            Alert popUp = new Alert(AlertType.INFORMATION);
            popUp.setAlertType(AlertType.INFORMATION);
            popUp.setTitle(Puissance4.NOM_LOGICIEL + " - " + NOM_JEU);
            popUp.setHeaderText("Fin de la partie !\n"
                                + "La partie est remportée par " 
                                + partie.getWinner().getPseudo() + " !");
            popUp.show();
            
        }
    }
    
    
    /** 
     * Lorsque le joueur ajoute un jeton
     * @param x 
     * @param y 
     * @param colone à modifier
     * @param player 
     * 
     */
    private void addJeton(int x, int y, Player player) {
        partie.getGrid().setCaseContent(y, x, player.getPrefix());
        setCircleColor(""+ x + y, player.getColor());
    }
    
    /** 
     * initialise les composant graphiques tel que la grille, les pseudos 
     * des joueurs, leur couleur...
     * 
     */
    @FXML
    private void initGraphicsComponent() {
        Grid grille = partie.getGrid();
        int[][] matrice = grille.getMatrice();
        int prefix = 0;
        Player detenteurDuPion;
        Player playingPlayer;
        
        /* On met a jour la grille graphique lors du chargement */
        for (int x = 0; x < matrice.length; x++) {
            for (int y = 0; y < matrice[x].length; y++) {
                prefix = grille.getCaseContent(x, y); //0, 1 ou 2
                if (prefix != 0) { // Si un joueur a pose un pion sur cette case
                    detenteurDuPion = partie.getPlayerByPrefix(prefix);
                    setCircleColor(""+ y + x, detenteurDuPion.getColor());
                }
            }
        }
        joueur1.setText(partie.getPlayer1().getPseudo());
        joueur2.setText(partie.getPlayer2().getPseudo());
        cercleJoueur1.setFill(Paint.valueOf(partie.getPlayer1().getColor()));
        cercleJoueur2.setFill(Paint.valueOf(partie.getPlayer2().getColor()));
        /* on met a jour les cartes des joueurs */
//        playingPlayer = partie.getPlayerPlaying();
//        if (playingPlayer.getPrefix() == 1) {
//            joueur1.set
//        }
    }
    
    
    
    /** 
     * met a jour le visuel de la grille
     * @param id
     * @param color 
     */
     private void setCircleColor(String id, String color) {
        System.out.println("looking for " + id);
        ((Shape) grille.lookup("#"+id)).setFill(Paint.valueOf(color));
    }
}