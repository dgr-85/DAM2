package application;

public class GameManager {

	private Integer numRows;

	private Integer numColumns;

	private Boolean useUppers;

	private static GameManager manager;

	public GameManager(Integer numRows, Integer numColumns, Boolean useUppers) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.useUppers = useUppers;
	}

	public static GameManager getManager() {
		if (manager == null) {
			manager = new GameManager(4, 5, true);
		}
		return manager;
	}

	public Integer getNumRows() {
		return numRows;
	}

	public void setNumRows(Integer numRows) {
		this.numRows = numRows;
	}

	public Integer getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(Integer numColumns) {
		this.numColumns = numColumns;
	}

	public Boolean getUseUppers() {
		return useUppers;
	}

	public void setUseUppers(Boolean useUppers) {
		this.useUppers = useUppers;
	}

}
