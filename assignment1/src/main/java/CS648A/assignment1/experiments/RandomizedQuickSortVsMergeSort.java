package CS648A.assignment1.experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import CS648A.assignment1.randoms.RandomGenerator;
import CS648A.assignment1.sort.MergeSort;
import CS648A.assignment1.sort.RandomizedQuickSort;
import CS648A.assignment1.sort.Sort;
import lombok.Data;

public class RandomizedQuickSortVsMergeSort {
	public static void main(String[] args) {
		List<Integer> lengths = Arrays.asList(100, 1000, 10000, 100000, 1000000);
		RandomizedQuickSortVsMergeSort exp = new RandomizedQuickSortVsMergeSort();
		lengths.forEach(exp::testLength);
	}

	public void testLength(int len) {
		Analyzer merge = Analyzer.create();
		Analyzer quick = Analyzer.create();
		ResultClass2 res = ResultClass2.create(len);
		res.overTaken = 0;

		List<ExperimentClass> results = Stream.iterate(len, n -> len)
				.parallel()
				.limit(500).map(this::performExperiment)
				.collect(Collectors.toList());

		
//		System.out.println("Results From Stream : " + results);
		for (ExperimentClass result : results) {
			merge.addValues(result.getMergeTime(), result.getMergeCmps());
			quick.addValues(result.getQuickTime(), result.getQuickCmps());
			if (result.getMergeTime() < result.getQuickTime()) {
				res.overTaken++;
			}
		}

		merge.experiment();
		quick.experiment();

		res.avgCmpMS = (merge.getAvgComparisions());
		res.avgCmpQS = (quick.getAvgComparisions());

		res.avgTimeMS = (merge.getAvgRunningTime());
		res.avgTimeQS = (quick.getAvgRunningTime());

		System.out.println(res);
	}

	private ExperimentClass performExperiment(int len) {

		Sort<Integer> mergeSorter = new MergeSort<Integer>();
		Sort<Integer> randomizedQuickSort = new RandomizedQuickSort<>();
		List<Integer> array = RandomGenerator.getRandomIntegerListOfLength(len);
		List<Integer> brray = new ArrayList<>();
		ExperimentClass result = new ExperimentClass();
		brray.addAll(array);

		mergeSorter.sort(array);
		randomizedQuickSort.sort(brray);

		long mTime = mergeSorter.getSortingTime();
		long mCmp = mergeSorter.getNumberOfComparisions();

		long qTime = randomizedQuickSort.getSortingTime();
		long qCmp = randomizedQuickSort.getNumberOfComparisions();

		result.setMergeTime(mTime);
		result.setMergeCmps(mCmp);
		result.setQuickCmps(qCmp);
		result.setQuickTime(qTime);

		return result;
	}
}

class ResultClass2 {
	public int n;
	public Double avgTimeQS;
	public Double avgTimeMS;
	public Double avgCmpQS;
	public Double avgCmpMS;
	public long overTaken;
	public Double n2log_en;
	public Double nlog_2n;

	private ResultClass2(int testLength) {
		n = testLength;
		n2log_en = n * 2 * Math.log(n);
		nlog_2n = n * (Math.log10(n) / Math.log10(2));
	}

	public static ResultClass2 create(int testLength) {
		return new ResultClass2(testLength);
	}

	@Override
	public String toString() {
		String res = String.format(
				"Average Running Time of Quick Sort: %f\n" + "Average Running Time of Merge Sort: %f\n"
						+ "Average Number of Comparisions during QS: %f\n" + "2nlog_(e)n: %f\n"
						+ "Average Number of Comparisions during MS: %f\n" + "nlog_(2)n: %f\n"
						+ "Number of out performs by MS: %d",
				avgTimeQS, avgTimeMS, avgCmpQS, n2log_en, avgCmpMS, nlog_2n, overTaken);
		String hr = "\n\n============================================\n\n";
		res = "Test Case For Length: " + n + "\n" + res + hr;
		return res;
	}

}

@Data
class ExperimentClass {
	private long mergeCmps;
	private long quickCmps;
	private long mergeTime;
	private long quickTime;
}