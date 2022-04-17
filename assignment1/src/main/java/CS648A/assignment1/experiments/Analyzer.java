package CS648A.assignment1.experiments;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	int experiments = 0;
	private List<Long> runningTimes;
	private List<Long> cmps;

	private Double runningTimeAvg = 0.0;
	private Double comparisionsAvg = 0.0;

	private Analyzer() {
		this.runningTimes = new ArrayList<>();
		this.cmps = new ArrayList<>();
	}
	
	public static Analyzer create() {
		return new Analyzer();
	}
	
	
	public void addValues(long runningTime, long comparisions) {
		runningTimes.add(runningTime);
		cmps.add(comparisions);
		experiments++;
	}

	private Double getAverage(List<Long> list) {
		Double total = list.stream().mapToDouble(Long :: doubleValue).sum();
		Double avg = (Double) total / experiments;
		return avg;
	}

	public void experiment() {
		runningTimeAvg = getAverage(runningTimes);
		comparisionsAvg = getAverage(cmps);
	}

	public Double getAvgRunningTime() {
		return runningTimeAvg;
	}

	public Double getAvgComparisions() {
		return comparisionsAvg;
	}
	

}
