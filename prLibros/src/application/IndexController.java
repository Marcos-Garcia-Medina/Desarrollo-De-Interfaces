package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class IndexController {

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private ChoiceBox choiceConsola;
	@FXML
	private ChoiceBox choicePEGI;
	@FXML
	private TableView <Juego> tableVideojuegos;
	@FXML
	private TableColumn <Juego,String> nombre;
	@FXML
	private TableColumn <Juego,Integer> precio;
	@FXML
	private TableColumn <Juego,String> consola;
	@FXML
	private TableColumn <Juego,String> pegi;
	
	public ObservableList<String> listaConsolas =
			FXCollections.observableArrayList("PS4","XBOX 360","Nintendo Switch");
	
	public ObservableList<String> listaPegis =
			FXCollections.observableArrayList("4","18","99");
	
	@FXML
	private void initialize() {
		choiceConsola.setItems(listaConsolas);
		column
	}
}
