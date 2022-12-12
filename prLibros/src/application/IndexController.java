package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Result;

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

public class IndexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private ChoiceBox chbEditorial;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtPaginas;

	@FXML
	private TableView<Libro> tableLibros;

	@FXML
	private TableColumn<Libro, String> columTitulo;

	@FXML
	private TableColumn<Libro, String> columEditorial;

	@FXML
	private TableColumn<Libro, String> columAutor;

	@FXML
	private TableColumn<Libro, Integer> columPaginas;

	@FXML
	private Button btAniadir;

	@FXML
	private Button btBorrar;

	private ObservableList<Libro> listaLibros = FXCollections
			.observableArrayList();

	public ObservableList<String> listaEditoriales = FXCollections.observableArrayList("Planeta", "Altaya", "Kadokawa",
			"Penguin Libros");

	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);

		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		tableLibros.setItems(listaLibros); 
		
		ObservableList listaLibrosBD = getLibrosBD();
		tableLibros.setItems(listaLibrosBD);	
	}
	
	private ObservableList<Libro> getLibrosBD(){
		
		ObservableList<Libro> listaLibrosBD = FXCollections.observableArrayList();
		
		DatabaseConnection bdConnection = new DatabaseConnection();
		Connection connection = bdConnection.getConnection();
		
		String query = "select * from libros";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt("id"),rs.getString("titulo"), rs.getString("editorial"), rs.getString("autor"), rs.getInt("paginas"));
				listaLibrosBD.add(libro);
			}
			
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaLibrosBD;
	}
	

	@FXML
	public void aniadirLibro(ActionEvent event) {
		
		if(txtTitulo.getText().isEmpty() || chbEditorial.getSelectionModel().isEmpty() || txtAutor.getText().isEmpty() || txtPaginas.getText().isEmpty()){
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Informaci�n incompleta");
			alerta.setHeaderText("Falta informaci�n del libro");
			alerta.setContentText("Por favor, introduce todos los campos");
			alerta.showAndWait();

			txtTitulo.clear();
			chbEditorial.getSelectionModel().clearSelection();
			txtAutor.clear();
			txtPaginas.clear(); 
		
		
		}else if (!esNumero(txtPaginas.getText())) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("No se ha introducido un n�mero en las p�ginas");
			alerta.setContentText("Por favor, introduzca un n�mero en las p�ginas");
			alerta.showAndWait(); 
			
		}else {
			Libro libro = new Libro(txtTitulo.getText(), chbEditorial.getValue().toString(),txtAutor.getText(),	Integer.parseInt(txtPaginas.getText()));
			txtTitulo.clear();
			chbEditorial.getSelectionModel().clearSelection();
			txtAutor.clear();
			txtPaginas.clear(); 
			
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			try {
				String query = "insert into libros (titulo, editorial, autor, paginas) values (?,?,?,?)";
				
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, libro.getTitulo());
				ps.setString(2, libro.getEditorial());
				ps.setString(3, libro.getAutor());
				ps.setInt(4, libro.getPaginas());
				ps.executeUpdate();
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ObservableList listaLibrosBD = getLibrosBD();
			tableLibros.setItems(listaLibrosBD);	
		}
		
	}

		

	public void borrarLibro(ActionEvent event) {

		int indiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();
		System.out.println("Indice a borrar: " + indiceSeleccionado);
		if(indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha podido borrar el elemento");
			alerta.setContentText("Por favor, seleccione el elemento que quiere borrar");
			alerta.showAndWait(); 
		}else {		
			DatabaseConnection bdConnection = new DatabaseConnection();
			Connection connection = bdConnection.getConnection();
			
			try {
				String query = "delete from libros where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Libro libro = tableLibros.getSelectionModel().getSelectedItem();
				ps.setInt(1,libro.getId());
				ps.executeUpdate();
				
				tableLibros.getSelectionModel().clearSelection();
				
				ObservableList listaLibrosBD = getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}