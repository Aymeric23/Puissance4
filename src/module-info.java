/**
 * @author Romain
 *
 */
module p4 {
	requires javafx.controls;
        requires javafx.graphics;
        requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.controleur to javafx.fxml;
}
