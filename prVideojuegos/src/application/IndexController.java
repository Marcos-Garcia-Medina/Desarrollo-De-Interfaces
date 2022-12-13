package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * 
 * @author Marcos Garcia Medina.
 *
 */
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
	private TableColumn <Juego,Integer> columnPegi;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;
	
	public ObservableList<Juego> listaVideojuegos =
			FXCollections.observableArrayList();
	
	public ObservableList<String> listaConsolas =
			FXCollections.observableArrayList("PS4","XBOX 360","Nintendo Switch");
	
	public ObservableList<Integer> listaPegis =
			FXCollections.observableArrayList(4,18,99);
	
	@FXML
	/**
	 * 
	 */
	private void initialize() {
			
		choiceConsola.setItems(listaConsolas);
		choicePEGI.setItems(listaPegis);
		
			
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		
		tableVideojuegos.setItems(listaVideojuegos); 
		
		ObservableList listaJuegosBD = getJuegosBD();
		tableVideojuegos.setItems(listaJuegosBD); 
	}
	
	private ObservableList<Juego> getJuegosBD(){
		
		ObservableList<Juego> listaJuegosBD = FXCollections.observableArrayList();
		
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from videojuegos";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Juego juego = new Juego(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getInt("precio"),
						rs.getInt("pegi"),
						rs.getString("consola"));
				listaJuegosBD.add(juego);
			}
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaJuegosBD;
	}
	
	@FXML
	public void anadirJuego(ActionEvent event) {
		if(!txtNombre.getText().isEmpty() || !txtPrecio.getText().isEmpty() || !choiceConsola.getSelectionModel().isEmpty() || !choicePEGI.getSelectionModel().isEmpty()) {
			if(isNumeric(txtPrecio.getText())) {
				Juego nuevoJuego = new Juego(
					txtNombre.getText(),
					Integer.parseInt(txtPrecio.getText()),
					Integer.parseInt(choicePEGI.getValue().toString()),
					choiceConsola.getValue().toString());
				
				System.out.println(nuevoJuego.getNombre());
				
	            DatabaseConnection bdConnection = new DatabaseConnection();
	            Connection connection = bdConnection.getConnection();
	            
	            String query = "insert into videojuegos (nombre, precio, consola, pegi) values (?,?,?,?)";

                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, nuevoJuego.getNombre());
                    ps.setInt(2, nuevoJuego.getPrecio());
                    ps.setString(3, nuevoJuego.getConsola());
                    ps.setInt(4, nuevoJuego.getPegi());
					ps.executeUpdate();
					
					listaVideojuegos.add(nuevoJuego);
					
					ObservableList listaVideojuegosBD = getJuegosBD();

		            tableVideojuegos.setItems(listaVideojuegosBD);
					
	                connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un precio.");
				alerta.setContentText("Por favor, introduzca un precio.");
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
		
		if(indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado algo que borrar.");
			alerta.setContentText("Asegurese de que hay algo que borrar.");
			alerta.showAndWait();
		}else {
			
			DatabaseConnection bdConnection = new DatabaseConnection();
			Connection connection = bdConnection.getConnection();
			
			try {
				String query = "delete from videojuegos where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Juego juego = tableVideojuegos.getSelectionModel().getSelectedItem();
				ps.setInt(1,juego.getId());
				ps.executeUpdate();
				
				tableVideojuegos.getSelectionModel().clearSelection();
				
				ObservableList listaJuegosBD = getJuegosBD();
				tableVideojuegos.setItems(listaJuegosBD);
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
