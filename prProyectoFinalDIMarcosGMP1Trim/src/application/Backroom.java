package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase del pojo Backroom.
 * @author Marcos Garcia Medina.
 */
public class Backroom {
	
	/**
	 * levelNumber: Numero de la backroom.
	 */
	private SimpleIntegerProperty levelNumber;
	/**
	 * backroomName: Nombre de la backroom.
	 */
	private SimpleStringProperty backroomName;
	/**
	 * entitysNumber: Numero de entidades de la backrooom.
	 */
	private SimpleIntegerProperty entitysNumber;
	/**
	 * difficulty: Dificultad de las backroom.
	 */
	private SimpleStringProperty difficulty;
	
	/**
	 * Constuctor del Objeto backroom.
	 * @param levelNumber Numero de la backroom.
	 * @param backroomName Nombre de la backroom.
	 * @param entitysNumber Numero de entidades de la backrooom.
	 * @param difficulty Dificultad de las backroom.
	 */
	public Backroom(int levelNumber, String backroomName,
			int entitysNumber, String difficulty) {
		super();
		this.levelNumber = new SimpleIntegerProperty(levelNumber);
		this.backroomName = new SimpleStringProperty(backroomName);
		this.entitysNumber = new SimpleIntegerProperty(entitysNumber);
		this.difficulty = new SimpleStringProperty(difficulty);
	}

	/**
	 * Funcion getLevelNumber para obtener el levelNumber.
	 * @return Devuelve el levelNumber.
	 */
	public int getLevelNumber() {
		return levelNumber.get();
	}

	/**
	 * Funcion setLevelNumber para poner un levelNumber.
	 * @param levelNumber El nuevo levelNumber.
	 */
	public void setLevelNumber(SimpleIntegerProperty levelNumber) {
		this.levelNumber = levelNumber;
	}

	/**
	 * Funcion getBackroomName para obtener el backroomName.
	 * @return Devuelve el backroomName.
	 */
	public String getBackroomName() {
		return backroomName.get();
	}

	/**
	 * Funcion setBackroomName para poner un backroomName.
	 * @param backroomName El nuevo backroomName.
	 */
	public void setBackroomName(SimpleStringProperty backroomName) {
		this.backroomName = backroomName;
	}

	/**
	 * Funcion getEntitysNumber para obtener el entitysNumber.
	 * @return Devuelve el entitysNumber.
	 */
	public int getEntitysNumber() {
		return entitysNumber.get();
	}

	/**
	 * Funcion setEntitysNumber para poner un entitysNumber.
	 * @param entitysNumber El nuevo entitysNumber.
	 */
	public void setEntitysNumber(SimpleIntegerProperty entitysNumber) {
		this.entitysNumber = entitysNumber;
	}

	/**
	 * Funcion getDifficulty para obtener el difficulty.
	 * @return Devuelve el difficulty.
	 */
	public String getDifficulty() {
		return difficulty.get();
	}

	/**
	 * Funcion setDifficulty para poner un difficulty.
	 * @param difficulty El nuevo difficulty.
	 */
	public void setDifficulty(SimpleStringProperty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Funcion toString para imprimir el objeto Backroom.
	 */
	@Override
	public String toString() {
		return "Backroom [levelNumber=" + levelNumber + ", backroomName=" + backroomName + ", entitysNumber="
				+ entitysNumber + ", difficulty=" + difficulty + "]";
	}
}
