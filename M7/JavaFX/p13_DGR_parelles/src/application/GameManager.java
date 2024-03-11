package application;

public class GameManager {

	private Integer points;

	private static GameManager manager;

	private GameManager(Integer points) {
		super();
		this.points = points;
	}

	public static GameManager getManager() {
		if (manager == null) {
			manager = new GameManager(6);
		}
		return manager;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
