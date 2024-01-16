package cat.institutmarianao.part2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class RaceStatus {
	private List<Pilot> score;
	private boolean finish = false;
	private int winnerTime;

	public RaceStatus() {
		score = new ArrayList<>();
		winnerTime = -1;
	}

	public void lap(Pilot pilot) {
		if (!finish && pilot.getLaps() <= 0) {
			finish = true;
			winnerTime = pilot.getTotalTimeMillis();
			score.add(pilot);

		} else if (finish) {
			pilot.setTotalTimeMillis(pilot.getTotalTimeMillis() - winnerTime);
			score.add(pilot);

		}
	}

	public boolean isFinish() {
		return finish;
	}

	public String parsePilotTime(int milliseconds, boolean firstPlace) {
		DecimalFormat df = new DecimalFormat("00.000");
		float seconds = (float) milliseconds / 1000;
		int minutes = 0;
		int hours = 0;
		if (firstPlace) {
			if (seconds > 60) {
				minutes = (int) seconds / 60;
				seconds %= 60;
			}
			if (minutes > 60) {
				hours = minutes / 60;
				minutes %= 60;
			}
			return String.valueOf(hours) + ":" + StringUtils.leftPad(String.valueOf(minutes), 2, "0") + ":"
					+ df.format(seconds).replace(',', '.');
		}
		return StringUtils.rightPad(String.valueOf(seconds), 5, "0");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[RACE RESULTS]\n");

		int i = 1;
		sb.append(StringUtils.rightPad((i++) + ". " + score.get(0).getName(), 15) + "|");
		sb.append(StringUtils.leftPad((parsePilotTime(score.get(0).getTotalTimeMillis(), true) + "s\n"), 15));

		score.remove(0);
		score.sort(Comparator.comparing(Pilot::getLaps).thenComparing(Pilot::getTotalTimeMillis));

		for (Pilot p : score) {
			sb.append(StringUtils.rightPad((i++) + ". " + p.getName(), 15) + "|");
			sb.append(
					StringUtils.leftPad(p.getLaps() <= 0 ? "+" + (parsePilotTime(p.getTotalTimeMillis(), false) + "s\n")
							: "+" + (p.getLaps() + " lap" + (p.getLaps() > 1 ? "s" : "") + "\n"), 15));
		}
		return sb.toString();
	}
}
