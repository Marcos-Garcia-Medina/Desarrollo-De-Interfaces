package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IndexController {
	@FXML
	private TextField name;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private Label lblMsg;
	
	@FXML
	public void welcome() {
		lblMsg.setText("Welcome, " + name.getText() + ".");
	}
}
