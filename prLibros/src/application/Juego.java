package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Juego {
	private SimpleStringProperty nombre;
	private SimpleIntegerProperty precio;
	private SimpleIntegerProperty pegi;
	private SimpleStringProperty consola;
	
	public Juego(String nombre, int precio, int pegi,
			String consola) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleIntegerProperty(precio);
		this.pegi = new SimpleIntegerProperty(pegi);
		this.consola = new SimpleStringProperty(nombre);
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	
	public int getPrecio() {
		return precio.get();
	}
	
	public void setPrecio(int precio) {
		this.precio = new SimpleIntegerProperty(precio);
	}
	
	public int getPegi() {
		return pegi.get();
	}
	
	public void setPegi(int pegi) {
		this.pegi = new SimpleIntegerProperty(pegi);
	}
	
	public String getConsola() {
		return consola.get();
	}
	
	
	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}

}
