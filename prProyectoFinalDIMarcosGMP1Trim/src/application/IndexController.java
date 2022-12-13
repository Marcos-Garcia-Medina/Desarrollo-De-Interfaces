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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Clase que nos permitira usar y modificar los elementos de la interfaz.
 * @author Marcos Garcia Medina.
 */
public class IndexController {
	/**
	 * txtLevelNumber: TextField levelNumber.
	 */
	@FXML
	private TextField txtLevelNumber;
	/**
	 * txtBackroomName: TextField txtBackroomName.
	 */
	@FXML
	private TextField txtBackroomName;
	/**
	 * chEntitysNumber: ChoiceBox chEntitysNumber;
	 */
	@FXML
	private ChoiceBox chEntitysNumber;
	/**
	 * chDifficulty: ChoiceBox chDifficulty.
	 */
	@FXML
	private ChoiceBox chDifficulty;
	/**
	 * tableBackrooms: Tabla tableBackrooms.
	 */
	@FXML
	private TableView <Backroom> tableBackrooms;
	/**
	 * columnNumber: Columna columnNumber.
	 */
	@FXML
	private TableColumn <Backroom,Integer> columnNumber;
	/**
	 * columnName: Columna columnName.
	 */
	@FXML
	private TableColumn <Backroom,String> columnName;
	/**
	 * columnEntitys: Columna columnEntitys.
	 */
	@FXML
	private TableColumn <Backroom,Integer> columnEntitys;
	/**
	 * columnDifficulty: Columna columnDifficulty.
	 */
	@FXML
	private TableColumn <Backroom,String> columnDifficulty;
	/**
	 * btnAdd: Boton btnAdd.
	 */
	@FXML
	private Button btnAdd;
	/**
	 * btnDelete: Boton btnDelete.
	 */
	@FXML
	private Button btnDelete;
	/**
	 * backroomsList: ObservableList<Backroom> backroomsList.
	 */
	public ObservableList<Backroom> backroomsList =
			FXCollections.observableArrayList();
	/**
	 * entitysNumberList: ObservableList<Integer> entitysNumberList.
	 */
	public ObservableList<Integer> entitysNumberList =
			FXCollections.observableArrayList(1,10,999);
	/**
	 * difficultyList: ObservableList<String> difficultyList.
	 */
	public ObservableList<String> difficultyList =
			FXCollections.observableArrayList("Easy","Medium","Hard","Extreme");
	
	/**
	 * Funcion que se ejecuta al iniciar el programa.
	 * Esta, asigna las opcion a los choiceboxs, asigna los valores de las columnas de la tabla,
	 * y recupera las backrooms de la base de datos que ya estan, y las mete en la tabla.
	 */
	@FXML
	private void initialize() {
		
		chEntitysNumber.setItems(entitysNumberList);
		chDifficulty.setItems(difficultyList);
		
			
		columnNumber.setCellValueFactory(new PropertyValueFactory<>("levelNumber"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("backroomName"));
		columnEntitys.setCellValueFactory(new PropertyValueFactory<>("entitysNumber"));
		columnDifficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
		
		ObservableList backroomsSelect = getBackroomsBD();
		tableBackrooms.setItems(backroomsSelect);
	}
	
	/**
	 * Funcion getBackroomsBD que obtiene todas las Backrooms ya introducidas en la base de datos, metiendolas
	 * en un ObservableList.
	 * @return Devuelve un ObservableList con todas las backrooms.
	 */
	private ObservableList<Backroom> getBackroomsBD() {
		
		ObservableList<Backroom> backroomsListBD = FXCollections.observableArrayList();
		
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from backrooms";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Backroom backroom = new Backroom(rs.getInt("levelNumber"),
						rs.getString("backroomName"),
						rs.getInt("entitysNumber"),
						rs.getString("difficulty"));
				backroomsListBD.add(backroom);
			}
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return backroomsListBD;
	}
	
	/**
	 * Funcion addBackroom que añade una nueva backroom tanto a la tabla, como a la base de datos.
	 * @param event El evento que hara que se ejecute la funcion.
	 */
	@FXML
	public void addBackroom(ActionEvent event) {
		
		if(txtLevelNumber.getText().isEmpty() || txtBackroomName.getText().isEmpty() || chEntitysNumber.getSelectionModel().isEmpty() || chDifficulty.getSelectionModel().isEmpty()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error at insert.");
			alerta.setHeaderText("Some fields could be empty.");
			alerta.setContentText("Please, fill all the fields to insert a backroom.");
			alerta.showAndWait();
		}else {
			if(isNumeric(txtLevelNumber.getText())) {
			
				Backroom newBackroom = new Backroom(
					Integer.parseInt(txtLevelNumber.getText()),
					txtBackroomName.getText(),
					Integer.parseInt(chEntitysNumber.getValue().toString()),
					chDifficulty.getValue().toString());
				
				DatabaseConnection bdConnection = new DatabaseConnection();
	            Connection connection = bdConnection.getConnection();
	            
	            String query = "insert into backrooms (levelNumber, backroomName, entitysNumber, difficulty) values (?,?,?,?)";
	
	            try {
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setInt(1, newBackroom.getLevelNumber());
	                ps.setString(2, newBackroom.getBackroomName());
	                ps.setInt(3, newBackroom.getEntitysNumber());
	                ps.setString(4, newBackroom.getDifficulty());
					ps.executeUpdate();
					
					backroomsList.add(newBackroom);
					
					ObservableList backroomsBDList = getBackroomsBD();
	
		            tableBackrooms.setItems(backroomsBDList);
					
	                connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
				backroomsList.add(newBackroom);
			
				txtLevelNumber.clear();
				txtBackroomName.clear();
				chEntitysNumber.getSelectionModel().clearSelection();
				chDifficulty.getSelectionModel().clearSelection();
				
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error at insert.");
				alerta.setHeaderText("The backroom/level number is not a number.");
				alerta.setContentText("Please, set the backroom number as a number.");
				alerta.showAndWait();
			}
		}
	}
	
	/**
	 * Funcion deleteBackroom que borra una backroom de la tabla y la base de datos.
	 * @param event
	 */
	@FXML
	public void deleteBackroom(ActionEvent event) {
		int selectIndex = tableBackrooms.getSelectionModel().getSelectedIndex();
		
		if(selectIndex <= -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error at delete.");
			alert.setHeaderText("You did not select anything to delete.");
			alert.setContentText("Please, select something to delete.");
			alert.showAndWait();
		}else {
			DatabaseConnection bdConnection = new DatabaseConnection();
			Connection connection = bdConnection.getConnection();
			
			try {
				String query = "delete from backrooms where levelNumber = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Backroom backroom = tableBackrooms.getSelectionModel().getSelectedItem();
				ps.setInt(1,backroom.getLevelNumber());
				ps.executeUpdate();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tableBackrooms.getSelectionModel().clearSelection();
			
			ObservableList backroomsBDList = getBackroomsBD();
			tableBackrooms.setItems(backroomsBDList);
			

		}
	}
	
	/**
	 * Funcion que nos dice si una cadena de texto es numerica o no.
	 * @param s La cadena que comprobaremos.
	 * @return true si es numerica, false si no es numerica.
	 */
	public boolean isNumeric (String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}
