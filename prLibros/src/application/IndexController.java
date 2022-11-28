package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;
	
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
	
	@FXML
	public void anadirJuego(ActionEvent event) {
		if(!txtNombre.getText().isEmpty() || !txtPrecio.getText().isEmpty() || !choiceConsola.getSelectionModel().isEmpty() || !choicePEGI.getSelectionModel().isEmpty()) {
			if(isNumeric(txtPrecio.getText())) {
				Juego nuevoJuego = new Juego(
					txtNombre.getText(),
					Integer.parseInt(txtPrecio.getText())
					,Integer.parseInt(choicePEGI.getValue().toString())
					,choiceConsola.getValue().toString());
				
				listaVideojuegos.add(nuevoJuego);
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en las paginas.");
				alerta.setContentText("Por favor, introduzca un numero en las paginas.");
				alerta.showAndWait();
			}
		}else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("Algun/os campo/s esta incompleto.");
			alerta.setContentText("Por favor, revise el estado de los campos.");
			alerta.showAndWait();
		}
		
		txtNombre.clear();
		txtPrecio.clear();
		choicePEGI.getSelectionModel().clearSelection();
		choiceConsola.getSelectionModel().clearSelection();
	}
	
	@FXML
	public void borrarJuego(ActionEvent event) {
		int indiceSeleccionado = tableVideojuegos.getSelectionModel().getSelectedIndex();
		
		tableVideojuegos.getItems().remove(indiceSeleccionado);
	}
	
	public boolean isNumeric (String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}
