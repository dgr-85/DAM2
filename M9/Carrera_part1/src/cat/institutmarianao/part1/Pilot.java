package cat.institutmarianao.part1;

public class Pilot implements Runnable {
	private String name;
	private int laps;
	private int totalTimeMillis;
	private RaceStatus raceStatus;

	public Pilot(String name, RaceStatus raceStatus) {
		this.name = name;
		this.raceStatus = raceStatus;
		this.laps = Race.TOTAL_LAPS;
		this.totalTimeMillis = 0;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalTimeMillis() {
		return totalTimeMillis;
	}

	public void setTotalTimeMillis(int totalTimeMillis) {
		this.totalTimeMillis = totalTimeMillis;
	}

	@Override
	public void run() {
		while (!raceStatus.isFinish()) {
			try {
				int lapTime = 23 + (int) (Math.random() * 5);
				totalTimeMillis += lapTime;
				Thread.sleep(lapTime);
				laps--;
				raceStatus.lap(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
