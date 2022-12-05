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

public class IndexController {
	@FXML
	private TextField txtLevelNumber;
	@FXML
	private TextField txtBackroomName;
	@FXML
	private ChoiceBox chEntitysNumber;
	@FXML
	private ChoiceBox chDifficulty;
	@FXML
	private TableView <Backroom> tableBackrooms;
	@FXML
	private TableColumn <Backroom,Integer> columnNumber;
	@FXML
	private TableColumn <Backroom,String> columnName;
	@FXML
	private TableColumn <Backroom,Integer> columnEntitys;
	@FXML
	private TableColumn <Backroom,String> columnDifficulty;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnDelete;
	
	public ObservableList<Backroom> backroomsList =
			FXCollections.observableArrayList(new Backroom(1,"Backroom 1",1,"Easy"));
	
	public ObservableList<Integer> entitysNumberList =
			FXCollections.observableArrayList(1,10,999);
	
	public ObservableList<String> difficultyList =
			FXCollections.observableArrayList("Easy","Medium","Hard","Extreme");
	
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
	
	@FXML
	public void addBackroom(ActionEvent event) {
		if(isNumeric(txtLevelNumber.getText())) {
			if(txtLevelNumber.getText().isEmpty() || txtBackroomName.getText().isEmpty() || chEntitysNumber.getSelectionModel().isEmpty() || chDifficulty.getSelectionModel().isEmpty()) {
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Error at insert.");
				alerta.setHeaderText("Some fields could be empty.");
				alerta.setContentText("Please, fill all the fields to insert.");
				alerta.showAndWait();
			}else {
				Backroom newBackroom = new Backroom(
					Integer.parseInt(txtLevelNumber.getText())
					,txtBackroomName.getText(),
					Integer.parseInt(chEntitysNumber.getValue().toString())
					,chDifficulty.getValue().toString());
							
				backroomsList.add(newBackroom);
			
				txtLevelNumber.clear();
				txtBackroomName.clear();
				chEntitysNumber.getSelectionModel().clearSelection();
				chDifficulty.getSelectionModel().clearSelection();
				
				insertNewBackroom(newBackroom);
				tableBackrooms.refresh();

			}
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error at insert.");
			alerta.setHeaderText("The backroom number is not a number.");
			alerta.setContentText("Please, set the backroom number as a number.");
			alerta.showAndWait();
		}
	}
	
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
			tableBackrooms.getItems().remove(selectIndex);
			tableBackrooms.getSelectionModel().clearSelection();
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
	
	public void insertNewBackroom(Backroom newBackroom) {
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

        String query = "insert into backrooms(levelNumber, backroomName, entitysNumber, difficulty) values (?,?,?,?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newBackroom.getLevelNumber());
            ps.setString(2, newBackroom.getBackroomName());
            ps.setInt(3, newBackroom.getEntitysNumber());
            ps.setString(4, newBackroom.getDifficulty());
            
            ps.executeUpdate();
			connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
