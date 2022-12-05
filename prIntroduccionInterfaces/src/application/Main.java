package application;

import java.awt.Panel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Parte 1 Ej 1
		    /*StackPane sp = new StackPane(); sp = new StackPane(); sp.setPadding(new
			Insets(15)); sp.getChildren().addAll(new Rectangle(500, 500,Color.AQUA),new
			Rectangle(250, 250, Color.RED), new Rectangle(100, 100, Color.GREEN));
			 
			Scene s = new Scene(sp); primaryStage.setScene(s); primaryStage.show();*/
			 
			 
			//Parte 1 Ej 2
			/*
			 BorderPane bp = new BorderPane(); bp.getChildren().addAll();
			 
			 Rectangle r1 = new Rectangle(300, 300,Color.DARKBLUE); Rectangle r2 = new
			 Rectangle(400, 50,Color.DARKGREEN); Rectangle r3 = new Rectangle(400,
			 50,Color.DARKGREEN); Rectangle r4 = new Rectangle(50, 300,Color.LIGHTGREY);
			 Rectangle r5 = new Rectangle(50, 300,Color.LIGHTGREY);
			 
			 bp.setCenter(r1); bp.setTop(r2); bp.setBottom(r3); bp.setRight(r4);
			 bp.setLeft(r5);
			 
			 Scene s = new Scene(bp); primaryStage.setScene(s); primaryStage.show();
			 */

			//Teclado numérico:
            /*Button boton1 = new Button("1");
            Button boton2 = new Button("2");
            Button boton3 = new Button("3");
            Button boton4 = new Button("4");
            Button boton5 = new Button("5");
            Button boton6 = new Button("6");
            Button boton7 = new Button("7");
            Button boton8 = new Button("8");
            Button boton9 = new Button("9");
            Button boton0 = new Button("0");
            Button botonLlamar = new Button("Llamar");
            Button botonColgar = new Button("Colgar");

            BorderPane panel = new BorderPane();
            Label label_numero = new Label();
            panel.setBottom(label_numero);


            GridPane grid = new GridPane();
            panel.setCenter(grid);

            boton7.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String numero = label_numero.getText() + 7;
					label_numero.setText(numero);
					
				}
			});
            
            boton8.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String numero = label_numero.getText() + 8;
					label_numero.setText(numero);
					
				}
			});
            
            grid.setVgap(0);
            grid.setHgap(0);
            grid.add(boton1, 0, 2); //Primero va la columna (0), y luego la fila (2)
            grid.add(boton2, 1, 2);
            grid.add(boton3, 2, 2);
            grid.add(boton4, 0, 1);
            grid.add(boton5, 1, 1);
            grid.add(boton6, 2, 1);
            grid.add(boton7, 0, 0);
            grid.add(boton8, 1, 0);
            grid.add(boton9, 2, 0);
            grid.add(boton0, 1, 3);
            grid.add(botonLlamar, 3, 1);
            grid.add(botonColgar, 3, 2);

            Scene s = new Scene(panel,300,300);
            primaryStage.setScene(s);
            primaryStage.show();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
