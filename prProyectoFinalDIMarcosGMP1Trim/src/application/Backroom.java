package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Backroom {
	
	private SimpleIntegerProperty levelNumber;
	private SimpleStringProperty backroomName;
	private SimpleIntegerProperty entitysNumber;
	private SimpleStringProperty difficulty;
	
	public Backroom(int levelNumber, String backroomName,
			int entitysNumber, String difficulty) {
		super();
		this.levelNumber = new SimpleIntegerProperty(levelNumber);
		this.backroomName = new SimpleStringProperty(backroomName);
		this.entitysNumber = new SimpleIntegerProperty(entitysNumber);
		this.difficulty = new SimpleStringProperty(difficulty);
	}

	public int getLevelNumber() {
		return levelNumber.get();
	}

	public void setLevelNumber(SimpleIntegerProperty levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getBackroomName() {
		return backroomName.get();
	}

	public void setBackroomName(SimpleStringProperty backroomName) {
		this.backroomName = backroomName;
	}

	public int getEntitysNumber() {
		return entitysNumber.get();
	}

	public void setEntitysNumber(SimpleIntegerProperty entitysNumber) {
		this.entitysNumber = entitysNumber;
	}

	public String getDifficulty() {
		return difficulty.get();
	}

	public void setDifficulty(SimpleStringProperty difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Backroom [levelNumber=" + levelNumber + ", backroomName=" + backroomName + ", entitysNumber="
				+ entitysNumber + ", difficulty=" + difficulty + "]";
	}
}
