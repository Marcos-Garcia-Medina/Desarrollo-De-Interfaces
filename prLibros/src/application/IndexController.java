package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableColumn <Juego,String> columnNombre;
	@FXML
	private TableColumn <Juego,Integer> columnPrecio;
	@FXML
	private TableColumn <Juego,String> columnConsola;
	@FXML
	private TableColumn <Juego,String> columnPegi;
	
	public ObservableList<Juego> listaVideojuegos =
			FXCollections.observableArrayList(new Juego("Pepito Grillo",1,4,"PS4"));
	
	public ObservableList<String> listaConsolas =
			FXCollections.observableArrayList("PS4","XBOX 360","Nintendo Switch");
	
	public ObservableList<Integer> listaPegis =
			FXCollections.observableArrayList(4,18,99);
	
	@FXML
	private void initialize() {
			
		choiceConsola.setItems(listaConsolas);
		choicePEGI.setItems(listaPegis);
		
			
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
			
		tableVideojuegos.setItems(listaVideojuegos); 
	}
}
