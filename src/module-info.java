/**
 * @author Romain
 *
 */
module puissance4 {
	requires javafx.controls;
        requires javafx.graphics;
        requires javafx.fxml;
        requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.controleur to javafx.fxml;
}
