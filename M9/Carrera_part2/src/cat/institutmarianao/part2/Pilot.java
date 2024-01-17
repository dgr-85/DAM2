package cat.institutmarianao.part2;

public class Pilot implements Runnable {
	private String name;
	private int laps;
	private int totalTimeMillis;
	private RaceStatus raceStatus;
	private static final int MAX_TANK = 25;
	private Team team;
	private int fuelTank;

	public Pilot(String name, RaceStatus raceStatus) {
		this.name = name;
		this.raceStatus = raceStatus;
		this.laps = Race.TOTAL_LAPS;
		this.totalTimeMillis = 0;
		this.fuelTank = MAX_TANK;
		this.team = null;
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

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public int getFuelTank() {
		return fuelTank;
	}

	public void refuel() {
		fuelTank = MAX_TANK;
	}

	public String currentState() {
		return "Pilot: [" + getName() + "(" + getTeam().getName() + "), laps=" + getLaps() + ", fuel=" + getFuelTank()
				+ "]";
	}

	@Override
	public void run() {
		while (!raceStatus.isFinish()) {
			int lapTime = 180 + (int) (Math.random() * 70);
			try {
				Thread.sleep(lapTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (raceStatus) {
				totalTimeMillis += lapTime;
				laps--;
				fuelTank--;
				raceStatus.lap(this);
			}

			if (fuelTank >= 5) {
				System.out.println(currentState());
			}

			if (fuelTank < 5) {
				System.out.println(currentState() + " needs refuelling.");
				if (!team.getBox().isFree()) {
					System.out.println(currentState() + " BOX BUSY!!");
				} else {
					synchronized (team.getBox()) {
						System.out.println(currentState() + " entering box.");
						team.getBox().setPilot(this);
						System.out.println(currentState() + " waiting in box.");
						team.getBox().notify();
					}
					synchronized (this) {
						if (fuelTank != MAX_TANK) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println(currentState() + " refuelled! Leaving box.");
					team.getBox().setPilotOut();
					synchronized (team.getBox()) {
						team.getBox().notify();
					}

				}
			}

		}
	}

}
