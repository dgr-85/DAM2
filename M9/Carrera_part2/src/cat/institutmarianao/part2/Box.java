package cat.institutmarianao.part2;

public class Box implements Runnable {
	private RaceStatus raceStatus;
	private Pilot pilotInBox;
	private String teamName;

	public Box(RaceStatus raceStatus) {
		this.raceStatus = raceStatus;
		pilotInBox = null;
		teamName = null;
	}

	public void setPilot(Pilot pilot) {
		pilotInBox = pilot;
	}

	public boolean isFree() {
		return pilotInBox == null;
	}

	public synchronized void setPilotOut() {
		pilotInBox = null;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String boxName() {
		return "BOX(" + getTeamName() + ")";
	}

	@Override
	public void run() {
		while (!raceStatus.isFinish()) {
			System.out.println(boxName() + " is free.");
			synchronized (this) {
				try {
					wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pilotInBox != null) {
				System.out.println(boxName() + " refuelling " + pilotInBox.currentState() + "...");
				try {
					Thread.sleep(180 + (int) (Math.random() * 20));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pilotInBox.refuel();
				synchronized (pilotInBox) {
					System.out.println(boxName() + " waiting for " + pilotInBox.currentState() + " to leave");
					pilotInBox.notify();
				}
			}
		}
	}

}
