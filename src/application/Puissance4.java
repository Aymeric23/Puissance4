/* Puissance4.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe principale de l'application
 * @author aymeric.thevenet,
 *         romain.trimouille
 */
public class Puissance4 extends Application {
    
    /** stage principal **/
    public static Stage stage = null;

    /** Nom du logiciel */
    public static final String NOM_LOGICIEL = "Puissance 4";

    @Override
    public void start(Stage primaryStage) throws Exception {    
        FXMLLoader chargeurFXML = new FXMLLoader();
        // création d'un chargeur de code FXML

        // charge le fichier FXML
        chargeurFXML.setLocation(getClass().getResource("/application/fxml/Menu.fxml"));

        Parent racine = chargeurFXML.load();
        Scene scene = new Scene(racine);
        scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());

        // définition des caractéristiques de la fenêtre
        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreenExitHint("");
        primaryStage.setTitle(NOM_LOGICIEL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/images/logo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Programme principal
     * @param args argument non utilisé
     */
    public static void main(String[] args) {
        launch(args);
    }
}