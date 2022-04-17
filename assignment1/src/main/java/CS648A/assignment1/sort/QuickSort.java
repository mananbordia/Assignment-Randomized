package CS648A.assignment1.sort;

import static java.util.Collections.swap;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class QuickSort<T extends Comparable<T>> implements Sort<T> {

	private int numberOfCmps;
	private long timeTaken;

	private List<T> quickSort(List<T> array, Integer l, Integer r) {

		List<T> baseCaseResult = baseCase(array, l, r);

		if (Objects.nonNull(baseCaseResult)) {
			return baseCaseResult;
		}

		Integer pivot = getPivot(l, r);
		T pe = array.get(pivot);

		Collections.swap(array, pivot, r);

		Integer i = l;

		for (int j = l; j < r; j++) {
			T e = array.get(j);
			this.numberOfCmps++;
			if (e.compareTo(pe) < 0) {
				Collections.swap(array, i, j);
				i++;
			}
		}
		Collections.swap(array, i, r);
		array = quickSort(array, l, i - 1);
		array = quickSort(array, i + 1, r);

		return array;
	}

	private List<T> baseCase(List<T> array, Integer l, Integer r) {
		if (l.compareTo(r) >= 0)
			return array;

		if (l.equals(r - 1)) {
			T f = array.get(l);
			T s = array.get(r);
			if (f.compareTo(s) > 0)
				swap(array, l, r);
			return array;
		}
		return null;
	}

	@Override
	public List<T> sort(List<T> testCase) {
		
		Integer l = 0;
		Integer r = testCase.size() - 1;
		this.numberOfCmps = 0;
		
		long startTime = System.currentTimeMillis();
		List<T> result = quickSort(testCase, l, r);
		long endTime = System.currentTimeMillis();
		
		this.timeTaken = endTime - startTime;

		return result;

	}

	@Override
	public long getSortingTime() {
		return this.timeTaken;
	}

	@Override
	public long getNumberOfComparisions() {
		return this.numberOfCmps;
	}

	protected abstract Integer getPivot(Integer l, Integer r);

}
