package CS648A.assignment1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import CS648A.assignment1.randoms.RandomGenerator;
import CS648A.assignment1.sort.DeterministicQuickSort;
import CS648A.assignment1.sort.MergeSort;
import CS648A.assignment1.sort.RandomizedQuickSort;
import CS648A.assignment1.sort.Sort;

public class AppTest {

	private final int length = 100;
	private final int reps = 500;

	private List<Integer> copyList(List<Integer> list) {
		List<Integer> copy = new ArrayList<>();
		copy.addAll(list);
		return copy;
	}

	private boolean testList(Sort<Integer> sorter) {
		List<Integer> testArray = RandomGenerator.getRandomIntegerListOfLength(length);
		List<Integer> actual = copyList(testArray);
		List<Integer> expected = copyList(testArray);

		Collections.sort(expected);
		actual = sorter.sort(actual);

		return actual.equals(expected);
	}

	@RepeatedTest(value = reps)
	public void TestRandomQuickSort() {
		Sort<Integer> sorter = new RandomizedQuickSort<>();

		boolean result = testList(sorter);
		assertTrue(result, "Randomized Quick Sort Failed");

	}

	@RepeatedTest(value = reps)
	public void TestMergeSort() {
		Sort<Integer> sorter = new MergeSort<>();
		boolean result = testList(sorter);
		assertTrue(result, "Merge Sort Failed");
	}

	@RepeatedTest(value = reps)
	public void TestPlainQuickSort() {
		Sort<Integer> sorter = new DeterministicQuickSort<>();
		boolean result = testList(sorter);
		assertTrue(result, "Deterministic Quick Sort Failed");
	}

}
