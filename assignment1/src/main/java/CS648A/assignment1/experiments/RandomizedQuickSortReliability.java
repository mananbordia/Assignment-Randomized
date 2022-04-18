package CS648A.assignment1.experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import CS648A.assignment1.randoms.RandomGenerator;
import CS648A.assignment1.sort.RandomizedQuickSort;
import lombok.Data;

public class RandomizedQuickSortReliability {
	private static final String hr = "\n\n===================================\n";

	public static void main(String[] args) {
		List<Integer> lengths = Arrays.asList(100, 1000, 10000, 100000, 1000000);
		RandomizedQuickSortReliability experiment = new RandomizedQuickSortReliability();
		lengths.forEach(experiment::perform);
	}

	private void perform(Integer length) {
		List<QuickSortAnalysis> data = Stream.iterate(length, n -> length).parallel().limit(500).map(this::getRuntimes)
				.collect(Collectors.toList());

		Double totalTime = data.stream().collect(Collectors.summingDouble(e -> e.getRunTime()));
		Double totalCmps = data.stream().collect(Collectors.summingDouble(e -> e.getComparisions()));

		Double avgTime = totalTime / 500;
		Double avgCmps = totalCmps / 500;

		List<Integer> exceedPercentages = Arrays.asList(5, 10, 20, 30, 50, 100);
		List<Long> answers = new ArrayList<>();

		for (int p : exceedPercentages) {

			final Double x = p * avgTime;
			final Double y = x / 100 + avgTime;

			long answer = data.stream().map(e -> Double.valueOf(e.getRunTime())).filter(e -> (e).compareTo(y) > 0)
					.count();
			answers.add(answer);
		}
		Double n2logen = Double.valueOf(2 * length) * Math.log(length);

		String res = "Test Case length: " + length + "\n"
				+ String.format("Average Runtime of randomized quick sort: %f\n" + "2nlog_(e)n: %f\n"
						+ "Average no. of comparisions: %f\n", avgTime, n2logen, avgCmps);

		for (int i = 0; i < answers.size(); ++i) {
			String now = String.format("No. of case where runtime exceed by %d: %d\n", exceedPercentages.get(i),
					answers.get(i));
			res = res + now;
		}
		res = res + hr;
		System.out.println(res);
	}

	private QuickSortAnalysis getRuntimes(Integer length) {
		List<Integer> list = RandomGenerator.getRandomIntegerListOfLength(length);
		RandomizedQuickSort<Integer> sorter = new RandomizedQuickSort<>();
		QuickSortAnalysis data = new QuickSortAnalysis();
		sorter.sort(list);
		data.setComparisions(sorter.getNumberOfComparisions());
		data.setRunTime(sorter.getSortingTime());
		return data;
	}
}

@Data
class QuickSortAnalysis {
	private long runTime;
	private long comparisions;
}
/*
 * x - a / a * 100 = p x - a = pa / 100 x = pa / 100 + a
 */
