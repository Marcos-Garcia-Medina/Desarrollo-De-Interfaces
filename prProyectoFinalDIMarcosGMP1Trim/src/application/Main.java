package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *  Clase main para ejecutar el programa.
 * @author Marcos Garcia Medina.
 */
public class Main extends Application {
	/**
	 * Funcion start que se ejecuta cuando el programa se inicia.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Index.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("/application/br.png"));
			primaryStage.setTitle("Backrooms BD MGM");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion main.
	 * @param args Son los argumentos.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
