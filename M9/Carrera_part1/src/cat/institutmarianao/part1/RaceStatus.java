package cat.institutmarianao.part1;

import java.util.ArrayList;
import java.util.List;

public class RaceStatus {
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false;

	public RaceStatus() {
	}

	public void lap(Pilot pilot) {
		pilot.setLaps(pilot.getLaps() - 1);
		System.out.println("Pilot: [" + pilot.getName() + ", laps=" + pilot.getLaps() + "]");
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "RaceStatus [score=" + score + ", finish=" + finish + "]";
	}
}
