package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Juego {
	private SimpleStringProperty nombre;
	private SimpleIntegerProperty precio;
	private SimpleStringProperty pegi;
	private SimpleStringProperty consola;
	
	public Juego(SimpleStringProperty nombre, SimpleIntegerProperty precio, SimpleStringProperty pegi,
			SimpleStringProperty consola) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.pegi = pegi;
		this.consola = consola;
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public String setNombre() {
		this.nombre = new SimpleStringProperty(nombre);
	}
	
	public int getPrecio() {
		return precio.get();
	}
	
	public String getPegi() {
		return pegi.get();
	}
	
	public String getConsola() {
		return consola.get();
	}

}
