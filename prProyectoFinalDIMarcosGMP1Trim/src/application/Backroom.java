package application;

public class Backroom {
	
	private int levelNumber;
	private String backroomName;
	private int entitysNumber;
	private String difficulty;
	
	public Backroom(int levelNumber, String backroomName, int entitysNumber, String difficulty) {
		super();
		this.levelNumber = levelNumber;
		this.backroomName = backroomName;
		this.entitysNumber = entitysNumber;
		this.difficulty = difficulty;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getBackroomName() {
		return backroomName;
	}

	public void setBackroomName(String backroomName) {
		this.backroomName = backroomName;
	}

	public int getEntitysNumber() {
		return entitysNumber;
	}

	public void setEntitysNumber(int entitysNumber) {
		this.entitysNumber = entitysNumber;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Backroom [levelNumber=" + levelNumber + ", backroomName=" + backroomName + ", entitysNumber="
				+ entitysNumber + ", difficulty=" + difficulty + "]";
	}
	
}
